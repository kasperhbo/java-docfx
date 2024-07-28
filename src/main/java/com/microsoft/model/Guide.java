/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.microsoft.model;

/**
 * The type Guide.
 */
//  for including guides with toc items
// [items: [name: Overview,
//          href: overview.html,
//          name: Version history,
//          href: history.md,
//          name: name,
//          uid: package.name,...]]
public class Guide {
  private final String name;
  private final String href;

    /**
     * Instantiates a new Guide.
     *
     * @param name the name
     * @param href the href
     */
    public Guide(String name, String href) {
    this.name = name;
    this.href = href;
  }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
    return name;
  }

    /**
     * Gets href.
     *
     * @return the href
     */
    public String getHref() {
    return href;
  }
}