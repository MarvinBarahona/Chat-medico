/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

//import java.io.IOException;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.client.methods.RequestBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;

/**
 *
 * @author fran
 */
public class HttpConfig {

//    public String httpGetSimple(String url) {
//        String source = null;
//
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(url);
//        try {
//            HttpResponse httpResponse = httpClient.execute(httpGet);
//            source = EntityUtils.toString(httpResponse.getEntity());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return source;
//    }
//
//    public String httpPostSimple(String url, NameValuePair... parametros) {
//        String source = null;
//        
//        NameValuePair valuePair = new BasicNameValuePair("nombre", "valor");
//        
//        RequestBuilder requestBuilder = RequestBuilder.post().setUri(url);
//        for (NameValuePair parametro : parametros) {
//            requestBuilder.addParameter(parametro);
//        }
//
//        HttpUriRequest uriRequest = requestBuilder.build();
//        
//        HttpClient httpClient = HttpClients.createDefault();
//        //HttpGet httpGet = new HttpGet(url);
//        try {
//            HttpResponse httpResponse = httpClient.execute(uriRequest);
//            source = EntityUtils.toString(httpResponse.getEntity());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return source;
//    }
}
