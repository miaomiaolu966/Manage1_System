package view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.RoleDao;
import dao.UserDao;
import model.Role;
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

public class RoleForm extends JFrame {
    public JList<String> fileList;
    private DefaultListModel<String> fileListModel;

    public RoleForm(){//构造方法，初始化窗口的标题，大小，关闭操作
        setTitle("");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout()){{//创建按钮面板
            String [] buttonLabels ={"添加角色","删除角色","修改角色","刷新列表","查看角色"};

            // 创建内部匿名类数组，每个类对应按钮的ActionListener
            ActionListener[] actionListeners = new ActionListener[buttonLabels.length];

            actionListeners[0] = e -> addRole();
            actionListeners[1] = e -> delRole();
            actionListeners[2] = e -> changeRole();
            actionListeners[3] = e -> updateFileList();
            actionListeners[4] = e -> viewRole();

            //遍历按钮文本列表
            for (int i =0; i< buttonLabels.length;i++){
                JButton button = new JButton(buttonLabels[i]);
                add(button);

                button.addActionListener(actionListeners[i]);//添加监听器
            }
        }};


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
    private void addRole(){
        //弹出对话接收要添加的角色的信息
        String [] Role_atrs = new String[3];
        String [] atr_messages ={"请输入ID","请输入名字：","请输入排序:"};

        for(int i = 0; i< Role_atrs.length; i++){
            Role_atrs[i] = JOptionPane.showInputDialog(this,atr_messages[i]);
        }

        if (Role_atrs == null){
            JOptionPane.showMessageDialog(this,"输入不能为空","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Role role = new Role(Role_atrs[0], Role_atrs[1], Role_atrs[2], new Date(),new Date());

            //数据写入json
            RoleDao.add(role);

            JOptionPane.showMessageDialog(this,"角色添加成功","提示",JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"出错了，请检查","错误",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void delRole(){
        int selectedIndex = fileList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "请选择一个用户文件", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String filename =fileListModel.getElementAt(selectedIndex);


        try {
            RoleDao.del(filename);

            JOptionPane.showMessageDialog(this,"已删除角色 " + filename,"提示",JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"删除失败","错误",JOptionPane.INFORMATION_MESSAGE);
        }
        //System.out.println("删除用户被点击");

        updateFileList();
    }
    private void changeRole(){
        int selectedIndex = fileList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "请选择一个用户文件", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String filename =fileListModel.getElementAt(selectedIndex);

        Role role =RoleDao.change(filename);

        new ChangeForm(role);
    }
    private void viewRole(){
        int selectedIndex = fileList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "请选择一个用户文件", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String filename =fileListModel.getElementAt(selectedIndex);

        RoleDao.view(filename);
    }
    private void updateFileList(){

        try {
            Path path = Paths.get(DB_DIR, "Role.json");
            String content = new String(Files.readAllBytes(path));
            Gson gson = new Gson();
            java.util.List<Role> roles = gson.fromJson(content, new TypeToken<List<Role>>() {}.getType());
            fileListModel.clear();
            for (Role role : roles) {
                fileListModel.addElement(role.getName());
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
        new RoleForm();
    }
}
