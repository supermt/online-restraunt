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
import javax.persistence.OneToOne;

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
    @OneToOne(fetch = FetchType.EAGER)
    private User author;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "COOK_BOOK_IMAGE", joinColumns = {
            @JoinColumn(name = "COOK_BOOK_ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "COMMENT_ID") })
    private List<ImageInfo> images;
    private CookBookStatus status;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<CookStep> steps;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Comment> comments;
    private String story;
    private String material;
    private String tips;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "COOK_BOOK_TYPE", joinColumns = {
            @JoinColumn(name = "COOK_BOOK_ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "CATEGORY_ID") })
    private List<MenuType> category;

    public Set<Comment> getComments() {
        return comments;
    }
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
    public List<MenuType> getCategory() {
        return category;
    }
    public String getStory() {
        return story;
    }
    public void setStory(String story) {
        this.story = story;
    }
    public void setCategory(List<MenuType> category) {
        this.category = category;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getTips() {
        return tips;
    }
    public void setTips(String tips) {
        this.tips = tips;
    }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
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
    public Set<CookStep> getSteps() {
        return steps;
    }
    public void setSteps(Set<CookStep> steps) {
        this.steps = steps;
    }
}
