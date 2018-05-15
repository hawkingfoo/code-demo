package com.mine.thread;


import java.util.concurrent.ConcurrentHashMap;

public class ThreadSync extends Thread {

    // 只有一个副本
    private static ConcurrentHashMap<String, String> MAP = new ConcurrentHashMap<String, String>();

    private String getStrFromMap(String id) throws Exception {
        String ret = MAP.get(id);

        synchronized (MAP) { //加锁
            if (ret == null) {
                if (MAP.get(id) == null) {
                    MAP.put(id, "1"); // 写入一个
                    ret = MAP.get(id);
                }
            }
        }

        if (ret == null) {
            throw new Exception("result is null");
        }
        return ret;
    }

    @Override
    public void run() {
        try {
            System.out.println(getStrFromMap("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new ThreadSync();
        Thread t2 = new ThreadSync();
        Thread t3 = new ThreadSync();

        t1.start();
        t2.start();
        t3.start();
    }
}
