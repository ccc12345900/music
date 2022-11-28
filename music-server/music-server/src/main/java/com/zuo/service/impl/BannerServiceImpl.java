package com.zuo.service.impl;

import com.zuo.entity.Banner;
import com.zuo.mapper.BannerMapper;
import com.zuo.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Cacheable(value = "banner", key = "'list'")  //放在缓存中 redis 是以key-value进行存储的
    @Override
    public List<Banner> getAllBanner() {
        System.out.println("没有走缓存");
        return bannerMapper.selectList(null);
    }
}
