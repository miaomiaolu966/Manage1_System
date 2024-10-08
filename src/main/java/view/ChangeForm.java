package view;

import javax.swing.*;
import java.awt.*;

import static util.DataStore.saveObjectToFile;

public class ChangeForm extends JFrame {
    //private T object;

    public ChangeForm(){
        //this.object = obj;
        setTitle("修改数据");
        setSize(600, 300);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel StringPanel = new JPanel();
        StringPanel.setLayout(new BorderLayout());

        // 创建文本区域并允许编辑
        JTextArea textArea = new JTextArea();
        //textArea.setText(obj.toString());
        textArea.setEditable(false); // 允许编辑
        textArea.setBackground(Color.WHITE); // 设置背景颜色为白色

        // 使用滚动面板包裹文本区域，以便处理过长的文本
        JScrollPane scrollPane = new JScrollPane(textArea);
        StringPanel.add(scrollPane, BorderLayout.CENTER);


        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

//        // 动态生成按钮
//        for (Method method : obj.getClass().getMethods()) {
//            if (method.getName().startsWith("set")) {
//                if (method.getName().equals("setCreateTime")) {
//                    continue;  // 跳过此迭代
//                }
//                JButton button = new JButton("修改" + method.getName().substring(3));
//                button.addActionListener(e -> modifyProperty(method));
//                buttonPanel.add(button);
//            }
//        }

        // 将按钮面板和文件列表面板添加到主面板
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(StringPanel, BorderLayout.CENTER);
//        System.out.println(user.toString());
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {

        new ChangeForm();
    }
}
