package com.wsw.summercloud.task.service;

import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.wsw.summercloud.api.data.EsData;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/4 21:34
 */
public interface ElasticSearchService {
    /**
     * 创建索引
     *
     * @param indexName
     * @return void
     */
    void createIndex(String indexName) throws IOException;

    /**
     * 获取索引
     *
     * @param indexName
     * @return GetIndexResponse
     */
    GetIndexResponse getIndex(String indexName) throws IOException;

    /**
     * 判断索引是否存在
     *
     * @param indexName
     * @return boolean
     */
    boolean isIndexExist(String indexName) throws IOException;

    /**
     * 删除索引
     *
     * @param indexName
     * @return void
     */
    void deleteIndex(String indexName) throws IOException;

    void createDocument(String indexName, String documentId, EsData document) throws IOException;

    void batchCreateDocument(String indexName, List<EsData> documents) throws IOException;

    boolean isDocumentExist(String indexName, String documentId) throws IOException;

    void deleteDocument(String indexName, String documentId) throws IOException;

    void updateDocument(String indexName, String documentId, EsData document) throws IOException;

    EsData getDocument(String indexName, String documentId) throws IOException;

    List<Hit<EsData>> searchDocument(String indexName) throws IOException;
}
