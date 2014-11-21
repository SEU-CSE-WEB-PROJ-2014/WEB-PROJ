package com.easygo.user.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user"
    ,catalog="easygo"
)

public class User  implements java.io.Serializable {


    // Fields    

     private String userId;
     private String loginName;
     private String nickName;
     private Short sex;
     private String password;
     private Short state;


    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(String loginName, String nickName, Short sex, String password, Short state) {
        this.loginName = loginName;
        this.nickName = nickName;
        this.sex = sex;
        this.password = password;
        this.state = state;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")@Id @GeneratedValue(generator="generator")
    
    @Column(name="user_id", unique=true, nullable=false, length=50)

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    @Column(name="login_name", length=50)

    public String getLoginName() {
        return this.loginName;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
    @Column(name="nick_name", length=50)

    public String getNickName() {
        return this.nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    @Column(name="sex")

    public Short getSex() {
        return this.sex;
    }
    
    public void setSex(Short sex) {
        this.sex = sex;
    }
    
    @Column(name="password", length=128)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="state")

    public Short getState() {
        return this.state;
    }
    
    public void setState(Short state) {
        this.state = state;
    }
   








}