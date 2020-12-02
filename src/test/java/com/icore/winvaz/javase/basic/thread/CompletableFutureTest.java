package com.icore.winvaz.javase.basic.thread;

import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Deciption 读取一个web页面，扫描页面得到其中的图像，并保存在本地。
 * 注意所有耗费时间的方法都返回一个CompletableFuture。为了启动异步计算，
 * 我们使用了一个小技巧，这里没有直接调用readPage方法，而是用URL参数建立一个
 * 完成的futrue，然后将这个future与this::readPage组合。这样一来，这个管线看起来很统一
 * @Author wdq
 * @Create 2020/12/1 17:50
 * @Version 1.0.0
 */
public class CompletableFutureTest {

    private static final Pattern IMG_PATTERN = Pattern.compile(
            "[<]\\s*[iI][mM][gG]\\s*[^>]*[sS][rR][cC]\\s*['\"]([^'\"]*)['\"][^>]*[>]"
    );

    private ExecutorService executor = Executors.newCachedThreadPool();
    private URL urlToProcess;

    public CompletableFuture<String> readPage(URL url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String contents = new String(IOUtils.toByteArray(url.openStream()), StandardCharsets.UTF_8);
                System.out.println("Read page from " + url);
                return contents;
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    public List<URL> getImageURLs(String webpage) {
        try {
            List<URL> result = new ArrayList<>();
            Matcher matcher = IMG_PATTERN.matcher(webpage);
            while (matcher.find()) {
                URL url = new URL(urlToProcess, matcher.group(1));
                result.add(url);
            }
            System.out.println("Found URLs: " + result);
            return result;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public CompletableFuture<List<BufferedImage>> getImages(List<URL> urls) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                List<BufferedImage> result = new ArrayList<>();
                for (URL url : urls) {
                    result.add(ImageIO.read(url));
                    System.out.println("Loaded " + url);
                }
                return result;
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    public void saveImages(List<BufferedImage> images) {
        System.out.println("Saving " + images.size() + " images");
        try {
            for (int i = 0; i < images.size(); i++) {
                String filename = "/tmp/image" + (i + 1) + ".png";
                ImageIO.write(images.get(i), "PNG", new File(filename));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        executor.shutdown();
    }

    public void run(URL url) {
        urlToProcess = url;
        CompletableFuture.completedFuture(url)
                .thenComposeAsync(this::readPage, executor)
                .thenApply(this::getImageURLs)
                .thenCompose(this::getImages)
                .thenAccept(this::saveImages);

        // JDK 9
        /*HttpClient client = HttpClient.newBuilder().executor(executor).build();
        HttpRequest request = HttpRequest.newBuilder(urlToProcess.toURI().GET().build());
        client.sendAsync(request, BodyProcessor.asString())
                .thenAppley(HttpResponse::body).thenApply(this::getImageURLs)
                .thenCompose(this::getImages).thenAccept(this::saveImages);*/
    }

    public static void main(String[] args) throws MalformedURLException {
        new CompletableFutureTest().run(new URL("http://horstmann.com/index.html"));
    }
}
