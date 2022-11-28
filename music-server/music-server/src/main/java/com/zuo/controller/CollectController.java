package com.zuo.controller;


import com.zuo.common.R;
import com.zuo.entity.request.CollectRequest;
import com.zuo.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
@RestController
public class CollectController {
    @Autowired
    private CollectService collectService;

    //返回用户收藏歌单
    @GetMapping("/collection/detail")
    public R collectionOfUser(@RequestParam Integer userId) {
        return collectService.collectionOfUser(userId);
    }

    //用户收藏歌单
    @PostMapping("/collection/add")
    public R addCollection(@RequestBody CollectRequest addCollectRequest){
        return collectService.addCollection(addCollectRequest);
    }

    //TODO  这些其实有点偏简单的逻辑  所以就一点 所以放在外面  拿到里面
    // 取消收藏的歌曲
    @DeleteMapping("/collection/delete")
    public R deleteCollection(@RequestParam Integer userId, @RequestParam Integer songId) {
        return collectService.deleteCollect(userId, songId);
    }

    // 是否收藏歌曲
    @PostMapping("/collection/status")
    public R isCollection(@RequestBody CollectRequest isCollectRequest) {
        return collectService.existSongId(isCollectRequest);

    }
}

