package com.perf.test.aspect.httpclient;

import com.perf.test.property.PropertyHolder;

public abstract class HttpMethodExecuteAspect {

  protected static final boolean IS_ENABLED = PropertyHolder.getPerfTestProperties().isEnabled();

}
