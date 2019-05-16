/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Your name> <Your login>
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	/*the unit manager which manager all units include player and map*/
	private UnitManager manager;

    private Camera camera;

    /** Create a new World object. */
    public World(int screenwidth, int screenheight)
    throws SlickException
    {
    	manager = new UnitManager();
        camera = new Camera(manager.getPlayer());
    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(double dir_x, double dir_y, int delta, char keyPressed)
    throws SlickException
    {
    	manager.update(dir_x, dir_y, delta, keyPressed);
        camera.update();
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
        manager.render(g,camera);
    }
}
