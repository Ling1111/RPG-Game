import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


public class Unit {
	
    /**
     * the postion x of unit
     */
    protected float posX;
    
    /**
     * the position y of unit
     */
    protected float posY;
    
    /**
     * the image of unit
     */
    protected Image image; 
    
    /**
     * the maze of unit
     */
    protected TiledMap maze;
    
    /**
     * the name of unit
     */
    private String name;

    /**
     * @param ref	the reference name of imagefile
     * @param map	map of world
     */
    public Unit(String ref, TiledMap map) {

        try {
        	
            this.posX = this.posY = 0;
            
            image = new Image(ref);
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        maze = map;

    }
    
    /**
     * @param ref	the reference name of imagefile
     * @param map	map of world
     * @param name	name of unit
     * @param posX  the position x of unit
     * @param posY	the position y of unit
     */
    public Unit(String ref, TiledMap map, String name, float posX, float posY) {

        this(ref,map);
        
        this.posX = posX;
        this.posY = posY;
        this.name = name;

    }


    /**
	 * @return the posX
	 */
	public float getX() {
		return posX;
	}


	/**
	 * @param posX the posX to set
	 */
	public void setX(float posX) {
		this.posX = posX;
	}


	/**
	 * @return the posY
	 */
	public float getY() {
		return posY;
	}


	/**
	 * @param posY the posY to set
	 */
	public void setY(float posY) {
		this.posY = posY;
	}
	
	/**
	 * @param posX the posX to set
	 * @param posY the posY to set
	 */
	public void setPos(float posX,float poxY) {
		this.setX(posX);
		this.setY(poxY);
	}



	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}


	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}


	/**
	 * @return the maze
	 */
	public TiledMap getMaze() {
		return maze;
	}


	/**
	 * @param maze the maze to set
	 */
	public void setMaze(TiledMap maze) {
		this.maze = maze;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * 
	 * @param other 
	 * @return the distance between this and other Unit object
	 */
	public double getDistance(Unit other)
    {
        double dx = this.getX() - other.getX();
        double dy = this.getY() - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * @param g
     * render the unit with image on the right position
     */
    public void render(Graphics g,Camera camera) {
        float x = this.posX - camera.getMinX() - image.getWidth() / 2;
        float y = this.posY - camera.getMinY() - image.getHeight() / 2;
        image.draw(x, y);
    }

}
