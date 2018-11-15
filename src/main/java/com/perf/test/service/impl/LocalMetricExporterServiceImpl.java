/*
 * Copyright 2018 Continuous Performance Test
 * 
 * 
 * This file is part of Continuous Performance Test.
 * https://github.com/continuous-perf-test/agent-java
 * 
 * Report Portal is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * Report Portal is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with Report Portal. If
 * not, see <http://www.gnu.org/licenses/>.
 */

package com.perf.test.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import com.google.gson.Gson;
import com.perf.test.entity.domain.Environment;
import com.perf.test.entity.domain.Perfomance;
import com.perf.test.entity.dto.PerfomanceMetric;
import com.perf.test.property.JvmProperties;
import com.perf.test.property.PerfTestProperties;
import com.perf.test.property.PropertyHolder;
import com.perf.test.property.TestNgProperties;
import com.perf.test.service.MetricExporterService;

/**
 * Exports metrics to file system
 * 
 * @author Aleh Struneuski
 */
public class LocalMetricExporterServiceImpl implements MetricExporterService {

  @Override
  public void export(List<Perfomance> metrics) {
    PerfTestProperties perfTestProps = PropertyHolder.getPerfTestProperties();
    JvmProperties jvmProps = PropertyHolder.getJvmProperties();

    Environment env = new Environment();
    env.setLaunchId(perfTestProps.launchId());
    env.setLaunchDate(perfTestProps.launchDate());
    env.setExecutionArgs(getExecutionArgs());

    List<PerfomanceMetric> perfMetrics = new CopyOnWriteArrayList<>();
    metrics.forEach(metric -> perfMetrics.add(new PerfomanceMetric(env, metric)));

    Gson gson = new Gson();
    for (PerfomanceMetric perfMetric : perfMetrics) {
      String resultFileName = String.format("%s-result.json", UUID.randomUUID().toString());

      Path resultDir = Paths.get(jvmProps.buildDirectory(), perfTestProps.resultsDirectory());
      try {
        Path createdResultDir = Files.createDirectories(resultDir);
        Path resultFile = createdResultDir.resolve(resultFileName);

        Files.write(resultFile, gson.toJson(perfMetric).getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private Map<String, String> getExecutionArgs() {
    Map<String, String> executionArgs = new HashMap<>();
    executionArgs.putAll(PropertyHolder.getReportingProperties());

    TestNgProperties testNgProps = PropertyHolder.getTestNgProperties();
    executionArgs.put("parallel", testNgProps.parallelMode());
    executionArgs.put("groups", testNgProps.groups());
    executionArgs.put("threadcount", String.valueOf(testNgProps.threadCount()));
    executionArgs.put("dataproviderthreadcount",
        String.valueOf(testNgProps.dataProviderThreadCount()));
    return executionArgs;
  }
}
