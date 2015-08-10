package models;

import java.util.ArrayList;
import java.util.List;
import enums.Color;
import enums.TrinketType;

public class HauntedSeasTrinket extends Trinket {
	public final static String tomeName = "Haunted tome";
	public final static List<String> colorlessTrinkets;
	public final static List<Color> availableColors;
	public final static List<Color> availableTomeColors;

	static {
		colorlessTrinkets = new ArrayList<String>();
		colorlessTrinkets.add("Phantom hand");

		availableColors = new ArrayList<Color>();
		availableColors.add(Color.WHITE);
		availableColors.add(Color.BLACK);
		availableColors.add(Color.GREY);
		availableColors.add(Color.VIOLET);
		availableColors.add(Color.PURPLE);
		availableColors.add(Color.NAVY);
		availableColors.add(Color.BLUE);
		availableColors.add(Color.LIGHT_BLUE);
		availableColors.add(Color.NIGHT_BLUE);

		availableTomeColors = new ArrayList<Color>();
		availableTomeColors.add(Color.WHITE);
		availableTomeColors.add(Color.BLACK);
		availableTomeColors.add(Color.GREY);
		availableTomeColors.add(Color.VIOLET);
		availableTomeColors.add(Color.PURPLE);
		availableTomeColors.add(Color.NAVY);
		availableTomeColors.add(Color.BLUE);
		availableTomeColors.add(Color.LIGHT_BLUE);
		availableTomeColors.add(Color.SEA_GREEN);
		availableTomeColors.add(Color.NIGHT_BLUE);
	}

	public HauntedSeasTrinket(String name, int count) {
		type = TrinketType.HAUNTED_SEAS;
		colorless = true;
		this.name = name;
		this.primaryColor = null;
		this.secondaryColor = null;
		this.count = count;
	}

	public HauntedSeasTrinket(String name, Color color, int count) {
		this(name, count);
		colorless = false;
		this.primaryColor = color;
	}
}
