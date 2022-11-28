package com.zuo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zuo.common.R;
import com.zuo.entity.Comment;
import com.zuo.entity.request.CommentRequest;

public interface CommentService extends IService<Comment> {

    R addComment(CommentRequest addCommentRequest);

    R updateCommentMsg(CommentRequest upCommentRequest);

    R deleteComment(Integer id);

    R commentOfSongId(Integer songId);

    R commentOfSongListId(Integer songListId);

}
