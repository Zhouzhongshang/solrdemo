package com.solr.demo.service;

import java.util.concurrent.ConcurrentHashMap;

public class Total implements Runnable{

    private static ConcurrentHashMap<String ,Integer> concurrentHashMap  = new ConcurrentHashMap();


    public static void main(String[] args) throws InterruptedException {

     //  int [] arr ={2,3,4,5,6,7,8,9,0};

        Total total = new Total();

        Thread a = new Thread(total, "A");
        Thread b = new Thread(total, "B");
        a.start();
        b.start();
        Thread.sleep(10000);

     System.out.println(concurrentHashMap.get("sum")+":"+concurrentHashMap.get("index"));

    }

    @Override
    public void run() {
        {
            int [] arr ={2,3,4,5,6,7,8,9,10};
            Integer index = concurrentHashMap.get("index");
            Integer sum = concurrentHashMap.get("sum");
            if (index==null && sum == null){

                index=0;
                sum=0;

                for (int i =index;i<arr.length;i++){
                    sum+=arr[i];
                    concurrentHashMap.put("index",i);
                    concurrentHashMap.put("sum",sum);
                    System.out.println("当前线程："+Thread.currentThread().getName()+"index:"+i+"sum"+sum);
                   /* try {s
                        System.out.println("睡"+Thread.currentThread().getName());
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }else {
                for (int i =index+1;i<arr.length;i++){
                    sum+=arr[i];
                    concurrentHashMap.put("index",i);
                    concurrentHashMap.put("sum",sum);
                    System.out.println("当前线程："+Thread.currentThread().getName()+"index:"+i+"sum"+sum);
                  /*  try {
                        System.out.println("线程1睡");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }
    }
}
