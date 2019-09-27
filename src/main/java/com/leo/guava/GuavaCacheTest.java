package com.leo.guava;

import com.google.common.cache.*;
import com.google.common.collect.Lists;
import com.leo.extension18.lambda.beans.User;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings("all")
@Log
public class GuavaCacheTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Cache<String, User> userLoadingCache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.SECONDS)//定时回收 给定时间内没有读写访问时回收
                .maximumSize(5)// 基于容量回收
                .build();

        LoadingCache<Integer, AtomicLong> loadingCache = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<Object, Object>() { //移除监听
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        log.info("删除原因=" + notification.getCause().toString() + "，删除 key=" + notification.getKey() + ",删除 value=" + notification.getValue() + "");
                    }
                })
                .build(new CacheLoader<Integer, AtomicLong>() {//自定义 CacheLoader
                    @Override
                    public AtomicLong load(Integer key) throws Exception {
                        return new AtomicLong(0);
                    }
                });

        List<User> users = initList();
        users.forEach(user -> userLoadingCache.put(user.getName(), user));

        userLoadingCache.asMap().forEach((k, v) -> System.out.println(k + "," + v));

        User lisa = userLoadingCache.getIfPresent("lisa");// 获取
        System.out.println(lisa);
        userLoadingCache.invalidate("lisa");//个别移除
        userLoadingCache.invalidateAll(Lists.newArrayList("tom", "john"));//批量清除
        userLoadingCache.invalidateAll();//清除所有缓存项

        userLoadingCache.asMap().forEach((k, v) -> System.out.println(k + "," + v));

        TimeUnit.SECONDS.sleep(11);
        User mack = userLoadingCache.getIfPresent("mack");
        System.out.println(mack);
    }


    private static List<User> initList() {
        List<User> list = new ArrayList<>();
        list.add(new User("lisa", 23));
        list.add(new User("tom", 11));
        list.add(new User("john", 16));
        list.add(new User("jennis", 26));
        list.add(new User("tin", 26));
        list.add(new User("army", 26));
        list.add(new User("mack", 19));
        list.add(new User("jobs", 65));
        return list;
    }
}
