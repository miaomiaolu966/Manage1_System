package dao;

import model.Menu;
import model.Role;
import util.DataStore;

import java.util.Date;

public class RoleDao extends UserDao{
    public boolean add(Role role) {//添加用户
        DataStore Ds = new DataStore();
        Ds.add(role, Role.class);
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
