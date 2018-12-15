/*
 * Copyright 2018 Continuous Performance Test
 * 
 * 
 * This file is part of Continuous Performance Test.
 * https://github.com/continuousperftest/agent-java
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

package com.github.continuousperftest.thread;

import com.github.continuousperftest.entity.domain.Perfomance;
import com.github.continuousperftest.queue.MetricQueue;
import com.github.continuousperftest.service.MetricExporterService;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Takes metrics out of the metric queue and report them based on the set exporter.
 *
 * @author Aleh Struneuski
 */
public class PerfMetricConsumerTask implements Runnable {

  private static final long SEC_PAUSE = 100;
  private static final int NUMBER_OF_METRICS = 40;

  private MetricQueue metricQueue;
  private MetricExporterService metricExporterService;

  public PerfMetricConsumerTask(MetricQueue metricQueue,
      MetricExporterService metricExporterService) {
    this.metricQueue = metricQueue;
    this.metricExporterService = metricExporterService;
  }

  public void run() {
    while (true) {
      try {
        List<Perfomance> metrics = metricQueue.poll(NUMBER_OF_METRICS);

        if (metrics == null || metrics.isEmpty()) {
          TimeUnit.MILLISECONDS.sleep(SEC_PAUSE);
        } else {
          metricExporterService.export(metrics);
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
