package com.eastsoft.pub.dao.impl;

import com.eastsoft.pub.dao.UserDao;
import com.eastsoft.pub.entity.TsysUser;
import org.springframework.stereotype.Repository;

/**
 * Created by eastsoft on 2015/11/18.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<TsysUser,Long> implements UserDao{
}
