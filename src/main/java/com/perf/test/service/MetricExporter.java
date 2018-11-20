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

package com.perf.test.service;

/**
 * Specifies types of existent metric exporter services
 * 
 * @author Aleh Struneuski
 */
public enum MetricExporter {
  LOCAL, REMOTE, OPTED;

  MetricExporter() {}
}
