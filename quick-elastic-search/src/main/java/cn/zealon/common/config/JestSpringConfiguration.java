package cn.zealon.common.config;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

/**
 * ES Jest配置
 * @author zealon
 */
@Configuration
public class JestSpringConfiguration {
    private static Logger logger = LoggerFactory.getLogger(JestSpringConfiguration.class);
    @Value("${es.servers}")
    private String es_servers;

    @Bean
    public JestClient jestClient() {
        logger.info("es_servers:"+es_servers);
        String[] sers = es_servers.split(",");
        HttpClientConfig httpClientConfig =
                new HttpClientConfig.Builder(Arrays.asList(sers))
                        .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                        .multiThreaded(true)
                        .connTimeout(5000)
                        .readTimeout(10000)
                        .maxTotalConnection(100)
                        .defaultMaxTotalConnectionPerRoute(50)
                        .build();
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(httpClientConfig);
        return factory.getObject();
    }
}
