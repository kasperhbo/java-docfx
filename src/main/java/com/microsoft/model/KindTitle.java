/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.model;

/**
 * The type Kind title.
 */
public class KindTitle {
  private final String elementKind;
  private final String title;

    /**
     * Instantiates a new Kind title.
     *
     * @param elementKind the element kind
     * @param title       the title
     */
    public KindTitle(String elementKind, String title) {
    this.elementKind = elementKind;
    this.title = title;
  }

    /**
     * Gets element kind.
     *
     * @return the element kind
     */
    public String getElementKind() {
    return elementKind;
  }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
    return title;
  }
}
