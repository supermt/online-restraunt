/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: UserRepository.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.repo 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午4:34:24 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.repo;

import org.springframework.data.repository.CrudRepository;

import edu.uestc.msstudio.panorama.model.User;

/**
 * @ClassName: UserRepository
 * @Description: TODO
 * @author: MT
 */
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
