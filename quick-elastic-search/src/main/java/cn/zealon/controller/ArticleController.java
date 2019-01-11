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
import java.util.Random;

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
    public Object save(Article article){
        article.setId(new Random().nextInt(999999));
        return reposiory.save(article);
    }

    @ResponseBody
    @RequestMapping("/get")
    public Object get(String key,String value){
        QueryBuilder queryBuilder = QueryBuilders.matchQuery(key,value);

        Iterable<Article> list = reposiory.search(queryBuilder);

        return list.iterator();
    }
}
