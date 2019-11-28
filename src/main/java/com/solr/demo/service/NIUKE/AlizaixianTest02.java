package com.solr.demo.service.NIUKE;

public class AlizaixianTest02 {
    /**
     * 线程安全的单例模式
     */
    private static final AlizaixianTest02 alizaixianTest02 = new AlizaixianTest02();

    private AlizaixianTest02 (){

    }

    public static AlizaixianTest02 getAlizaixianTest02(){
        return alizaixianTest02;
    }
}
