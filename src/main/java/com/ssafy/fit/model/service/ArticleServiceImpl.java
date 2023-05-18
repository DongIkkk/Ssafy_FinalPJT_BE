package com.ssafy.fit.model.service;

import com.ssafy.fit.model.dao.IArticleDao;

import com.ssafy.fit.model.dto.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    private IArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(IArticleDao articleDao){
        this.articleDao = articleDao;
    }

    @Override
    public List<Article> selectArticleAll() {
        return articleDao.selectArticleAll();
    }

    @Override
    public List<Article> selectMyArticle(int userNo) {
        return articleDao.selectMyArticle(userNo);
    }

    @Override
    public Article selectArticleByNo(int articleNo){
        // 조회수 증가 메서드 들어가야함
        return articleDao.selectArticleByNo(articleNo);
    }

    @Override
    public void insertArticle(Article article) {
        articleDao.insertArticle(article);
    }

    @Override
    public void deleteArticle(int articleNo) {
        articleDao.deleteArticle(articleNo);
    }

    @Override
    public void updateArticle(int articleNo, Article article) {
        articleDao.updateArticle(articleNo, article);
    }
}
