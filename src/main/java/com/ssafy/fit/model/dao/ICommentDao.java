package com.ssafy.fit.model.dao;

import java.util.List;

import com.ssafy.fit.model.dto.Comment;

public interface ICommentDao {
	
	public List<Comment> selectCommentAll(int articleNo);

	public Comment selectCommentByNo(int no);

	public void insertComment(Comment comment);

	public void deleteComment(int no);

	public void updateComment(int commentNo, String content);

}
