package com.zuo.service;

import com.zuo.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
public interface BannerService extends IService<Banner> {
    List<Banner> getAllBanner();
}
