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
 * Represents attributes of HTTP response.
 * 
 * @author Aleh Struneuski
 */
public class ResponseAttribute {

  private int statusCode;
  private String reasonPhrase;
  private String protocolVersion;
  private List<Header> headers;

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getReasonPhrase() {
    return reasonPhrase;
  }

  public void setReasonPhrase(String reasonPhrase) {
    this.reasonPhrase = reasonPhrase;
  }

  public String getProtocolVersion() {
    return protocolVersion;
  }

  public void setProtocolVersion(String protocolVersion) {
    this.protocolVersion = protocolVersion;
  }

  public List<Header> getHeaders() {
    return headers;
  }

  public void setHeaders(List<Header> headers) {
    this.headers = headers;
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof ResponseAttribute)) {
      return false;
    }
    ResponseAttribute castOther = (ResponseAttribute) other;
    return Objects.equals(statusCode, castOther.statusCode)
        && Objects.equals(reasonPhrase, castOther.reasonPhrase)
        && Objects.equals(protocolVersion, castOther.protocolVersion)
        && Objects.equals(headers, castOther.headers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode, reasonPhrase, protocolVersion, headers);
  }
}
