package view;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import dao.UserDao;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import static util.DataStore.DB_DIR;

public class UserForm extends JFrame {
    private JList<String> fileList;
    private DefaultListModel<String> fileListModel;

    public UserForm(){//构造函数
        setTitle("用户界面");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());//创建主面板

        JPanel buttonPanel = new JPanel(new FlowLayout()){{//创建按钮面板
            String [] buttonLabels ={"添加用户","删除用户","修改用户","刷新列表","查看用户"};
            // 创建内部匿名类数组，每个类对应按钮的ActionListener
            ActionListener[] actionListeners = new ActionListener[buttonLabels.length];

            actionListeners[0] = e -> addUser();
            actionListeners[1] = e -> delUser();
            actionListeners[2] = e -> changeUser();
            actionListeners[3] = e -> updateFileList();
            actionListeners[4] = e -> viewUser();

            for (int i = 0;i<buttonLabels.length;i++){
                JButton button = new JButton(buttonLabels[i]);

                add(button);

                button.addActionListener(actionListeners[i]);
            }
        }};

        //mainPanel.add(buttonPanel);

        //创建文件列表面板
        JPanel fileListPanel = new JPanel();
        fileListPanel.setLayout(new BorderLayout());
        //fileListPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5)); // 可选：增加边距

        fileListModel =new DefaultListModel<>();
        fileList = new JList<>(fileListModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(fileList);
        fileListPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(fileListPanel,BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
        updateFileList();

    }
    private void addUser(){//“添加用户”按钮触发的操作方法
        //弹出对话接收要添加的用户的信息
        String [] User_atrs = new String[6];
        String [] atr_messages ={"请输入ID","请输入账号:","请输入名字：","请输入性别:","请输入年龄:","请输入密码:"};

        for(int i =0;i<User_atrs.length;i++){
            User_atrs[i] = JOptionPane.showInputDialog(this,atr_messages[i]);
        }

        if (User_atrs == null){
            JOptionPane.showMessageDialog(this,"输入不能为空","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User user = new User(User_atrs[0],User_atrs[1],User_atrs[2],User_atrs[3],Integer.parseInt(User_atrs[4]),User_atrs[5],new Date(),new Date());

            //数据写入json
            UserDao.add(user);

            JOptionPane.showMessageDialog(this,"用户添加成功","提示",JOptionPane.INFORMATION_MESSAGE);

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,"年龄必须是数字","错误",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void delUser(){
        int selectedIndex = fileList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "请选择一个用户文件", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String filename =fileListModel.getElementAt(selectedIndex);


        UserDao.del(filename);
        //System.out.println("删除用户被点击");

        updateFileList();
    }

    private void viewUser(){
        int selectedIndex = fileList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "请选择一个用户文件", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String filename =fileListModel.getElementAt(selectedIndex);

        UserDao.view(filename);

        //System.out.println("查看用户被点击");
    }
    private void changeUser(){
        int selectedIndex = fileList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "请选择一个用户文件", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String filename =fileListModel.getElementAt(selectedIndex);

        User user =UserDao.change(filename);

        new ChangeForm(user);
        
        System.out.println("修改用户被点击");
    }

    private void updateFileList(){

        try {
            Path path = Paths.get(DB_DIR, "User.json");
            String content = new String(Files.readAllBytes(path));
            Gson gson = new Gson();
            java.util.List<User> users = gson.fromJson(content, new TypeToken<List<User>>() {}.getType());
            fileListModel.clear();
            for (User user : users) {
                fileListModel.addElement(user.getAccount());
            }
        } catch (IOException e) {
            // 处理文件读取异常
            e.printStackTrace();
            // 可以在这里记录日志或显示错误信息
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            // 处理其他异常
            e.printStackTrace();
            // 可以在这里记录日志或显示错误信息
            System.err.println("Error updating file list: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        new UserForm();
    }
}