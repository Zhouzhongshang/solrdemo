package com.solr.demo.utils;

import com.solr.demo.vo.Product;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductUtil {
    public static void main(String[] args) throws IOException, InterruptedException, AWTException {

        String fileName = "140k_products.txt";

        List<Product> products = file2list(fileName);

        System.out.println(products.size());

    }

    public static List<Product> file2list(String fileName) throws IOException {
        String path = "E:\\zzs\\solr\\140k_products\\"+fileName;
        File f = new File(path);

        //读取数据
        List<String> lines = FileUtils.readLines(f,"UTF-8");
        List<Product> products = new ArrayList<>();
        for (String line : lines) {
            Product p = line2product(line);
            products.add(p);
        }
        return products;
    }

    //格式处理
    private static Product line2product(String line) {
        Product p = new Product();
        String[] fields = line.split(",");
        p.setId(Integer.parseInt(fields[0]));
        p.setName(fields[1]);
        p.setCategory(fields[2]);
        p.setPrice(Float.parseFloat(fields[3]));
        p.setPlace(fields[4]);
        p.setCode(fields[5]);
        return p;
    }
}
