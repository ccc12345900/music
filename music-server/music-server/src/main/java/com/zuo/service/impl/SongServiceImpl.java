package com.zuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zuo.common.R;
import com.zuo.entity.Song;
import com.zuo.entity.request.SongRequest;
import com.zuo.mapper.SongMapper;
import com.zuo.service.SongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public R songOfId(Integer id) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return R.success(null, songMapper.selectList(queryWrapper));
    }

    @Override
    public R songOfSingerId(Integer singerId) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("singer_id",singerId);
        return R.success(null,songMapper.selectList(queryWrapper));
    }

    @Override
    public R songOfSingerName(String name) {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return R.success(null,songMapper.selectList(queryWrapper));
    }

    @Override
    public R allSong() {
        return R.success(null,songMapper.selectList(null));
    }

    @Override
    public R deleteSong(Integer id) {
        if(songMapper.deleteById(id) > 0){
            return R.success("删除成功");
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R updateSongMsg(SongRequest updateSongRequest) {
        Song song = new Song();
        BeanUtils.copyProperties(updateSongRequest,song);
        if(songMapper.updateById(song) > 0){
            return R.success("修改成功");
        }else {
            return R.error("修改失败");
        }
    }

    @Override
    public R updateSongPic(MultipartFile urlFile, int id) {
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            if (!file1.mkdir()) {
                return R.fatal("创建文件夹失败");
            }
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/img/songPic/" + fileName;
        try {
            urlFile.transferTo(dest);
        } catch (IOException e) {
            return R.fatal("上传失败" + e.getMessage());
        }
        Song song = new Song();
        song.setId(id);
        song.setPic(storeUrlPath);
        if (songMapper.updateById(song) > 0) {
            return R.success("上传成功", storeUrlPath);
        } else {
            return R.error("上传失败");
        }
    }

    @Override
    public R updateSongUrl(MultipartFile urlFile, int id) {
        String fileName = urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if(!file1.exists()){
            if (!file1.mkdir()) {
                return R.fatal("创建目的文件夹失败");
            }
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            urlFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return R.fatal("更新失败" + e.getMessage());
        }
        Song song = new Song();
        song.setId(id);
        song.setUrl(storeUrlPath);
        if (songMapper.updateById(song) > 0) {
            return R.success("更新成功", storeUrlPath);
        } else {
            return R.error("更新失败");
        }
    }

    @Override
    public R addSong(SongRequest addSongRequest, MultipartFile mpfile) {
        Song song = new Song();
        BeanUtils.copyProperties(addSongRequest, song);
        String pic = "/img/songPic/tubiao.jpg";
        String fileName = mpfile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            if (!file1.mkdir()) {
                return R.fatal("创建文件失败");
            }
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            mpfile.transferTo(dest);
        } catch (IOException e) {
            return R.fatal("上传失败" + e.getMessage());
        }
        song.setCreateTime(LocalDateTime.now());
        song.setUpdateTime(LocalDateTime.now());
        song.setPic(pic);
        song.setUrl(storeUrlPath);
        if (songMapper.insert(song) > 0) {
            return R.success("上传成功", storeUrlPath);
        } else {
            return R.error("上传失败");
        }
    }
}
