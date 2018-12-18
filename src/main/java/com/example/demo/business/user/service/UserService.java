package com.example.demo.business.user.service;

import com.example.demo.business.user.entity.UserInfo;
import com.example.demo.commons.service.BaseCrudService;

/**
 * @desc:
 * @author: create by SunHJ
 * @date:2018/7/6 13:35
 */
public interface UserService extends BaseCrudService<UserInfo,Long>{

    UserInfo registerService(UserInfo userInfo);
}
