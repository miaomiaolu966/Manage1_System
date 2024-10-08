package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Role;
import util.DataStore;

import javax.swing.*;

import static util.DataStore.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RoleDao{
    static DataStore Ds = new DataStore();

    static String className = Role.class.getSimpleName();

    static List<Object> classList= Ds.GetMap().get(className);

    public static List<Role> roleList = convertList(classList);
    private static List<Role> convertList(List<Object> objectList) {
        List<Role> roleList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for (Object obj : objectList) {
            String jsonString = gson.toJson(obj);
            Role role = gson.fromJson(jsonString, Role.class);
            roleList.add(role);
        }

        return roleList;
    }
    public static boolean add(Role role){//添加角色

        roleList.add(role);

        saveObjectToFile(roleList,className,DB_DIR);

        return true;
    }
    public static boolean del(String del_name){//删除角色

        Iterator<Role> iterator = roleList.iterator();//迭代器
        while (iterator.hasNext()) {

            Role role = iterator.next();

            if (role.getName().equals(del_name)) {
                iterator.remove();  // 使用迭代器的 remove 方法

                // 可选：打印或执行其他操作
                //System.out.println("已删除角色 " + del_name);
            }
        }
        saveObjectToFile(roleList,className,DB_DIR);
        return true;
    }
    public static Role change(String name){//更改角色
        Iterator<Role> iterator = roleList.iterator();//迭代器
        while (iterator.hasNext()) {

            Role role = iterator.next();

            if (role.getName().equals(name)) {
                return role;  // 使用迭代器的 remove 方法

            }
        }
        return null;
    }
    public static boolean view(String name){//查看角色
        //DataStore Ds = new DataStore();

        Iterator<Role> iterator = roleList.iterator();//迭代器
        while (iterator.hasNext()) {

            Role role = iterator.next();

            if (role.getName().equals(name)) {
                displayUser(role);
                // 可选：打印或执行其他操作
            }
        }
        return true;
    }
    //public boolean up();
    public static void displayUser(Role role) {
        // 创建一个匿名对象作为父组件（如果需要的话）
        JOptionPane.showMessageDialog(new JFrame(), role.toString(), "提示", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        Role role1 =new Role(
                "1371", "脆脆鲨", "311",
                new Date(),
                new Date());
        System.out.println(role1);
        System.out.println("Hello world!");

        RoleDao roleDaoTest = new RoleDao(){{
            add(role1);
        }};


    }
}
