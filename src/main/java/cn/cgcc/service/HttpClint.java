package cn.cgcc.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HttpClint {

    private static final Logger logger = LoggerFactory.getLogger(HttpClint.class);
    private CloseableHttpClient httpClient;
    private HttpGet httpGet;
    public static final String CONTENT_TYPE = "Content-Type";

    public String doGet(String url) throws Exception {

        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String resp;

        try {
            HttpEntity entity = response.getEntity();
            entity.getContentEncoding();
            resp = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            logger.info("entity: " + entity);
        } finally {
            response.close();
        }
        LoggerFactory.getLogger(getClass()).info(" resp:{}", resp);
        return resp;
    }

    public HttpResponse doPost(String url) throws Exception {

        String getUrl = url;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(getUrl);
        HttpResponse response = httpClient.execute(post);

        return response;
    }


}
