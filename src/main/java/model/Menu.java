package model;

import javax.xml.crypto.Data;
import java.util.Date;

import static util.DataStore.DB_DIR;

//
public class Menu implements SerializableObject{
    private String Id;
    private String Name;
    private String Order;
    private String Page_path;
    private Date CreateTime;
    private Date ModifyTime;

    public Menu(String id, String name, String order, String page_path, Date createTime) {
        Id = id;
        Name = name;
        Order = order;
        Page_path = page_path;
        CreateTime = createTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }

    public String getPage_path() {
        return Page_path;
    }

    public void setPage_path(String page_path) {
        Page_path = page_path;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getModifyTime() {
        return ModifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        ModifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Order='" + Order + '\'' +
                ", Page_path='" + Page_path + '\'' +
                ", CreateTime=" + CreateTime +
                ", ModifyTime=" + ModifyTime +
                '}';
    }

    @Override
    public String getIdentifier() {
        return getName(); // 假设 getName() 返回唯一标识符
    }

    @Override
    public String getDBDir() {
        return DB_DIR; // 用户数据的目录
    }
}
