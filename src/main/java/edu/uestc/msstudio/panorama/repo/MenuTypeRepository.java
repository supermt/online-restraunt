/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: MenuTypeRepository.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.repo 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午5:43:03 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.repo;

import org.springframework.data.repository.CrudRepository;

import edu.uestc.msstudio.panorama.model.MenuType;

/** 
 * @ClassName: MenuTypeRepository 
 * @Description: TODO
 * @author: MT 
 */
public interface MenuTypeRepository extends CrudRepository<MenuType, Long>{
}
