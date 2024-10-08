package view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static util.DataStore.DB_DIR;
import static util.MD5Util.encrypt;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm(){
        setTitle("登录");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel =new JPanel(){{
            setLayout(new GridLayout(3,2));
            add(new JLabel("用户名"));
            usernameField = new JTextField();
            add(usernameField);

            add(new JLabel("密码"));
            passwordField = new JPasswordField();
            add(passwordField);
        }};

        JButton loginButton = new JButton("登录"){{
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    char[] passwordChars = passwordField.getPassword();
                    String password = new String(passwordChars);

                    try{
                        if (validateLogin(username,password)){
                            JOptionPane.showMessageDialog(LoginForm.this, "登录成功！");
                            // 关闭登录界面
                            dispose();
                            // 显示主界面
                            new MainForm().setLocation(850,400);
                        }else {
                            JOptionPane.showMessageDialog(LoginForm.this, "用户名或密码错误！");

                        }
                    }catch (FileNotFoundException ex){
                        throw new RuntimeException(ex);
                    }
                }
            });
        }};

        panel.add(loginButton);

        add(panel);

        setVisible(true);


    }
    private boolean validateLogin(String username, String password) throws FileNotFoundException {
        // 从JSON文件中读取用户数据并验证
        //从路径user.json中读取用户数据，user.json是一个列表数据，里面有多个用户数据
        FileReader reader = new FileReader(DB_DIR + "/User.json");
        Gson gson = new Gson();
        List<User> userList = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
        //System.out.println(userList);
        for (User user : userList) {
            //System.out.println(user.toString());
            if (user != null && user.getAccount().equals(username) && user.getPassWord().equals(password)) {
                return true;
            }
        }
        return false;
    }//密码MD5 检验

    public static void main(String[] args) {
        new LoginForm().setLocation(850,400);
    }
}

