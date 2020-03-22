package com.example.redis_chapter01.common;

/**
 * @author yuwensong
 * @date 2020/3/21
 */
public class Constant {

    public final static Long ONE_WEEK_IN_SECONDS = 7 * 24 * 60 * 60L;

    public final static Long VOTE_SCORE = ONE_WEEK_IN_SECONDS / 200;

    public final static String TIME_ZSET = "time:";

    public final static String SCORE_ZSET = "score:";

    public final static String VOTED_PREFIX_SET = "voted:";

    public final static String ARTICLE_PREFIX = "article:";

    public final static String USER_PREFIX = "user:";
}
