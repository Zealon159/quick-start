package cn.zealon.controller;

import cn.zealon.domain.Article;
import cn.zealon.repository.ArticleReposiory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

/**
 * @Author: zealon
 * @Version: 1.0
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleReposiory reposiory;

    @ResponseBody
    @RequestMapping("/save")
    public Object save(){
        Article article = new Article();
        article.setId(2);
        article.setTitle("hhhh");
        article.setContext("你好，不懒");
        return reposiory.save(article);
    }

    @ResponseBody
    @RequestMapping("/get")
    public Object get(){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("context","你好");

        Iterable<Article> list = reposiory.search(queryBuilder);
        return list.iterator();
    }
}
