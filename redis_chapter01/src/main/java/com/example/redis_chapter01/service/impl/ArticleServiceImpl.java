package com.example.redis_chapter01.service.impl;

import com.example.redis_chapter01.common.Constant;
import com.example.redis_chapter01.pojo.Article;
import com.example.redis_chapter01.pojo.User;
import com.example.redis_chapter01.service.ArticleService;
import com.sun.org.apache.xerces.internal.impl.dv.DVFactoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yuwensong
 * @date 2020/3/21
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Article> getArticles(Integer page, Integer pageNum, String order) {
        Integer start = (page - 1) * pageNum;
        Integer end = start + pageNum - 1;
        List<Article> articles = new ArrayList<>();
        Set<Object> articleIds = redisTemplate.opsForZSet().reverseRange(order, start, end);
        for (Object articleId : articleIds) {
            Article article =(Article) redisTemplate.opsForValue().get(articleId);
            articles.add(article);
        }
        return articles;
    }

    @Override
    public Article articleVote(Article article, User voteUser) {
        Article dbArticle = (Article)redisTemplate.opsForValue().get(Constant.ARTICLE_PREFIX + article.getId());
        if (dbArticle.getTime() < System.currentTimeMillis() / 1000 - Constant.ONE_WEEK_IN_SECONDS) {
            return dbArticle;
        }

        if (!redisTemplate.opsForSet().isMember(Constant.VOTED_PREFIX_SET + article.getId(), Constant.USER_PREFIX + voteUser.getId())) {
            redisTemplate.opsForSet().add(Constant.VOTED_PREFIX_SET + article.getId(), Constant.USER_PREFIX + voteUser.getId());
            redisTemplate.opsForZSet().incrementScore(
                    Constant.SCORE_ZSET,
                    Constant.ARTICLE_PREFIX + article.getId(),
                    Constant.VOTE_SCORE);
            dbArticle.setVotes(dbArticle.getVotes() + 1);
            redisTemplate.opsForValue().set(Constant.ARTICLE_PREFIX + article.getId(), dbArticle);
        }
        return dbArticle;


    }

    @Override
    public Article postArticle(Article article, User postUser) {
        Long id = redisTemplate.opsForValue().increment(Constant.ARTICLE_PREFIX);
        redisTemplate.opsForSet().add(
                Constant.VOTED_PREFIX_SET + id,
                Constant.USER_PREFIX + postUser.getId());
        redisTemplate.expire(Constant.VOTED_PREFIX_SET + id,
                Constant.ONE_WEEK_IN_SECONDS, TimeUnit.SECONDS);
        article.setId(id + "");
        article.setTime(System.currentTimeMillis() / 1000);
        article.setVotes(1);
        article.setPoster(postUser);
        redisTemplate.opsForValue().set(
                Constant.ARTICLE_PREFIX + id,
                article
        );
        redisTemplate.opsForZSet().add(
                Constant.SCORE_ZSET,
                Constant.ARTICLE_PREFIX + article.getId(),
                article.getTime() + Constant.VOTE_SCORE
        );
        redisTemplate.opsForZSet().add(
                Constant.TIME_ZSET,
                Constant.ARTICLE_PREFIX + article.getId(),
                article.getTime()
        );
        return article;
    }
}
