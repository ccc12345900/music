package com.zuo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zuo.common.R;
import com.zuo.entity.RankList;
import com.zuo.entity.request.RankListRequest;

public interface RankListService extends IService<RankList> {

    R addRank(RankListRequest rankListAddRequest);

    R rankOfSongListId(Long songListId);

    R getUserRank(Long consumerId, Long songListId);

}
