import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;


public class NPC extends Character {
    private long talkingTime;
    private String sentence;

    NPC(String ref, TiledMap map) {
        super(ref, map);
        talkingTime = 5000;
        sentence = "";
    }

    /**
     * @param player
     * interaction with the player. Generate different sentence according to the different status of player.
     */
    public void interaction(Player player) {

        if (this.getName().equals("Aldric"))
        {
            if (player.hasItem("Elixir of Life"))
            {
                sentence = "The elixir! My father is cured! Thank you!";
            }else
            {
                sentence = "Please seek out the Elixir of Life to cure the king.";
            }
        }

        if (this.getName().equals("Elvira"))
        {
            if (player.getStats().isFullHealth())
            {
                sentence = "Return to me if you ever need healing.";
            }else
            {
                sentence = "You're looking much healthier now.";
            }
        }

        if (this.getName().equals("Garth"))
        {
            if (!(player.hasItem("Amulet of Vitality")))
            {
                sentence = "Find the Amulet of Vitality, across the river to the west.";
            }
            else if (!(player.hasItem("Sword of Strength")))
            {
                sentence = "Find the Sword of Strength - cross the bridge to the east, then head south.";
            }
            else if(!(player.hasItem("Tome of Agility")))
            {
                sentence = "Find the Tome of Agility, in the Land of Shadows.";
            }
            else
            {
                sentence = "You have found all the treasure I know of.";
            }
        }
        talkingTime = 0;
    }

    public void update(int delta)
    {
        talkingTime += delta;
    }

    /**
     * @param g
     * display sentence 
     */
    private void renderSentence(Graphics g, Camera camera)
    {
        float x = this.posX - camera.getMinX() - image.getWidth() / 2;
        float y = this.posY - camera.getMinY() - image.getHeight() / 2;
        String text = sentence;
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw

        bar_width = g.getFont().getWidth(text) + 6;
        bar_height = 30;
        bar_x = (int)(x + image.getWidth() / 2 - bar_width / 2);
        bar_y = (int)(y - 2 * bar_height);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        text_y = bar_y + 5;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
    }

    @Override
    public void render(Graphics g, Camera camera) {
        super.render(g, camera);
        this.renderHealthBar(g, camera);
        if (talkingTime <= 4000)
        {
            this.renderSentence(g, camera);
        }
    }
}
