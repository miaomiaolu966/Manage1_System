package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import util.DataStore;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static util.DataStore.DB_DIR;
import static util.DataStore.saveObjectToFile;

public class UserDao {
    public static boolean add(User user) {//添加用户
        DataStore Ds = new DataStore();
        Ds.add(user, User.class);
        return true;
    }
    //public boolean select();
    public static boolean del(String del_name){//删除用户
        DataStore Ds = new DataStore();

        List<Object> classlist =Ds.delete(User.class,del_name);
        int length = classlist.size();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Iterator<Object> iterator = classlist.iterator();//迭代器
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            String jsonString = gson.toJson(obj); // 将对象转换为JSON字符串
            User user = gson.fromJson(jsonString, User.class);
            if (user.getAccount().equals(del_name)) {
                iterator.remove();  // 使用迭代器的 remove 方法

                // 可选：打印或执行其他操作
                System.out.println("已删除用户 " + del_name);
            }
        }
        saveObjectToFile(classlist,User.class.getSimpleName(),DB_DIR);
        //System.out.println(del_name);
        return true;
    }
    public boolean change(User user){//更改用户
        DataStore Ds = new DataStore();

        return true;
    }
    public boolean view(User user){//更改用户
        DataStore Ds = new DataStore();

        return true;
    }
    //public boolean up();
    public static void main(String[] args) {
        User user = new User(
                "7516584",
                "OPPo",
                "欧派",
                "男",
                10,
                "123456",
                new Date()
        );
        UserDao userDao_test =new UserDao(){{
            add(user);
        }};
    }
}
