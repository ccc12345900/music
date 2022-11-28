package com.zuo.service;

import com.zuo.common.R;
import com.zuo.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zuo.entity.request.AdminRequest;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
public interface AdminService extends IService<Admin> {
    R verityPasswd(AdminRequest adminRequest, HttpSession session);
}
