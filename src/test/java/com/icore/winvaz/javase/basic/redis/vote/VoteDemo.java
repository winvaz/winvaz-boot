package com.icore.winvaz.javase.basic.redis.vote;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.*;

/**
 * @Deciption Redis--投票案例
 * 1.当一篇文章获得的票数至少为200张支持票(up vote)时，认为这是一篇有趣的文章。
 * 2.假如一个网站一天发布100篇文章，如果其中50篇符合有趣文章的要求，那么这50篇文章要放到文章列表的前50位至少一天。
 * 3.为了让文章的评分随着时间的推移而减少，需要根据文章的发布时间和当前时间来计算评分，具体的计算方法 = 文章的支持票数 *  常量 + 文章的发布时间。
 * 时间(Unix)(1970-01-01)的秒数。
 * 常量(432)=一天的秒数(24 * 60 * 60 = 86400) / 一天所需的票数(200)
 * 4.需要使用Redis存储网站上每篇文章的信息，需要使用散列(Hash)来存储文章的标题(title)，指向文章的网址(link)，发布文章的用户(poster)，文章的发布时间(time)，文章得到的票数(votes)
 * 5.使用两个有序集合(Sorted Set)来存储根据文章的发布时间和文章的评分
 * 6.为防止用户对同一篇文章多次投票，需要为每一篇文章记录已投票用户。
 * 7.为了节约内存，当一篇文章满一周后，文章不可以投票，文章的票数被固定，而记录文章投票的用户名单被清除。
 * @Author wdq
 * @Create 2019-09-25 16:15
 */
public class VoteDemo {

    // 一周常量
    private static final long ONE_WEEK_IN_SECONDS = 7 * 86400;

    // 投票常量
    private static final long VOTE_SCORE = 432;

    // Jedis客户端对象
    private Jedis jedis;

    // 构造函数连接Redis
    public VoteDemo() {
        // 连接本地Redis
        this.jedis = new Jedis("127.0.0.1");
        System.out.println("Redis服务器连接成功》》》:" + jedis.ping());
    }

    /**
     * @Description 投票功能用到的集合
     *
     * 1.文章信息表(article:文章ID---hash结构)
     * Map<String, String> map = new HashMap<>();
     * map.put("title", "Go to statement considered harmful"):
     * map.put("link", "http://goo.com.cn");
     * map.put("poster", "user:001);
     * map.put("time", Unix毫秒值);
     * map.put("votes", 1);
     * jedis.hset("article:001", map);
     *
     * 2.发布时间有序集合(time:--Sorted Set)
     * jedis.zadd("time", 时间，"article:001);
     *
     * 3.评分有序集合(score:--Sorted Set)
     * jedis.zadd("score", 评分, "article:001);
     *
     * 4.文章投票过的用户集合("voted:文章ID--Set)
     * jedis.sadd("voted:文章ID", "user:用户ID");
     */
    /**
     * @Description 投票功能实现
     * @Author wdq
     * @Params []
     * @Return void
     * @Create 2019-09-25 16:40
     */
    public void articleVote(String user, String article) {
        // 计算文章投票截止时间
        long l = new Date().getTime() - ONE_WEEK_IN_SECONDS;
        // 获取文章发布时间判断
        if (jedis.zscore("time:", article) < l) {
            System.out.println("此【" + article + "】文章已发布超过一周，不能投票！！！");
        }
        // 获取文章ID
        String articleId = article.split(":")[1];
        // 判断用户是否已投过票
        if (jedis.sismember("voted:" + articleId, user)) {
            System.out.println("此【" + user + "】用户已投过此【" + article + "】文章支持票，一周之内不能重复投票。");
        } else {
            // 没有投过此文章的支持票数。
            // Redis Incr 命令将 key 中储存的数字值增一。
            //如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
            //如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
            //本操作的值限制在 64 位(bit)有符号数字表示之内。
            // 为文章增加支持票数。
            jedis.zincrby("score:", VOTE_SCORE, article);
            // 为此文章增加投票数量
            jedis.hincrBy(article, "votes", 1);
            // 投票之后把该用户添加到已投票集合
            jedis.sadd("voted:" + articleId, user);
        }
    }

    /**
     * @Description
     * 发布一篇新文章首先需要创建一个新的文章ID，可以通过对一个计数器(counter)进行INCR命令来完成。
     * 然后将文章发布者的ID添加到记录文章的投票集合中，设置一个过期时间(暂存一周)
     * 在存储文章的基本信息，并将文章的初始评分和发布时间添加到相应集合
     * @Author wdq
     * @Params [user, title, link]
     * @Return java.lang.String
     * @Create 2019-09-25 17:07
     */
    public String postArticle(String user, String title, String link) {
        // 生成一个新的文章ID
        String articleId = String.valueOf(jedis.incr("article:"));
        // 已投票集合key
        String voted = "voted:" + articleId;
        // 把发布文章的用户添加到已投票集合
        jedis.sadd(voted, user);
        // 投票集合设置一周的过期时间
        jedis.expire(voted, new Long(ONE_WEEK_IN_SECONDS).intValue());

        // 再存储文章的基本信息
        String article = "article:" + articleId;
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("link", link);
        map.put("poster", user);
        map.put("time", new Long(new Date().getTime()).toString());
        map.put("votes", "1");
        jedis.hmset(article, map);

        // 将文章添加到根据发布时间和根据评分的有序集合
        jedis.zadd("time:", new Long(new Date().getTime()).doubleValue(), article);
        jedis.zadd("score:", new Long(new Date().getTime()).doubleValue() + VOTE_SCORE, article);

        return articleId;
    }

    /**
     * @Description
     * 取出评分最高和最新发布的文章
     * 首先，使用ZREVRANGE命令取出多个文章ID，然后对每个文章的ID执行一次HGETALL命令来取出文章的详细信息。
     * @Author wdq
     * @Params [page, key]
     * @Return java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     * @Create 2019-09-25 17:26
     */
    private static final int ARTICLES_PER_PAGE = 25;
    public List<Map<String, String>> getArticles(int page, String key) {
        List<Map<String, String>> list = new ArrayList<>();
        int start = (page - 1) * ARTICLES_PER_PAGE;
        int end = start + ARTICLES_PER_PAGE - 1;

        Set<String> scoreSet = jedis.zrevrange(key, start, end);
        for (String scoreSetKey : scoreSet) {
            Map<String, String> map = jedis.hgetAll(scoreSetKey);
            list.add(map);
        }
        return list;
    }

    public List<Map<String,String>> getArticles(int page) {
        return getArticles(page, "score:");
    }

    /**
     * @Description 对文章进行分组
     * 群组功能有两部分组成，一个部分记录文章所属组，另一部分取出群组里面的文章
     * @Author wdq
     * @Params [article, toAdd, toRemove]
     * @Return void
     * @Create 2019-09-25 17:45
     */
    public void addRemoveGroups(String articleId, String[] toAdd) {
        // 文章标识
        String article = "article:" + articleId;
        // 将文章添加到所属组
        for (String group : toAdd) {
            jedis.sadd("group:" + group, article);
        }
        /*
        // 从群组中移除文章
        for (String group : toRemove) {
            jedis.srem("group:" + group, article);
        }
        */
    }

    /**
     * @Description 获取一整页文章
     * @Author wdq
     * @Params []
     * @Return void
     * @Create 2019-09-25 17:52
     */
    public List<Map<String,String>> getGroupArticles(String group, int page) {
        return getGroupArticles(group, page, "score:");
    }

    public List<Map<String,String>> getGroupArticles(String group, int page, String order) {
        String key = order + group;
        if (!jedis.exists(key)) {
            ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
            jedis.zinterstore(key, params, "group:" + group, order);
            jedis.expire(key, 60);
        }
        return getArticles(page, key);
    }

    private void printArticles(List<Map<String,String>> articles){
        for (Map<String,String> article : articles){
            System.out.println("  id: " + article.get("id"));
            for (Map.Entry<String,String> entry : article.entrySet()){
                if (entry.getKey().equals("id")){
                    continue;
                }
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public void run() {

        // 发布文章
        // 随机用户发送文章
        String articleId = postArticle("username", "A title", "http://www.google.com");
        System.out.println("We posted a new article with id: " + articleId);
        System.out.println("Its HASH looks like:");
        Map<String,String> articleData = jedis.hgetAll("article:" + articleId);
        for (Map.Entry<String,String> entry : articleData.entrySet()){
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("============");

        // 投票
        articleVote("other_user", "article:" + articleId);
        String votes = jedis.hget("article:" + articleId, "votes");
        System.out.println("We voted for the article, it now has votes: " + votes);
        assert Integer.parseInt(votes) > 1;

        System.out.println("The currently highest-scoring articles are:");
        List<Map<String,String>> articles = getArticles(1);
        printArticles(articles);
        assert articles.size() >= 1;

        addRemoveGroups(articleId, new String[]{"new-group"});
        System.out.println("We added the article to a new group, other articles include:");
        articles = getGroupArticles("new-group", 1);
        printArticles(articles);
        assert articles.size() >= 1;
    }

    public static void main(String[] args) {
        new VoteDemo().run();
    }
}
