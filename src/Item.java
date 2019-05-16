import org.newdawn.slick.tiled.TiledMap;


public class Item extends Unit {
	
	/**
	 * the item's buff
	 */
	private BuffState buff;
	
    /**
     * @param ref
     * @param map
     * @param buff
     */
    /**
     * @param ref
     * @param map
     * @param buff
     */
    Item(String ref, TiledMap map,BuffState buff) {
        super(ref, map);
        this.buff = buff;
    }
    
    /**
     * @param ref
     * @param map
     * @param name
     * @param posX
     * @param posY
     * @param buff
     */
    Item(String ref, TiledMap map,String name, float posX, float posY, BuffState buff) {
        super(ref, map, name, posX, posY);
        this.buff = buff;
    }
    

	/**
	 * @return the buff
	 */
	public BuffState getBuff() {
		return buff;
	}

	/**
	 * @param buff the buff to set
	 */
	public void setBuff(BuffState buff) {
		this.buff = buff;
	}
    
    
}
