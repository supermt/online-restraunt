/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: Comment.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.model 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午2:35:15 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @ClassName: Comment
 * @Description: TODO
 * @author: MT
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private User author;
    private Date postDate;
    private String content;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public Date getPostDate() {
        return postDate;
    }
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
