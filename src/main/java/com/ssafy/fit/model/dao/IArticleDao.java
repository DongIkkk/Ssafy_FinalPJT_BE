package com.ssafy.fit.model.dao;

import com.ssafy.fit.model.dto.Article;

import java.util.List;

public interface IArticleDao {

    public List<Article> selectArticleAll();

    public List<Article> selectMyArticle(int userNo);

    public Article selectArticleByNo(int articleNo);

    public void insertArticle(Article article);

    public void deleteArticle(int articleNo);

    public void updateArticle(int articleNo, Article article);
    
    public void increaseViewCnt(int articleNo);

    public void articleLike(int articleNo, int userNo);

    public void articleUnlike(int articleNo, int userNo);

}
