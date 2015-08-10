package models;

import java.util.ArrayList;
import java.util.List;
import enums.Color;
import enums.TrinketType;

public class BlackBoxTrinket extends Trinket {
	public final static String kitName = "Disguise kit";
	public final static List<Color> availableColors;
	public final static List<Color> availableKitColors;

	static {
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
		availableColors.add(Color.ROSE);
		availableColors.add(Color.LAVENDER);
		availableColors.add(Color.MINT);
		availableColors.add(Color.LIGHT_GREEN);
		availableColors.add(Color.MAGENTA);
		availableColors.add(Color.LEMON);
		availableColors.add(Color.PEACH);
		availableColors.add(Color.LIGHT_BLUE);
		availableColors.add(Color.PERSIMMON);

		availableKitColors = new ArrayList<Color>();
		availableKitColors.add(Color.RED);
		availableKitColors.add(Color.SILVER);
		availableKitColors.add(Color.WHITE);
		availableKitColors.add(Color.BLACK);
		availableKitColors.add(Color.BROWN);
		availableKitColors.add(Color.LIGHT_BROWN);
		availableKitColors.add(Color.STRAWBERRY_BLONDE);
		availableKitColors.add(Color.SANDY);
		availableKitColors.add(Color.BLONDE);
	}

	public BlackBoxTrinket(String name, Color color, int count) {
		type = TrinketType.BLACK_BOX;
		colorless = false;
		this.name = name;
		this.primaryColor = color;
		this.secondaryColor = null;
		this.count = count;
	}
}
