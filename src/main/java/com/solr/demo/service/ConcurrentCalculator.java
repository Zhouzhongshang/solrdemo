package com.solr.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.apache.coyote.http11.Constants.A;

public class ConcurrentCalculator{

    /**
     * 多线程求和:将任务按照线程的数量划分，并行计算和，最后汇总
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long l = System.currentTimeMillis();
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12};
        int sum = sumTotal(arr,3);
        System.out.println(sum);
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }

    private static int b(int[] arr) {
        int sum =0;
        for (int i :arr){
            sum+=i;
        }
        return sum;
    }


    private static int sumTotal(int[] arr, int i) throws ExecutionException, InterruptedException {
        /**
         * 1创建线程池
         * 2根据线程数量分配任务
         * 3利用线程池提交任务，拿到结果
         * 4对拿到得每个结果求和
         */
        ExecutorService executorService = Executors.newFixedThreadPool(i);
        int temp = arr.length/i+1;
        int start = 0;
        int end =0;
        int sum =0;
        //2提交任务

      List<FutureTask> result =  new ArrayList<>(3);

        for (int j=0;j<i;j++ ){
              start = j*temp;
              end= j*temp+temp;
              if (end>=arr.length-1)
                  end = arr.length;

            SumRun sumRun = new SumRun(start, end, arr);

            //这是返回结果也是任务
            FutureTask<Integer> integerFutureTask = new FutureTask<>(sumRun);
            executorService.submit(integerFutureTask);
            result.add(integerFutureTask);
        }

        for (Future<Integer> integerFutureTask: result){
           sum += integerFutureTask.get();
        }
        executorService.shutdown();

        return sum;
    }

    static class SumRun implements Callable<Integer>{

        private int start;
        private int end;
        private int [] arr;

        public SumRun(int start, int end, int[] arr) {
            this.start = start;
            this.end = end;
            this.arr = arr;
        }

        private int sum = 0;
        @Override
        public Integer call() throws Exception {
            for (int i = start ; i<end ;i++){
                sum += arr[i];
            }
            return sum;
        }
    }

}
