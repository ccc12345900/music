package com.zuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zuo.common.R;
import com.zuo.entity.Song;
import com.zuo.entity.SongList;
import com.zuo.entity.request.SongListRequest;
import com.zuo.mapper.SongListMapper;
import com.zuo.service.SongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public R allSongList() {
        return R.success(null,songListMapper.selectList(null));
    }

    @Override
    public R likeTitle(String name) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",name);
        return R.success(null,songListMapper.selectList(queryWrapper));
    }

    @Override
    public R songListOfStyle(String name) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("style",name);
        return R.success(null,songListMapper.selectList(queryWrapper));
    }

    @Override
    public R songListDelete(Integer id) {
        if(songListMapper.deleteById(id) > 0){
            return R.success("删除成功");
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R addSongList(SongListRequest addSongListRequest) {
        SongList songList = new SongList();
        BeanUtils.copyProperties(addSongListRequest, songList);
        String pic = "/img/songListPic/123.jpg";
        songList.setPic(pic);
        if (songListMapper.insert(songList) > 0) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @Override
    public R updateSongListMsg(SongListRequest updateSongListRequest) {
        SongList songList = new SongList();
        BeanUtils.copyProperties(updateSongListRequest, songList);
        if (songListMapper.updateById(songList) > 0) {
            return R.success("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    @Override
    public R updateSongListImg(MultipartFile avatorFile, int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            return R.fatal("上传失败" + e.getMessage());
        }
        SongList songList = new SongList();
        songList.setId(id);
        songList.setPic(imgPath);
        if (songListMapper.updateById(songList) > 0) {
            return R.success("上传成功", imgPath);
        } else {
            return R.error("上传失败");
        }
    }
}
