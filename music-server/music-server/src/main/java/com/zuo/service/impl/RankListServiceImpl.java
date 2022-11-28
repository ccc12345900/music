package com.zuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zuo.common.R;
import com.zuo.entity.RankList;
import com.zuo.entity.request.RankListRequest;
import com.zuo.mapper.RankListMapper;
import com.zuo.service.RankListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class RankListServiceImpl extends ServiceImpl<RankListMapper, RankList> implements RankListService {


    @Autowired
    private RankListMapper rankMapper;

    @Override
    public R addRank(RankListRequest rankListAddRequest) {
        RankList rankList = new RankList();
        BeanUtils.copyProperties(rankListAddRequest, rankList);
        if (rankMapper.insert(rankList) > 0) {
            return R.success("评价成功");
        } else {
            return R.error("评价失败");
        }
    }

    @Override
    public R rankOfSongListId(Long songListId) {
        // 评分总人数如果为 0，则返回0；否则返回计算出的结果
        QueryWrapper<RankList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("songListId",songListId);
        Integer rankNum = rankMapper.selectCount(queryWrapper);
        return R.success(null, (rankNum <= 0) ? 0 : rankMapper.selectScoreSum(songListId) / rankNum);
    }

    @Override
    public R getUserRank(Long consumerId, Long songListId) {
        return R.success(null, rankMapper.selectUserRank(consumerId, songListId));
    }
}
