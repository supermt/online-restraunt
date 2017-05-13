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

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.uestc.msstudio.panorama.model.Comment;

/** 
 * @ClassName: CommentRepository 
 * @Description: TODO
 * @author: MT 
 */
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>{
}
