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

package com.perf.test.listener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.testng.IExecutionListener;
import com.perf.test.property.PropertyHolder;
import com.perf.test.queue.MetricQueue;
import com.perf.test.queue.impl.PerfomanceMetricQueue;
import com.perf.test.service.MetricExporterService;
import com.perf.test.service.MetricExporter;
import com.perf.test.service.MetricExporterFactory;
import com.perf.test.thread.PerfMetricConsumerTask;

/**
 * Starts a thread pool to be prepared to report metrics when metrics appear in a metric queue. Once
 * test execution finishes, all the metrics are taken out of the metric queue and reported
 *
 * @author Aleh Struneuski
 */
public class TestNgExecutionListener implements IExecutionListener {

  private static final int THREAD_NUMBER = 5;

  private static final MetricExporterService METRIC_EXPORTER_SERVICE = getMetricExporterService();
  private static final MetricQueue METRIC_QUEUE = PerfomanceMetricQueue.getInstance();
  private static final ExecutorService THREAD_POOL_EXE =
      Executors.newFixedThreadPool(THREAD_NUMBER);

  @Override
  public void onExecutionStart() {
    String launchId = UUID.randomUUID().toString() + System.currentTimeMillis();
    String launchDate =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

    System.setProperty("perf-test.launch.id", launchId);
    System.setProperty("perf-test.launch.date", launchDate);

    for (int i = 0; i <= THREAD_NUMBER; i++) {
      THREAD_POOL_EXE.execute(new PerfMetricConsumerTask(METRIC_QUEUE, METRIC_EXPORTER_SERVICE));
    }
  }

  @Override
  public void onExecutionFinish() {
    METRIC_EXPORTER_SERVICE.export(METRIC_QUEUE.pollAll());
  }

  private static MetricExporterService getMetricExporterService() {
    String exporterType = PropertyHolder.getPerfTestProperties().exporterType();
    return MetricExporterFactory.createExporter(MetricExporter.valueOf(exporterType.toUpperCase()));
  }
}
