/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: CookStep.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.model 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午2:50:43 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @ClassName: CookStep
 * @Description: TODO
 * @author: MT
 */
@Entity
public class CookingStep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long index;
    private String description;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIndex() {
        return index;
    }
    public void setIndex(Long index) {
        this.index = index;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
