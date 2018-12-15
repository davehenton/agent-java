package com.github.continuousperftest.queue;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.github.continuousperftest.TestDataGenerator;
import com.github.continuousperftest.entity.domain.Perfomance;
import com.github.continuousperftest.queue.MetricQueue;
import com.github.continuousperftest.queue.impl.PerfomanceMetricQueue;

public class MetricQueueTest {

  private List<Perfomance> expMetrics;
  private MetricQueue metricQueue;

  @BeforeMethod
  public void init() {
    expMetrics = TestDataGenerator.generatePerformanceMetrics(5);

    metricQueue = PerfomanceMetricQueue.getInstance();
    expMetrics.stream().forEach(metric -> metricQueue.offer(metric));
  }

  @Test
  public void pollAllShouldReturnAllExistentPerformanceMertrics() {
    List<Perfomance> actMetrics = metricQueue.pollAll();
    Assert.assertEquals(actMetrics, expMetrics);
  }

  @Test
  public void pollShouldReturnRequestedPerformanceMetricsNumber() {
    int numberForPolling = expMetrics.size() / 2;
    List<Perfomance> actMetrics = metricQueue.poll(numberForPolling);
    Assert.assertEquals(actMetrics, expMetrics.subList(0, numberForPolling));
  }
}
