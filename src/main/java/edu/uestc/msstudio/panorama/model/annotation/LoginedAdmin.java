/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: LoginedAdmin.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.model.annotation 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午2:03:38 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @ClassName: LoginedAdmin
 * @Description: TODO
 * @author: MT
 */
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LoginedAdmin {
}
