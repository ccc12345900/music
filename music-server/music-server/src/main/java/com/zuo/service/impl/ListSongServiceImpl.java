package com.zuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zuo.common.R;
import com.zuo.entity.ListSong;
import com.zuo.mapper.ListSongMapper;
import com.zuo.service.ListSongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, ListSong> implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public R listSongofSongId(Integer songListId) {
        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_List_Id",songListId);
        return R.success("查询成功", listSongMapper.selectList(queryWrapper));
    }
}
