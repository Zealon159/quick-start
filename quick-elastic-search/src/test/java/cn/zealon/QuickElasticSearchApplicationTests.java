package cn.zealon;

import cn.zealon.domain.Employee;
import cn.zealon.repository.EmployeeRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuickElasticSearchApplication.class)
public class QuickElasticSearchApplicationTests {

    @Autowired
    private EmployeeRepository repository;

    // 匹配查询（默认方式）
    @Test
    public void contextLoads() {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("last_name","smith");

        Iterable<Employee> iterable = repository.search(queryBuilder);
        print(iterable);
    }

    // 精确查询
    @Test()
    public void termQuery(){
        TermQueryBuilder termQuery = QueryBuilders.termQuery("age",32);
        //QueryBuilders.
        //QueryBuilders.
        Iterable<Employee> iterable  = repository.search(termQuery);
        print(iterable);
    }

    public void print(Iterable<Employee> iterable){
        Iterator<Employee> iterator = iterable.iterator();
        while (iterator.hasNext()){
            Employee employee = iterator.next();
            System.out.println(employee.toString());
        }
    }
}

