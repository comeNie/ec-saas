package com.yjg.ec.platform.common.util.weixin;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;


public class HttpClientUtil {
	private  RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(7000)
			.setConnectionRequestTimeout(7000).setSocketTimeout(7000).build();


	private class MyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	private class AllTrustStrategy implements TrustStrategy {
		@Override
		public boolean isTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
			return true;
		}
	}

	/*
	 * final TrustManager[] trustAllCerts = new TrustManager[] { new
	 * X509TrustManager() {
	 * 
	 * @Override public void
	 * checkClientTrusted(java.security.cert.X509Certificate[] chain, String
	 * authType) { }
	 * 
	 * @Override public void
	 * checkServerTrusted(java.security.cert.X509Certificate[] chain, String
	 * authType) { }
	 * 
	 * @Override public java.security.cert.X509Certificate[]
	 * getAcceptedIssuers() { return new java.security.cert.X509Certificate[]{};
	 * } } };
	 */

	private  CloseableHttpClient buileHttpClient(boolean isSSL) {
		CloseableHttpClient httpClient = null;
		SSLConnectionSocketFactory sslSocketFactory = null;
		if (isSSL) {
			try {
				SSLContext sslContext = SSLContexts.custom()
						.loadTrustMaterial(null, new AllTrustStrategy())
						.build();
				sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
						new MyHostnameVerifier());
			} catch (Exception e) {
				return null;
			}
			httpClient = HttpClients.custom()
					.setSSLSocketFactory(sslSocketFactory).setDefaultRequestConfig(requestConfig).build();
		} else {
			httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		}

		return httpClient;
	}

	private String execGetRequest(CloseableHttpClient httpClient, String url) {
		String ret = "";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;

			try {
				response = httpClient.execute(httpGet);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();
					if (null != entity) {
						ret = EntityUtils.toString(entity, ContentType
								.getOrDefault(entity).getCharset());
						EntityUtils.consume(entity);
					}
				}
			} catch (Exception e) {
				
			} finally {
				try {
					if (response != null) {
						response.close();
						response = null;
					}
				}catch(Exception e) {
				}
			}
		

		return ret;
	}

	public String sendGet(boolean isSSL, String url) {
		String ret = "";
		CloseableHttpClient httpClient = null;
		try {
			httpClient = buileHttpClient(isSSL);
			if (httpClient != null) {
				ret = execGetRequest(httpClient, url);
				httpClient.close();
				httpClient = null;
			}
		}
		catch (Exception e) {
			ret = "";
		} 
		return ret;
	}
	
	public String sendPost(boolean isSSL,String url,JSONObject jsonParam ) {
		String ret = "";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = buileHttpClient(isSSL);
			HttpPost post = new HttpPost(url); 
			
			
			StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
            entity.setContentEncoding("UTF-8");    
            entity.setContentType("application/json");    
            post.setEntity(entity);     
            
			response = httpClient.execute(post);
            try {
            	if (response != null) {
            		ret  = EntityUtils.toString(response.getEntity());
            	}
			}finally {
				if (response != null) {
					response.close();
					response = null;
				}
			}
		}catch(Exception e) {
			
		}
		finally {
			try {
				if (httpClient != null) {
					httpClient.close();
					httpClient = null;
				}
			}catch(Exception e) {
				
			}
		}
		return ret;
	}
	
	public String sendPost(boolean isSSL,String url,Map<String,String>params) {
		String ret = "";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		List<NameValuePair> list = new ArrayList<NameValuePair>(); 
		try {
			httpClient = buileHttpClient(isSSL);
			for (String key : params.keySet()) {
				String value = params.get(key);
				list.add(new BasicNameValuePair(key, value));
			}
			
			HttpEntity entity = new UrlEncodedFormEntity(list, "utf-8");  
			HttpPost post = new HttpPost(url); 
			post.setEntity(entity); 
			response = httpClient.execute(post);
            try {
            	if (response != null) {
            		ret  = EntityUtils.toString(response.getEntity());
            	}
			}finally {
				if (response != null) {
					response.close();
					response = null;
				}
			}
		}catch(Exception e) {
			
		}
		finally {
			try {
				if (httpClient != null) {
					httpClient.close();
					httpClient = null;
				}
			}catch(Exception e) {
				
			}
		}
		return ret;
	}
	
	
	public String sendPost(boolean isSSL,String url,String xmlParam ) {
		String ret = "";
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = buileHttpClient(isSSL);
			HttpPost post = new HttpPost(url); 
			
			
			StringEntity entity = new StringEntity(xmlParam,"utf-8");//解决中文乱码问题    
            entity.setContentEncoding("UTF-8");    
            entity.setContentType("application/xml");    
            post.setEntity(entity);     
            
			response = httpClient.execute(post);
            try {
            	if (response != null) {
            		ret  = EntityUtils.toString(response.getEntity());
            	}
			}finally {
				if (response != null) {
					response.close();
					response = null;
				}
			}
		}catch(Exception e) {
			
		}
		finally {
			try {
				if (httpClient != null) {
					httpClient.close();
					httpClient = null;
				}
			}catch(Exception e) {
				
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		HttpClientUtil h = new HttpClientUtil();
		h.sendPost(true,
				"https://www.google.com.hk/webhp?hl=zh-CN&sourceid=cnhp&gws_rd=ssl",new HashMap<String,String>());
	}

}
 