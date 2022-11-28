package com.zuo.service;

import com.zuo.common.R;
import com.zuo.entity.SongList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zuo.entity.request.SongListRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
public interface SongListService extends IService<SongList> {
     R allSongList();

     R likeTitle(String name);

     R songListOfStyle(String name);

     R songListDelete(Integer id);

     R addSongList(SongListRequest addSongListRequest);

     R updateSongListMsg(SongListRequest updateSongListRequest);

     R updateSongListImg(MultipartFile avatorFile,int id);
}
