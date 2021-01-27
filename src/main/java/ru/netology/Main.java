package ru.netology;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=0xYxua6e8LG0w2x5O5XkD1OszajD9eZUfdebfkqv");
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
            String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

//            Nasa nasa = mapper.readValue(response.getEntity().getContent(), Nasa.class);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Nasa nasa = gson.fromJson(body, Nasa.class);
            System.out.println(nasa.getUrl());
            HttpGet requestImage = new HttpGet(nasa.getUrl());
            CloseableHttpResponse responseImage = httpClient.execute(requestImage);

            String[] splitName = nasa.getUrl().split("/");
            String nameFile = splitName[splitName.length - 1];
            System.out.println(nameFile);

            byte[] bytes = responseImage.getEntity().getContent().readAllBytes();

            InputStream stream = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(stream);
            ImageIO.write(image, "jpg",
                    new File("C:/Users/svdav/java_project/task9_2_NASA/" + nameFile));

            BufferedImage imageUrl = ImageIO.read(new URL(nasa.getUrl()));
            ImageIO.write(imageUrl, "JPEG",
                    new File("C:/Users/svdav/java_project/task9_2_NASA/" + nameFile));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
