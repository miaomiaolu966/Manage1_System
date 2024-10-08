package dao;//dao包

import com.google.gson.*;
import model.User;
import util.DataStore;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import static util.DataStore.*;


public class UserDao extends Component {

    static String className = User.class.getSimpleName();
    static List<Object> classList=  new DataStore().GetMap().get(className);
    public static List<User> userList = convertList(classList);
    private static List<User> convertList(List<Object> objectList) {
        List<User> userList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for (Object obj : objectList) {
            String jsonString = gson.toJson(obj);
            User user = gson.fromJson(jsonString, User.class);
            userList.add(user);
        }

        return userList;
    }
    public static boolean add(User user) {//添加用户

        userList.add(user);

        saveObjectToFile(userList,className,DB_DIR);

        return true;
    }
    //public boolean select();
    public static boolean del(String del_name){//删除用户

        Iterator<User> iterator = userList.iterator();//迭代器
        while (iterator.hasNext()) {

            User user = iterator.next();

            if (user.getAccount().equals(del_name)) {
                iterator.remove();  // 使用迭代器的 remove 方法

                // 可选：打印或执行其他操作
                System.out.println("已删除用户 " + del_name);
            }
        }
        saveObjectToFile(userList,className,DB_DIR);
        return true;
    }
    public static User change(String name){//更改用户
        Iterator<User> iterator = userList.iterator();//迭代器
        while (iterator.hasNext()) {

            User user = iterator.next();

            if (user.getAccount().equals(name)) {
                return user;  // 使用迭代器的 remove 方法

            }
        }
        return null;
    }
    public static boolean view(String name){//查看用户

        //Ds.view(User.class,name);
        Iterator<User> iterator = userList.iterator();//迭代器
        while (iterator.hasNext()) {

            User user = iterator.next();

            if (user.getAccount().equals(name)) {
                displayUser(user);
                // 可选：打印或执行其他操作
            }
        }
        return true;
    }
    //public boolean up();
    public static void displayUser(User user) {
        // 创建一个匿名对象作为父组件（如果需要的话）
        JOptionPane.showMessageDialog(new JFrame(), user.toString(), "提示", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        User user = new User(
                "7516584",
                "OPPo",
                "欧派",
                "男",
                10,
                "123456",
                new Date(),
                new Date()
        );
        UserDao userDao_test =new UserDao(){{
            add(user);
        }};
    }
}
