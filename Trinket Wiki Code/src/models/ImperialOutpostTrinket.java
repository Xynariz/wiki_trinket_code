package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import enums.Color;
import enums.TrinketType;

public class ImperialOutpostTrinket extends Trinket {
	private static List<Color> availablePrimaryColors;
	private static List<Color> availableSecondaryColors;

	static {
		availablePrimaryColors = new ArrayList<Color>();
		availablePrimaryColors.add(Color.BLACK);
		availablePrimaryColors.add(Color.GREY);
		availablePrimaryColors.add(Color.PURPLE);
		availablePrimaryColors.add(Color.NAVY);
		availablePrimaryColors.add(Color.BLUE);
		availablePrimaryColors.add(Color.MAROON);

		availableSecondaryColors = new ArrayList<Color>();
		availableSecondaryColors.add(Color.WHITE);
		availableSecondaryColors.add(Color.GREY);
		availableSecondaryColors.add(Color.YELLOW);
		availableSecondaryColors.add(Color.MAROON);
		availableSecondaryColors.add(Color.GOLD);
		availableSecondaryColors.add(Color.LEMON);
	}

	public ImperialOutpostTrinket(String name, Color primary, Color secondary, int count) {
		type = TrinketType.IMPERIAL_OUTPOST;
		colorless = false;
		this.name = name;
		this.primaryColor = primary;
		this.secondaryColor = secondary;
		this.count = count;
	}
}
