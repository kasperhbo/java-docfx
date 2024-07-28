package com.microsoft.model;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Toc item.
 */
public class TocItem {

    private String uid;
    private String name;

    private String status;

    private String href;

    private String        heading;
    private List<TocItem> items = new ArrayList<>();

    private Boolean packageOverview = false;//todo: unused?

    /**
     * Instantiates a new Toc item.
     *
     * @param uid  the uid
     * @param name the name
     */
    public TocItem(String uid, String name) {
        this.uid  = uid;
        this.name = name;
    }

    /**
     * Instantiates a new Toc item.
     *
     * @param uid             the uid
     * @param name            the name
     * @param href            the href
     * @param packageOverview the package overview
     */
    public TocItem(String uid, String name, String href, boolean packageOverview) {
        this.uid             = uid;
        this.name            = name;
        this.href            = href;
        this.packageOverview = packageOverview;
    }

    /**
     * Instantiates a new Toc item.
     *
     * @param uid    the uid
     * @param name   the name
     * @param status the status
     */
    public TocItem(String uid, String name, String status) {
        this.uid    = uid;
        this.name   = name;
        this.status = status;
    }

    /**
     * Instantiates a new Toc item.
     *
     * @param heading the heading
     */
    public TocItem(String heading) {
        this.heading = heading;
    }

    /**
     * Gets href.
     *
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
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
    public List<TocItem> getItems() {
        return items;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets heading.
     *
     * @return the heading
     */
    public String getHeading() {
        return heading;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(TocItem.class)
                .add("uid", uid).toString();
    }
}
