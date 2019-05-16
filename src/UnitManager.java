import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * A manager to manage all units 
 */
public class UnitManager {

	/**
	 * all monster units
	 */
	private ArrayList<Monster> monsterList;
	
	/**
	 *all npc units 
	 */
	private ArrayList<NPC> npcList;
	/**
	 * all item units
	 */
	private ArrayList<Item> itemList;
	/**
	 * player
	 */
	private Player player;

	/**
	 * tilemap 
	 */
	private TiledMap maze;
	
	/**
	 * bloacked
	 */
	boolean[][] blocked;

	/**
	 * @throws SlickException
	 */
	public UnitManager() throws SlickException {
		monsterList = new ArrayList<>();
		npcList = new ArrayList<>();
		itemList = new ArrayList<>();

		loadMap();
		loadPlayer();
		loadItems();
		loadOtherUnits();
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @throws SlickException
	 */
	private void loadMap() throws SlickException {
		maze = new TiledMap("assets/map.tmx", "assets/");

		// calculate the blocked matrix
		blocked = new boolean[maze.getWidth()][maze.getHeight()];
		for (int i = 0; i < maze.getWidth(); i++) {
			for (int j = 0; j < maze.getHeight(); j++) {
				int tileID = maze.getTileId(i, j, 0);
				String value = maze.getTileProperty(tileID, "block", "0");
				if (value.equals("1")) {
					blocked[i][j] = true;
				}
			}
		}
	}

	/**
	 * load player
	 */
	private void loadPlayer() {
		player = new Player("assets/units/player.png", maze);
		player.loadPanel("assets/panel.png");
		player.setBlocked(blocked);
	}

	/**
	 * load all items
	 */
	private void loadItems() {
		Item amuletItem = new Item("assets/items/amulet.png", maze, "Amulet of Vitality", 965, 3563,
				new BuffState(0, 0, 80));
		itemList.add(amuletItem);
		Item swordItem = new Item("assets/items/sword.png", maze, "Sword of Strength", 546, 6707,
				new BuffState(0, 30, 0));
		itemList.add(swordItem);
		Item tomeItem = new Item("assets/items/tome.png", maze, "Tome of Agility", 4791, 1253,
				new BuffState(-300, 0, 0));
		itemList.add(tomeItem);
		Item elixirItem = new Item("assets/items/elixir.png", maze, "Elixir of Life", 1976, 402,
				new BuffState(0, 0, 0));
		itemList.add(elixirItem);

	}

	/**
	 * load other unit include monsters and npcs
	 */
	private void loadOtherUnits() {
		monsterList = new ArrayList<>();
		npcList = new ArrayList<>();

		NPC princeAldric = new NPC("assets/units/prince.png", maze);
		princeAldric.setName("Aldric");
		NPC elvira = new NPC("assets/units/shaman.png", maze);
		elvira.setName("Elvira");
		NPC garth = new NPC("assets/units/peasant.png", maze);
		garth.setName("Garth");

		// read units.dat
		try (BufferedReader br = new BufferedReader(new FileReader("data/units.dat"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts[0].equals("Player")) {
					player.setPos(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
					player.setInitPosX(Float.parseFloat(parts[1]));
					player.setInitPosY(Float.parseFloat(parts[2]));
					player.setStats(new Stats(100, 600, 26));
				}
				// NPC unit
				if (parts[0].equals("PrinceAldric")) {
					princeAldric.setPos(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
					princeAldric.setStats(new Stats(1, 0, 0));
					npcList.add(princeAldric);
				}
				if (parts[0].equals("Elvira")) {
					elvira.setPos(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
					elvira.setStats(new Stats(1, 0, 0));
					npcList.add(elvira);
				}
				if (parts[0].equals("Garth")) {
					garth.setPos(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
					garth.setStats(new Stats(1, 0, 0));
					npcList.add(garth);
				}
				// monster unit
				if (parts[0].equals("GiantBat")) {
					Monster giantBat = new PassiveMonster("assets/units/dreadbat.png", maze);
					giantBat.setPos(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
					giantBat.setStats(new Stats(40, 0, 0));
					giantBat.setName("GiantBat");
					monsterList.add(giantBat);
				}
				if (parts[0].equals("Draelic")) {
					Monster draelic = new AggressiveMonster("assets/units/necromancer.png", maze);
					draelic.setPos(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
					draelic.setStats(new Stats(140, 400, 30));
					draelic.setName("Draelic");
					monsterList.add(draelic);
				}
				if (parts[0].equals("Bandit")) {
					Monster bandit = new AggressiveMonster("assets/units/bandit.png", maze);
					bandit.setPos(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
					bandit.setStats(new Stats(40, 200, 8));
					bandit.setName("Bandit");
					monsterList.add(bandit);
				}
				if (parts[0].equals("Zombie")) {
					Monster zombie = new AggressiveMonster("assets/units/zombie.png", maze);
					zombie.setPos(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
					zombie.setStats(new Stats(60, 800, 10));
					zombie.setName("Zombie");
					monsterList.add(zombie);
				}
				if (parts[0].equals("Skeleton")) {
					Monster skeleton = new AggressiveMonster("assets/units/skeleton.png", maze);
					skeleton.setPos(Float.parseFloat(parts[1]), Float.parseFloat(parts[2]));
					skeleton.setStats(new Stats(100, 500, 16));
					skeleton.setName("Skeleton");
					monsterList.add(skeleton);
				}
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		for (Monster monster : monsterList) {
			monster.setBlocked(blocked);
		}
	}
	
	/**
	 * @return Item
	 *  get the first item which is near from player (<=50)
	 */
	private Item getItemNear() {
		for (Item item : itemList) {
			if(player.getDistance(item) <= 50) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * @param item  the item to remove
	 */
	private void removeItem(Item item) {
		this.itemList.remove(item);
	}
	
	/**
	 * @return List<Monster>
	 * get the monster list which is near from player (<=50)
	 */
	private List<Monster> getMonstersNear() {
		List<Monster> monsters= new ArrayList<>();
		for (Monster monster : monsterList) {
			if(player.getDistance(monster) <= 50) {
				monsters.add(monster);
			}
		}
		return monsters;
	}
	
	/**
	 * @return NPC
	 * get the first NPC which is near from player (<=50)
	 */
	private NPC getNPCNear() {
		for (NPC npc : npcList) {
			if(player.getDistance(npc) <= 50) {
				return npc;
			}
		}
		return null;
	}

	/**
	 * check all units' status.
	 */
	private void checkStatus() {
		if (player.stats.isDeath()) {
			player.relive();
		}
		for (Iterator<Monster> it = monsterList.iterator(); it.hasNext();) {
			if (it.next().stats.isDeath()) {
				it.remove();
				//if kill any monster,player will gain 5 hit points.
				player.stats.setHP(player.stats.getHP() + 5);
			}
		}

	}

	/**
	 * @param dir_x
	 * @param dir_y
	 * @param delta
	 * @param keyPressed
	 * update all units status 
	 */
	public void update(double dir_x, double dir_y, int delta, char keyPressed) {
		player.updatePos(dir_x, dir_y, delta);
		
		//check near items
		Item itemNear = getItemNear();
		if(itemNear != null) {
			removeItem(itemNear);
			player.pickItem(itemNear);
		}
		//check user input
		if (keyPressed == 'T') {
			NPC npcNear = getNPCNear();
			if(npcNear!=null) {
				npcNear.interaction(player);
			}
		}
		if (keyPressed == 'A') {
			List<Monster> monstersNear = getMonstersNear();
			for (Monster monster : monstersNear) {
				player.attack(monster);
			}
		}
		for (NPC npc : npcList) {
			npc.update(delta);
		}
		for (Monster monster : monsterList) {
			monster.update(player, delta);
		}

		checkStatus();
	}

	/**
	 * @param g
	 * @param camera
	 * render all units and map
	 */
	public void render(Graphics g, Camera camera) {
		int tileWidth = maze.getTileWidth();
		int tileHeight = maze.getTileHeight();

		// draw the section of the map on the screen
		maze.render(-camera.getMinX() % tileWidth, -camera.getMinY() % tileHeight, camera.getMinX() / tileWidth,
				camera.getMinY() / tileHeight, 13, 10);

		// render all units
		for (NPC npc : npcList) {
			npc.render(g, camera);
		}
		for (Monster monster : monsterList) {
			monster.render(g, camera);
		}
		for (Item item : itemList) {
			item.render(g, camera);
		}
		player.render(g, camera);
	}

}
