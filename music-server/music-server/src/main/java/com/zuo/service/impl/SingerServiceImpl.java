package com.zuo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zuo.common.R;
import com.zuo.entity.Singer;
import com.zuo.entity.request.SingerRequest;
import com.zuo.mapper.SingerMapper;
import com.zuo.service.SingerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public R allSinger() {
        return R.success(null,singerMapper.selectList(null));
    }

    @Override
    public R singOfSex(Integer sex) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex",sex);
        return R.success(null,singerMapper.selectList(queryWrapper));
    }

    @Override
    public R deleteSinger(Integer id) {
        if(singerMapper.deleteById(id) > 0){
            return R.success("删除成功");
        }else {
            return R.error("删除失败");
        }
    }

    @Override
    public R updateSingerMsg(SingerRequest updateSingerRequest) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(updateSingerRequest, singer);
        if (singerMapper.updateById(singer) > 0) {
            return R.success("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    @Override
    public R addSinger(SingerRequest addSingerRequest) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(addSingerRequest, singer);
        String pic = "/img/avatorImages/user.jpg";
        singer.setPic(pic);
        if (singerMapper.insert(singer) > 0) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    @Override
    public R updateSingerPic(MultipartFile avatorFile, int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
        } catch (IOException e) {
            return R.fatal("上传失败" + e.getMessage());
        }
        Singer singer = new Singer();
        singer.setId(id);
        singer.setPic(imgPath);
        if (singerMapper.updateById(singer) > 0) {
            return R.success("上传成功", imgPath);
        } else {
            return R.error("上传失败");
        }
    }
}
