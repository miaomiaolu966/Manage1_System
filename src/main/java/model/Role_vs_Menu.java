package model;

public class Role_vs_Menu {
    private String Id;
    private String Role_Id;
    private String Menu_Id;

    public Role_vs_Menu(String id, String role_Id, String menu_Id) {
        Id = id;
        Role_Id = role_Id;
        Menu_Id = menu_Id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRole_Id() {
        return Role_Id;
    }

    public void setRole_Id(String role_Id) {
        Role_Id = role_Id;
    }

    public String getMenu_Id() {
        return Menu_Id;
    }

    public void setMenu_Id(String menu_Id) {
        Menu_Id = menu_Id;
    }
}
