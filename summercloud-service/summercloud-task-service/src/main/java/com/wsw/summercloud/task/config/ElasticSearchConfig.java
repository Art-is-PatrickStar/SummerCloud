package com.wsw.summercloud.task.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/4 21:31
 */
@Configuration
public class ElasticSearchConfig {
    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient restClient = RestClient.builder(new HttpHost("192.168.223.100", 9200, "http")).build();
        RestClientTransport restClientTransport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(restClientTransport);
    }
}
