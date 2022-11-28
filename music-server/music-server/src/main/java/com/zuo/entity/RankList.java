package com.zuo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class RankList implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("songListId")
    private Long songListId;

    @TableField("consumerId")
    private Long consumerId;

    private Integer score;


}
