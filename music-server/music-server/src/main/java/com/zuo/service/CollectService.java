package com.zuo.service;

import com.zuo.common.R;
import com.zuo.entity.Collect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zuo.entity.request.CollectRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
public interface CollectService extends IService<Collect> {
    R collectionOfUser(Integer userId);

    R addCollection(CollectRequest addCollectRequest);

    R existSongId(CollectRequest isCollectRequest);

    R deleteCollect(Integer userId,Integer songId);
}
