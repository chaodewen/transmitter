package zju.ccnt.mdsp.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import zju.ccnt.mdsp.model.User;
import zju.ccnt.mdsp.settings.Constant;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Cc on 2016/12/14.
 */

public class Utils {
    /**
     * 验证用户并返回idcard
     * 验证失败时返回null
     */
    public static String verifyUser(String key) {
        try {
            String verifyUrl = Constant.userInfoUrl + "/users/verification";
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("key", key));
            JSONObject info = Utils.postJSONObject(verifyUrl, null, pairs);
            if(info == null) {
                return null;
            }
            return info.getString("idcard");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 隐私化处理
     */
    public static String rmPrivacy(boolean privacy, JSON object) {
        if(privacy) {
            return JSON.toJSONString(object, new PropertyFilter() {
                public boolean apply(Object object, String name, Object value) {
                    if(object == null) {
                        return false;
                    }
                    for(String field : Constant.privateFields) {
                        if(field.equals(name)) {
                            return false;
                        }
                    }
                    return true;
                }
            });
        }
        else {
            return JSON.toJSONString(object, new PropertyFilter() {
                public boolean apply(Object object, String name, Object value) {
                    return object != null;
                }
            });
        }
    }
    /**
     * 生成错误回复
     */
    public static ResponseEntity genErrorResponse(int status, String message) {
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("{\"error\":\"" + message + "\"}");
    }

    /**
     * 正常返回实体字符串
     * 异常时返回null
     */
    private static String getEntity(CloseableHttpResponse response) {
        if(response.getStatusLine().getStatusCode() / 100 == 2) {
            try {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 出错返回null
     */
    public static JSONObject postJSONObject(String url, Set<Header> headers
            , List<NameValuePair> pairList) {
        CloseableHttpResponse response = sendPost(url, pairList, headers);
        String entity = getEntity(response);

        if(entity == null) {
            return null;
        }

        return JSON.parseObject(entity);
    }
    /**
     * 出错返回null
     */
    public static JSONArray getJSONArray(String url, Set<Header> headers) {
        CloseableHttpResponse response = sendGet(url, headers);
        String entity = getEntity(response);

        if(entity == null) {
            return null;
        }

        return JSON.parseArray(entity);
    }
    /**
     * 出错返回null
     */
    public static JSONObject getByJSONObject(String url, Set<Header> headers) {
        CloseableHttpResponse response = sendGet(url, headers);
        String entity = getEntity(response);

        if(entity == null) {
            return null;
        }

        return JSON.parseObject(entity);
    }
    private static CloseableHttpClient generateClient(String url) {
        CloseableHttpClient httpClient = null;
        try {
            if(url.startsWith("https")) {
                // https
                SSLContext sslContext = SSLContexts.custom()
                        .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                        .useTLS()
                        .build();
                SSLConnectionSocketFactory sslSocketFactory =
                        new SSLConnectionSocketFactory(sslContext
                                , new AllowAllHostnameVerifier());
                httpClient = HttpClients.custom().setSSLSocketFactory(
                        sslSocketFactory).build();
            }
            else {
                httpClient = HttpClients.createDefault();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpClient;
    }

    /**
     * 发送HTTP的GET请求，失败时返回null
     */
    private static CloseableHttpResponse sendGet(String url, Set<Header> headers) {
        CloseableHttpClient httpClient = generateClient(url);
        if (httpClient == null) {
            return null;
        }
        CloseableHttpResponse response;
        try {
            HttpGet httpGet = new HttpGet(url);
            if (headers != null) {
                for (Header header : headers) {
                    httpGet.addHeader(header);
                }
            }
            response = httpClient.execute(httpGet);
        } catch (Exception e) {
            System.out.println(url);
            e.printStackTrace();
            return null;
        }
        return response;
    }

    /**
     * 发送HTTP的POST请求，出错时返回null
     */
    private static CloseableHttpResponse sendPost(String url
            , List<NameValuePair> pairList, Set<Header> headers) {
        CloseableHttpClient httpClient = generateClient(url);
        if (httpClient == null) {
            return null;
        }
        CloseableHttpResponse response;
        try {
            // 实现将请求的参数封装到表单中，即请求体中
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                    pairList, "UTF-8");
            // 使用post方式提交数据
            HttpPost httpPost = new HttpPost(url);
            if (headers != null) {
                for (Header header : headers) {
                    httpPost.addHeader(header);
                }
            }
            httpPost.setEntity(entity);
            // 执行post请求，并获取服务器端的响应HttpResponse
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }
}

class MyConnectionSocketFactory implements ConnectionSocketFactory {
    public Socket createSocket(HttpContext context)
            throws IOException {
        InetSocketAddress socksAddress = (InetSocketAddress)
                context.getAttribute("socks.address");
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, socksAddress);
        return new Socket(proxy);
    }

    public Socket connectSocket(int connectTimeout
            , Socket sock, HttpHost host
            , InetSocketAddress remoteAddress
            , InetSocketAddress localAddress
            , HttpContext context)
            throws IOException {
        Socket socket;
        if (sock != null) {
            socket = sock;
        } else {
            socket = createSocket(context);
        }
        if (localAddress != null) {
            socket.bind(localAddress);
        }
        try {
            socket.connect(remoteAddress, connectTimeout);
        } catch (SocketTimeoutException ex) {
            throw new ConnectTimeoutException(
                    ex, host, remoteAddress.getAddress());
        }
        return socket;
    }
}