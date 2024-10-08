package dao;

import model.Menu;
import util.DataStore;

import java.util.Date;

public class MenuDao {
    public boolean add(Menu menu) {//添加用户
        DataStore Ds = new DataStore();
        Ds.add(menu, Menu.class);
        return true;
    }

    public boolean del(Menu menu, String del_name){//删除用户
        DataStore Ds = new DataStore();
        //Ds.delete(Menu.class);
        return true;
    }
    public boolean change(Menu menu){//更改用户
        DataStore Ds = new DataStore();

        return true;
    }
    public boolean view(Menu menu){//更改用户
        DataStore Ds = new DataStore();

        return true;
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
