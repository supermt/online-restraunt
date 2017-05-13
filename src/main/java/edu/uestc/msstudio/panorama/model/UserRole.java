/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: Role.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.model 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午4:14:17 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @ClassName: Role
 * @Description: TODO
 * @author: MT
 * @date: 2017年5月10日 下午4:14:17
 */
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;

    // @ManyToOne
    // private User owner;
    //
    // public User getOwner() {
    // return owner;
    // }
    // public void setOwner(User owner) {
    // this.owner = owner;
    // }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
