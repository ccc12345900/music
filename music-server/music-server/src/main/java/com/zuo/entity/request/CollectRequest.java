package com.zuo.entity.request;

import lombok.Data;

import java.util.Date;

@Data
public class CollectRequest {
    private Integer id;

    private Integer userId;

    private Integer type;

    private Integer songId;

    private Integer songListId;

    private Date createTime;
}
