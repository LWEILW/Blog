package com.blogger.server;

import com.blogger.entity.ArticleEntity.Article;

import java.util.List;

public interface ArticleService {

    // 文章台账
    List<Article> getArticleList(int pageNum, int pageSize);

    // 文章保存
    boolean saveArticle(Article article);

    // 文章删除
    int deleteArticle(int articleId);

    // 文章详情
    Article detailsArticle(int articleId);
}
