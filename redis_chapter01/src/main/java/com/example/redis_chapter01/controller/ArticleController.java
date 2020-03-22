package com.example.redis_chapter01.controller;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.example.redis_chapter01.common.Constant;
import com.example.redis_chapter01.pojo.Article;
import com.example.redis_chapter01.pojo.User;
import com.example.redis_chapter01.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author yuwensong
 * @date 2020/3/21
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getArticlesByTime")
    public List<Article> getArticlesByTime(Integer page, Integer pageNum) {
        return articleService.getArticles(page, pageNum, Constant.TIME_ZSET);
    }

    @GetMapping("/getArticlesByScore")
    public List<Article> getArticlesByScore(Integer page, Integer pageNum) {
        return articleService.getArticles(page, pageNum, Constant.SCORE_ZSET);
    }

    @PostMapping("/postArticleId1")
    public Article postArticleId1(@RequestBody Article article) {
        User user = new User();
        user.setId("1");
        return articleService.postArticle(article, user);

    }

    @PostMapping("/postArticleId2")
    public Article postArticleId2(@RequestBody Article article) {
        User user = new User();
        user.setId("2");
        return articleService.postArticle(article, user);

    }

    @PostMapping("/voteArticleId1")
    public Article voteArticleId1(@RequestBody Article article) {
        User user = new User();
        user.setId("1");
        return articleService.articleVote(article, user);

    }

    @PostMapping("/voteArticleId2")
    public Article voteArticleId2(@RequestBody Article article) {
        User user = new User();
        user.setId("2");
        return articleService.articleVote(article, user);

    }


}
