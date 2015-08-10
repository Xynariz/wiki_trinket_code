package models;

import java.util.ArrayList;
import java.util.List;
import enums.Color;
import enums.TrinketType;

public class VikingRaidTrinket extends Trinket {
	public final static List<Color> availableColors;

	static {
		availableColors = new ArrayList<Color>();

		availableColors.add(Color.RED);
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
		availableColors.add(Color.BROWN);
		availableColors.add(Color.GOLD);
	}

	public VikingRaidTrinket(String name, Color color, int count) {
		type = TrinketType.VIKING_RAID;
		colorless = false;
		this.name = name;
		this.primaryColor = color;
		this.secondaryColor = null;
		this.count = count;
	}
}
