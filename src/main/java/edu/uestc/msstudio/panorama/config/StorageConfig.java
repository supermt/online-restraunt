/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: StorageConfig.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.config 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月13日 下午5:12:12 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.uestc.msstudio.panorama.config.property.StorageProperties;

@Configuration
public class StorageConfig {
    @Bean
    public StorageProperties setProperties() {
        StorageProperties config = new StorageProperties();
        return config;
    }
}
