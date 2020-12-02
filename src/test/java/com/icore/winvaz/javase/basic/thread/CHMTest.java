package com.icore.winvaz.javase.basic.thread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Deciption ConcurrentHashMap测试
 * @Author wdq
 * @Create 2020/12/1 10:22
 * @Version 1.0.0
 */
public class CHMTest {
    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    public static void process(Path file) {
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                String word = in.next();
                map.merge(word, 1L, Long::sum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException {
        try (Stream<Path> entries = Files.walk(rootDir)) {
            return entries.collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        Path path = Paths.get(".");
        Path pathToRoot = path.getRoot();
        for (Path p : descendants(pathToRoot)) {
            if (p.getFileName().toString().endsWith(".java")) {
                executor.execute(() -> process(p));
            }
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        map.forEach((k, v) -> {
            if (v >= 10) {
                System.out.println(k + " occurs " + v + " times");
            }
        });
    }
}
