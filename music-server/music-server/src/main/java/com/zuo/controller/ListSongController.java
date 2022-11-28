package com.zuo.controller;


import com.zuo.common.R;
import com.zuo.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
@RestController
public class ListSongController {
    @Autowired
    private ListSongService listSongService;
    // 返回歌单里指定歌单 ID 的歌曲
    @GetMapping("/listSong/detail")
    public R listSongOfSongId(@RequestParam int songListId){return listSongService.listSongofSongId(songListId);}
}

