package view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.MenuDao;
import dao.RoleDao;
import model.Role;
import model.Menu;

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

public class MenuForm extends JFrame {
    public JList<String> fileList;
    private DefaultListModel<String> fileListModel;

    public MenuForm(){//创建构造方法
        setTitle("菜单管理");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());//创建主面板，设置布局

        JPanel buttonPanel = new JPanel(new FlowLayout()){{//创建按钮面板，设置布局
            String[] buttonLabels = {"添加菜单","删除菜单","修改菜单","刷新列表","查看菜单"};
            // 创建内部匿名类数组，每个类对应按钮的ActionListener
            ActionListener[] actionListeners = new ActionListener[buttonLabels.length];

            actionListeners[0] = e -> addMenu();
            actionListeners[1] = e -> delMenu();
            actionListeners[2] = e -> changeMenu();
            actionListeners[3] = e -> updateFileList();
            actionListeners[4] = e -> viewMenu();

            //遍历按钮文本列表
            for (int i =0; i< buttonLabels.length;i++){
                JButton button = new JButton(buttonLabels[i]);
                add(button);

                button.addActionListener(actionListeners[i]);//添加监听器
            }
        }};

        //创建文件列表面板
        JPanel fileListPanel = new JPanel(new BorderLayout());
        fileListModel = new DefaultListModel<>();
        fileList =new JList<>(fileListModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(fileList);
        fileListPanel.add(scrollPane,BorderLayout.CENTER);

        mainPanel.add(buttonPanel,BorderLayout.NORTH);
        mainPanel.add(fileListPanel,BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
        updateFileList();
    }

    private void addMenu(){
        //弹出对话接收要添加的菜单的信息
        String [] Menu_atrs = new String[4];
        String [] atr_messages ={"请输入ID","请输入名字","请输入排序","请输入页面链接"};

        for (int i = 0; i< Menu_atrs.length;i++){
            Menu_atrs[i] = JOptionPane.showInputDialog(this,atr_messages[i]);
        }

        if (Menu_atrs == null){
            JOptionPane.showMessageDialog(this,"输入不能为空","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Menu menu = new Menu(Menu_atrs[0], Menu_atrs[1], Menu_atrs[2], Menu_atrs[3], new Date(),new Date());

            //数据写入json
            MenuDao.add(menu);

            JOptionPane.showMessageDialog(this,"菜单添加成功","提示",JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"出错了，请检查","错误",JOptionPane.INFORMATION_MESSAGE);
        }
        updateFileList();
    }

    private void delMenu(){
        int selectedIndex = fileList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "请选择一个用户文件", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String filename =fileListModel.getElementAt(selectedIndex);


        try {
            MenuDao.del(filename);

            JOptionPane.showMessageDialog(this,"已删除角色 " + filename,"提示",JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"删除失败","错误",JOptionPane.INFORMATION_MESSAGE);
        }
        //System.out.println("删除用户被点击");

        updateFileList();
    }
    private void changeMenu(){

    }
    private void viewMenu(){

    }
    private void updateFileList(){

        try {
            Path path = Paths.get(DB_DIR, "Menu.json");
            String content = new String(Files.readAllBytes(path));
            Gson gson = new Gson();
            java.util.List<Menu> menus = gson.fromJson(content, new TypeToken<List<Menu>>() {}.getType());
            fileListModel.clear();
            for (Menu menu : menus) {
                fileListModel.addElement(menu.getName());
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
        new MenuForm();
    }
}
