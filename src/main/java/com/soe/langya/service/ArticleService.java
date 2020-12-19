package com.soe.langya.service;

import com.github.pagehelper.PageHelper;
import com.soe.langya.mapper.*;
import com.soe.langya.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired(required = false)
    private ArticleMapper articleMapper;
    @Autowired(required = false)
    private TagMapper tagMapper;
    @Autowired(required = false)
    private CommentMapper commentMapper;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private LikeCountMapper likeCountMapper;
    @Autowired(required = false)
    private LikeMapper likeMapper;
    @Autowired(required = false)
    private LikeService likeService;
    @Autowired(required = false)
    private FavoriteMapper favoriteMapper;
    @Autowired(required = false)
    private CommentService commentService;


    private String myDate;

    /**
     * add necessary information into article and put article into database
     * @param article the article object from front end
     * @return 0 for success,-1 for fail
     */
    public Integer addOneArticle(Article article) {
        //添加信息
        addInfo(article);
        Integer res = articleMapper.save(article);
        return (res == null || res < 1 )? -1 : 0;
    }

    /**
     * delete an article from database by art_id
     * @param article the article object from front end
     * @return 0 for success, -1 for fail
     */
    public Integer deleteOneArticle(Article article) {
        List<Tag> tags = tagMapper.findByArtId(article.getId());
        for (Tag tag : tags) {
            tagMapper.delete(tag.getId());
        }
        Integer res1 = likeCountMapper.delete(article.getId());
        Integer res = articleMapper.delete(article.getId());
        return res <= 0 || res1 <= 0 ? -1 : 0;
    }

    /**
     * update an article from database
     * @param article the article object from front end
     * @return 0 for success, -1 for fail
     */
    public Integer update(Article article) {
        addInfo(article);
        Integer res = articleMapper.update(article);
        return res <= 0  ? -1  : 0;
    }

    /**
     * find A articles from database which are belong to user whose user'id is provide
     * @param userId userId you should provide
     * @return A list of Article which belongs to user whose user'id is provide
     */
    public List<Article> findByUserId(Integer userId) {
        List<Article> articles = articleMapper.findAllById(userId);
        for (Article article : articles) {
            article.setLikeCount(getCounts(article));
        }
        addFaceUrl(articles);
        return articles;
    }

    /**
     * find articles from database by key-word
     * @param msg the msg is key-word which you should provide
     * @return A List of Article which includes key-word
     */
    public List<Article> findByKey(String msg) {
        String key = "%" + msg + "%";
        List<Article> articles = articleMapper.findByKeyFromTitle(key);
        addFaceUrl(articles);
        return articles;
    }

//    /**舍弃
//     * find an article from database by artId
//     * @param article article you should provide from front end
//     * @return an article you want
//     */
//    public Article findOneById(Article article) {
//        Article theOne = articleMapper.findOneById(article.getId());
//        getTags(theOne);
//        getComments(theOne);
//        theOne.setLikeCount(getCounts(theOne));
//        setLikeAndFavoriteStatus(theOne);
//        return theOne;
//    }

    /**
     * find an article from database by artId
     * @param article article you should provide from front end
     * @param userId the user's id who are using web set
     * @return an article you want
     */
    public Article findOneById(Article article,Integer userId) {
        Article theOne = articleMapper.findOneById(article.getId());
        getTags(theOne);
        getComments(theOne);
        theOne.setLikeCount(getCounts(theOne));
        setLikeAndFavoriteStatus(theOne,userId);
        addFaceUrl(theOne);
        return theOne;
    }
    /**
     * find an article from database by artId
     * @param artId article's id you should provide from front end
     * @return an article you want
     */
    public Article findOneById(Integer artId) {
        Article theOne = articleMapper.findOneById(artId);
        if (theOne != null) {
            getTags(theOne);
            getComments(theOne);
            theOne.setLikeCount(getCounts(theOne));
            addFaceUrl(theOne);
        }
        return theOne;
    }

    /**
     * find all of articles
     * @return A List of Article which include all of articles
     */
    public List<Article> findAll() {
        List<Article> articles = articleMapper.findAll();
        for (Article article : articles) {
            article.setLikeCount(getCounts(article));
        }
        addFaceUrl(articles);
        return articles;
    }

    /**
     * find articles by tag
     * @param tagName tagName you should provide
     * @return A List of Article which article'Tag is you provide
     */
    public List<Article> findByTag (String tagName) {
        List<Tag> tags = tagMapper.findByName(tagName);

        List<Article> articles = new ArrayList<>();
        for (Tag t : tags) {
            Article oneArticle = articleMapper.findOneById(t.getArtId());
            if (oneArticle != null) {
                articles.add(oneArticle);
            }
        }
        for (Article article : articles) {
            article.setLikeCount(getCounts(article));
        }
        addFaceUrl(articles);
        return articles;
    }





    /**
     * stripHtml for mainContext
     * @param context context you should provide
     * @return A String object which has striped
     */
    private String stripHtml (String context) {
        context = context.replaceAll("<p .*?>", "");
        context = context.replaceAll("<br\\s*/?>", "");
        context = context.replaceAll("\\<.*?>", "");
        return context;
    }

    /**
     * to add Tags into a article when article is going to store into database
     * to add Tags into database
     * @param article the article which you want to add info of tags
     */
    public void addTags (Article article) {
        Article theOne = articleMapper.findByDateAndUserId(myDate, article.getUserId());
        String[] tagNames = article.getTagNames();
        List<Tag> tags = new ArrayList<>();
        if (tagNames != null) {
            for (String tagName : tagNames) {
                Tag tag = new Tag();
                tag.setArtId(theOne.getId());
                tag.setTagName(tagName);
                tagMapper.save(tag);
                tags.add(tag);
            }
        }
        article.setTags(tags);
    }

    /**
     * add necessary information into article
     * @param article
     */
    private void addInfo(Article article) {
        //设置时间和简要内容
        //设置状态
        /**
         * 1表示正常   0 for published
         * 0表示 审核中 1 for verify
         * -1   不通过
         *
         */
        article.setStatus(0);
        //设置时间戳
        Long date = System.currentTimeMillis();
        myDate = String.valueOf(date);
        article.setEditTime(myDate);
        //设置简要内容
        String stripHtml =stripHtml(article.getContext());
        String mainContext = stripHtml.substring(0, Math.min(stripHtml.length(), 200));
        article.setMainContext(mainContext);
    }

    /**
     * let the article get their tags, when an article is going to be display in front end.
     * so you should take tags object by article's Id and put TagNames into article.
     * @param article
     * @return A List of Tag which has article's Id. the the result of return is just for test.
     */
    private List<Tag> getTags(Article article) {
        List<Tag> tags = tagMapper.findByArtId(article.getId());
        if (tags != null) {
            List<String> tagNames = new ArrayList<>();
            for (Tag tag : tags) {
                String tagName = tag.getTagName();
                tagNames.add(tagName);
            }
            String[] objects = tagNames.toArray(new String[tagNames.size()]);
            article.setTags(tags);
            article.setTagNames(objects);
        }
        return tags;
    }

    /**
     * let article
     * @param article the article you should provide
     */
    private void getComments(Article article) {
        List<Comment> comments = commentService.findByArtId(article.getId());
        addUserNameIntoComment(comments);
        article.setComments(comments);
    }





    /**
     * set Username into Comment
     * @param comments the comments witch you should add UserName in it
     */
    public void addUserNameIntoComment(List<Comment> comments) {
        for (Comment comment : comments) {
            Integer userId = comment.getUserId();
            User user = userMapper.findById(userId);
            comment.setUserName(user.getUser_name());
        }
    }

    private Integer getCounts(Article article) {
        return likeCountMapper.findByArtId(article.getId()).getLikeCounts();
    }


    public void insertLikeCountInDatabase(Article article) {
        Article theOne = articleMapper.findByDateAndUserId(article.getEditTime(), article.getUserId());
        if (theOne!=null) {
            likeService.initLikeCount(theOne);
        }
    }

    private void setLikeAndFavoriteStatus(Article article,Integer userId){
        //设置点赞的状态
        Like like = likeMapper.findByUserIdAndArtId(userId, article.getId());
        if (like == null) {
            article.setIsLike(0);
        } else {
            article.setIsLike(1);
        }
        //设置收藏的状态
        Favorite favorite =favoriteMapper.findByUserIdAndArtId(userId,article.getId());
        if (favorite == null) {
            article.setIsFavorite(0);
        } else {
            article.setIsFavorite(1);
        }
    }

    private void addFaceUrl(Article article){
        Integer userId = article.getUserId();
        User user = userMapper.findById(userId);
        article.setFaceUrl(user.getFaceUrl());
    }

    private void addFaceUrl(List<Article> articles){
        for (Article article : articles) {
            if (article != null) {
                addFaceUrl(article);
            }
        }
    }

    public  List<Article> rank(){
        List<LikeCount> rank = likeCountMapper.rank();
        List<Article> articles = new ArrayList<>();
        for (LikeCount likeCount : rank) {
            Article article = findOneById(likeCount.getArtId());
            articles.add(article);
        }
        return articles;
    }
}
