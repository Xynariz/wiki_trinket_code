package models;

import java.util.ArrayList;
import java.util.List;
import enums.Color;
import enums.TrinketType;

public class GoldBoxTrinket extends Trinket {
	public final static String featherName = "Radiant phoenix feather";
	public final static List<String> colorlessTrinkets;
	public final static List<Color> availableColors;
	public final static List<Color> availablePhoenixFeatherColors;

	static {
		colorlessTrinkets = new ArrayList<String>();
		colorlessTrinkets.add("Golden dragon scale");
		colorlessTrinkets.add("Golden turtle shell");

		availableColors = new ArrayList<Color>();
		availableColors.add(Color.RED);
		availableColors.add(Color.WHITE);
		availableColors.add(Color.GREY);
		availableColors.add(Color.YELLOW);
		availableColors.add(Color.VIOLET);
		availableColors.add(Color.PURPLE);
		availableColors.add(Color.NAVY);
		availableColors.add(Color.BLUE);
		availableColors.add(Color.LIME);
		availableColors.add(Color.GREEN);
		availableColors.add(Color.ORANGE);
		availableColors.add(Color.MAROON);
		availableColors.add(Color.BROWN);
		availableColors.add(Color.GOLD);
		availableColors.add(Color.LIGHT_GREEN);
		availableColors.add(Color.PERSIMMON);
		availableColors.add(Color.ICE_BLUE);
		availableColors.add(Color.SPRING_GREEN);
		availableColors.add(Color.BANANA);
		availableColors.add(Color.WINE);
		availableColors.add(Color.PLUM);
		availableColors.add(Color.CHOCOLATE);

		availablePhoenixFeatherColors = new ArrayList<Color>();
		availablePhoenixFeatherColors.add(Color.RED);
		availablePhoenixFeatherColors.add(Color.WHITE);
		availablePhoenixFeatherColors.add(Color.YELLOW);
		availablePhoenixFeatherColors.add(Color.VIOLET);
		availablePhoenixFeatherColors.add(Color.PURPLE);
		availablePhoenixFeatherColors.add(Color.NAVY);
		availablePhoenixFeatherColors.add(Color.BLUE);
		availablePhoenixFeatherColors.add(Color.LIME);
		availablePhoenixFeatherColors.add(Color.GREEN);
		availablePhoenixFeatherColors.add(Color.ORANGE);
		availablePhoenixFeatherColors.add(Color.MAROON);
		availablePhoenixFeatherColors.add(Color.BROWN);
		availablePhoenixFeatherColors.add(Color.GOLD);
		availablePhoenixFeatherColors.add(Color.LIGHT_GREEN);
		availablePhoenixFeatherColors.add(Color.SPRING_GREEN);
	}

	public GoldBoxTrinket(String name, int count) {
		type = TrinketType.GOLD_BOX;
		colorless = true;
		this.name = name;
		this.primaryColor = null;
		this.secondaryColor = null;
		this.count = count;
	}

	public GoldBoxTrinket(String name, Color color, int count) {
		this(name, count);
		colorless = false;
		this.primaryColor = color;
	}
}
