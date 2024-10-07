package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

            actionListeners[0] = e -> System.out.println("添加菜单被点击");
            actionListeners[1] = e -> System.out.println("删除菜单被点击");
            actionListeners[2] = e -> System.out.println("修改菜单被点击");
            actionListeners[3] = e -> System.out.println("刷新菜单被点击");
            actionListeners[4] = e -> System.out.println("查看菜单被点击");

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
    }

    public static void main(String[] args) {
        new MenuForm();
    }
}
