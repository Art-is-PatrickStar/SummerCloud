package com.wsw.summercloud.task.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.wsw.summercloud.api.data.EsData;
import com.wsw.summercloud.task.service.ElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/4 21:36
 */
@Slf4j
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public void createIndex(String indexName) throws IOException {
        elasticsearchClient.indices().create(c -> c.index(indexName));
    }

    @Override
    public GetIndexResponse getIndex(String indexName) throws IOException {
        return elasticsearchClient.indices().get(c -> c.index(indexName));
    }

    @Override
    public boolean isIndexExist(String indexName) throws IOException {
        BooleanResponse booleanResponse = elasticsearchClient.indices().exists(c -> c.index(indexName));
        return booleanResponse.value();
    }

    @Override
    public void deleteIndex(String indexName) throws IOException {
        elasticsearchClient.indices().delete(c -> c.index(indexName));
    }

    @Override
    public void createDocument(String indexName, String documentId, EsData document) throws IOException {
        elasticsearchClient.index(c -> c.index(indexName).id(documentId).document(document));
    }

    @Override
    public void batchCreateDocument(String indexName, List<EsData> documents) throws IOException {
        List<BulkOperation> bulkOperationArrayList = new ArrayList<>();
        //遍历添加到bulk中
        for (EsData esData : documents) {
            bulkOperationArrayList.add(BulkOperation.of(c -> c.index(i -> i.document(esData))));
        }
        elasticsearchClient.bulk(c -> c.index(indexName).operations(bulkOperationArrayList));
    }

    @Override
    public boolean isDocumentExist(String indexName, String documentId) throws IOException {
        BooleanResponse booleanResponse = elasticsearchClient.exists(c -> c.index(indexName).id(documentId));
        return booleanResponse.value();
    }

    @Override
    public void deleteDocument(String indexName, String documentId) throws IOException {
        elasticsearchClient.delete(c -> c.index(indexName).id(documentId));
    }

    @Override
    public void updateDocument(String indexName, String documentId, EsData document) throws IOException {
        elasticsearchClient.update(c -> c.index(indexName).id(documentId).doc(document), EsData.class);
    }

    @Override
    public EsData getDocument(String indexName, String documentId) throws IOException {
        GetResponse<EsData> getResponse = elasticsearchClient.get(c -> c.index(indexName).id(documentId), EsData.class);
        return getResponse.source();
    }

    @Override
    public List<Hit<EsData>> searchDocument(String indexName) throws IOException {
        //TODO: bug not fixed yet
        SearchResponse<EsData> searchResponse = elasticsearchClient.search(c -> c.index(indexName)
                .query(q -> q.match(t -> t.field("data").query("测试")))
                .from(0)
                .size(10)
                .sort(f -> f.field(o -> o.field("createdTime").order(SortOrder.Desc))), EsData.class);
        return searchResponse.hits().hits();
    }
}
