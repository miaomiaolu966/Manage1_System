package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Menu;
import model.Role;
import model.User;
import util.DataStore;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static util.DataStore.DB_DIR;
import static util.DataStore.saveObjectToFile;

public class MenuDao {
    static String className = User.class.getSimpleName();
    static List<Object> classList=  new DataStore().GetMap().get(className);
    public static List<Menu> menuList = convertList(classList);
    private static List<Menu> convertList(List<Object> objectList) {
        List<Menu> menuList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for (Object obj : objectList) {
            String jsonString = gson.toJson(obj);
            Menu menu = gson.fromJson(jsonString, Menu.class);
            menuList.add(menu);
        }

        return menuList;
    }
    public static boolean add(Menu menu) {//添加用户
        menuList.add(menu);

        saveObjectToFile(menuList,className,DB_DIR);

        return true;
    }

    public static boolean del(String del_name){//删除用户
        Iterator<Menu> iterator = menuList.iterator();//迭代器
        while (iterator.hasNext()) {

            Menu menu = iterator.next();

            if (menu.getName().equals(del_name)) {
                iterator.remove();  // 使用迭代器的 remove 方法

                // 可选：打印或执行其他操作
                //System.out.println("已删除角色 " + del_name);
            }
        }
        saveObjectToFile(menuList,className,DB_DIR);
        return true;
    }
    public boolean change(Menu menu){//更改用户
        DataStore Ds = new DataStore();

        return true;
    }
    public static boolean view(String name){//更改用户
        Iterator<Menu> iterator = menuList.iterator();//迭代器
        while (iterator.hasNext()) {

            Menu menu = iterator.next();

            if (menu.getName().equals(name)) {
                displayUser(menu);
                // 可选：打印或执行其他操作
            }
        }
        return true;
    }
    public static void displayUser(Menu menu) {
        // 创建一个匿名对象作为父组件（如果需要的话）
        JOptionPane.showMessageDialog(new JFrame(), menu.toString(), "提示", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
//        Menu menu = new Menu(
//                "45647d",
//                "主页面",
//                "4564",
//                "www.baidu.com",
//                new Date()
//        );
//        DataStore Ds =new DataStore(){{
//            add(menu,Menu.class);
//        }};
        System.out.println("1");
    }
}
