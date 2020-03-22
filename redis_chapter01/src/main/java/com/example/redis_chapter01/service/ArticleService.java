package com.example.redis_chapter01.service;

import com.example.redis_chapter01.pojo.Article;
import com.example.redis_chapter01.pojo.User;

import java.util.List;
import java.util.UUID;

/**
 * @author yuwensong
 * @date 2020/3/21
 */
public interface ArticleService {
    List<Article> getArticles(Integer page, Integer pageNum, String order);
    Article articleVote(Article article, User voteUser);

    Article postArticle(Article article, User postUser);
}
