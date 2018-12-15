package com.github.continuousperftest.service;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.continuousperftest.service.MetricExporter;
import com.github.continuousperftest.service.MetricExporterFactory;
import com.github.continuousperftest.service.MetricExporterService;
import com.github.continuousperftest.service.impl.LocalMetricExporterServiceImpl;
import com.github.continuousperftest.service.impl.RemoteMetricExporterServiceImpl;

public class MetricExporterFactoryTest {

  @Test
  public void factoryShouldCreateLocalMetricExporterService() {
    MetricExporterService metricExporter =
        MetricExporterFactory.createExporter(MetricExporter.LOCAL);
    Assert.assertTrue(
        metricExporter.getClass().isAssignableFrom(LocalMetricExporterServiceImpl.class));
  }

  @Test
  public void factoryShouldCreateRemoteMetricExporterService() {
    MetricExporterService metricExporter =
        MetricExporterFactory.createExporter(MetricExporter.REMOTE);
    Assert.assertTrue(
        metricExporter.getClass().isAssignableFrom(RemoteMetricExporterServiceImpl.class));
  }

  @Test
  public void factoryShouldCreateOptedMetricExporterService() {
    MetricExporterService metricExporter =
        MetricExporterFactory.createExporter(MetricExporter.OPTED);
    Assert.assertTrue(
        metricExporter.getClass().isAssignableFrom(OptedMetricExporterServiceImpl.class));
  }
}
