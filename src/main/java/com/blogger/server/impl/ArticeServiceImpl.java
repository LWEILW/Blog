package com.blogger.server.impl;

import com.blogger.dao.ArticleMapper.ArticleMapper;
import com.blogger.entity.ArticleEntity.Article;
import com.blogger.server.ArticleService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ArticleService")
public class ArticeServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    // 文章台账
    @Override
    public List<Article> getArticleList() {
        return articleMapper.getArticleList();
    }

    // 文章保存
    @Override
    public boolean saveArticle(Article article) {
        int succ = 0;
        if (article.getArticleId() != null && !"".equals(article.getArticleId())) {
            // ID不为空，更新操作
            succ = articleMapper.updateArticle(article);
        } else {
            // ID为空，创建操作
            succ = articleMapper.createArticle(article);
        }
        if (succ != 1) {
            return false;
        }
        return true;
    }

    // 文章删除
    @Override
    public int deleteArticle(int articleId) {
        return articleMapper.deleteArticle(articleId);
    }


    // 文章详情
    @Override
    public Article detailsArticle(int articleId) {
       return articleMapper.detailsArticle(articleId);
    }
}
