package models;

import java.util.ArrayList;
import java.util.List;
import enums.Color;
import enums.TrinketType;

public class KrakenTrinket extends Trinket {
	public final static List<String> colorlessTrinkets;
	public final static List<Color> availableColors;

	static {
		colorlessTrinkets = new ArrayList<String>();
		colorlessTrinkets.add("Kraken coin");
		colorlessTrinkets.add("Kraken egglet");
		colorlessTrinkets.add("Kraken scale");
		colorlessTrinkets.add("Inky weeds");
		colorlessTrinkets.add("Tentacle spikelet");
		colorlessTrinkets.add("Inky Cephalopod doll");

		availableColors = new ArrayList<Color>();
		availableColors.add(Color.BLACK);
		availableColors.add(Color.GREY);
		availableColors.add(Color.VIOLET);
		availableColors.add(Color.PURPLE);
		availableColors.add(Color.NAVY);
		availableColors.add(Color.BLUE);
		availableColors.add(Color.AQUA);
		availableColors.add(Color.LIME);
		availableColors.add(Color.GREEN);
		availableColors.add(Color.LIGHT_GREEN);
		availableColors.add(Color.INKY);
		availableColors.add(Color.SEA_GREEN);
	}

	public KrakenTrinket(String name, int count) {
		type = TrinketType.KRAKEN;
		colorless = true;
		this.name = name;
		this.primaryColor = null;
		this.secondaryColor = null;
		this.count = count;
	}

	public KrakenTrinket(String name, Color color, int count) {
		this(name, count);
		colorless = false;
		this.primaryColor = color;
	}
}
