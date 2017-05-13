/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: CommentRepository.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.repo 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午3:26:11 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.uestc.msstudio.panorama.model.Comment;
import edu.uestc.msstudio.panorama.model.User;

/**
 * @ClassName: CommentRepository
 * @Description: TODO
 * @author: MT
 */
public interface CommentRepository
        extends PagingAndSortingRepository<Comment, Long> {
    @Query("select C from Comment C where C.author.userid = ?1")
    Page<Comment> findByAuthor(User target, Pageable pageable);
}
