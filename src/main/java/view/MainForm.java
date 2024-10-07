package view;

import dao.UserDao;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    public MainForm(){//构造方法，初始化窗口
        //设置窗口基本参数，标题，大小，关闭操作
        setTitle("主界面");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainpanel = new JPanel(){{//创建主面板
            setLayout(new BorderLayout());
        }};

        JPanel buttonPanel = new JPanel(){{//创建按钮面板
            setLayout(new FlowLayout());

            JButton usersButton = new JButton("用户管理");
            JButton rolesButton = new JButton("角色管理");
            JButton menusButton = new JButton("菜单管理");

            add(usersButton);
            add(rolesButton);
            add(menusButton);

            usersButton.addActionListener(e -> {new UserForm().setLocation(850,400);});
            rolesButton.addActionListener(e -> {new RoleForm().setLocation(850,400);});
            menusButton.addActionListener(e -> {new MenuForm().setLocation(850,400);});

        }};


        JPanel infoPanel = new JPanel(){{// 创建说明面板
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        }};


        JTextArea infoTextArea = new JTextArea("这是一个后台管理系统，用于管理用户、菜单和角色。\n\n" +
                "- 用户管理：管理系统的用户信息。在此可以绑定/解绑用户中的角色\n" +
                "- 菜单管理：管理系统的菜单信息。\n" +
                "- 角色管理：管理系统的角色信息。在此可以绑定/解绑角色中的菜单"){{// 创建说明文本
            setEditable(false);
            setLineWrap(true);
            setWrapStyleWord(true);
            setFont(new Font("Arial", Font.PLAIN, 14));
            setBackground(infoPanel.getBackground());
        }};

        JScrollPane scrollPane = new JScrollPane(infoTextArea){{
            setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        }};
        //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        infoPanel.add(scrollPane);

        mainpanel.add(buttonPanel,BorderLayout.NORTH);
        mainpanel.add(infoPanel,BorderLayout.CENTER);

        add(mainpanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainForm().setLocation(850,400);;
    }
}
