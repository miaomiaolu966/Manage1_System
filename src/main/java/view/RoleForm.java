package view;

import javax.swing.*;
import java.awt.*;

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

            for (int i=0;i<buttonLabels.length;i++){
                add(new JButton(buttonLabels[i]));
            }
        }};


        mainPanel.add(buttonPanel);

        add(mainPanel);
        setVisible(true);


    }

    public static void main(String[] args) {
        new RoleForm();
    }
}
