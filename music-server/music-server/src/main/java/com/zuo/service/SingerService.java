package com.zuo.service;

import com.zuo.common.R;
import com.zuo.entity.Singer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zuo.entity.request.SingerRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
public interface SingerService extends IService<Singer> {
    R allSinger();

    R singOfSex(Integer sex);

    R deleteSinger(Integer id);

    R updateSingerMsg(SingerRequest updateSingerRequest);

    R addSinger(SingerRequest addSingerRequest);

    R updateSingerPic(MultipartFile avatorFile,int id);
}
