package com.perf.test;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

public class AspectTestTest {

	@Test(groups = {"test", "perf"})
	public void testMethodOne() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://stackoverflow.com");
		CloseableHttpResponse response = httpclient.execute(httpGet);
		IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset());
		response.getStatusLine().getStatusCode();
	}
	
	@Test(groups = {"test", "perf"})
	public void testMethodTwo() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://stackoverflow.com");
		CloseableHttpResponse response = httpclient.execute(httpGet);
		IOUtils.toString(response.getEntity().getContent(), Charset.defaultCharset());
		response.getStatusLine().getStatusCode();
	}
}
