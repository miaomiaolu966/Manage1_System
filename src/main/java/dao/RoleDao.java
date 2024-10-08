package dao;

import model.Role;
import util.DataStore;

import java.util.Date;

public class RoleDao extends UserDao{
    public boolean add(Role role) {//添加用户
        DataStore Ds = new DataStore();
        Ds.add(role, Role.class);
        return true;
    }
    //public boolean select();
    public boolean del(Role role, String del_name){//删除用户
        DataStore Ds = new DataStore();
        //Ds.delete(Role.class);
        return true;
    }
    public boolean change(Role role){//更改用户
        DataStore Ds = new DataStore();

        return true;
    }
    public boolean view(Role role){//更改用户
        DataStore Ds = new DataStore();

        return true;
    }
    public static void main(String[] args) {
        Role role =new Role(
                "31",
                "1371",
                "脆脆鲨",
                new Date());
        DataStore Ds = new DataStore(){{
            add(role,Role.class);
        }};
    }
}
