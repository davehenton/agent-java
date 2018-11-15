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

package com.perf.test.queue.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.perf.test.entity.domain.Perfomance;
import com.perf.test.queue.MetricQueue;

/**
 * Implements operations for a metric queue
 * 
 * @author Aleh Struneuski
 */
public class PerfomanceMetricQueue implements MetricQueue {

  private static final PerfomanceMetricQueue INSTANCE = new PerfomanceMetricQueue();

  private Queue<Perfomance> queue;
  private Lock lock;

  private PerfomanceMetricQueue() {
    this.queue = new ConcurrentLinkedQueue<>();
    this.lock = new ReentrantLock();
  }

  public static PerfomanceMetricQueue getInstance() {
    return INSTANCE;
  }

  @Override
  public void offer(Perfomance metric) {
    queue.add(metric);
  }

  @Override
  public List<Perfomance> pollAll() {
    List<Perfomance> metrics = new ArrayList<>();
    lock.lock();
    try {
      int queueSize = queue.size();
      for (int i = 0; i != queueSize; i++) {
        metrics.add(queue.poll());
      }
    } finally {
      lock.unlock();
    }
    return metrics;
  }

  @Override
  public List<Perfomance> poll(int number) {
    List<Perfomance> metrics = new ArrayList<>();
    lock.lock();
    try {
      if (queue.size() >= number) {
        for (int i = 0; i < number; i++) {
          metrics.add(queue.remove());
        }
      }
    } finally {
      lock.unlock();
    }
    return metrics;
  }
}
