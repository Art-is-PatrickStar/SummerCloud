//package com.wsw.summercloud.task.service;
//
//import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
//import co.elastic.clients.elasticsearch.core.DeleteRequest;
//import co.elastic.clients.elasticsearch.core.SearchRequest;
//import co.elastic.clients.elasticsearch.core.SearchResponse;
//import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
//import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
//import co.elastic.clients.elasticsearch.indices.GetIndexRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.client.RequestOptions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
///**
// * @Author wangsongwen
// * @Date 2025/8/25 13:03
// * @Description:
// */
//@Slf4j
//@RestController
//@RequestMapping("/datamap/es")
//public class EsTests {
//
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;
//
//    @PostMapping("/indexExists")
//    public Boolean indexExists(@RequestParam("indexName") String indexName) throws IOException {
//        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
//        return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
//    }
//
//    @PostMapping("/deleteIndex")
//    public Boolean deleteIndex(@RequestParam("indexName") String indexName) throws IOException {
//        if (!indexExists(indexName)) {
//            return false;
//        }
//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
//        restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
//        return true;
//    }
//
//    @PostMapping("/createIndex")
//    public Boolean createIndex(@RequestParam("indexName") String indexName) throws IOException {
//        if (indexExists(indexName)) {
//            return false;
//        }
//
//        // 创建索引
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
//
//        // 设置索引的映射
//        // 定义映射的 JSON 格式字符串
//        String mappings = "{\n" +
//                "    \"properties\": {\n" +
//                "        \"dataSetId\": {\n" +
//                "            \"type\": \"keyword\"\n" +
//                "        },\n" +
//                "        \"dataSetName\": {\n" +
//                "            \"type\": \"text\",\n" +
//                "            \"analyzer\": \"ik_max_word\"\n" +
//                "        },\n" +
//                "        \"dataSetDesc\": {\n" +
//                "            \"type\": \"text\",\n" +
//                "            \"analyzer\": \"ik_max_word\"\n" +
//                "        },\n" +
//                "        \"dataSetGmtCreate\": {\n" +
//                "            \"type\": \"date\",\n" +
//                "            \"format\": \"yyyy-MM-dd HH:mm:ss\"\n" +
//                "        },\n" +
//                "        \"dataSetGmtModified\": {\n" +
//                "            \"type\": \"date\",\n" +
//                "            \"format\": \"yyyy-MM-dd HH:mm:ss\"\n" +
//                "        },\n" +
//                "        \"attachment\": {\n" +
//                "            \"properties\": {\n" +
//                "                \"content\": {\n" +
//                "                    \"type\": \"text\",\n" +
//                "                    \"analyzer\": \"ik_max_word\"\n" +
//                "                }\n" +
//                "            }\n" +
//                "        }\n" +
//                "    }\n" +
//                "}";
//
//        createIndexRequest.mapping(mappings, XContentType.JSON);
//
//        restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//        return true;
//    }
//
//    @PostMapping("/addDatasetToIndex")
//    public Boolean addDatasetToIndex(@RequestParam("indexName") String indexName,
//                                     @RequestParam("indexType") String indexType,
//                                     @RequestParam("dataSetId") String dataSetId,
//                                     @RequestParam("dataSetName") String dataSetName,
//                                     @RequestParam("dataSetDesc") String dataSetDesc,
//                                     @RequestParam("file") MultipartFile file) throws IOException {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("dataSetId", dataSetId);
//        jsonObject.put("dataSetName", dataSetName);
//        jsonObject.put("dataSetDesc", dataSetDesc);
//        jsonObject.put("dataSetGmtCreate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        jsonObject.put("dataSetGmtModified", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//
//        byte[] bytes = file.getBytes();
//        String base64 = Base64.getEncoder().encodeToString(bytes);
//        jsonObject.put("content", base64);
//
//        // 会新增，还可以更新覆盖
//        IndexRequest indexRequest = new IndexRequest(indexName, indexType, dataSetId)
//                .source(jsonObject.toJSONString(), XContentType.JSON)
//                .setPipeline("attachment")
//                .timeout(TimeValue.timeValueMinutes(10));
//        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//        return true;
//    }
//
//    @PostMapping("/removeDatasetFromIndex")
//    public Boolean removeDatasetFromIndex(@RequestParam("indexName") String indexName,
//                                          @RequestParam("indexType") String indexType,
//                                          @RequestParam("dataSetId") String dataSetId) throws IOException {
//        DeleteRequest deleteRequest = new DeleteRequest(indexName, indexType, dataSetId);
//        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
//        return true;
//    }
//
//    @PostMapping("/search")
//    public List<JSONObject> search(@RequestParam("indexName") String indexName,
//                                   @RequestParam("keyWord") String keyWord) throws IOException {
//        // 创建搜索请求
//        SearchRequest searchRequest = new SearchRequest(indexName);
//
//        // 创建搜索源构建器
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        // 使用 multi-match 查询
//        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(keyWord,
//                "dataSetId",
//                "dataSetName",
//                "dataSetDesc",
//                "attachment.content"));
//
//        searchRequest.source(searchSourceBuilder);
//
//        // 执行搜索请求
//        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        // 处理搜索结果
//        List<JSONObject> jsonObjectList = new ArrayList<>();
//
//        for (SearchHit hit : searchResponse.getHits().getHits()) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("dataSetId", hit.getSourceAsMap().get("dataSetId"));
//            jsonObject.put("dataSetName", hit.getSourceAsMap().get("dataSetName"));
//            jsonObject.put("dataSetDesc", hit.getSourceAsMap().get("dataSetDesc"));
//            jsonObject.put("attachment", hit.getSourceAsMap().get("attachment"));
//            jsonObjectList.add(jsonObject);
//        }
//
//        return jsonObjectList;
//    }
//
//}
//
//
