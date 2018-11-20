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

import org.aeonbits.owner.Config;

/**
 * Receives the required properties that are passed on to system properties while launching tests
 *
 * @author Aleh Struneuski
 */
public interface PerfTestProperties extends Config {

  @Key("perf-test.isEnabled")
  @DefaultValue("false")
  Boolean isEnabled();

  @Key("perf-test.exporter")
  @DefaultValue("local")
  String exporterType();

  @Key("perf-test.results.host")
  @DefaultValue("http://127.0.0.1:8095")
  String resultsHost();

  @Key("perf-test.results.directory")
  @DefaultValue("perf-test-results")
  String resultsDirectory();

  @Key("perf-test.launch.date")
  String launchDate();

  @Key("perf-test.launch.id")
  String launchId();
}
