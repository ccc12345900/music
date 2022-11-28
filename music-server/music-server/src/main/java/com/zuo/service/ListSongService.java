package com.zuo.service;

import com.zuo.common.R;
import com.zuo.entity.ListSong;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
public interface ListSongService extends IService<ListSong> {
    R listSongofSongId(Integer songListId);
}
