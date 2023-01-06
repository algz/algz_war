//package com.wl;
//
//
//import avicit.docgs.configuration.bean.HttpClientResult;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.exception.ExceptionUtils;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.ResponseHandler;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.*;
//import org.apache.http.cookie.Cookie;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.*;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.security.Security;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//
///**
// * @金航数码科技有限责任公司
// * @作者：caolong
// * @邮箱：18992379536@163.com
// * @创建时间：2022/3/29 16:17
// * @类说明：
// * @修改记录：
// */
//public class HttpClientUtils {
//    //日志记录
////    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
//
//    private static Pattern NUMBER_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");
//
//    // 编码格式。发送编码格式统一用UTF-8
//    private static final String ENCODING = "UTF-8";
//
//    // 设置连接超时时间，单位毫秒。
//    private static final int CONNECT_TIMEOUT = 6000;
//
//    // 请求获取数据的超时时间(即响应时间)，单位毫秒。
//    private static final int SOCKET_TIMEOUT = 6000;
//
//    private static final String SECRET = "AES";
//    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
//
//
//    public static HttpClientResult login(String url, String username, String password) {
//        CookieStore store = new BasicCookieStore();
//        Object obj = new Object();
//        String strCookie = "";
//        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(store).build();
//        HttpPost httpPost = new HttpPost(url);
//        List<NameValuePair> list = new ArrayList<>();
//        // 对明文密码进行aes加密
//        //String encryptStr = null;
////        try {
////            encryptStr = aes256ECBPkcs7PaddingEncrypt(password, "dufy20170329java");
////        } catch (Exception e) {
////            logger.error("aes256ECBPkcs7 padding encrypt error", e.toString());
////        }
//        list.add(new BasicNameValuePair("username_", username));
//        list.add(new BasicNameValuePair("password_", password));
//        UrlEncodedFormEntity uefEntity = null;
//        try {
//            uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            logger.error("UnsupportedEncoding Exception {}", e.toString());
//        }
//        httpPost.setEntity(uefEntity);
//        HttpClientResult httpClientResult = new HttpClientResult(500, "login dms error");
//        CloseableHttpResponse httpResponse = null;
//        try {
//            httpResponse = httpClient.execute(httpPost);
//            // 读取cookie信息， 并拼接cookie字符串
//            List<Cookie> cookielist = store.getCookies();
//            for (Cookie cookie : cookielist) {
//                String name = cookie.getName();
//                String value = cookie.getValue();
//                strCookie = strCookie + name + "=" + value + ";";
//            }
//
//            try {
//                if (httpResponse != null && httpResponse.getStatusLine() != null) {
//                    return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(), strCookie);
//                }
//            } finally {
//                httpResponse.close();
//            }
//        } catch (Exception e) {
//            logger.error("获取读取cookie信息异常", e.toString().substring(0, 50));
//        }
//        return httpClientResult;
//    }
//
//    public static String serverName(String url) {
//        String name = url.substring(url.lastIndexOf("/") + 1);
//        return name;
//    }
//
//
//    public static String accessableUrl(String url) {
//        //使用正则表达式过滤，
//        String re = "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)";
//        String str = "";
//        // 编译正则表达式
//        Pattern pattern = Pattern.compile(re);
//        // 忽略大小写的写法
//        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(url);
//        //若url==http://127.0.0.1:9040或www.baidu.com的，正则表达式表示匹配
//        if (matcher.matches()) {
//            str = url;
//        } else {
//            String[] split2 = url.split(re);
//            if (split2.length > 1) {
//                String substring = url.substring(0, url.length() - split2[1].length());
//                str = substring + "/" + split2[1].split("/")[1];
//
//            } else {
//                str = split2[0];
//            }
//        }
//        return str;
//    }
//
//    /**
//     * AES加密ECB模式PKCS7Padding填充方式
//     *
//     * @param str 字符串
//     * @param key 密钥
//     * @return 加密字符串
//     * @throws Exception 异常信息
//     */
//    public static String aes256ECBPkcs7PaddingEncrypt(String str, String key) throws Exception {
//        Security.addProvider(new BouncyCastleProvider());
//        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
//        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
//        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, SECRET));
//        byte[] doFinal = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
//        return new String(Base64.getEncoder().encode(doFinal));
//    }
//
//    public static void main(String[] args) throws Exception {
//        String dufy20170329java = aes256ECBPkcs7PaddingEncrypt("123456", "dufy20170329java");
//        System.out.println(dufy20170329java);
//    }
//
//    public static InputStream getImgInputStream(String imgPath, String strCookie) {
//        InputStream inStream = null;
//        //new一个URL对象
//        try {
//
//            URL url = new URL(urlEncodeChinese(imgPath));
//            //打开链接
//            HttpURLConnection conn = (HttpURLConnection)
//                    url.openConnection();
//            //设置请求方式为"GET"
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36");
//            conn.setRequestProperty("Cookie", strCookie);
//            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
//            conn.setRequestProperty("Charset", "UTF-8");// 设置编码
//            //超时响应时间为5秒
//            conn.setConnectTimeout(5 * 1000);
//            //通过输入流获取图片数据
//            inStream = conn.getInputStream();
//
//        } catch (Exception e) {
////            logger.error("query img error,reason is " + ExceptionUtils.getStackTrace(e));
//        }
//        return inStream;
//    }
//
//    /**
//     * 对url中的中文和空格进行编码
//     *
//     * @param url
//     * @return
//     */
//    public static String urlEncodeChinese(String url) {
//        try {
//            Matcher matcher = NUMBER_PATTERN.matcher(url);
//            String tmp = "";
//            while (matcher.find()) {
//                tmp = matcher.group();
//                url = url.replaceAll(tmp, URLEncoder.encode(tmp, "UTF-8"));
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return url.replace(" ", "%20");
//    }
//
//    /**
//     * 发送get请求；不带请求头和请求参数
//     *
//     * @param url 请求地址
//     * @return
//     * @throws Exception
//     */
//    public static HttpClientResult doGet(String url, String strCookie) throws Exception {
//        return doGet(url, null, null, strCookie);
//    }
//
//    /**
//     * 发送get请求；带请求参数
//     *
//     * @param url    请求地址
//     * @param params 请求参数集合
//     * @return
//     * @throws Exception
//     */
//    public static HttpClientResult doGet(String url, Map<String, String> params, String strCookie) throws Exception {
//        return doGet(url, null, params, strCookie);
//    }
//
//    /**
//     * 发送get请求；带请求头和请求参数
//     *
//     * @param url     请求地址
//     * @param headers 请求头集合
//     * @param params  请求参数集合
//     * @return
//     * @throws Exception
//     */
//    public static HttpClientResult doGet(String url, Map<String, String> headers, Map<String, String> params, String strCookie) throws Exception {
//        if (StringUtils.isEmpty(strCookie)) {
//            return new HttpClientResult(500, "login strCookie is null");
//        }
//        // 获得Http客户端
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        // 参数
//        StringBuilder accessParams = new StringBuilder();
//
//        if (params != null) {
//            Set<Map.Entry<String, String>> entrySet = params.entrySet();
//            for (Map.Entry<String, String> entry : entrySet) {
//                accessParams.append(entry.getKey() + "=").append(entry.getValue() + "&");
//            }
//        }
//
//        HttpGet httpGet = null;
//        // 创建Get请求
//        if (null == params) {
//            httpGet = new HttpGet(url);
//        } else {
//            String paramStr = new String(accessParams).substring(0, accessParams.length() - 1);
//            httpGet = new HttpGet(url + "?" + paramStr);
//        }
//        // 读取cookie信息， 并拼接cookie字符串
//        httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
//        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
//        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36");
//        httpGet.setHeader("Cookie", strCookie);
//        // 设置请求头
//        packageHeader(headers, httpGet);
//
//        // 响应模型
//        CloseableHttpResponse response = null;
//
//        try {
//            // 配置信息
//            RequestConfig requestConfig = RequestConfig.custom()
//                    // 设置连接超时时间(单位毫秒)
//                    .setConnectTimeout(5000)
//                    // 设置请求超时时间(单位毫秒)
//                    .setConnectionRequestTimeout(5000)
//                    // socket读写超时时间(单位毫秒)
//                    .setSocketTimeout(5000)
//                    // 设置是否允许重定向(默认为true)
//                    .setRedirectsEnabled(true).build();
//
//            // 将上面的配置信息 运用到这个Get请求里
//            httpGet.setConfig(requestConfig);
//
//            // 执行请求并获得响应结果
//            return getHttpClientResult(response, httpClient, httpGet);
//        } finally {
//            // 释放资源
//            release(response, httpClient);
//        }
//    }
//
////    /**
////     * 发送get请求，参数为json
////     *
////     * @param url
////     * @param param
////     * @param encoding
////     * @return
////     * @throws Exception
////     */
////    public static String sendJsonByGetReq(String url, String param, String encoding, String strCookie) throws Exception {
////        String body = "";
////        //创建httpclient对象
////        CloseableHttpClient client = HttpClients.createDefault();
////        HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity(new URI(url));
////        HttpEntity httpEntity = new StringEntity(param, ContentType.APPLICATION_JSON);
////        httpGetWithEntity.setEntity(httpEntity);
////        httpGetWithEntity.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
////        httpGetWithEntity.setHeader("Accept-Encoding", "gzip, deflate");
////        httpGetWithEntity.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
////        httpGetWithEntity.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36");
////        httpGetWithEntity.setHeader("Cookie", strCookie);
////        // 配置信息
////        RequestConfig requestConfig = RequestConfig.custom()
////                // 设置连接超时时间(单位毫秒)
////                .setConnectTimeout(5000)
////                // 设置请求超时时间(单位毫秒)
////                .setConnectionRequestTimeout(5000)
////                // socket读写超时时间(单位毫秒)
////                .setSocketTimeout(5000)
////                // 设置是否允许重定向(默认为true)
////                .setRedirectsEnabled(true).build();
////
////        // 将上面的配置信息 运用到这个Get请求里
////        httpGetWithEntity.setConfig(requestConfig);
////        //执行请求操作，并拿到结果（同步阻塞）
////        CloseableHttpResponse response = client.execute(httpGetWithEntity);
////        //获取结果实体
////        HttpEntity entity = response.getEntity();
////        if (entity != null) {
////            //按指定编码转换结果实体为String类型
////            body = EntityUtils.toString(entity, encoding);
////        }
////        //释放链接
////        response.close();
////        client.close();
////        return body;
////    }
//
//
//    /**
//     * 发送post请求；不带请求头和请求参数
//     *
//     * @param s
//     * @param pararm
//     * @param o
//     * @param url    请求地址
//     * @return
//     * @throws Exception
//     */
//    public static HttpClientResult doPost(String url, JSONObject pararmObj, String strCookie) throws Exception {
//        return doPost(url, pararmObj, null, strCookie);
//    }
//
//    /**
//     * 发送post请求；带请求头和请求参数
//     *
//     * @param url     请求地址
//     * @param headers 请求头集合
//     * @param params  请求参数集合
//     * @return
//     * @throws Exception
//     */
//    public static HttpClientResult doPost(String url, JSONObject obj, Map<String, String> headers, String strCookie) throws Exception {
//        // 创建httpClient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        // 创建httpResponse对象
//        CloseableHttpResponse httpResponse = null;
//        // 创建http对象
//        HttpPost httpPost = new HttpPost(url);
//        /**
//         * setConnectTimeout：设置连接超时时间，单位毫秒。
//         * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
//         * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
//         * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
//         */
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
//        httpPost.setConfig(requestConfig);
//        // 设置请求头
//        httpPost.setHeader("Accept", "*/*");
//        httpPost.setHeader("Accept-Encoding", "gzip,deflate,br");
//        httpPost.setHeader("Cookie", strCookie);
//        httpPost.setHeader("Connection", "keep-alive");
//        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
//        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
//        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
//        packageHeader(headers, httpPost);
//        if (null != obj) {
//            //设置参数为json格式
//            String str = obj.toJSONString();
//            StringEntity stringEntity = new StringEntity(str, ENCODING);
//            httpPost.setEntity(stringEntity);
//        }
//        // 封装请求参数
//        //packageParam(params, httpPost);
//        try {
//            // 执行请求并获得响应结果
//            return getHttpClientResult(httpResponse, httpClient, httpPost);
//        } finally {
//            // 释放资源
//            release(httpResponse, httpClient);
//        }
//    }
//
//    public static String doPostWithJson(String url, String json) {
//        return doPostWithJson(url, null, json);
//    }
//
//    public static String doPostWithJson(String url, Map<String, String> headers, String json) {
//        String returnValue = "";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        ResponseHandler<String> responseHandler = new BasicResponseHandler();
//        try {
//            httpClient = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost(url);
//            StringEntity requestEntity = new StringEntity(json, "utf-8");
//            requestEntity.setContentEncoding("UTF-8");
//            httpPost.setHeader("Content-type", "application/json");
//            packageHeader(headers, httpPost);
//            httpPost.setEntity(requestEntity);
//
//            //第四步：发送HttpPost请求，获取返回值
//            returnValue = httpClient.execute(httpPost, responseHandler); //调接口获取返回值时，必须用此方法
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        //第五步：处理返回值
//        return returnValue;
//    }
//
//    /**
//     * Description: 封装请求头
//     *
//     * @param params
//     * @param httpMethod
//     */
//    public static void packageHeader(Map<String, String> params, HttpRequestBase httpMethod) {
//        // 封装请求头
//        if (params != null) {
//            Set<Map.Entry<String, String>> entrySet = params.entrySet();
//            for (Map.Entry<String, String> entry : entrySet) {
//                // 设置到请求头到HttpRequestBase对象中
//                httpMethod.setHeader(entry.getKey(), entry.getValue());
//            }
//        }
//    }
//
//    /**
//     * Description: 封装请求参数
//     *
//     * @param params
//     * @param httpMethod
//     * @throws UnsupportedEncodingException
//     */
//    public static void packageParam(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod)
//            throws UnsupportedEncodingException {
//        // 封装请求参数
//        if (params != null) {
//            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            Set<Map.Entry<String, String>> entrySet = params.entrySet();
//            for (Map.Entry<String, String> entry : entrySet) {
//                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//            }
//            // 设置到请求的http对象中
//            httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
//        }
//    }
//
//    /**
//     * Description: 获得响应结果
//     *
//     * @param httpResponse
//     * @param httpClient
//     * @param httpMethod
//     * @return
//     * @throws Exception
//     */
//    public static HttpClientResult getHttpClientResult(CloseableHttpResponse httpResponse,
//                                                       CloseableHttpClient httpClient, HttpRequestBase httpMethod) throws Exception {
//        // 执行请求
//        httpResponse = httpClient.execute(httpMethod);
//        Object obj = new Object();
//        // 获取返回结果
//        if (httpResponse != null && httpResponse.getStatusLine() != null) {
//            String content = "";
//            //System.out.println("httpResponse.getEntity():" + httpResponse.getEntity());
//            if (httpResponse.getEntity() != null) {
//
//                content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
//                JSONObject jsonObject = JSONObject.parseObject(content);
//                obj = jsonObject.get("responseBody");
//            }
//            return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(), obj);
//        }
//        return new HttpClientResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, obj);
//    }
//
//    /**
//     * Description: 释放资源
//     *
//     * @param httpResponse
//     * @param httpClient
//     * @throws IOException
//     */
//    public static void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) throws IOException {
//        // 释放资源
//        if (httpResponse != null) {
//            httpResponse.close();
//        }
//        if (httpClient != null) {
//            httpClient.close();
//        }
//    }
//}
