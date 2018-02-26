package com.firesoon.drgs.exe;

import io.swagger.models.auth.In;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author create by yingjie.chen on 2018/1/23.
 * @version 2018/1/23 15:23
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Pattern p = Pattern.compile("");
//        Matcher matcher =p.matcher("xxxxx");
//        boolean is_matches = matcher.matches();
//        System.out.println(is_matches);

//        String s = "1,2,3,4,5";
//
//        String[] ss = s.split(",");
//        List<String> list = Arrays.asList(ss);
//        list.stream().filter(s1 -> !s1.contains("5")).forEach(System.out::println);


        Callable<Integer> callable = () -> (2);
        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());


    }

    static void prp(String s) {
        System.out.println(s);
    }

}
