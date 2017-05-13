/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: User.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.model 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午3:58:55 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * @ClassName: User
 * @Description: This is the User entity which describe the operator of this system
 * @author: MT
 * @date: 2017年5月10日 下午3:58:55
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;
    private String username;
    private String gender;
    private String hometown;
    private String residence;
    private String email;
    private String password;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "USER_USER_ROLE", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "USER_ROLE_ID") })
    private List<UserRole> roles;
    private Date lastPasswordResetDate;

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHometown() {
        return hometown;
    }
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getResidence() {
        return residence;
    }
    public void setResidence(String residence) {
        this.residence = residence;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<UserRole> getRoles() {
        return roles;
    }
    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
