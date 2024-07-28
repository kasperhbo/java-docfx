package com.microsoft.samples.commentinheritance;

/**
 * Mammals that give birth to young that develop within the mother's body.
 */
public interface Viviparous {

    /**
     * Give birth.
     *
     * @param numberOfOffspring the number of offspring
     */
    void giveBirth(int numberOfOffspring);

    /**
     * Get kind from Viviparous.  @return the kind
     */
    String getKind();
}
