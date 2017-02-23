package net.test.generator.service.http;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class HttpRequestSenderImpl implements HttpRequestSender {


    @Override
    public int sendGet(String url) {

        HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
        GetMethod get = new GetMethod(url);
        int status = 0;
        try {
            client.executeMethod(get);
            status = get.getStatusCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            get.releaseConnection();
        }
        System.out.println("Status: " + status + " | Time: " + System.currentTimeMillis() / 1000);

        return status;
    }

}
