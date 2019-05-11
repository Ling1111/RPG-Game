import java.util.Random;

public class Stats {

	/**
	 * the hit points
	 */
	private int HP;
	
	/**
	 * maximum hit points
	 */
	private int maxHP;
	
	/**
	 * the minimum length of time the character has to wait between attacks, in milliseconds
	 */
	private int cooldown;
	
	/**
	 * the maximum amount of damage the unit can do when attacking
	 */
	private int maxDamage;
	
	/**
	 *  store the status if the character is death
	 */
	private boolean isDeath;


	/**
	 * @param hP
	 * @param maxHP
	 * @param cooldown
	 * @param maxDamage
	 * @param isDeath
	 */
	public Stats(int maxHP, int cooldown, int maxDamage) {
		this.HP = maxHP;
		this.maxHP = maxHP;
		this.cooldown = cooldown;
		this.maxDamage = maxDamage;
		this.isDeath = false;
	}

	/**
	 * @return the hP
	 */
	public int getHP() {
		return HP;
	}

	/**
	 * @param hP the hP to set
	 */
	public void setHP(int hP) {
		this.HP = hP>maxHP?maxHP : hP;
	}

	/**
	 * @return the maxHP
	 */
	public int getMaxHP() {
		return maxHP;
	}

	/**
	 * @param maxHP the maxHP to set
	 */
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	/**
	 * @return the cooldown
	 */
	public int getCooldown() {
		return cooldown;
	}

	/**
	 * @param cooldown the cooldown to set
	 */
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	/**
	 * @return the maxDamage
	 */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	 * @param maxDamage the maxDamage to set
	 */
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}
	
	
	/**
	 * @return the isDeath
	 */
	public boolean isDeath() {
		return isDeath;
	}

	/**
	 * @param isDeath the isDeath to set
	 */
	public void setDeath(boolean isDeath) {
		this.isDeath = isDeath;
	}

	/**
	 * @return if HP equals maxHP
	 */
	public boolean isFullHealth() {
		return this.HP == this.maxHP;
	}
	
	/**
	 * set value of HP to maxHP
	 */
	public void setFullHealth() {
		this.HP = this.maxHP;
	}
	
    /**
     * @return random value of damage between 0 and maxDamage
     */
    public int getRandomDamage() {
        return (new Random()).nextInt(this.getMaxDamage() + 1);
    }
    
    /**
     * @param damage
     * be attacked with damage
     */
    public void onDamaged(int damage) {
        this.HP -= damage;
        if (this.HP <= 0)
        {
            isDeath = true;
        }
    }
    
    /**
     * @param buff
     * reveive the buffstate and update the stats
     */
    public void onReceiveBuff(BuffState buff) {
    	this.maxHP += buff.getBuffMaxHP();
    	this.HP += buff.getBuffMaxHP();
    	this.cooldown += buff.getBuffCooldown();
    	this.maxDamage += buff.getBuffDamage();
    }
}
