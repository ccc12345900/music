package com.zuo.mapper;

import com.zuo.entity.RankList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
public interface RankListMapper extends BaseMapper<RankList> {

    int selectUserRank(@Param("consumer_id") Long consumerId,@Param("song_list_id")  Long songListId);

    int selectScoreSum(Long songListId);
}
