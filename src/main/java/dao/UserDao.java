package dao;

import model.User;
import util.DataStore;

import java.util.Date;

public class UserDao {
    public static boolean add(User user) {//添加用户
        DataStore Ds = new DataStore();
        Ds.add(user, User.class);
        return true;
    }
    //public boolean select();
    public boolean del(User user, String del_name){//删除用户
        DataStore Ds = new DataStore();
        Ds.delete(user,User.class,del_name);
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
