package model;

import java.util.Date;

import static util.DataStore.DB_DIR;

public class Role implements SerializableObject{
    private String Id;
    private String Name;
    private String Order;
    private Date CreateTime;
    private Date ModifyTime;

    public Role(String id,String name,String order,   Date createTime,Date modifyTime) {
        Order = order;
        Id = id;
        Name = name;
        CreateTime = createTime;
        ModifyTime = modifyTime;
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
        return "Role{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Order='" + Order + '\'' +
                ", CreateTime=" + CreateTime +
                ", ModifyTime=" + ModifyTime +
                '}';
    }
    @Override
    public String getIdentifier() {
        return getName(); // 假设 getName() 返回唯一标识符
    }

}
