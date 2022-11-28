package com.zuo.controller;


import com.zuo.common.R;
import com.zuo.entity.request.ConsumerRequest;
import com.zuo.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

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
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    //用户登录
    @PostMapping("/user/login/status")
    public R loginStatus(@RequestBody ConsumerRequest loginRequest, HttpSession session){
        return consumerService.loginStatus(loginRequest,session);
    }

    //用户个人信息
    @GetMapping("/user/detail")
    public R userOfId(@RequestParam int id){return consumerService.userOfId(id);}

    //用户信息更新
    @PostMapping("/user/update")
    public R updateUserMsg(@RequestBody ConsumerRequest updateRequest){
        return consumerService.updateUserMsg(updateRequest);
    }

    //用户密码更改
    @PostMapping("/user/updatePassword")
    public R updatePassword(@RequestBody ConsumerRequest updatePasswordRequest)
    {
        return consumerService.updatePassword(updatePasswordRequest);
    }

    @GetMapping("/user")
    public R allUser(){
        return consumerService.allUser();
    }

    //更新用户头像
    @PostMapping("/user/avatar/update")
    public R updateUserPic(@RequestParam("file") MultipartFile avatorFile,@RequestParam("id") int id){
        return consumerService.updateUserAvator(avatorFile, id);
    }

    //用户注册
    @PostMapping("/user/add")
    public R addUser(@RequestBody ConsumerRequest registryRequest){return consumerService.addUser(registryRequest);}

    @GetMapping("/user/delete")
    public R deleteUser(@RequestParam Integer id){
        return consumerService.deleteUser(id);
    }
}

