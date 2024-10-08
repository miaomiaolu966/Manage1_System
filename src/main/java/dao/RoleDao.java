package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Role;
import model.User;
import util.DataStore;
import static util.DataStore.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleDao{
    DataStore Ds = new DataStore();

    String className = Role.class.getSimpleName();

    List<Object> classList= Ds.GetMap().get(className);

    public  List<Role> roleList = convertList(classList);
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
    public boolean add(Role role){

        roleList.add(role);

        saveObjectToFile(roleList,className,DB_DIR);

        return true;
    }
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
        //DataStore Ds = new DataStore();

        return true;
    }
    public static void main(String[] args) {
        Role role1 =new Role(
                "311",
                "1371",
                "脆脆鲨",
                new Date());
        System.out.println(role1);
        System.out.println("Hello world!");

        RoleDao roleDaoTest = new RoleDao(){{
            add(role1);
        }};


    }
}
