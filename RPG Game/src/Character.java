import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

public class Character extends Unit {

	/**
	 * the stats
	 */
	protected Stats stats;
    /**
     * blocked
     */
    private boolean blocked[][];
    /**
     * duration since last fit other
     */
    protected long timeSinceLastHit;
    /**
     * duration since last fight with other
     */
    protected long timeSinceLastFight;
    

	Character(String ref, TiledMap map) {
		super(ref, map);
	}


	/**
	 * @return the stats
	 */
	public Stats getStats() {
		return stats;
	}

	/**
	 * @param stats the stats to set
	 */
	public void setStats(Stats stats) {
		this.stats = stats;
	}

	/**
	 * @return the blocked
	 */
	public boolean[][] getBlocked() {
		return blocked;
	}


	/**
	 * @param blocked the blocked to set
	 */
	public void setBlocked(boolean[][] blocked) {
		this.blocked = blocked;
	}
    

	/**
	 * @param g
	 * render the health bar at right position
	 */
	protected void renderHealthBar(Graphics g,Camera camera) {
		float x = this.posX - camera.getMinX() - image.getWidth() / 2;
		float y = this.posY - camera.getMinY() - image.getHeight() / 2;

		Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f); // Black, transp
		Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f); // Red, transp
		Color VALUE = new Color(1.0f, 1.0f, 1.0f); // White
		String text; // Text to display
		
		int text_x, text_y; // Coordinates to draw text
		int bar_x, bar_y; // Coordinates to draw rectangles
		int bar_width, bar_height; // Size of rectangle to draw
		int hp_bar_width; // Size of red (HP) rectangle
		float health_percent; // Player's health, as a percentage

		text = this.getName();
		bar_width = g.getFont().getWidth(text) + 6;
		bar_height = 30;
		bar_x = (int) (x + image.getWidth() / 2 - bar_width / 2);
		bar_y = (int) (y - bar_height);
		health_percent = this.stats.getHP() * 1.0f / this.stats.getMaxHP();
		hp_bar_width = (int) (bar_width * health_percent);
		text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
		text_y = bar_y + 5;
		g.setColor(BAR_BG);
		g.fillRect(bar_x, bar_y, bar_width, bar_height);
		g.setColor(BAR);
		g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
		g.setColor(VALUE);
		g.drawString(text, text_x, text_y);
	}
	
	 /**
	 * @param dx
	 * @param dy
	 */
	public void move(double dx, double dy)
	    {
	        double futureX = this.posX + dx;
	        double futureY = this.posY + dy;
	        boolean isBlocked = blocked[(int)((futureX + image.getWidth() / 2 - 5) / maze.getTileWidth())][(int)((futureY + image.getHeight() / 2 - 5) / maze.getTileHeight())] ||
	                blocked[(int)((futureX + image.getWidth() / 2 - 5) / maze.getTileWidth())][(int)((futureY - image.getHeight() / 2 + 5) / maze.getTileHeight())] ||
	                blocked[(int)((futureX - image.getWidth() / 2 + 5) / maze.getTileWidth())][(int)((futureY + image.getHeight() / 2 - 5) / maze.getTileHeight())] ||
	                blocked[(int)((futureX - image.getWidth() / 2 + 5) / maze.getTileWidth())][(int)((futureY - image.getHeight() / 2 + 5) / maze.getTileHeight())];

	        boolean xBlocked =  blocked[(int)((futureX + image.getWidth() / 2 - 5) / maze.getTileWidth())][(int)((this.posY + image.getHeight() / 2 - 5) / maze.getTileHeight())] ||
	                blocked[(int)((futureX + image.getWidth() / 2 - 5) / maze.getTileWidth())][(int)((this.posY - image.getHeight() / 2 + 5) / maze.getTileHeight())] ||
	                blocked[(int)((futureX - image.getWidth() / 2 + 5) / maze.getTileWidth())][(int)((this.posY + image.getHeight() / 2 - 5) / maze.getTileHeight())] ||
	                blocked[(int)((futureX - image.getWidth() / 2 + 5) / maze.getTileWidth())][(int)((this.posY - image.getHeight() / 2 + 5) / maze.getTileHeight())];

	        boolean yBlocked =  blocked[(int)((this.posX + image.getWidth() / 2 - 5) / maze.getTileWidth())][(int)((futureY + image.getHeight() / 2 - 5) / maze.getTileHeight())] ||
	                blocked[(int)((this.posX + image.getWidth() / 2 - 5) / maze.getTileWidth())][(int)((futureY - image.getHeight() / 2 + 5) / maze.getTileHeight())] ||
	                blocked[(int)((this.posX - image.getWidth() / 2 + 5) / maze.getTileWidth())][(int)((futureY + image.getHeight() / 2 - 5) / maze.getTileHeight())] ||
	                blocked[(int)((this.posX - image.getWidth() / 2 + 5) / maze.getTileWidth())][(int)((futureY - image.getHeight() / 2 + 5) / maze.getTileHeight())];
	        if (!isBlocked)
	        {
	            setPos((float)futureX, (float)futureY);
	        }
	        else
	        {
	            if (!xBlocked)
	            {
	                setPos((float)futureX, this.posY);
	            }
	            if (!yBlocked)
	            {
	                setPos(this.posX, (float)futureY);
	            }
	        }
	    }

	    /**
	     * @param other
	     * attack other character
	     */
	    public void attack(Character other) {
	    	this.timeSinceLastFight = 0;
	        if (timeSinceLastHit >= stats.getCooldown())
	        {
	            int damage = this.stats.getRandomDamage();
	            other.onAttacked(damage);
	            timeSinceLastHit = 0;
	        }
	    }
	    
	    /**
	     * @param damage
	     * be attacked with the damage of "damage"
	     */
	    public void onAttacked(int damage) {
	    	this.timeSinceLastFight = 0;
	    	this.stats.onDamaged(damage);
	    }
	    
}
