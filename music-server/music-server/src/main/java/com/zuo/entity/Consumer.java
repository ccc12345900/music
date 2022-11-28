package com.zuo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Consumer implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private Integer sex;

    private String phoneNum;

    private String email;

    private LocalDateTime birth;

    private String introduction;

    private String location;

    private String avator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
