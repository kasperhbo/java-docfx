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

import java.util.List;

/**
 * The type Project contents.
 */
//  wraps guides + tocItems with product name hierarchy
//  [name: project-name, [items: [...]]]
public class ProjectContents {
  private final String name;
  private final List<Object> items;

    /**
     * Instantiates a new Project contents.
     *
     * @param name  the name
     * @param items the items
     */
    public ProjectContents(String name, List<Object> items) {
    this.name = name;
    this.items = items;
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
     * Gets items.
     *
     * @return the items
     */
    public List<Object> getItems() {
    return items;
  }
}
