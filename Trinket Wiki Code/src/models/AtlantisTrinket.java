package models;

import java.util.ArrayList;
import java.util.List;
import enums.Color;
import enums.TrinketType;

public class AtlantisTrinket extends Trinket {
	public final static String eggName = "Archelon egg";
	public final static List<String> colorlessTrinkets;
	public final static List<Color> availableColors;
	public final static List<Color> availableEggColors;

	static {
		colorlessTrinkets = new ArrayList<String>();
		colorlessTrinkets.add("Broken trident tine");
		colorlessTrinkets.add("Enormous pearl");
		colorlessTrinkets.add("Carcharias coin");
		colorlessTrinkets.add("Trident coin");
		colorlessTrinkets.add("Scale strigil");

		availableColors = new ArrayList<Color>();
		availableColors.add(Color.NAVY);
		availableColors.add(Color.BLUE);
		availableColors.add(Color.AQUA);
		availableColors.add(Color.ATLANTEAN);
		availableColors.add(Color.LIGHT_BLUE);

		availableEggColors = new ArrayList<Color>();
		availableEggColors.add(Color.NAVY);
		availableEggColors.add(Color.BLUE);
		availableEggColors.add(Color.AQUA);
		availableEggColors.add(Color.LIME);
		availableEggColors.add(Color.ATLANTEAN);
		availableEggColors.add(Color.LIGHT_BLUE);
		availableEggColors.add(Color.SEA_GREEN);
	}

	public AtlantisTrinket(String name, int count) {
		type = TrinketType.ATLANTIS;
		colorless = true;
		this.name = name;
		this.primaryColor = null;
		this.secondaryColor = null;
		this.count = count;
	}

	public AtlantisTrinket(String name, Color color, int count) {
		this(name, count);
		colorless = false;
		this.primaryColor = color;
	}
}
