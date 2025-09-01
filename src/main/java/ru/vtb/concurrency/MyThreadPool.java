package ru.vtb.concurrency;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
public class MyThreadPool {
    private final LinkedBlockingQueue<Runnable> taskQueue;
    private final WorkerThread[] workers;
    private final AtomicBoolean isShutdown;

    private final AtomicInteger taskCount;

    private final int capacity;


    public MyThreadPool(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity должен быть больше 0");
        }

        this.taskQueue = new LinkedBlockingQueue<>();
        ; // очередь исполнения
        this.workers = new WorkerThread[capacity];  // массив потоков
        this.isShutdown = new AtomicBoolean(false);
        this.taskCount = new AtomicInteger(0);
        this.capacity = capacity;

        // Инициализация и запуск  потоков
        for (int i = 0; i < capacity; i++) {
            workers[i] = new WorkerThread();
            workers[i].start();
        }
    }

    @SneakyThrows
    public void execute(Runnable task) {
        if (isShutdown.get()) {
            throw new IllegalStateException("Пул нахоится в стадии завершения, задачи не принимаются");
        }
        taskCount.incrementAndGet();
        taskQueue.put(task);
    }

    public void shutdown() {
        isShutdown.set(true);  //устанавливаем флаг завершения
        // Отправляем убейся
        for (int i = 0; i < capacity; i++) {
            workers[i].interrupt();
        }
    }

    public void awaitTermination() {
        try {
            // подождать пока все выполняться
            while (taskCount.get() > 0) {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        shutdown();
    }

    /**
     * Поток для выполнения задачи
     */
    private class WorkerThread extends Thread {
        @Override
        public void run() {
            Runnable task;
            while (true) {
                task = taskQueue.poll();
                if (isShutdown.get() && task == null) {
                    break;
                }
                try {
                    if (task != null && !Thread.interrupted()) {
                        task.run();
                        taskCount.decrementAndGet();
                    }
                } catch (Exception e) {
                    log.error("произошла ошибка ", e);
                }
            }
        }
    }
}
