/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: Image.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.model 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月13日 下午3:09:09 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** 
 * @ClassName: Image 
 * @Description: TODO
 * @author: MT 
 */
@Entity
public class ImageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imagename;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImagename() {
        return imagename;
    }
    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
    
}
