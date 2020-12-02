package com.icore.winvaz.javase.basic.thread;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Deciption 阻塞队列
 * @Author wdq
 * @Create 2020/11/30 18:18
 * @Version 1.0.0
 */
public class BlockingQueueTest {

    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    // JDK 9
    // private static final Path DUMMY = Path.of("");
    private static final Path DUMMY = Paths.get("");
    private static final BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/jdk-9-src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            Runnable enumerator = () -> {
                try {
                    enumerate(Paths.get(directory));
                    queue.put(DUMMY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            new Thread(enumerator).start();
            for (int i = 0; i < SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    try {
                        boolean done = false;
                        while (!done) {
                            Path file = queue.take();
                            if (file == DUMMY) {
                                queue.put(file);
                                done = true;
                            } else {
                                search(file, keyword);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
                new Thread(searcher).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enumerate(Path directory) throws IOException, InterruptedException {
        try (Stream<Path> children = Files.list(directory)){
            for (Path child : children.collect(Collectors.toList())) {
                if (Files.isDirectory(child)) {
                    enumerate(child);
                } else {
                    queue.put(child);
                }
            }
        }
    }

    public static void search(Path file, String keyword) throws IOException {
        try (Scanner in = new Scanner(file, String.valueOf(StandardCharsets.UTF_8))) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s%n", file, lineNumber, line);
                }
            }
        }
    }
}
