package com.icore.winvaz.javase.basic.thread;

import org.springframework.security.core.parameters.P;

import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Deciption 如何使用Callable和执行器
 * @Author wdq
 * @Create 2020/12/1 14:31
 * @Version 1.0.0
 */
public class ExecutorTest {

    public static long occurrences(String word, Path path) {
        try (Scanner in = new Scanner(path)) {
            int count = 0;
            while (in.hasNext()) {
                if (in.next().equals(word)) {
                    count++;
                }
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }

    public static Callable<Path> searchForTask(String word, Path path) {
        return () -> {
            try (Scanner in = new Scanner(path)) {
                while (in.hasNext()) {
                    if (in.next().equals(word))
                        return path;
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Search in " + path + " canceled");
                        return null;
                    }
                }
            }
            throw new NoSuchElementException();
        };
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/jdk-9-src): ");
            String start = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String word = in.nextLine();
            Set<Path> files = descendants(Paths.get(start));
            List<Callable<Long>> tasks = new ArrayList<>();
            for (Path file : files) {
                Callable<Long> task = () -> occurrences(word, file);
                tasks.add(task);
            }
            // 执行器
            ExecutorService executor = Executors.newCachedThreadPool();
            Instant startTime = Instant.now();
            List<Future<Long>> results = executor.invokeAll(tasks);
            long total = 0;
            for (Future<Long> result : results) {
                total += result.get();
            }
            Instant endTime = Instant.now();
            System.out.println("Occurences of " + word + ": " + total);
            System.out.println("Time elapsed: " + Duration.between(startTime, endTime).toMillis() + " ms");
            List<Callable<Path>> searchTasks = new ArrayList<>();
            for (Path file : files) {
                searchTasks.add(searchForTask(word, file));
            }
            Path found = executor.invokeAny(searchTasks);
            System.out.println(word + " occurs in: "  + found);
            if (executor instanceof ThreadPoolExecutor)
                System.out.println("Largest pool size:" + ((ThreadPoolExecutor)executor).getLargestPoolSize());
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
