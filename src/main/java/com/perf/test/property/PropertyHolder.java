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

package com.perf.test.property;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.aeonbits.owner.ConfigCache;

/**
 * Holds all the classes receiving properties while launching tests. Creates properties that each
 * metric requires while being exported
 * 
 * @author Aleh Struneuski
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class PropertyHolder {

  private static final Map<String, String> JVM_PROPS;
  private static final Map<String, String> REPORTING_PROPS;

  static {
    JVM_PROPS = new HashMap<>((Map) System.getProperties());
    REPORTING_PROPS = new HashMap<>(findReportingProperties(JVM_PROPS));
  }

  public static JvmProperties getJvmProperties() {
    return ConfigCache.getOrCreate(JvmProperties.class, JVM_PROPS);
  }

  public static TestNgProperties getTestNgProperties() {
    return ConfigCache.getOrCreate(TestNgProperties.class, JVM_PROPS);
  }

  public static PerfTestProperties getPerfTestProperties() {
    return ConfigCache.getOrCreate(PerfTestProperties.class, JVM_PROPS);
  }

  public static Map<String, String> getReportingProperties() {
    return REPORTING_PROPS;
  }

  private static Map<String, String> findReportingProperties(Map<String, String> jvmProps) {
    Map<String, String> reportingProperties = new HashMap<>();

    String reportingArgsPattern = "perf-test.reporting.";
    for (Entry<String, String> entry : jvmProps.entrySet()) {
      String key = entry.getKey();
      if (key != null && key.startsWith(reportingArgsPattern)) {
        key = key.replace(reportingArgsPattern, "");
        reportingProperties.put(key, entry.getValue());
      }
    }
    return reportingProperties;
  }
}
