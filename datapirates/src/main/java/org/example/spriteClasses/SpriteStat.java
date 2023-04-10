package org.example.spriteClasses;

/**
 * Contains the stat system, health and damage.
 * Also contains the calculated damage.
 *
 * @author Data Pirates Team.
 *
 * @version JDK 18.
 */
public class SpriteStat {
  /* Melee Damage. */
  public static final float SPACE_PUNCH = 10F;
  /* DeOppier */
  public static final float THE_DEOPPIER = 0.50F;
  private final float maxHealth;
  private float health;
  private final float damage;
  private final Sprite s;

  /**
   * Construct a stat object.
   *
   * @param s the entity we will put the stat on.
   * @param HP health of the entity.
   * @param DMG entity's damage.
   *
   */
  public SpriteStat(Sprite s, float HP, float DMG) {
    health = HP;
    damage = DMG;
    this.s = s;
    maxHealth = health;
  }

  /**
   * Enemy to Player.
   *
   * @param p Player object.
   * @return the new health of the player.
   *
   */
  public float getCalculatedDamage(Player p) {
    SpriteStat pl = p.getPlayerStat();
    float effectiveDMG = ((Enemy) s).getEnemyStat().damage * ((SPACE_PUNCH * THE_DEOPPIER) / 20);
    pl.health -= effectiveDMG;
    return pl.health;
  }

  /* TODO: Getters and Setters beyond this point. */

  public float getMaxHealth() {
    return maxHealth;
  }


  public float getHealth() {
    return health;
  }

  public void setHealth(float health) {
    this.health = health;
  }
}
