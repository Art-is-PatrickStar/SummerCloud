package com.wsw.summercloud.task.service;

import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.wsw.summercloud.api.data.EsData;
import com.wsw.summercloud.api.dto.EsQueryDto;

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

    /**
     * 创建文档
     *
     * @param indexName
     * @param documentId
     * @param document
     * @return void
     */
    void createDocument(String indexName, String documentId, EsData document) throws IOException;

    /**
     * 批量创建文档
     *
     * @param indexName
     * @param documents
     * @return void
     */
    void batchCreateDocument(String indexName, List<EsData> documents) throws IOException;

    /**
     * 判断文档是否存在
     *
     * @param indexName
     * @param documentId
     * @return boolean
     */
    boolean isDocumentExist(String indexName, String documentId) throws IOException;

    /**
     * 删除文档
     *
     * @param indexName
     * @param documentId
     * @return void
     */
    void deleteDocument(String indexName, String documentId) throws IOException;

    /**
     * 更新文档
     *
     * @param indexName
     * @param documentId
     * @param document
     * @return void
     */
    void updateDocument(String indexName, String documentId, EsData document) throws IOException;

    /**
     * 获取文档
     *
     * @param indexName
     * @param documentId
     * @return EsData
     */
    EsData getDocument(String indexName, String documentId) throws IOException;

    /**
     * 搜索文档
     *
     * @param esQueryDto
     * @return List<Hit<EsData>>
     */
    List<Hit<EsData>> searchDocument(EsQueryDto esQueryDto) throws IOException;
}
