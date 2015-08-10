package models;

import java.util.ArrayList;
import java.util.List;
import enums.Color;
import enums.TrinketType;

public class BrigandKingTrinket extends Trinket {
	public final static List<String> BarnabasTrinkets;
	public final static List<String> WidowTrinkets;
	public final static List<String> SkullsplitterTrinkets;
	public final static List<String> AzarbadTrinkets;
	public final static List<String> VargasTrinkets;
	public final static List<String> MadamTrinkets;
	public final static List<String> GretchenTrinkets;
	public final static List<String> FiniusTrinkets;
	public final static List<Color> availableBasicColors;
	public final static List<Color> availableExtendedColors;

	static {
		BarnabasTrinkets = new ArrayList<String>();
		BarnabasTrinkets.add("Ectoplasm");
		BarnabasTrinkets.add("Will o' wisp");
		BarnabasTrinkets.add("Cursed idol");
		BarnabasTrinkets.add("Music box of lamentations");
		BarnabasTrinkets.add("Spectral chain");
		BarnabasTrinkets.add("Key to the Nether-Sea");

		WidowTrinkets = new ArrayList<String>();
		WidowTrinkets.add("Silk handkerchief");
		WidowTrinkets.add("Jeweled brooch");
		WidowTrinkets.add("Rose goddess perfume");
		WidowTrinkets.add("Arsenic ring");
		WidowTrinkets.add("Ruffled garter");
		WidowTrinkets.add("Concealed blade");

		SkullsplitterTrinkets = new ArrayList<String>();
		SkullsplitterTrinkets.add("Mjolnir amulet");
		SkullsplitterTrinkets.add("Runestone");
		SkullsplitterTrinkets.add("Engraved wristband");
		SkullsplitterTrinkets.add("Solar compass");
		SkullsplitterTrinkets.add("Hoard brooch");
		SkullsplitterTrinkets.add("Wyrm scale");

		AzarbadTrinkets = new ArrayList<String>();
		AzarbadTrinkets.add("Exotic confection");
		AzarbadTrinkets.add("Ornate earring");
		AzarbadTrinkets.add("Jeweled dagger");
		AzarbadTrinkets.add("Heart of fire");
		AzarbadTrinkets.add("Scented beard oil");
		AzarbadTrinkets.add("Golden oil lamp");

		VargasTrinkets = new ArrayList<String>();
		VargasTrinkets.add("Jigsaw puzzle piece");
		VargasTrinkets.add("Scientific tome");
		VargasTrinkets.add("Reading pipe");
		VargasTrinkets.add("Siren's comb");
		VargasTrinkets.add("Grimoire page");
		VargasTrinkets.add("Yo-yo");

		MadamTrinkets = new ArrayList<String>();
		MadamTrinkets.add("Lucky coin talisman");
		MadamTrinkets.add("Jade carving");
		MadamTrinkets.add("Jeweled finger guard");
		MadamTrinkets.add("Hand fan");
		MadamTrinkets.add("Cut stone box");
		MadamTrinkets.add("Jade comb");

		GretchenTrinkets = new ArrayList<String>();
		GretchenTrinkets.add("Ivory toothpick");
		GretchenTrinkets.add("Salt shaker");
		GretchenTrinkets.add("Wooden amulet");
		GretchenTrinkets.add("Lucky rabbit's foot");
		GretchenTrinkets.add("Golden horseshoe");
		GretchenTrinkets.add("Stained wishbone");

		FiniusTrinkets = new ArrayList<String>();
		FiniusTrinkets.add("Porcelain cup");
		FiniusTrinkets.add("Fish eye lens monocle");
		FiniusTrinkets.add("Royal anchovy tin");
		FiniusTrinkets.add("Prestigious naval medal");
		FiniusTrinkets.add("Baby portrait of Adm. Finius");
		FiniusTrinkets.add("Self-addressed love letter");
		FiniusTrinkets.add("Pearl pocket watch");

		availableBasicColors = new ArrayList<Color>();
		availableBasicColors.add(Color.RED);
		availableBasicColors.add(Color.VIOLET);
		availableBasicColors.add(Color.BLUE);
		availableBasicColors.add(Color.AQUA);
		availableBasicColors.add(Color.LIME);
		availableBasicColors.add(Color.ORANGE);

		availableExtendedColors = new ArrayList<Color>();
		availableExtendedColors.addAll(availableBasicColors);
		availableExtendedColors.add(Color.YELLOW);
	}

	public BrigandKingTrinket(String name, Color color, int count) {
		type = TrinketType.BRIGAND_KING;
		colorless = false;
		this.name = name;
		this.primaryColor = color;
		this.secondaryColor = null;
		this.count = count;
	}
}
