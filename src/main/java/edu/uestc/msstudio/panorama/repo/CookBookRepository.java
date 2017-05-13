/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: CookBookRepository.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.repo 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午3:27:38 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.uestc.msstudio.panorama.model.CookBook;

/**
 * @ClassName: CookBookRepository
 * @Description: TODO
 * @author: MT
 */
public interface CookBookRepository
        extends PagingAndSortingRepository<CookBook, Long> {
    @Override
    @Query("select CB from CookBook CB where CB.status = 'PUBLISHED'")
    Page<CookBook> findAll(Pageable pageable);
    @Query("select CB from CookBook CB where CB.author.userid = ?1")
    Page<CookBook> findByAuthor(Long authorid, Pageable pageable);
}
