import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;


public abstract class Monster extends Character {

    /**
     * a inner class which indicate a position(x,y)
     */
    public class Vector2D
    {
        float x;
        float y;
        Vector2D(float m_x, float m_y)
        {
            this.x = m_x;
            this.y = m_y;
        }

        public double lengh()
        {
            return Math.sqrt(x * x + y * y);
        }
        
    }

    /**
     * all 8 directions
     */
    protected final Vector2D [] directions = {
            new Vector2D( 0,  1),
            new Vector2D( 0, -1),
            new Vector2D( 1,  0),
            new Vector2D(-1,  0),
            new Vector2D( 1,  1),
            new Vector2D( 1, -1),
            new Vector2D(-1,  1),
            new Vector2D(-1, -1),
            new Vector2D( 0,  0)
    };

    Monster(String ref, TiledMap map) {
        super(ref, map);
    }
    
    abstract void update(Player player, int delta);

    /**
     * @return a random direction
     */
    protected Vector2D genRandomDirection()
    {
        Random random = new Random();
        int idx = random.nextInt(directions.length);
        return directions[idx];
    }

    @Override
    public void render(Graphics g, Camera camera) {
        super.render(g, camera);
        this.renderHealthBar(g, camera);
    }
}
