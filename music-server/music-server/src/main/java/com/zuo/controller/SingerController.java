package com.zuo.controller;


import com.zuo.common.R;
import com.zuo.entity.request.SingerRequest;
import com.zuo.mapper.SingerMapper;
import com.zuo.service.SingerService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
@RestController
public class SingerController {
    @Autowired
    private SingerService singerService;

    @GetMapping("/singer")
    public R allSinger(){
        return singerService.allSinger();
    }

    @GetMapping("/singer/sex/detail")
    public  R singOfSex(Integer sex){
        return singerService.singOfSex(sex);
    }

    @DeleteMapping("/singer/delete")
    public R deleteSinger(@RequestParam Integer id){
        return singerService.deleteSinger(id);
    }

    @PostMapping("/singer/update")
    public R updateStringMsg(@RequestBody SingerRequest updateSingerRequest){
        return singerService.updateSingerMsg(updateSingerRequest);
    }

    // 添加歌手
    @PostMapping("/singer/add")
    public R addSinger(@RequestBody SingerRequest addSingerRequest) {
        return singerService.addSinger(addSingerRequest);
    }

    // 更新歌手头像
    @PostMapping("/singer/avatar/update")
    public R updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return singerService.updateSingerPic(avatorFile, id);
    }
}

