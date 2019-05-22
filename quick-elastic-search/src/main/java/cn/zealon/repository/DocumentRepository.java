package cn.zealon.repository;

import cn.zealon.domain.Book;
import io.searchbox.action.BulkableAction;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: tangyl
 * @since: 2019/5/7
 * @version: 1.0
 */
@Repository
public class DocumentRepository {

    @Autowired
    private JestClient jestClient;

    public JestResult createDocument(String index, String type, Book book){
        BulkResult result = null;
        Bulk bulk = new Bulk.Builder()
                .defaultIndex(index)
                .defaultType(type)
                .addAction(Arrays.asList(
                        new Index.Builder(book).build()
                ))
                .build();
        try {
            result = jestClient.execute(bulk);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public JestResult deleteDocumentByQuery(String index, String type, String params) {
        DeleteByQuery db = new DeleteByQuery.Builder(params)
                .addIndex(index)
                .addType(type)
                .build();

        JestResult result = null ;
        try {
            result = jestClient.execute(db);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public JestResult updateDocument(String script,String index,String type,String id){
        Update update = new Update.Builder(script).index(index).type(type).id(id).build();
        JestResult result = null ;
        try {
            result = jestClient.execute(update);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    public <T> JestResult getDocument(T object , String index, String type, String id) {
        Get get = new Get.Builder(index, id).type(type).build();
        JestResult result = null ;
        try {
            result = jestClient.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public JestResult getDocuments(String index, String type) {

        Get get = new Get.Builder(index,"").type(type).build();
        JestResult result = null ;
        try {
            result = jestClient.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
