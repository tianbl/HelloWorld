package com.eastsoft.pub.service.impl;

import com.eastsoft.pub.dao.UserDao;
import com.eastsoft.pub.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ljt on 2015/11/18.
 */
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
