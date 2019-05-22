package cn.zealon.controller;

import cn.zealon.common.Constant;
import cn.zealon.domain.Book;
import cn.zealon.repository.DocumentRepository;
import cn.zealon.repository.SearchRepository;
import io.searchbox.client.JestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询
 * @author: tangyl
 * @since: 2019/5/22
 * @version: 1.0
 */
@RequestMapping("basic")
@Controller
public class BasicSearchController {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private SearchRepository searchRepository;

    /**
     * 基础查询
     */
    @RequestMapping("/search-page")
    public String basicSearch(){
        return "search/basic-search";
    }

    /**
     * 检索文档
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/document/{id}")
    public String getDocumentById(@PathVariable("id") String id){
        JestResult document = documentRepository.getDocument(Book.class, Constant.DEFAULT_INDEX, Constant.DEFAULT_TYPE, id);
        return document.getJsonString();
    }

    /**
     * 图书名称匹配查询
     * @return
     */
    @ResponseBody
    @RequestMapping("/search")
    public String search(String bookName,String searchMode,Integer highlight){
        StringBuffer json = new StringBuffer();
        json.append("{\n" +
                "    \"query\" : {\n" +
                "        \""+searchMode+"\" : {\n" +
                "            \"bookName\" : \""+bookName+"\"\n" +
                "        }\n" +
                "    }");

        // 高亮
        if (highlight!=null && highlight==1){
            json.append(",\"highlight\": {\n" +
                    "        \"fields\" : {\n" +
                    "            \"bookName\" : {}\n" +
                    "        }\n" +
                    "    }");
        }
        json.append("}");

        JestResult document = searchRepository.search(json.toString());
        return document.getJsonString();
    }

    /**
     * 查询分析
     * @return
     */
    @ResponseBody
    @RequestMapping("/search/aggs")
    public String searchAggs(String fieldName){
        StringBuffer json = new StringBuffer();
        json.append("{\n" +
                "  \"aggs\": {\n" +
                "    \"all_interests\": {\n" +
                "      \"terms\": { \"field\": \""+fieldName+"\" }\n" +
                "    }\n" +
                "  }\n" +
                "}");
        JestResult document = searchRepository.search(json.toString());
        return document.getJsonString();
    }
}
