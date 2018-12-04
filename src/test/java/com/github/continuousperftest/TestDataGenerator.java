package com.github.continuousperftest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.github.continuousperftest.entity.domain.Header;
import com.github.continuousperftest.entity.domain.Perfomance;
import com.github.continuousperftest.entity.domain.RequestAttribute;
import com.github.continuousperftest.entity.domain.ResponseAttribute;

public class TestDataGenerator {

  public static List<Perfomance> generatePerformanceMetrics(int number) {
    return IntStream.range(0, number).mapToObj(i -> generatePerformanceMetric())
        .collect(Collectors.toList());
  }

  public static Perfomance generatePerformanceMetric() {
    Perfomance metric = new Perfomance();
    metric.setExecutionTimeInMillis(new Random().nextInt());
    metric.setRequestAttribute(generateRequestAttribute());
    metric.setResponseAttribute(generateResponseAttribute());
    return metric;
  }

  private static RequestAttribute generateRequestAttribute() {
    RequestAttribute requestAttribute = new RequestAttribute();
    requestAttribute.setSchemeName("HTTP");
    requestAttribute.setHostName("perf-test.coms");
    requestAttribute.setPort(8080);
    requestAttribute.setHttpMethod("GET");
    requestAttribute.setUri("/api/v1");
    requestAttribute.setHeaders(Arrays.asList(new Header("rqHeader", "rqValue")));
    return requestAttribute;
  }

  private static ResponseAttribute generateResponseAttribute() {
    ResponseAttribute responseAttribute = new ResponseAttribute();
    responseAttribute.setProtocolVersion("1.1");
    responseAttribute.setReasonPhrase("OK");
    responseAttribute.setStatusCode(200);
    responseAttribute.setHeaders(Arrays.asList(new Header("rpHeader", "rpValue")));
    return responseAttribute;
  }
}
