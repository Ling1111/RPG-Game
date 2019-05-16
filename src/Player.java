import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


public class Player extends Character{
    private float initPosX;
    private float initPosY;
    /*the direction of player. o:left 1:right*/
    private Image panel;
    private ArrayList<Item> inventory;
    
    Player(String ref, TiledMap map) {
        super(ref, map);
        inventory = new ArrayList<>();
    }

    /**
     * @param initX	the init x to set
     */
    public void setInitPosX(float initX) {
        this.initPosX = initX;
    }

    /**
     * @param initY  the init y to set
     */
    public void setInitPosY(float initY) {
        this.initPosY = initY;
    }

    /**
     * @param ref  panel's ref file to load 
     * load panel
     */
    public void loadPanel(String ref)
    {
        try {
            panel = new Image(ref);
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pickItem(Item item) {
        inventory.add(item);
        this.stats.onReceiveBuff(item.getBuff());
    }

    /**
     * @param itemName
     * @return
     * check if palyers has the item whose name is itemName
     */
    public boolean hasItem(String itemName)
    {
        for (Item item : inventory)
        {
            if (item.getName().equals(itemName))
            {
                return true;
            }
        }
        return false;
    }


    /**
     * @param dir_x
     * @param dir_y
     * @param delta
     * update player's position
     */
    public void updatePos(double dir_x, double dir_y, int delta) {
        this.timeSinceLastHit += delta;
        move(dir_x * delta, dir_y * delta);
    }

    
    /**
     * after player dead,make player TP to init position and heal player.
     */
    public void relive() {
        this.setPos(initPosX, initPosY);
        this.stats.setFullHealth();
        this.stats.setDeath(false);
    }

    /**
     * @param g
     * render panel
     */
    public void renderPanel(Graphics g) {
        // Panel colours
        Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        String text;                // Text to display
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel.draw(0, RPG.screenheight - RPG.panelheight);

        // Display the player's health
        text_x = 15;
        text_y = RPG.screenheight - RPG.panelheight + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        text = Integer.toString(this.stats.getHP()) + "/" + Integer.toString(this.stats.getMaxHP());           

        bar_x = 90;
        bar_y = RPG.screenheight - RPG.panelheight + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = this.stats.getHP() * 1.0f / this.stats.getMaxHP();                       
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;
        text = Integer.toString(this.stats.getMaxDamage());                      
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        text = Integer.toString(this.stats.getCooldown());  
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", 420, text_y);
        bar_x = 490;
        bar_y = RPG.screenheight - RPG.panelheight + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);

        inv_x = 490;
        inv_y = RPG.screenheight - RPG.panelheight
                + ((RPG.panelheight - 72) / 2);
        for (Item item : inventory) 
        {
            // Render the item to (inv_x, inv_y)
            item.getImage().draw(inv_x, inv_y);
            inv_x += 72;
        }
    }

    @Override
    public void render(Graphics g, Camera camera)
    {
        super.render(g,camera);
        renderPanel(g);
    }
    
}
