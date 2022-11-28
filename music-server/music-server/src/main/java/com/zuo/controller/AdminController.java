package com.zuo.controller;


import com.zuo.common.R;
import com.zuo.entity.request.AdminRequest;
import com.zuo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/login/status")
    public R loginStatus(@RequestBody AdminRequest adminRequest, HttpSession session){
        return adminService.verityPasswd(adminRequest,session);
    }
}

