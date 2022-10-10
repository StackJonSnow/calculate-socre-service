package com.snow.cs.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snow.cs.dao.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TXM
 * @since 2021-09-26
 */
public interface IUserService extends IService<User> {

    User findUserByOpenid(String openid);

}
