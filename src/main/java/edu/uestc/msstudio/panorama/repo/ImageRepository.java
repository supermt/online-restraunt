/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: ImageRepository.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.repo 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月13日 下午4:42:23 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import edu.uestc.msstudio.panorama.model.ImageInfo;

/** 
 * @ClassName: ImageRepository 
 * @Description: TODO
 * @author: MT 
 */
@Component
public interface ImageRepository extends CrudRepository<ImageInfo, Long>{
    public ImageInfo findByImagename(String imagename);
}
