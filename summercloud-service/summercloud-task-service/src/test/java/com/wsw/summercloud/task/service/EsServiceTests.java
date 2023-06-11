package com.wsw.summercloud.task.service;

import cn.hutool.core.date.DateUtil;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.wsw.summercloud.api.data.EsData;
import com.wsw.summercloud.api.dto.EsQueryDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/4 21:44
 */
@Slf4j
@SpringBootTest
public class EsServiceTests {
    @Autowired
    private ElasticSearchService elasticSearchService;

    @Test
    void createIndex() throws IOException {
        elasticSearchService.createIndex("test1");
    }

    @Test
    void getIndex() throws IOException {
        GetIndexResponse getIndexResponse = elasticSearchService.getIndex("test1");
        log.info("getIndexResponse: {}", getIndexResponse);
    }

    @Test
    void isIndexExist() throws IOException {
        boolean result = elasticSearchService.isIndexExist("test1");
        log.info("isIndexExist: {}", result);
    }

    @Test
    void deleteIndex() throws IOException {
        elasticSearchService.deleteIndex("test1");
    }

    @Test
    void createDocument() throws IOException {
        EsData esData = new EsData();
        esData.setDataId("1");
        esData.setData("wsw测试1");
        esData.setCreatedTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        esData.setUpdatedTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        elasticSearchService.createDocument("test1", esData.getDataId(), esData);
    }

    @Test
    void isDocumentExist() throws IOException {
        boolean result = elasticSearchService.isDocumentExist("test1", "1");
        log.info("isDocumentExist: {}", result);
    }

    @Test
    void deleteDocument() throws IOException {
        elasticSearchService.deleteDocument("test1", "1");
    }

    @Test
    void getDocument() throws IOException {
        EsData esData = elasticSearchService.getDocument("test1", "1");
        log.info("getDocument: {}", esData);
    }

    @Test
    void updateDocument() throws IOException {
        EsData esData = new EsData();
        esData.setDataId("1");
        esData.setData("wsw测试1");
        esData.setCreatedTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        esData.setUpdatedTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        elasticSearchService.updateDocument("test1", String.valueOf(esData.getDataId()), esData);
    }

    @Test
    void batchCreateDocument() throws IOException {
        List<EsData> esDataList = new ArrayList<>();
        esDataList.add(new EsData("1", "wsw测试1", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"), DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")));
        esDataList.add(new EsData("2", "wsw测试2", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"), DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")));
        esDataList.add(new EsData("3", "wsw测试3", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"), DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")));
        esDataList.add(new EsData("4", "wsw测试4", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"), DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")));
        esDataList.add(new EsData("5", "wsw测试5", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"), DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS")));
        elasticSearchService.batchCreateDocument("test1", esDataList);
    }

    @Test
    void searchDocument() throws IOException {
        EsQueryDto esQueryDto = new EsQueryDto();
        esQueryDto.setCurrentPage(0);
        esQueryDto.setPageSize(20);
        esQueryDto.setIndexName("test1");
        esQueryDto.setQueryField("data");
        esQueryDto.setQueryValue("测试");
        esQueryDto.setSortField("createdTime.keyword");
        List<Hit<EsData>> hits = elasticSearchService.searchDocument(esQueryDto);
        for (Hit<EsData> hit : hits) {
            log.info("searchDocument: {}", hit.source());
        }
    }
}
