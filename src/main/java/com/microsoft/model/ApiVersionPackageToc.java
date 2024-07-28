package com.microsoft.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The type Api version package toc.
 */
public class ApiVersionPackageToc {
    /**
     * The Clients.
     */
    static final String CLIENTS = "Clients";
    /**
     * The Requests and responses.
     */
    static final String REQUESTS_AND_RESPONSES = "Requests and responses";
    /**
     * The Settings.
     */
    static final String SETTINGS = "Settings";
    /**
     * The All others.
     */
    static final String ALL_OTHERS = "All other classes and interfaces";
    /**
     * The Builders.
     */
    static final String BUILDERS = "Builders";
    /**
     * The Enums.
     */
    static final String ENUMS = "Enums";
    /**
     * The Interfaces.
     */
    static final String INTERFACES = "Interfaces";
    /**
     * The Messages.
     */
    static final String MESSAGES = "Messages";
    /**
     * The Exceptions.
     */
    static final String EXCEPTIONS = "Exceptions";
    /**
     * The Paging.
     */
    static final String PAGING = "Paging";
    /**
     * The Resource names.
     */
    static final String RESOURCE_NAMES = "Resource names";
    /**
     * The Uncategorized.
     */
    static final String UNCATEGORIZED = "Other";

  private final LinkedHashMap<String, List<TocItem>> visibleCategories = new LinkedHashMap<>();
  private final LinkedHashMap<String, List<TocItem>> hiddenCategories = new LinkedHashMap<>();

    /**
     * Instantiates a new Api version package toc.
     */
    public ApiVersionPackageToc() {
    // Order here determines final organization order.
    visibleCategories.put(CLIENTS, new ArrayList<>());
    visibleCategories.put(SETTINGS, new ArrayList<>());
    visibleCategories.put(REQUESTS_AND_RESPONSES, new ArrayList<>());

    hiddenCategories.put(BUILDERS, new ArrayList<>());
    hiddenCategories.put(ENUMS, new ArrayList<>());
    hiddenCategories.put(EXCEPTIONS, new ArrayList<>());
    hiddenCategories.put(MESSAGES, new ArrayList<>());
    hiddenCategories.put(PAGING, new ArrayList<>());
    hiddenCategories.put(RESOURCE_NAMES, new ArrayList<>());
    hiddenCategories.put(INTERFACES, new ArrayList<>());
    hiddenCategories.put(UNCATEGORIZED, new ArrayList<>());
  }

    /**
     * Add client.
     *
     * @param tocItem the toc item
     */
    public void addClient(TocItem tocItem) {
    visibleCategories.get(CLIENTS).add(tocItem);
  }

    /**
     * Add request or response.
     *
     * @param tocItem the toc item
     */
    public void addRequestOrResponse(TocItem tocItem) {
    visibleCategories.get(REQUESTS_AND_RESPONSES).add(tocItem);
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
     * Add builder.
     *
     * @param tocItem the toc item
     */
    public void addBuilder(TocItem tocItem) {
    hiddenCategories.get(BUILDERS).add(tocItem);
  }

    /**
     * Add enum.
     *
     * @param tocItem the toc item
     */
    public void addEnum(TocItem tocItem) {
    hiddenCategories.get(ENUMS).add(tocItem);
  }

    /**
     * Add exception.
     *
     * @param tocItem the toc item
     */
    public void addException(TocItem tocItem) {
    hiddenCategories.get(EXCEPTIONS).add(tocItem);
  }

    /**
     * Add interface.
     *
     * @param tocItem the toc item
     */
    public void addInterface(TocItem tocItem) {
    hiddenCategories.get(INTERFACES).add(tocItem);
  }

    /**
     * Add message.
     *
     * @param tocItem the toc item
     */
    public void addMessage(TocItem tocItem) {
    hiddenCategories.get(MESSAGES).add(tocItem);
  }

    /**
     * Add uncategorized.
     *
     * @param tocItem the toc item
     */
    public void addUncategorized(TocItem tocItem) {
    hiddenCategories.get(UNCATEGORIZED).add(tocItem);
  }

    /**
     * Add paging.
     *
     * @param tocItem the toc item
     */
    public void addPaging(TocItem tocItem) {
    hiddenCategories.get(PAGING).add(tocItem);
  }

    /**
     * Add resource name.
     *
     * @param tocItem the toc item
     */
    public void addResourceName(TocItem tocItem) {
    hiddenCategories.get(RESOURCE_NAMES).add(tocItem);
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

    TocItem allOthers = new TocItem(ALL_OTHERS, ALL_OTHERS, null);
    hiddenCategories.forEach(
        (name, category) -> {
          if (!category.isEmpty()) {
            allOthers.getItems().add(createCategory(name, category));
          }
        });
    if (allOthers.getItems().size() > 0) {
      toc.add(allOthers);
    }

    return toc;
  }

  private TocItem createCategory(String name, List<TocItem> items) {
    TocItem category = new TocItem(name, name, null);
    items.sort(Comparator.comparing(TocItem::getName));
    category.getItems().addAll(items);
    return category;
  }
}
