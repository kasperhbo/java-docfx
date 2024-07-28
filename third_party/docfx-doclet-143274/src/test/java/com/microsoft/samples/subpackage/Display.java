package com.microsoft.samples.subpackage;

import java.io.Serializable;
import java.util.List;

/**
 * Do you see some <code>First</code> code block?
 *
 * <p>Or this {@code Second} code block?
 *
 * @param <T> the type parameter
 * @param <R> the type parameter
 */
public interface Display<T, R> extends Serializable, List<Person<T>> {

    /**
     * Show.
     */
    void show();

    /**
     * Hide.
     */
    void hide();
}
