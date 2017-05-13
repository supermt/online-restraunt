/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: BasicTestObjects.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午3:07:13 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.uestc.msstudio.panorama.model.User;
import edu.uestc.msstudio.panorama.model.UserRole;
import edu.uestc.msstudio.panorama.repo.UserRoleRepository;
import edu.uestc.msstudio.panorama.service.AuthService;

/**
 * @ClassName: BasicTestObjects
 * @Description: TODO
 * @author: MT
 */
@Component
public class BasicTestObjects implements CommandLineRunner {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRoleRepository roleDao;

    @Override
    public void run(String... arg0) throws Exception {
        User userToAdd = new User();
        UserRole userRole = new UserRole();
        UserRole adminRole = new UserRole();
        userRole.setId(1l);
        userRole.setRole("ROLE_USER");
        adminRole.setId(2l);
        adminRole.setRole("ROLE_ADMIN");
        roleDao.save(userRole);
        roleDao.save(adminRole);
        userToAdd.setUsername("supermt");
        userToAdd.setPassword("sasomi");
        List<UserRole> roles = new ArrayList<>();
        roles.add(userRole);
        roles.add(adminRole);
        userToAdd.setRoles(roles);
        authService.register(userToAdd);
    }
}
