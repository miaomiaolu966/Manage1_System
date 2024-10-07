package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;

import dao.UserDao;
import model.User;

public class UserForm extends JFrame {
    public UserForm(){//构造方法
        setTitle("用户界面");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());//创建主面板

        JPanel buttonPanel = new JPanel(new FlowLayout()){{//创建按钮面板

            String [] buttonLabels ={"添加用户","删除用户","修改用户","刷新列表","查看菜单"};

            // 创建内部匿名类数组，每个类对应按钮的ActionListener
            ActionListener[] actionListeners = new ActionListener[buttonLabels.length];

            actionListeners[0] = e -> addUser();
            actionListeners[1] = e -> delUser();
            actionListeners[2] = e -> changeUser();
            actionListeners[3] = e -> System.out.println("刷新列表被点击");
            actionListeners[4] = e -> viewUser();

            for (int i = 0;i<buttonLabels.length;i++){
                JButton button = new JButton(buttonLabels[i]);

                add(button);

                button.addActionListener(actionListeners[i]);
            }
        }};

        mainPanel.add(buttonPanel);

        add(mainPanel);
        setVisible(true);

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
            User user = new User(User_atrs[0],User_atrs[1],User_atrs[2],User_atrs[3],Integer.parseInt(User_atrs[4]),User_atrs[5],new Date());

            //s数据写入json
            UserDao.add(user);

            JOptionPane.showMessageDialog(this,"用户添加成功","提示",JOptionPane.INFORMATION_MESSAGE);

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this,"年龄必须是数字","错误",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void delUser(){
        System.out.println("删除用户被点击");
    }

    private void viewUser(){
        System.out.println("查看用户被点击");
    }
    private void changeUser(){
        System.out.println("修改用户被点击");
    }
    public static void main(String[] args) {
        new UserForm();
    }
}
