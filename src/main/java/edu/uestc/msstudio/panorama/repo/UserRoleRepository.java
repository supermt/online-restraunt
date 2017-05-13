/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: UserRoleRepository.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.repo 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月13日 上午9:37:23 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.uestc.msstudio.panorama.model.UserRole;

/**
 * @ClassName: UserRoleRepository
 * @Description: TODO
 * @author: MT
 */
@Component
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    public UserRole findByRole(String role);
}
