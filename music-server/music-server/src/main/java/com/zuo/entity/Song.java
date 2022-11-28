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
public class Song implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer singerId;

    private String name;

    private String introduction;

    /**
     * 发行时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String pic;

    private String lyric;

    private String url;


}
