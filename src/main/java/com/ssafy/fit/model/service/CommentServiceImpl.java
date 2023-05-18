package com.ssafy.fit.model.service;

import com.ssafy.fit.model.dao.ICommentDao;
import com.ssafy.fit.model.dto.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private ICommentDao commentDao;

    @Autowired
    public CommentServiceImpl(ICommentDao commentDao){
        this.commentDao = commentDao;
    }

    // 댓글 전체 조회
    @Override
    public List<Comment> selectCommentAll(int articleNo) {
        return commentDao.selectCommentAll(articleNo);
    }

    // 없앨예정
    // 댓글 상세 조회
    @Override
    public Comment selectCommentByNo(int commentNo) {
        return commentDao.selectCommentByNo(commentNo);
    }

    // 댓글 작성
    @Override
    public void insertComment(Comment comment) {
        commentDao.insertComment(comment);
    }

    // 댓글 삭제
    @Override
    public void deleteComment(int commentNo) {
        commentDao.deleteComment(commentNo);
    }

    // 댓글 수정
    @Override
    public void updateComment(int commentNo, String content) {
        commentDao.updateComment(commentNo, content);
    }
}
