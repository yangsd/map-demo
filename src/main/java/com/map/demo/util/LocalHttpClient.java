package com.map.demo.util;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

public class LocalHttpClient {

	protected static CloseableHttpClient httpClient = HttpClientFactory.createHttpClient(100,10);

	private static Map<String,CloseableHttpClient> httpClient_mchKeyStore = new HashMap<String, CloseableHttpClient>();

	public static void init(int maxTotal,int maxPerRoute){
		try {
			httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpClient = HttpClientFactory.createHttpClient(maxTotal,maxPerRoute);
	}

	/**
	 * 初始化   MCH HttpClient KeyStore
	 * @param mch_id
	 * @param keyStoreFilePath
	 */
	public static void initMchKeyStore(String mch_id,String keyStoreFilePath){
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(keyStoreFilePath));
			keyStore.load(instream,mch_id.toCharArray());
			instream.close();
			CloseableHttpClient httpClient = HttpClientFactory.createKeyMaterialHttpClient(keyStore, mch_id);
			httpClient_mchKeyStore.put(mch_id, httpClient);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static CloseableHttpResponse execute(HttpUriRequest request){
		try {
			return httpClient.execute(request,HttpClientContext.create());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T execute(HttpUriRequest request,ResponseHandler<T> responseHandler){
		try {
			return httpClient.execute(request, responseHandler,HttpClientContext.create());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 数据返回自动JSON对象解析
	 * @param request
	 * @param clazz
	 * @return
	 */
	public static <T> T executeJsonResult(HttpUriRequest request,Class<T> clazz){
		return execute(request,JsonResponseHandler.createResponseHandler(clazz));
	}

	/**
	 * 数据返回自动JSON对象解析(新增参数：字符编码)
	 * @param request
	 * @param clazz
	 * @param chartset
	 * @param <T>
	 * @return
	 */
	public static <T> T executeJsonResult(HttpUriRequest request,Class<T> clazz,String chartset){
		return execute(request,JsonResponseHandler.createResponseHandler(clazz,chartset));
	}

}
