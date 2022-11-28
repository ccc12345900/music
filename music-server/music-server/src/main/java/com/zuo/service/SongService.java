package com.zuo.service;

import com.zuo.common.R;
import com.zuo.entity.Song;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zuo.entity.request.SongRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
public interface SongService extends IService<Song> {
    R songOfId(Integer id);
    R songOfSingerId(Integer singerId);

    R songOfSingerName(String name);

    R allSong();

    R deleteSong(Integer id);

    R updateSongMsg(SongRequest updateSongRequest);

    R updateSongPic(MultipartFile urlFile,int id);

    R updateSongUrl(MultipartFile urlFile,int id);

    R addSong(SongRequest addSongRequest,MultipartFile mpfile);
}
