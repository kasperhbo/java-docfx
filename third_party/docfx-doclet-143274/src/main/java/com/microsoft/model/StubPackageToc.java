package com.microsoft.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The type Stub package toc.
 */
public class StubPackageToc {
    /**
     * The Stubs.
     */
    static final String STUBS = "Stubs";
    /**
     * The Settings.
     */
    static final String SETTINGS = "Settings";
    /**
     * The Callable factories.
     */
    static final String CALLABLE_FACTORIES = "Callable factories";
    /**
     * The Uncategorized.
     */
    static final String UNCATEGORIZED = "Other";

  private final LinkedHashMap<String, List<TocItem>> visibleCategories = new LinkedHashMap<>();

    /**
     * Instantiates a new Stub package toc.
     */
    public StubPackageToc() {
    // Order here determines final organization order.
    visibleCategories.put(STUBS, new ArrayList<>());
    visibleCategories.put(SETTINGS, new ArrayList<>());
    visibleCategories.put(CALLABLE_FACTORIES, new ArrayList<>());
    visibleCategories.put(UNCATEGORIZED, new ArrayList<>());
  }

    /**
     * Add stub.
     *
     * @param tocItem the toc item
     */
    public void addStub(TocItem tocItem) {
    visibleCategories.get(STUBS).add(tocItem);
  }

    /**
     * Add settings.
     *
     * @param tocItem the toc item
     */
    public void addSettings(TocItem tocItem) {
    visibleCategories.get(SETTINGS).add(tocItem);
  }

    /**
     * Add callable factory.
     *
     * @param tocItem the toc item
     */
    public void addCallableFactory(TocItem tocItem) {
    visibleCategories.get(CALLABLE_FACTORIES).add(tocItem);
  }

    /**
     * Add uncategorized.
     *
     * @param tocItem the toc item
     */
    public void addUncategorized(TocItem tocItem) {
    visibleCategories.get(UNCATEGORIZED).add(tocItem);
  }

    /**
     * Build a list of TocItems for inclusion in the library's table of contents  @return the list
     */
    public List<TocItem> toList() {
    List<TocItem> toc = new ArrayList<>();

    visibleCategories.forEach(
        (name, category) -> {
          if (!category.isEmpty()) {
            toc.add(createCategory(name, category));
          }
        });

    return toc;
  }

  private TocItem createCategory(String name, List<TocItem> items) {
    TocItem category = new TocItem(name, name, null);
    items.sort(Comparator.comparing(TocItem::getName));
    category.getItems().addAll(items);
    return category;
  }
}
