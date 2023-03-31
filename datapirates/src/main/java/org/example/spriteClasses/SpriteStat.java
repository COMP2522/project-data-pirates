package org.example.spriteClasses;

public class SpriteStat {

  /* Enemy's Passive Damage Bonus! */
  /* World = 0*/
  public static final float DATA_MAGIC = 6.00F;

  /* Bottom World Hallway */
  public static final float PIRAETH_SPELL = 14.00F;

  /* Throne Room */
  public static final float KATHY_FFIELD = 50.60F;

  /*
   * Player's Passive Damage Bonus!
   */
  /* Just cause ~ */
  public static final float CST_BLESSING = 43F;

  /* Melee Damage. */
  public static final float SPACE_PUNCH = 10F;

  /* DeOppier */
  public static final float THE_DEOPPIER = 0.50F;

  /* Used for Projectiles */

  public static final float COMPUTER_GRAVITY = 120F;
  private float maxHealth;
  private float health;

  private float def;

  private float damage;

  private Sprite s;

  public SpriteStat(Sprite s, float HP, float DEF, float DMG) {
    health = HP;
    def = DEF;
    damage = DMG;
    this.s = s;
    maxHealth = health;
  }

  public SpriteStat(Sprite s, float DMG) {

  }


  /**
   * Damage to the Enemy.
   * Works for Player and Bullet.
   * @param a
   * @return
   */
  public float getCalculatedDamage(Enemy a) {
    SpriteStat e = a.getEnemyStat();
    float effectiveHealth = e.health + e.health * ((e.def + 25) / 100);

    if (s instanceof Player) {
//      float effectiveDMG = SPACE_PUNCH *
      return effectiveHealth - SPACE_PUNCH * THE_DEOPPIER;
    }
    else if (s instanceof Projectile) {
      float effectiveDMG = ((Projectile) s).getWeapon().getDmg() * ((THE_DEOPPIER * THE_DEOPPIER * COMPUTER_GRAVITY) / 100);
      return effectiveDMG - (effectiveDMG * THE_DEOPPIER);
    }
    // Calculated using HP, DEF, and DMG
    return 0;
  }

  /**
   * Enemy to Player.
   *
   * @param p
   * @return
   */
  public float getCalculatedDamage(Player p) {
    SpriteStat pl = p.getPlayerStat();
//    float effectiveHealth = pl.maxHealth + pl.maxHealth * ((pl.def + 125) / 100);
    float effectiveDMG = ((Enemy) s).getEnemyStat().damage * ((SPACE_PUNCH * THE_DEOPPIER) / 20);
//    System.out.println(effectiveDMG + " and " + effectiveHealth);
//    System.out.println((effectiveDMG - 100 * (effectiveDMG / pl.maxHealth)));
    pl.health-= effectiveDMG;
//    effectiveHealth-= pl.maxHealth * (int) (effectiveHealth / pl.maxHealth);
    return pl.health;
  }

  public float getMaxHealth() {
    return maxHealth;
  }

  public float getDamage() {
    return damage;
  }

  public float getDef() {
    return def;
  }

  public float getHealth() {
    return health;
  }

  public void setHealth(float health) {
    this.health = health;
  }

  public void setDamage(float damage) {
    this.damage = damage;
  }

  public void setDef(float def) {
    this.def = def;
  }
}
