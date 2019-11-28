package com.solr.demo.service.NIUKE;

import java.util.*;


public class AlizaixianTest01 {
    public static void main(String [] args){
        String in=new Scanner(System.in).next();
        String  result = get(in);
        System.out.println(result);

    }

    private static String get(String in) {
        char [] chars =in.toCharArray();
        Map<Character , Integer> map=new HashMap<>(16);
        for (char achar : chars) {
            Integer num = map.get(achar);
            if(num == null){
                map.put(achar,1);
            }else{
                map.put(achar,num+1);
            }
        }

        //按照abc排序
        List<Character> keyList=new ArrayList<>(map.keySet());
        Collections.sort(keyList);
        StringBuilder result=new StringBuilder();
        for (int i =0 ;i<keyList.size();i++){
            Character key =  keyList.get(i);
            result.append(key).append(map.get(key));
        }
        return result.toString();
    }
}
