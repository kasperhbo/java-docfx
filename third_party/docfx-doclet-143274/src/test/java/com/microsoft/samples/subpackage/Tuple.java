package com.microsoft.samples.subpackage;

/**
 * The type Tuple.
 *
 * @param <T1> the type parameter
 * @param <T2> the type parameter
 */
public class Tuple<T1, T2> {
  private T1 item1;

  private T2 item2;

    /**
     * Instantiates a new Tuple.
     *
     * @param item1 the item 1
     * @param item2 the item 2
     */
    public Tuple(T1 item1, T2 item2) {
    this.item1 = item1;
    this.item2 = item2;
  }

    /**
     * Gets item 1.
     *
     * @return the item 1
     */
    public T1 getItem1() {
    return item1;
  }

    /**
     * Sets item 1.
     *
     * @param item1 the item 1
     */
    public void setItem1(T1 item1) {
    this.item1 = item1;
  }

    /**
     * Gets item 2.
     *
     * @return the item 2
     */
    public T2 getItem2() {
    return item2;
  }

    /**
     * Sets item 2.
     *
     * @param item2 the item 2
     */
    public void setItem2(T2 item2) {
    this.item2 = item2;
  }
}
