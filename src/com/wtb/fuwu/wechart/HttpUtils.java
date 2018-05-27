package com.wtb.fuwu.wechart;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

public class HttpUtils {
	public static HttpResponse doGet(String url, Map<String, String> headers, Map<String, String> querys) throws Exception {
		HttpClient httpClient = wrapClient(url);

		HttpGet request = new HttpGet(buildUrl(url, querys));
		for (Map.Entry<String, String> e : headers.entrySet()) {
			request.addHeader(e.getKey(), e.getValue());
		}
		HttpResponse response = httpClient.execute(request);
		return response;
	}

	public static HttpResponse doPost(String url, Map<String, String> headers, Map<String, String> querys, Map<String, String> bodys) throws Exception {
		HttpClient httpClient = wrapClient(url);

		HttpPost request = new HttpPost(buildUrl(url, querys));
		if (headers != null) {
			for (Map.Entry<String, String> e : headers.entrySet()) {
				request.addHeader(e.getKey(), e.getValue());
			}
		}

		if (bodys != null) {
			List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

			for (String key : bodys.keySet()) {
				nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
			formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
			request.setEntity(formEntity);
		}
		HttpResponse response = httpClient.execute(request);
		return response;
	}

	public static HttpResponse doPost(String url, Map<String, String> headers, Map<String, String> querys, String body) throws Exception {
		HttpClient httpClient = wrapClient(url);

		HttpPost request = new HttpPost(buildUrl(url, querys));

		if (headers != null) {
			for (Map.Entry<String, String> e : headers.entrySet()) {
				request.addHeader(e.getKey(), e.getValue());
			}
		}

		if (StringUtils.isNotBlank(body)) {
			request.setEntity(new StringEntity(body, "UTF-8"));
		}

		HttpResponse response = httpClient.execute(request);
		return response;
	}

	public static HttpResponse doPost(String url, Map<String, String> headers, Map<String, String> querys, byte[] body) throws Exception {
		HttpClient httpClient = wrapClient(url);

		HttpPost request = new HttpPost(buildUrl(url, querys));
		for (Map.Entry<String, String> e : headers.entrySet()) {
			request.addHeader(e.getKey(), e.getValue());
		}

		if (body != null) {
			request.setEntity(new ByteArrayEntity(body));
		}

		HttpResponse response = httpClient.execute(request);
		return response;
	}

	public static HttpResponse doPut(String url, Map<String, String> headers, Map<String, String> querys, String body) throws Exception {
		HttpClient httpClient = wrapClient(url);

		HttpPut request = new HttpPut(buildUrl(url, querys));
		for (Map.Entry<String, String> e : headers.entrySet()) {
			request.addHeader(e.getKey(), e.getValue());
		}

		if (StringUtils.isNotBlank(body)) {
			request.setEntity(new StringEntity(body, "UTF-8"));
		}

		HttpResponse response = httpClient.execute(request);
		return response;
	}

	public static HttpResponse doPut(String url, Map<String, String> headers, Map<String, String> querys, byte[] body) throws Exception {
		HttpClient httpClient = wrapClient(url);

		HttpPut request = new HttpPut(buildUrl(url, querys));
		for (Map.Entry<String, String> e : headers.entrySet()) {
			request.addHeader(e.getKey(), e.getValue());
		}

		if (body != null) {
			request.setEntity(new ByteArrayEntity(body));
		}

		HttpResponse response = httpClient.execute(request);
		return response;
	}

	public static HttpResponse doDelete(String url, Map<String, String> headers, Map<String, String> querys) throws Exception {
		HttpClient httpClient = wrapClient(url);

		HttpDelete request = new HttpDelete(buildUrl(url, querys));
		for (Map.Entry<String, String> e : headers.entrySet()) {
			request.addHeader(e.getKey(), e.getValue());
		}

		HttpResponse response = httpClient.execute(request);
		return response;
	}

	public static String buildUrl(String url, Map<String, String> querys) throws UnsupportedEncodingException {
		StringBuilder sbUrl = new StringBuilder();
		sbUrl.append(url);
		if (null != querys) {
			StringBuilder sbQuery = new StringBuilder();
			for (Map.Entry<String, String> query : querys.entrySet()) {
				if (0 < sbQuery.length()) {
					sbQuery.append("&");
				}
				if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
					sbQuery.append(query.getValue());
				}
				if (!StringUtils.isBlank(query.getKey())) {
					sbQuery.append(query.getKey());
					if (!StringUtils.isBlank(query.getValue())) {
						sbQuery.append("=");
						sbQuery.append(URLEncoder.encode(query.getValue(), "UTF-8"));
					}
				}
			}
			if (0 < sbQuery.length()) {
				sbUrl.append("?").append(sbQuery);
			}
		}

		return sbUrl.toString();
	}

	private static HttpClient wrapClient(String url) {
		if (url.startsWith("https://")) { return createSSLClientDefault(); }
		HttpClient httpClient = HttpClients.createDefault();
		return httpClient;
	}

	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

}