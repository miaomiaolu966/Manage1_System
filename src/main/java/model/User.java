package model;

import javax.xml.crypto.Data;
import java.util.Date;

import static util.DataStore.DB_DIR;

public class User implements SerializableObject{
    private String Id;
    private String Account;
    private String Name;
    private String Sex;
    private int Age;
    private String PassWord;
    private Date CreateTime;
    private Date ModifyTime;
    //构造方法，初始化时就赋值
    public User(String id, String account, String name, String sex, int age, String passWord, Date createTime,Date modifyTime) {
        Id = id;
        Account = account;
        Name = name;
        Sex = sex;
        Age = age;
        PassWord = passWord;
        CreateTime = createTime;
        ModifyTime = modifyTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
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
        return "User{" +
                "Id='" + Id + '\'' +
                ", Account='" + Account + '\'' +
                ", Name='" + Name + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Age=" + Age +
                ", PassWord='" + PassWord + '\'' +
                ", CreateTime=" + CreateTime +
                ", ModifyTime=" + ModifyTime +
                '}';
    }
    @Override
    public String getIdentifier() {
        return getName(); // 假设 getName() 返回唯一标识符
    }
}
