package com.icore.winvaz.javase.basic.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * @Author 核心技术 卷II 第四章网络 225
 * @Create 2021/4/5 19:49
 * @Version 1.0.0
 */
public class PostTest {
    public static void main(String[] args) throws IOException {
        String proFileName = args.length > 0 ? args[0] : "post/post.properties";
        Properties props = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get(proFileName))) {
            props.load(inputStream);
        }
        String url = props.remove("url").toString();
        Object userAgent = props.remove("User-Agent");
        Object redirects = props.remove("redirects");
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        String result = doPost(new URL(url), props, userAgent == null ? null : userAgent.toString(), redirects == null ? -1 :
                Integer.parseInt(redirects.toString()));
        System.out.println(result);
    }

    private static String doPost(URL url, Map<Object, Object> nameValuePairs, String userAgent, int redirects) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (userAgent != null) {
            connection.setRequestProperty("User-Agent", userAgent);
        }
        if (redirects >= 0) {
            connection.setInstanceFollowRedirects(false);
        }
        connection.setDoOutput(true);

        try (PrintWriter out = new PrintWriter(connection.getOutputStream())) {
            boolean first = true;
            for (Map.Entry<Object, Object> pair : nameValuePairs.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    out.println("&");
                }
                String name = pair.getKey().toString();
                String value = pair.getValue().toString();
                out.print(name);
                out.print("=");
                out.print(URLEncoder.encode(value, "UTF-8"));
            }
        }

        String encoding = connection.getContentEncoding();
        if (encoding == null) encoding = "UTF-8";
        if (redirects > 0) {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                String location = connection.getHeaderField("Location");
                if (location != null) {
                    URL base = connection.getURL();
                    connection.disconnect();
                    return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
                }
            }
        } else if (redirects == 0) {
            throw new IOException("Too many redirects");
        }
        StringBuilder response = new StringBuilder();
        try (Scanner scanner = new Scanner(connection.getInputStream(), encoding)) {
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
                response.append("\n");
            }
        } catch (Exception e) {
            InputStream errorStream = connection.getErrorStream();
            if (errorStream == null) throw e;
            try (Scanner scanner = new Scanner(errorStream)) {
                response.append(scanner.nextLine());
                response.append("\n");
            }
        }
        return response.toString();
    }
}
