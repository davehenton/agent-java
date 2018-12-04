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

package com.github.continuousperftest.entity.domain;

import java.util.List;
import java.util.Objects;

/**
 * @author Aleh Struneuski
 */
public class RequestAttribute {

  private String schemeName;
  private String hostName;
  private int port;
  private String httpMethod;
  private String uri;
  private List<Header> headers;

  public String getSchemeName() {
    return schemeName;
  }

  public void setSchemeName(String schemeName) {
    this.schemeName = schemeName;
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getHttpMethod() {
    return httpMethod;
  }

  public void setHttpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public List<Header> getHeaders() {
    return headers;
  }

  public void setHeaders(List<Header> headers) {
    this.headers = headers;
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof RequestAttribute)) {
      return false;
    }
    RequestAttribute castOther = (RequestAttribute) other;
    return Objects.equals(schemeName, castOther.schemeName)
        && Objects.equals(hostName, castOther.hostName) && Objects.equals(port, castOther.port)
        && Objects.equals(httpMethod, castOther.httpMethod) && Objects.equals(uri, castOther.uri)
        && Objects.equals(headers, castOther.headers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schemeName, hostName, port, httpMethod, uri, headers);
  }
}
