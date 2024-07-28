package com.microsoft.samples;

import com.microsoft.samples.subpackage.Person;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/**
 * Hero is the main entity we will be using to something
 *
 * @author Captain America
 */
public class SuperHero extends Person implements Serializable, Cloneable {

  /** The public name of a hero that is common knowledge */
  private String heroName;

  private String uniquePower;
  private int health;
  private int defense;
    /**
     * The Hobby.
     */
    String hobby;

    /**
     * The Some public string.
     */
    public final String SOME_PUBLIC_STRING = "So important string value";

    /**
     * Instantiates a new Super hero.
     */
    public SuperHero() {}

    /**
     * Instantiates a new Super hero.
     *
     * @param heroName    the hero name
     * @param uniquePower the unique power
     * @param health      the health
     * @param defense     the defense
     */
    public SuperHero(String heroName, String uniquePower, int health, int defense) {
    this.heroName = heroName;
    this.uniquePower = uniquePower;
    this.health = health;
    this.defense = defense;
  }

    /**
     * This is a simple description of the method. . . <a
     * href="http://www.supermanisthegreatest.com">Superman!</a>
     *
     * @param incomingDamage the amount of incoming damage for {@link SuperHero}
     * @param damageType     type of damage with similar word damageTypeLong, sure
     * @return the amount of health hero has after attack
     * @throws IllegalArgumentException when incomingDamage is negative and thanks for {@link
     *                                  Exception}
     * @version 1.2
     * @see <a href="http://www.link_to_jira/HERO-402">HERO-402</a>
     * @since 1.0
     * @deprecated As of version 1.1, use . . . instead
     */
    @Deprecated
  public int successfullyAttacked(int incomingDamage, String damageType)
      throws IllegalArgumentException {
    // do things
    if (incomingDamage < 0) {
      throw new IllegalArgumentException("Cannot cause negative damage");
    }
    return 0;
  }

    /**
     * Gets hero name.
     *
     * @return the hero name
     */
    public String getHeroName() {
    return heroName;
  }

    /**
     * Sets hero name.
     *
     * @param heroName the hero name
     */
    public void setHeroName(String heroName) {
    this.heroName = heroName;
  }

  /**
   * Get capitalized last name. But it's not the end, because we add a lot of text to make this a
   * multiline comment
   *
   * @return lastName in uppercase. But it's not the end, because we add a lot of text to make this
   *     a multiline comment
   */
  @Override
  public String getLastName() {
    return StringUtils.upperCase(super.getLastName());
  }

    /**
     * Gets unique power.
     *
     * @return the unique power
     */
    public String getUniquePower() {
    return uniquePower;
  }

    /**
     * Sets unique power.
     *
     * @param uniquePower the unique power
     */
    public void setUniquePower(String uniquePower) {
    this.uniquePower = uniquePower;
  }

    /**
     * Gets health.
     *
     * @return the health
     */
    protected int getHealth() {
    return health;
  }

    /**
     * Sets health.
     *
     * @param health the health
     */
    protected void setHealth(int health) {
    this.health = health;
  }

    /**
     * Gets defense.
     *
     * @return the defense
     */
    public int getDefense() {
    return defense;
  }

    /**
     * Sets defense.
     *
     * @param defense the defense
     */
    public void setDefense(int defense) {
    this.defense = defense;
  }

  private void setHobby(String hobby) {
    this.hobby = hobby;
  }

    /**
     * Gets hobby.
     *
     * @return the hobby
     */
    String getHobby() {
    return hobby;
  }
}
