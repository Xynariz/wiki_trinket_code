package models;

import java.util.ArrayList;
import java.util.List;
import enums.Color;
import enums.TrinketType;

public class CursedIslesTrinket extends Trinket {
	public final static String broochName = "Chaos brooch";
	public final static List<String> colorlessTrinkets;
	public final static List<Color> availableColors;
	public final static List<Color> availableBroochColors;

	static {
		colorlessTrinkets = new ArrayList<String>();
		colorlessTrinkets.add("Amber spider");
		colorlessTrinkets.add("Bear claw");
		colorlessTrinkets.add("Crushed leeches");

		availableColors = new ArrayList<Color>();
		availableColors.add(Color.RED);
		availableColors.add(Color.TAN);
		availableColors.add(Color.WHITE);
		availableColors.add(Color.BLACK);
		availableColors.add(Color.YELLOW);
		availableColors.add(Color.VIOLET);
		availableColors.add(Color.PURPLE);
		availableColors.add(Color.BLUE);
		availableColors.add(Color.AQUA);
		availableColors.add(Color.LIME);
		availableColors.add(Color.ORANGE);
		availableColors.add(Color.GOLD);
		availableColors.add(Color.LIGHT_GREEN);
		availableColors.add(Color.MAGENTA);
		availableColors.add(Color.INDIGO);
		
		availableBroochColors = new ArrayList<Color>();
		availableBroochColors.addAll(availableColors);
		availableBroochColors.add(Color.SEA_GREEN);
	}

	public CursedIslesTrinket(String name, int count) {
		type = TrinketType.CURSED_ISLES;
		colorless = true;
		this.name = name;
		this.primaryColor = null;
		this.secondaryColor = null;
		this.count = count;
	}

	public CursedIslesTrinket(String name, Color color, int count) {
		this(name, count);
		colorless = false;
		this.primaryColor = color;
	}
}
