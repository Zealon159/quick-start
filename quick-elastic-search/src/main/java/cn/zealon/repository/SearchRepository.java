package cn.zealon.repository;

import cn.zealon.common.Constant;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * 查询API
 * @author: tangyl
 * @since: 2019/5/22
 * @version: 1.0
 */
@Repository
public class SearchRepository {

    @Autowired
    private JestClient jestClient;

    /**
     * 查询
     * @param query
     * @return
     */
    public SearchResult search(String query){
        SearchResult result = null ;
        Search search = new Search.Builder(query).addIndex(Constant.DEFAULT_INDEX).build();
        try {
            result = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
