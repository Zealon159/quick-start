package cn.zealon.repository;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Cat;
import io.searchbox.core.CatResult;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.List;

/**
 * ES 索引服务
 * @author: tangyl
 * @since: 2019/5/7
 * @version: 1.0
 */
@Repository
public class IndexesRepository {

    @Autowired
    private JestClient jestClient;

    /**
     * 创建索引
     * @param index
     * @param type
     * @return
     */
    public <T> JestResult createIndex(T o,String index,String type){
        JestResult result = null ;
        Index createIndex = new Index.Builder(o)
                .index(index)
                .type(type)
                .build();
        try {
            result = jestClient.execute(createIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除索引
     * @param index
     * @return
     */
    public JestResult deleteIndex(String index){
        JestResult result = null;
        DeleteIndex deleteIndex = new DeleteIndex.Builder(index).build();
        try {
            result = jestClient.execute(deleteIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取所有索引
     * @return
     */
    public CatResult catIndex(){
        CatResult result = null;
        Cat cat = new Cat.IndicesBuilder().build();
        try {
            result = jestClient.execute(cat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
