package com.cetian.core.util.weixin;

import com.cetian.core.util.MapEntryConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信工具类，包含微信平台对接的基本工具方法
 * @author liuzidong
 * @date 2016-11-16
 * @version 1.0
 */
public class WeixinUtil {

	private static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);

	private static XStream xStream;

	static {
		// 初始化xml
		xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
		xStream.registerConverter(new MapEntryConverter());
        xStream.registerConverter(new MapEntryConverter());
		xStream.alias("xml", Map.class);
	}

    /**
     * 微信服务器签名校验
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param token token
     * @return
     */
	public static boolean checkSignature(String signature, String timestamp, String nonce, String token) {
		// 对三个字符串进行字母排序并拼接
		String[] array = {token, timestamp, nonce};
		Arrays.sort(array);
		String str = StringUtils.join(array);

		// 对拼接字符串做sha1加密
		String result = DigestUtils.sha1Hex(str);

		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		if (StringUtils.equalsIgnoreCase(result, signature)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return String
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

    /**
     *  请求方法
     * @param requestUrl 请求地址
     * @return JSONObject
     * @throws KeyStoreException 通用密钥库异常
     * @throws NoSuchAlgorithmException 加密算法不可用异常
     * @throws KeyManagementException 密钥管理例外异常
     * @throws IOException IO异常
     */
	public static JSONObject doGetRequest(@NotNull String requestUrl) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        JSONObject jsonObject = null;
            HttpGet httpGet = new HttpGet(requestUrl);
            jsonObject = getJsonObject(setClient(requestUrl).execute(httpGet));
        return jsonObject;
    }

    /**
     * post请求方法
     * @param requestUrl 请求地址
     * @param strParam 请求参数
     * @return JSONObject
     * @throws KeyStoreException 通用密钥库异常
     * @throws NoSuchAlgorithmException 加密算法不可用异常
     * @throws KeyManagementException 密钥管理例外异常
     * @throws IOException IO异常
     */
	public static JSONObject doPostRequest(String requestUrl,String strParam) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
		JSONObject jsonObject = null;
                HttpPost httpPost = new HttpPost(requestUrl);
                if (StringUtils.isNotBlank(strParam)) {
                    StringEntity stringEntity = new StringEntity(strParam, "UTF-8");
                    httpPost.setEntity(stringEntity);
                }
            jsonObject = getJsonObject(setClient(requestUrl).execute(httpPost));
        return jsonObject;
    }

    /**
     * HttpResponse类型转JSONObject类型
     * @param httpResponse
     * @return JSONObject
     * @throws IOException
     */
    private static JSONObject getJsonObject(HttpResponse httpResponse) throws IOException {
        String result = EntityUtils.toString(httpResponse.getEntity());
        return new JSONObject(result);
    }

    /**
     * 设置客户端类型
     * @param requestUrl 请求url
     * @return CloseableHttpClient
     * @throws KeyStoreException 通用密钥库异常
     * @throws NoSuchAlgorithmException 加密算法不可用异常
     * @throws KeyManagementException 密钥管理例外异常
     */
    private static CloseableHttpClient setClient(String requestUrl) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String protocol = StringUtils.substringBefore(requestUrl,":");
        CloseableHttpClient client = null;
        if ("http".equals(protocol.toLowerCase())){
            client = createClient();
        } else {
            client = createSSLClient();
        }
        return client;
    }

    /**
     * 创建ssl链接客户端
     * @return CloseableHttpClient
     * @throws KeyStoreException 通用密钥库异常
     * @throws NoSuchAlgorithmException 加密算法不可用异常
     * @throws KeyManagementException 密钥管理例外异常
     */
    private static CloseableHttpClient createSSLClient () throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                return true;
            }
        }).build();
        CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext)).build();
        return client;
    }

    /**
     * 创建默认的链接客户端
     * @return CloseableHttpClient
     */
    public static CloseableHttpClient createClient(){
        return HttpClients.createDefault();
    }

    /**
     * 解析微信传入的xml流到map
     * @param request 包含xml的请求
     * @return Map<String, String> 微信请求的xml的map集合
     */
    public static Map<String, String> parseXml(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(request.getInputStream());
        Element element = document.getRootElement();
        List<Element> elementList = element.elements();

        elementList.forEach((item)->{
            map.put(item.getName(),item.getText());
        });

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return map;
    }
}
