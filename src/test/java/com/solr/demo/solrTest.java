package com.solr.demo;

import com.solr.demo.utils.SolrUtil;
import com.solr.demo.utils.ProductUtil;
import com.solr.demo.vo.Product;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public class solrTest {
    public static void main(String[] args) throws SolrServerException, IOException {
        //1.模拟从数据库读取数据得到list对象
        List<Product> products = ProductUtil.file2list("140k_products.txt");
        //2.利用solrJ 将数据导入到solr索引库
        SolrUtil.batchSaveOrUpdate(products);
    }
}
