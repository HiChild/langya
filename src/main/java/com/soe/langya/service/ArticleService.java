package com.soe.langya.service;

import com.soe.langya.mapper.ArticleMapper;
import com.soe.langya.mapper.TagMapper;
import com.soe.langya.pojo.Article;
import com.soe.langya.pojo.Tag;
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
    private String myDate;

    /**
     * add necessary information into article and put article into database
     * @param article the article object from front end
     * @return 0 for success,-1 for fail
     */
    public Integer addOneArticle(Article article) {
        //添加信息
        addInfo(article);
        //设置状态
        /**
         * 0表示正常   0 for published
         * 1表示审核中 1 for verify
         * -1表示不可用 -1 for freeze
         */
        article.setStatus(0);
        Integer res = articleMapper.save(article);
        return (res == null || res < 1 )? -1 : 0;
    }

    /**
     * delete an article from database by art_id
     * @param article the article object from front end
     * @return 0 for success, -1 for fail
     */
    public Integer deleteOneArticle(Article article) {
        Integer res = articleMapper.delete(article.getId());
        return res <= 0 ? -1 : 0;
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
     * find A articles from database which are belong to user who user'id is provide
     * @param userId userId you should provide
     * @return A list of Article  which is belong to user who user'id is provide
     */
    public List<Article> findAllById(Integer userId) {
        List<Article> allById = articleMapper.findAllById(userId);
        return allById;
    }

    /**
     * find articles from database by key-word
     * @param msg the msg is key-word which you should provide
     * @return A List of Article which include key-word
     */
    public List<Article> findByKey(String msg) {
        String key = "%" + msg + "%";
        return articleMapper.findByKeyFromTitle(key);
    }

    /**
     * find an article from database by artId
     * @param article article you should provide form front end
     * @return an article you want
     */
    public Article findOneById(Article article) {
        Article theOne = articleMapper.findOneById(article.getId());
        getTags(theOne);
        return theOne;
    }

    /**
     * find all of articles
     * @return A List of Article which include all of articles
     */
    public List<Article> findAll() {
        return articleMapper.findAll();
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
            articles.add(oneArticle);
        }
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
     * @param article the target which you should add tags
     */
    public void addTags (Article article) {
        System.out.println(article.toString());
        Article theOne = articleMapper.findByDateAndUserId(myDate, article.getUserId());
        String[] tagNames = article.getTagNames();
        List<Tag> tags = new ArrayList<>();
        for (String tagName : tagNames) {
            Tag tag = new Tag();
            tag.setArtId(theOne.getId());
            tag.setTagName(tagName);
            tagMapper.save(tag);
            tags.add(tag);
        }
        article.setTags(tags);
    }

    /**
     * add necessary information into article
     * @param article
     */
    private void addInfo(Article article) {
        System.out.println("article"+article);
        //设置时间和简要内容
        //设置时间戳
        Long date = System.currentTimeMillis();
        myDate = String.valueOf(date);
        System.out.println("myDate"+myDate);
        article.setEditTime(myDate);
        //设置简要内容
        String stripHtml =stripHtml(article.getContext());
        String mainContext = stripHtml.substring(0, Math.min(stripHtml.length(), 50));
        article.setMainContext(mainContext);
    }

    /**
     * set Tags in to an article, when an article is going to be display in front end.
     * so you should take tags object by article's Id and put TagNames into article.
     * @param article
     * @return A List of Tag by article's Id. the the result of return is just for test.
     */
    public List<Tag> getTags(Article article) {
        List<Tag> tags = tagMapper.findByArtId(article.getId());
        List<String> tagNames = new ArrayList<>();
        for (Tag tag : tags) {
            String tagName = tag.getTagName();
            tagNames.add(tagName);
        }
        String[] objects = tagNames.toArray(new String[tagNames.size()]);
        article.setTags(tags);
        article.setTagNames(objects);
        return tags;
    }
}
