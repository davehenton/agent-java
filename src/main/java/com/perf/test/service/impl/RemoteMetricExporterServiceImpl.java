/*
 * Copyright 2018 Continuous Performance Test
 * 
 * 
 * This file is part of Continuous Performance Test.
 * https://github.com/continuous-perf-test/agent-java
 * 
 * Continuous Performance Test is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 * 
 * Continuous Performance Test is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with Continuous
 * Performance Test. If not, see <http://www.gnu.org/licenses/>.
 */

package com.perf.test.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.perf.test.entity.domain.Environment;
import com.perf.test.entity.domain.Perfomance;
import com.perf.test.entity.dto.PerfomanceMetric;
import com.perf.test.property.PerfTestProperties;
import com.perf.test.property.PropertyHolder;
import com.perf.test.property.TestNgProperties;
import com.perf.test.service.MetricExporterService;

/**
 * Exports metrics to remote web-service
 * 
 * @author Aleh Struneuski
 */
public class RemoteMetricExporterServiceImpl implements MetricExporterService {

  @Override
  public void export(List<Perfomance> metrics) {
    PerfTestProperties perfTestProps = PropertyHolder.getPerfTestProperties();

    Environment env = new Environment();
    env.setLaunchId(perfTestProps.launchId());
    env.setLaunchDate(perfTestProps.launchDate());
    env.setExecutionArgs(getExecutionArgs());

    List<PerfomanceMetric> perfMetrics = new CopyOnWriteArrayList<>();
    metrics.forEach(metric -> perfMetrics.add(new PerfomanceMetric(env, metric)));

    RestTemplate restTemplate = new RestTemplate(Arrays.asList(new GsonHttpMessageConverter()));
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<List<PerfomanceMetric>> entity = new HttpEntity<>(perfMetrics, headers);

    String url = perfTestProps.resultsHost() + "/api/v1/metrics";
    restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
  }

  private Map<String, String> getExecutionArgs() {
    Map<String, String> executionArgs = new HashMap<>();
    executionArgs.putAll(PropertyHolder.getReportingProperties());

    TestNgProperties testNgProps = PropertyHolder.getTestNgProperties();
    executionArgs.put("parallel", testNgProps.parallelMode());
    executionArgs.put("groups", getOrderedGroupsSplittedByComma(testNgProps.groups()));
    executionArgs.put("threadcount", String.valueOf(testNgProps.threadCount()));
    executionArgs.put("dataproviderthreadcount",
        String.valueOf(testNgProps.dataProviderThreadCount()));
    return executionArgs;
  }

  private String getOrderedGroupsSplittedByComma(String groups) {
    if (groups.trim().contains(",")) {
      List<String> groupsSplittedByComma = Arrays.asList(groups.split(","));
      groupsSplittedByComma.sort((group1, group2) -> group1.compareTo(group2));
      return String.join(",", groupsSplittedByComma);
    } else {
      return groups;
    }
  }
}
