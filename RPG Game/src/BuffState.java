
public class BuffState {

	/**
	 * cooldown buff
	 */
	private int buffCooldown;
	/**
	 * damage buff
	 */
	private int buffDamage;
	/**
	 * maxhp buff
	 */
	private int buffMaxHP;
	
	
	/**
	 * @param buffCooldown
	 * @param buffDamage
	 * @param buffMaxHP
	 */
	public BuffState(int buffCooldown, int buffDamage, int buffMaxHP) {
		super();
		this.buffCooldown = buffCooldown;
		this.buffDamage = buffDamage;
		this.buffMaxHP = buffMaxHP;
	}
	/**
	 * @return the buffCooldown
	 */
	public int getBuffCooldown() {
		return buffCooldown;
	}
	/**
	 * @param buffCooldown the buffCooldown to set
	 */
	public void setBuffCooldown(int buffCooldown) {
		this.buffCooldown = buffCooldown;
	}
	/**
	 * @return the buffDamage
	 */
	public int getBuffDamage() {
		return buffDamage;
	}
	/**
	 * @param buffDamage the buffDamage to set
	 */
	public void setBuffDamage(int buffDamage) {
		this.buffDamage = buffDamage;
	}
	/**
	 * @return the buffMaxHP
	 */
	public int getBuffMaxHP() {
		return buffMaxHP;
	}
	/**
	 * @param buffMaxHP the buffMaxHP to set
	 */
	public void setBuffMaxHP(int buffMaxHP) {
		this.buffMaxHP = buffMaxHP;
	}
}
