package com.solr.demo.utils;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.List;

/**
 * 提供对solr的增删改查询的操作
 */
public class SolrUtil {
    public static SolrClient client;
    private static String url;
    static {
        url = "http://localhost:8983/solr/how2java"; //配置的solr core
        client = new HttpSolrClient.Builder(url).build();
    }

    /**
     * 添加数据到solr索引库
     * @param entities
     * @param <T>
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    public static <T> boolean batchSaveOrUpdate(List<T> entities) throws SolrServerException, IOException {

        DocumentObjectBinder binder = new DocumentObjectBinder();
        int total = entities.size();
        int count=0;
        for (T t : entities) {
            SolrInputDocument doc = binder.toSolrInputDocument(t);
            client.add(doc);
            System.out.printf("添加数据到索引中，总共要添加 %d 条记录，当前添加第%d条 %n",total,++count);
        }
        client.commit();
        return true;
    }

    /**
     * 分页查询的方法
     * @param keywords
     * @param startOfPage
     * @param numberOfPage
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    public static QueryResponse query(String keywords, int startOfPage, int numberOfPage) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.setStart(startOfPage);
        query.setRows(numberOfPage);

        query.setQuery(keywords);
        QueryResponse rsp = client.query(query);
        return rsp;
    }


}
