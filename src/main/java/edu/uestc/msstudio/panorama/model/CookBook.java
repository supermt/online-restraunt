/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: CookBook.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.model 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午2:35:44 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import edu.uestc.msstudio.panorama.model.enumeration.CookBookStatus;

/**
 * @ClassName: CookBook
 * @Description: TODO
 * @author: MT
 */
@Entity
public class CookBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "COOK_BOOK_IMAGE", joinColumns = {
            @JoinColumn(name = "COOK_BOOK_ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "COMMENT_ID") })
    private List<ImageInfo> images;
    private CookBookStatus status;
    @OneToMany(fetch=FetchType.EAGER)
    private Set<CookingStep> steps;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<ImageInfo> getImages() {
        return images;
    }
    public void setImages(List<ImageInfo> images) {
        this.images = images;
    }
    public CookBookStatus getStatus() {
        return status;
    }
    public void setStatus(CookBookStatus status) {
        this.status = status;
    }
    public Set<CookingStep> getSteps() {
        return steps;
    }
    public void setSteps(Set<CookingStep> steps) {
        this.steps = steps;
    }
}