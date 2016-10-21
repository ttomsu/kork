/*
 * Copyright 2016 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.kork.eureka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.Objects;

@ConfigurationProperties("eureka")
public class EurekaConfigurationProperties {
  public static class Namespace {
    private String namespace;

    public Namespace(String namespace) {
      this.namespace = fixNamespace(namespace);
    }

    public String getNamespace() {
      return namespace;
    }

    public void setNamespace(String namespace) {
      this.namespace = fixNamespace(namespace);
    }

    private static String fixNamespace(String namespace) {
      Objects.requireNonNull(namespace, "namespace");
      return namespace.endsWith(".") ? namespace : namespace + ".";
    }
  }

  @NestedConfigurationProperty
  private final Namespace instance = new Namespace("netflix.appinfo.");

  @NestedConfigurationProperty
  private final Namespace client = new Namespace("netflix.discovery.");

  public Namespace getInstance() {
    return instance;
  }

  public Namespace getClient() {
    return client;
  }
}
