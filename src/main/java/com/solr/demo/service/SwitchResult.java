package com.solr.demo.service;


public class SwitchResult {
    public static void main(String[] args) {

        int i=2;
        int result =getValue(i);
        System.out.println(result);

    }

    private static int getValue(int i) {
        int result=0;
        switch (i){
            case 1:
                result = result +1;
            case 2:
                result =result +i*2; //没有break
                break;
            case 3:
                result =result + i*3;
        }
        return result;
    }
}
