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

package com.github.continuousperftest.aspect.httpclient;

import com.github.continuousperftest.property.PropertyHolder;

/**
 * This is the base class of all HttpMethodExecuteAspect Classes. It contains configuration and
 * things these classes share in common.
 * 
 * @author Aleh Struneuski
 */
public abstract class HttpMethodExecuteAspect {

  protected static final boolean IS_ENABLED = PropertyHolder.getPerfTestProperties().isEnabled();

}
