/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: MenuType.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.model 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午4:15:09 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** 
 * @ClassName: MenuType 
 * @Description: TODO
 * @author: MT 
 */
@Entity
public class MenuType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
