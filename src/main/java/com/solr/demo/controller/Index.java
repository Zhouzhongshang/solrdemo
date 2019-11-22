package com.solr.demo.controller;

import com.solr.demo.vo.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Index {

    /**
     * 死锁产生的原因
     *  死锁解决的办法是将对象锁的顺序变为一致
     * @return
     */
    @RequestMapping("/getIndex")
    public String getIndex(){

       Product lock1= new Product();
        Product lock2= new Product();
        new Thread(() -> {
            synchronized (lock1) {
                try {
                    System.out.println("thread1 begin");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                synchronized (lock2) {
                    System.out.println("thread1 end");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                try {
                    System.out.println("thread2 begin");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                synchronized (lock1) {
                    System.out.println("thread2 end");
                }
            }
        }).start();

        System.out.println("main thread end");
        return "hello";
    }

    }




