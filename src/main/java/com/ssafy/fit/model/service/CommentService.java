package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dto.Comment;

public interface CommentService {

	public List<Comment> selectCommentAll(int articleNo);

	//없앨예정
	public Comment selectCommentByNo(int commentNo);

	public void insertComment(Comment comment);

	public void deleteComment(int commentNo);

	public void updateComment(int commentNo, String content);

}
