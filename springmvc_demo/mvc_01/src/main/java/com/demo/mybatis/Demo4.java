package com.demo.mybatis;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo4 {
    public static AtomicInteger ticket = new AtomicInteger(100);
    public static final Object OBJECT = new Object();
    public static void main(String[] args) throws InterruptedException {
       System.out.println(a("cddvv", "cvv"));
    }
    public static String a(String s1,String s2){
        if(s1==null||"".equals(s1)||s2==null||"".equals(s2)){
            return "";
        }
        if (s1.equals(s2)){
            return s1;
        }
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int l1 = ch1.length;
        int l2 = ch2.length;
        int maxLen = 0;//最大公串长度
        int endIndex = 0;//公串的最后一个字符的索引
        for(int i=0;i<l1;i++){
            for(int j=0;j<l2;j++){
                if(ch1[i]==ch2[j]){
                    int m=i;
                    int n=j;
                    int tempLen = 0;
                    while(((m<l1) && (n<l2))&&(ch1[m]==ch2[n])){
                        m++;n++;tempLen++;
                    }
                    if(tempLen>maxLen){
                        maxLen = tempLen;
                        endIndex = m;
                    }
                }
            }
        }
        return maxLen==0 ? "-1" : s1.substring(endIndex-maxLen,endIndex);
    }
}


