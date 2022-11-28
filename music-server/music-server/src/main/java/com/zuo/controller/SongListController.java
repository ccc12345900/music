package com.zuo.controller;


import com.zuo.common.R;
import com.zuo.entity.request.SongListRequest;
import com.zuo.service.SongListService;
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
public class SongListController {
    @Autowired
    private SongListService songListService;

    //获取所有歌单信息
    @GetMapping("/songList")
    public R allSongList(){
        return songListService.allSongList();
    }

    //根据名字模糊查询歌单
    @GetMapping("/songList/likeTitle/detail")
    public R songListOfTitle(@RequestParam String title){return songListService.likeTitle('%' + title + '%');}

    //根据类型查询歌单
    @GetMapping("/songList/style/detail")
    public R songListOfStyle(@RequestParam String style){
        return songListService.songListOfStyle(style);
    }

    @GetMapping("/songList/delete")
    public R songListDelete(@RequestParam Integer id){
        return songListService.songListDelete(id);
    }

    // 更新歌单信息
    @PostMapping("/songList/update")
    public R updateSongListMsg(@RequestBody SongListRequest updateSongListRequest) {
        return songListService.updateSongListMsg(updateSongListRequest);

    }

    // 添加歌单
    @PostMapping("/songList/add")
    public R addSongList(@RequestBody SongListRequest addSongListRequest) {
        return songListService.addSongList(addSongListRequest);
    }

    // 更新歌单图片
    @PostMapping("/songList/img/update")
    public R updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        return songListService.updateSongListImg(avatorFile,id);
    }
}

