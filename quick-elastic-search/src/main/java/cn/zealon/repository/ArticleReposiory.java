package cn.zealon.repository;

import cn.zealon.domain.Article;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
//@Document(indexName = "blog",type = "article",shards = 1,replicas = 0)
public interface ArticleReposiory extends ElasticsearchRepository<Article,Long> {

}
