package models;

import java.util.ArrayList;
import java.util.List;
import enums.Color;
import enums.TrinketType;

public class BeachcomberTrinket extends Trinket {
	public final static String conchName = "Conch shell";
	public final static String starfishName = "Starfish";
	public final static List<String> colorlessTrinkets;
	public final static List<Color> availableColors;
	public final static List<Color> availableStarfishColors;
	public final static List<Color> availableConchColors;

	static {
		colorlessTrinkets = new ArrayList<String>();
		colorlessTrinkets.add("Sand dollar");

		availableColors = new ArrayList<Color>();
		availableColors.add(Color.RED);
		availableColors.add(Color.TAN);
		availableColors.add(Color.WHITE);
		availableColors.add(Color.BLACK);
		availableColors.add(Color.GREY);
		availableColors.add(Color.YELLOW);
		availableColors.add(Color.PINK);
		availableColors.add(Color.VIOLET);
		availableColors.add(Color.PURPLE);
		availableColors.add(Color.NAVY);
		availableColors.add(Color.BLUE);
		availableColors.add(Color.AQUA);
		availableColors.add(Color.LIME);
		availableColors.add(Color.GREEN);
		availableColors.add(Color.ORANGE);
		availableColors.add(Color.MAROON);
		availableColors.add(Color.BROWN);
		availableColors.add(Color.GOLD);
		availableColors.add(Color.ATLANTEAN);
		availableColors.add(Color.ROSE);
		availableColors.add(Color.LAVENDER);
		availableColors.add(Color.MINT);
		availableColors.add(Color.LIGHT_GREEN);
		availableColors.add(Color.MAGENTA);
		availableColors.add(Color.LEMON);
		availableColors.add(Color.PEACH);
		availableColors.add(Color.LIGHT_BLUE);
		availableColors.add(Color.PERSIMMON);
		availableColors.add(Color.ROYAL_BLUE);
		availableColors.add(Color.ICE_BLUE);
		availableColors.add(Color.SPRING_GREEN);
		availableColors.add(Color.BANANA);
		availableColors.add(Color.WINE);
		availableColors.add(Color.PLUM);
		availableColors.add(Color.CHOCOLATE);

		availableStarfishColors = new ArrayList<Color>();
		availableStarfishColors.add(Color.RED);
		availableStarfishColors.add(Color.TAN);
		availableStarfishColors.add(Color.WHITE);
		availableStarfishColors.add(Color.BLACK);
		availableStarfishColors.add(Color.GREY);
		availableStarfishColors.add(Color.YELLOW);
		availableStarfishColors.add(Color.PINK);
		availableStarfishColors.add(Color.VIOLET);
		availableStarfishColors.add(Color.PURPLE);
		availableStarfishColors.add(Color.NAVY);
		availableStarfishColors.add(Color.BLUE);
		availableStarfishColors.add(Color.AQUA);
		availableStarfishColors.add(Color.LIME);
		availableStarfishColors.add(Color.GREEN);
		availableStarfishColors.add(Color.ORANGE);
		availableStarfishColors.add(Color.MAROON);
		availableStarfishColors.add(Color.BROWN);
		availableStarfishColors.add(Color.GOLD);
		availableStarfishColors.add(Color.ATLANTEAN);
		availableStarfishColors.add(Color.ROSE);
		availableStarfishColors.add(Color.LAVENDER);
		availableStarfishColors.add(Color.MINT);
		availableStarfishColors.add(Color.LIGHT_GREEN);
		availableStarfishColors.add(Color.MAGENTA);
		availableStarfishColors.add(Color.INDIGO);
		availableStarfishColors.add(Color.LEMON);
		availableStarfishColors.add(Color.PEACH);
		availableStarfishColors.add(Color.LIGHT_BLUE);
		availableStarfishColors.add(Color.PERSIMMON);
		availableStarfishColors.add(Color.ROYAL_BLUE);
		availableStarfishColors.add(Color.ICE_BLUE);
		availableStarfishColors.add(Color.SPRING_GREEN);
		availableStarfishColors.add(Color.BANANA);
		availableStarfishColors.add(Color.WINE);
		availableStarfishColors.add(Color.PLUM);
		availableStarfishColors.add(Color.CHOCOLATE);

		availableConchColors = new ArrayList<Color>();
		availableConchColors.add(Color.RED);
		availableConchColors.add(Color.TAN);
		availableConchColors.add(Color.WHITE);
		availableConchColors.add(Color.BLACK);
		availableConchColors.add(Color.GREY);
		availableConchColors.add(Color.YELLOW);
		availableConchColors.add(Color.PINK);
		availableConchColors.add(Color.VIOLET);
		availableConchColors.add(Color.PURPLE);
		availableConchColors.add(Color.NAVY);
		availableConchColors.add(Color.BLUE);
		availableConchColors.add(Color.AQUA);
		availableConchColors.add(Color.LIME);
		availableConchColors.add(Color.GREEN);
		availableConchColors.add(Color.ORANGE);
		availableConchColors.add(Color.MAROON);
		availableConchColors.add(Color.BROWN);
		availableConchColors.add(Color.GOLD);
		availableConchColors.add(Color.ROSE);
		availableConchColors.add(Color.LAVENDER);
		availableConchColors.add(Color.LIGHT_GREEN);
		availableConchColors.add(Color.LEMON);
	}

	public BeachcomberTrinket(String name, Color color, int count) {
		type = TrinketType.BEACHCOMBER;
		if (colorlessTrinkets.contains(name)) {
			colorless = true;
		} else {
			colorless = false;
		}
		this.name = name;
		this.primaryColor = color;
		this.secondaryColor = null;
		this.count = count;
	}
}
