package com.zuo.service;

import com.zuo.common.R;
import com.zuo.entity.Consumer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zuo.entity.request.ConsumerRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
public interface ConsumerService extends IService<Consumer> {

    boolean verityPasswd(String username, String password);
    R loginStatus(ConsumerRequest consumerRequest, HttpSession session);

    R userOfId(Integer id);

    R updateUserMsg(ConsumerRequest consumerRequest);

    R updatePassword(ConsumerRequest updatePasswordRequest);

    R updateUserAvator(MultipartFile avatorFile,int id);

    R addUser(ConsumerRequest registryRequest);

    R allUser();
    boolean existUser(String username);

    R deleteUser(Integer id);
}
