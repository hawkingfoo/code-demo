package com.mine.thread;

import java.util.LinkedList;

public class ThreadPool extends ThreadGroup {
    // 任务队列，提交给线程池的任务
    private LinkedList<Runnable> taskQueue;
    // 工作线程ID
    private int threadID;

    /* 启动线程池 */
    public ThreadPool(String poolName, int poolSize) {
        super(poolName);
        setDaemon(true);
        taskQueue = new LinkedList<Runnable>(); // 创建任务队列
        for (int i = 0; i < poolSize; i++) {
            new WorkThread().start(); // 创建并启动线程，将之加入到线程组中
        }
    }
    /* 关闭线程池 */
    public synchronized void close() {
        taskQueue.clear();
        interrupt(); // 中断所有线程
    }

    /* 向工作队列中添加一个任务 */
    public synchronized void execute(Runnable task) {
        if (task != null) {
            taskQueue.add(task);
            notify(); // 唤醒
        }
    }

    /* 从工作队列中取出一个任务 */
    private synchronized Runnable getTask() throws InterruptedException {
        while (taskQueue.isEmpty()) {
            wait(); // 如果任务队列中没有任务，就等待任务
        }
        return taskQueue.removeFirst();
    }




    private class WorkThread extends Thread {
        public WorkThread() { // 加入到ThreadPool的线程组中
            super(ThreadPool.this, "WorkThead-" + (threadID++));
        }
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Runnable task;
                    if ((task = getTask()) == null) {
                        return;
                    }
                    // 运行任务
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
