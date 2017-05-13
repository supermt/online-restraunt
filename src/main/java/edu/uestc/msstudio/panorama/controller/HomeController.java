/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: HomeController.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.controller 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午4:47:38 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.uestc.msstudio.panorama.common.utils.UserTokenUtils;
import edu.uestc.msstudio.panorama.model.Comment;
import edu.uestc.msstudio.panorama.model.CookBook;
import edu.uestc.msstudio.panorama.model.User;
import edu.uestc.msstudio.panorama.model.annotation.LoginedUser;
import edu.uestc.msstudio.panorama.repo.CommentRepository;
import edu.uestc.msstudio.panorama.repo.CookBookRepository;
import edu.uestc.msstudio.panorama.repo.UserRepository;

/**
 * @ClassName: HomeController
 * @Description: TODO
 * @author: MT
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    UserRepository userDao;
    @Autowired
    CookBookRepository menuDao;
    @Autowired
    CommentRepository commentDao;

    @LoginedUser
    @GetMapping("/book")
    public Page<CookBook> getCookBooks(
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "1") int num,
            @RequestParam(defaultValue = "true") boolean sort) {
        User target = UserTokenUtils.getUserFromToken(userDao);
        Pageable pageable;
        if (sort)
            pageable = new PageRequest(num - 1, size, Sort.Direction.ASC, "id");
        else
            pageable = new PageRequest(num - 1, size, Sort.Direction.DESC,
                    "id");
        return menuDao.findByAuthor(target.getUserid(), pageable);
    }
    @LoginedUser
    @GetMapping("/comments")
    public Page<Comment> getComments(
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "1") int num,
            @RequestParam(defaultValue = "true") boolean sort) {
        User target = UserTokenUtils.getUserFromToken(userDao);
        Pageable pageable;
        if (sort)
            pageable = new PageRequest(num - 1, size, Sort.Direction.ASC, "id");
        else
            pageable = new PageRequest(num - 1, size, Sort.Direction.DESC,
                    "id");
        return commentDao.findByAuthor(target, pageable);
    }
}
