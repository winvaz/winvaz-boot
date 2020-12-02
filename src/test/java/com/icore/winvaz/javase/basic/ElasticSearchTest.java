package com.icore.winvaz.javase.basic;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @Deciption ElasticSearch
 * @Author wdq
 * @Create 2019/12/12 10:22
 */
public class ElasticSearchTest {

    private RestHighLevelClient client;

    public ElasticSearchTest() {
        //获取连接
        String[] ips = {"127.0.0.1:9200"};
        HttpHost[] httpHosts = new HttpHost[ips.length];
        for (int i = 0; i < ips.length; i++) {
            httpHosts[i] = HttpHost.create(ips[i]);
        }
        RestClientBuilder builder = RestClient.builder(httpHosts);
        this.client = new RestHighLevelClient(builder);
    }

    @Test
    public void test() throws Exception {
        ElasticSearchTest elasticSearchTest = new ElasticSearchTest();
        //Map<String, Object> objectMap = elasticSearchDemo.get(1L);
        //System.out.println(objectMap);
        // List<Object> search = elasticSearchTest.search();
        // System.out.println(search);
        elasticSearchTest.client.close();
    }

    /**
     * @param id
     * @throws
     * @Description 通过ID获取数据
     * @author wdq
     * @create 2019/12/12 10:33
     * @Return T
     */
    public Map<String, Object> get(Long id) {
        try {
            GetRequest request = new GetRequest("twitter", String.valueOf(id));
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            Map<String, Object> source = response.getSource();
            return source;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    public List<Object> search() throws Exception {
        SearchRequest request = new SearchRequest();
        request.indices("twitter");
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits searchHits = response.getHits();
        TotalHits total = searchHits.getTotalHits();
        SearchHit[] searchHitArray = searchHits.getHits();
        List<Object> data = new ArrayList<>();
        for (SearchHit hit : searchHitArray) {
            Map<String, Object> source = hit.getSourceAsMap();
            data.add(source);
        }
        return data;
    }
    */
}
