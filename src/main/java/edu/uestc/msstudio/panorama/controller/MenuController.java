/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: MenuController.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.controller 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午3:30:53 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.uestc.msstudio.panorama.common.utils.UserTokenUtils;
import edu.uestc.msstudio.panorama.model.Comment;
import edu.uestc.msstudio.panorama.model.CookBook;
import edu.uestc.msstudio.panorama.model.CookStep;
import edu.uestc.msstudio.panorama.model.ImageInfo;
import edu.uestc.msstudio.panorama.model.MenuType;
import edu.uestc.msstudio.panorama.model.annotation.LoginedUser;
import edu.uestc.msstudio.panorama.model.enumeration.CookBookStatus;
import edu.uestc.msstudio.panorama.repo.CookBookRepository;
import edu.uestc.msstudio.panorama.repo.UserRepository;

/**
 * @ClassName: MenuController
 * @Description: TODO
 * @author: MT
 */
@RestController
public class MenuController {
    @Autowired
    private CookBookRepository menuDao;
    @Autowired
    private UserRepository userDao;

    // get cookbooks
    @GetMapping("/book/")
    public HttpEntity<?> selectAllCookBook(
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "1") int num,
            @RequestParam(defaultValue = "true") boolean sort) {
        Pageable pageable;
        if (sort)
            pageable = new PageRequest(num - 1, size, Sort.Direction.ASC, "id");
        else
            pageable = new PageRequest(num - 1, size, Sort.Direction.DESC,
                    "id");
        Page<CookBook> resultPage = menuDao.findAll(pageable);
        if (resultPage.getContent().size() == 0)
            return ResponseEntity.status(404).body(resultPage);
        return ResponseEntity.ok(resultPage);
    }
    @GetMapping("/book/{id}")
    public HttpEntity<?> selectTargetCookBook(@PathVariable Long id) {
        CookBook result = menuDao.findOne(id);
        if (result == null)
            return ResponseEntity.status(404).body("The Cook Book Is Not Here");
        else if (result.getStatus() != CookBookStatus.PUBLISHED) {
            return ResponseEntity.status(402)
                    .body("The Cook Book is now in edit,please visit later");
        } else
            return ResponseEntity.ok(result);
    }
    // create cookbook
    @LoginedUser
    @PostMapping("/book/")
    public HttpEntity<?> createCookBook(@RequestBody CookBook input) {
        input.setAuthor(UserTokenUtils.getUserFromToken(userDao));
        if (input.getStatus() == null)
            input.setStatus(CookBookStatus.DRAFT);
        CookBook result = menuDao.save(input);
        return ResponseEntity.ok(result);
    }
    // delete cookbook
    @LoginedUser
    @DeleteMapping("/book/{id}")
    @Transactional
    public HttpEntity<?> deletCookBook(@PathVariable Long id) {
        CookBook target = menuDao.findOne(id);
        target.setStatus(CookBookStatus.ABANDONED);
        if (target.getAuthor()
                .equals(UserTokenUtils.getUserFromToken(userDao))) {
            menuDao.save(target);
            return ResponseEntity.ok("Target CookBook Abandoned");
        } else {
            return ResponseEntity.status(403)
                    .body("This may not be your Cook Book");
        }
    }
    // update cookbook
    @LoginedUser
    @PutMapping("/book/story/{id}")
    @Transactional
    public HttpEntity<?> updateCookBookStory(@PathVariable Long id,
            @RequestBody String story) {
        CookBook target = menuDao.findOne(id);
        if (target.getAuthor()
                .equals(UserTokenUtils.getUserFromToken(userDao))) {
            target.setStory(story);
            return ResponseEntity.ok(menuDao.save(target));
        } else {
            return ResponseEntity.status(403)
                    .body("This may not be your Cook Book");
        }
    }
    @LoginedUser
    @PutMapping("/book/category/{id}")
    @Transactional
    public HttpEntity<?> updateCookBookCategory(@PathVariable Long id,
            @RequestBody List<MenuType> category) {
        CookBook target = menuDao.findOne(id);
        if (target.getAuthor()
                .equals(UserTokenUtils.getUserFromToken(userDao))) {
            target.setCategory(category);
            return ResponseEntity.ok(menuDao.save(target));
        } else {
            return ResponseEntity.status(403)
                    .body("This may not be your Cook Book");
        }
    }
    @LoginedUser
    @PutMapping("/book/material/{id}")
    @Transactional
    public HttpEntity<?> updateCookBookMaterial(@PathVariable Long id,
            @RequestBody String material) {
        CookBook target = menuDao.findOne(id);
        if (target.getAuthor()
                .equals(UserTokenUtils.getUserFromToken(userDao))) {
            target.setMaterial(material);
            return ResponseEntity.ok(menuDao.save(target));
        } else {
            return ResponseEntity.status(403)
                    .body("This may not be your Cook Book");
        }
    }
    @LoginedUser
    @PutMapping("/book/tips/{id}")
    @Transactional
    public HttpEntity<?> updateCookBookTips(@PathVariable Long id,
            @RequestBody String tips) {
        CookBook target = menuDao.findOne(id);
        if (target.getAuthor()
                .equals(UserTokenUtils.getUserFromToken(userDao))) {
            target.setTips(tips);
            return ResponseEntity.ok(menuDao.save(target));
        } else {
            return ResponseEntity.status(403)
                    .body("This may not be your Cook Book");
        }
    }
    // change status of cookbook
    @LoginedUser
    @PutMapping("/book/status/{id}")
    @Transactional
    public HttpEntity<?> changeStatus(@PathVariable Long id,
            @RequestParam CookBookStatus status) {
        CookBook target = menuDao.findOne(id);
        if (target.getAuthor()
                .equals(UserTokenUtils.getUserFromToken(userDao))) {
            if (target.getStatus() == CookBookStatus.ABANDONED)
                return ResponseEntity.status(402)
                        .body("The Cook Book is ABANDONED!");
            target.setStatus(status);
            return ResponseEntity.ok(menuDao.save(target));
        } else {
            return ResponseEntity.status(403)
                    .body("This may not be your Cook Book");
        }
    }
    @LoginedUser
    @PutMapping("/book/steps/{id}")
    @Transactional
    public HttpEntity<?> changeSteps(@PathVariable Long id,
            @RequestParam Set<CookStep> steps) {
        CookBook target = menuDao.findOne(id);
        if (target.getAuthor()
                .equals(UserTokenUtils.getUserFromToken(userDao))) {
            if (target.getStatus() == CookBookStatus.ABANDONED)
                return ResponseEntity.status(402)
                        .body("The Cook Book is ABANDONED!");
            target.setSteps(steps);
            return ResponseEntity.ok(menuDao.save(target));
        } else {
            return ResponseEntity.status(403)
                    .body("This may not be your Cook Book");
        }
    }
    @LoginedUser
    @PutMapping("/book/steps/{id}")
    @Transactional
    public HttpEntity<?> changeImages(@PathVariable Long id,
            @RequestParam List<ImageInfo> images) {
        CookBook target = menuDao.findOne(id);
        if (target.getAuthor()
                .equals(UserTokenUtils.getUserFromToken(userDao))) {
            if (target.getStatus() == CookBookStatus.ABANDONED)
                return ResponseEntity.status(402)
                        .body("The Cook Book is ABANDONED!");
            target.setImages(images);
            return ResponseEntity.ok(menuDao.save(target));
        } else {
            return ResponseEntity.status(403)
                    .body("This may not be your Cook Book");
        }
    }
    // add comment of cookbook
    @LoginedUser
    @PostMapping("/comment/{id}")
    public HttpEntity<?> addComment(@PathVariable Long id,
            @RequestBody Comment input) {
        CookBook target = menuDao.findOne(id);
        if (target.getAuthor()
                .equals(UserTokenUtils.getUserFromToken(userDao))) {
            if (target.getStatus() == CookBookStatus.ABANDONED)
                return ResponseEntity.status(402)
                        .body("The Cook Book is ABANDONED!");
            Set<Comment> comments = target.getComments();
            comments.add(input);
            return ResponseEntity.ok(menuDao.save(target));
        } else {
            return ResponseEntity.status(403)
                    .body("This may not be your Cook Book");
        }
    }
}
