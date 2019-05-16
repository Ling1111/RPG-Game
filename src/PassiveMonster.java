import org.newdawn.slick.tiled.TiledMap;


public class PassiveMonster extends Monster {
    /**
     * record the time of duration that monster keep one direcion
     */
    private long noTurningTime;
    /**
     * current direction to move
     */
    private Vector2D currenDirection;
    /**
     * the speed of PassiveMonster
     */
    private final double speed = 0.20;

    PassiveMonster(String ref, TiledMap map) {
        super(ref, map);
        this.timeSinceLastFight = 6000;
        this.noTurningTime = 0;
        this.currenDirection = genRandomDirection();
    }

    void update(Player player, int delta) {
        this.timeSinceLastFight += delta;
        if (this.timeSinceLastFight < 5000)
        {
            currenDirection = new Vector2D(this.getX() - player.getX(), this.getY() - player.getY());
            double ds = delta * speed;
            double dx = ds * currenDirection.x / currenDirection.lengh();
            double dy = ds * currenDirection.y / currenDirection.lengh();
            move(dx, dy);
            noTurningTime += delta;
        }else {
            if (noTurningTime >= 3000)
            {
                currenDirection = genRandomDirection();
                noTurningTime = 0;
            }
            double ds = delta * speed;
            double dx = ds * currenDirection.x / currenDirection.lengh();
            double dy = ds * currenDirection.y / currenDirection.lengh();
            move(dx, dy);
            noTurningTime += delta;
        }
    }
}