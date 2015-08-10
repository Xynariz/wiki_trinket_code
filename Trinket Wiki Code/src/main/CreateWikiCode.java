package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import enums.BrigandKingTrinketType;
import enums.Color;
import enums.OutputType;
import models.AtlantisTrinket;
import models.BeachcomberTrinket;
import models.BlackBoxTrinket;
import models.BrigandKingTrinket;
import models.CursedIslesTrinket;
import models.HauntedSeasTrinket;
import models.KrakenTrinket;
import models.Trinket;
import models.GoldBoxTrinket;
import models.VikingRaidTrinket;

public class CreateWikiCode {
	private final static int MAX_ROW_WIDTH = 15;
	private final static int ROWS_BETWEEN_COLOR_LIST = 10;

	private final static int MINIMUM_DUPLICATE_NUMBER = 2;

	private final static String DIVIDER = System.getProperty("file.separator");
	private final static String INPUT_FILE_PATH = "files" + DIVIDER + "in";
	private final static String OUTPUT_FILE_PATH = "files" + DIVIDER + "out";

	private final static String BRIGAND_KING_INPUT_FILE_PATH_MINE = "Brigand_King_Mine.txt";
	private final static String BRIGAND_KING_INPUT_FILE_PATH_OTHERS = "Brigand_King_Not_Mine.txt";
	private final static String BRIGAND_KING_POSSESS_OWN_FILE_PATH = "Brigand_King_Possess_Own."
		+ "txt";
	private final static String BRIGAND_KING_POSSESS_TOTAL_FILE_PATH =
		"Brigand_King_Possess_Total.txt";
	private final static String BRIGAND_KING_EXTRAS_FILE_PATH = "Brigand_King_Extras.txt";
	private final static String BEACHCOMBER_INPUT_FILE_PATH = "Beachcomber.txt";
	private final static String BEACHCOMBER_POSSESS_FILE_PATH = "Beachcomber_Possess.txt";
	private final static String BEACHCOMBER_EXTRAS_FILE_PATH = "Beachcomber_Extras.txt";
	private final static String ATLANTIS_INPUT_FILE_PATH = "Atlantis.txt";
	private final static String ATLANTIS_POSSESS_FILE_PATH = "Atlantis_Possess.txt";
	private final static String ATLANTIS_EXTRAS_FILE_PATH = "Atlantis_Extras.txt";
	private final static String CURSED_ISLES_INPUT_FILE_PATH = "Cursed_Isles.txt";
	private final static String CURSED_ISLES_POSSESS_FILE_PATH = "Cursed_Isles_Possess.txt";
	private final static String CURSED_ISLES_EXTRAS_FILE_PATH = "Cursed_Isles_Extras.txt";
	private final static String HAUNTED_SEAS_INPUT_FILE_PATH = "Haunted_Seas.txt";
	private final static String HAUNTED_SEAS_POSSESS_FILE_PATH = "Haunted_Seas_Possess.txt";
	private final static String HAUNTED_SEAS_EXTRAS_FILE_PATH = "Haunted_Seas_Extras.txt";
	private final static String KRAKEN_INPUT_FILE_PATH = "Kraken.txt";
	private final static String KRAKEN_POSSESS_FILE_PATH = "Kraken_Possess.txt";
	private final static String KRAKEN_EXTRAS_FILE_PATH = "Kraken_Extras.txt";
	private final static String VIKING_RAID_INPUT_FILE_PATH = "Viking_Raid.txt";
	private final static String VIKING_RAID_POSSESS_FILE_PATH = "Viking_Raid_Possess.txt";
	private final static String VIKING_RAID_EXTRAS_FILE_PATH = "Viking_Raid_Extras.txt";
	private final static String BLACK_BOX_INPUT_FILE_PATH = "Black_Box.txt";
	private final static String BLACK_BOX_POSSESS_FILE_PATH = "Black_Box_Possess.txt";
	private final static String BLACK_BOX_EXTRAS_FILE_PATH = "Black_Box_Extras.txt";
	private final static String GOLD_BOX_INPUT_FILE_PATH = "Gold_Box.txt";
	private final static String GOLD_BOX_POSSESS_FILE_PATH = "Gold_Box_Possess.txt";
	private final static String GOLD_BOX_EXTRAS_FILE_PATH = "Gold_Box_Extras.txt";

	private final static String SUBPAGE_POSSESS_LINE = "This is a sub-page of [[Jamesh]]\'s "
		+ "trinket collections.  For the main page, please click [[Jamesh/Trinkets|here]].\n\nThis"
		+ " is a list of trinkets Jamesh has collected.  Any trinkets that are '''''not''''' shown"
		+ " here are trinkets Jamesh is missing and will buy.";
	private final static String SUBPAGE_DUPLICATE_LINE = "NOT IMPLEMENTED";
	private final static String SUBPAGE_MISSING_LINE = "NOT IMPLEMENTED!";
	private final static String SUBPAGE_BK_TOTAL_LINE = "This is a sub-page of [[Jamesh]]\'s "
		+ "trinket collections.  For the main page, please click [[Jamesh/Trinkets|here]].\n\nThis"
		+ " is a list of all [[Brigand King]] trinkets that Jamesh has collected, whether "
		+ "inscribed to him or to another pirate.  Any trinkets that are '''''not''''' shown here "
		+ "are trinkets Jamesh is missing and will buy.\n\nFor a list of Brigand King trinkets "
		+ "inscribed to Jamesh, please see [[Jamesh/Trinkets/BK|this page]].";
	private final static String SUBPAGE_BK_OWN_LINE = "This is a sub-page of [[Jamesh]]\'s trinket"
		+ " collections.  For the main page, please click [[Jamesh/Trinkets|here]].\n\nThese are "
		+ "the [[Brigand King]] trinkets that are '''''inscribed to Jamesh'''''.\n\nFor a list of "
		+ "Brigand King trinkets Jamesh will buy, please see [[Jamesh/Trinkets/BK/All|this "
		+ "page]].";
	private final static String SUBPAGE_BK_DUPLICATE_LINE = "NOT IMPLEMENTED!!";
	private final static String SUBPAGE_BK_MISSING_MINE_LINE = "NOT IMPLEMENTED!!!";
	private final static String SUBPAGE_BK_MISSING_NOT_MINE_LINE = "NOT IMPLEMENTED!!!!";
	private final static String CATEGORY_LINE = "[[Category:Subpages:Trinkets]]";

	private final static String TITLE_LINE_START = "=== ";
	private final static String TITLE_LINE_END = " ===";
	private final static String COLORLESS_TITLE_LINE = TITLE_LINE_START + "Colorless"
		+ TITLE_LINE_END;
	private final static String COLORLESS_EXTRA_LINE = "These trinkets do not come in multiple "
		+ "colors.";
	private final static String COLORLESS_SINGLE_EXTRA_LINE = "This trinket does not come in "
		+ "multiple colors.";

	private final static String BRIGAND_KING_TITLE_LINE_START = "=== ";
	private final static String BRIGAND_KING_TITLE_LINE_END = " ===";
	private final static String BRIGAND_KING_EXTRA_LINE_1 = "These trinkets are awarded when an "
		+ "experienced player is on a ship that defeats [[";
	private final static String BRIGAND_KING_EXTRA_LINE_2 = "]] in a brawl.  They are available in"
		+ " the colors of the ";
	private final static String BRIGAND_KING_BASIC_PALETTE = "[[Trinket_palette#Basic_colors|Basic"
		+ " Brigand King palette]].";
	private final static String BRIGAND_KING_EXTENDED_PALETTE = "[[Trinket_palette#Extended|"
		+ "Extended Brigand King palette]].";
	private final static String BRIGAND_KING_BARNABAS_NAME = "Barnabas the Pale";
	private final static String BRIGAND_KING_WIDOW_NAME = "The Widow Queen";
	private final static String BRIGAND_KING_SKULLSPLITTER_NAME = "Brynhild Skullsplitter";
	private final static String BRIGAND_KING_AZARBAD_NAME = "Azarbad the Great";
	private final static String BRIGAND_KING_VARGAS_NAME = "Vargas the Mad";
	private final static String BRIGAND_KING_MADAM_NAME = "Madam Yu Jian";
	private final static String BRIGAND_KING_GRETCHEN_NAME = "Gretchen Goldfang";
	private final static String BRIGAND_KING_FINIUS_NAME = "Admiral Finius";
	private final static String BRIGAND_KING_BARNABAS_TITLE_LINE = BRIGAND_KING_TITLE_LINE_START
		+ BRIGAND_KING_BARNABAS_NAME + BRIGAND_KING_TITLE_LINE_END;
	private final static String BRIGAND_KING_BARNABAS_EXTRA_LINE = BRIGAND_KING_EXTRA_LINE_1
		+ BRIGAND_KING_BARNABAS_NAME + BRIGAND_KING_EXTRA_LINE_2 + BRIGAND_KING_BASIC_PALETTE;
	private final static String BRIGAND_KING_WIDOW_TITLE_LINE = BRIGAND_KING_TITLE_LINE_START
		+ BRIGAND_KING_WIDOW_NAME + BRIGAND_KING_TITLE_LINE_END;
	private final static String BRIGAND_KING_WIDOW_EXTRA_LINE = BRIGAND_KING_EXTRA_LINE_1
		+ BRIGAND_KING_WIDOW_NAME + BRIGAND_KING_EXTRA_LINE_2 + BRIGAND_KING_BASIC_PALETTE;
	private final static String BRIGAND_KING_SKULLSPLITTER_TITLE_LINE =
		BRIGAND_KING_TITLE_LINE_START + BRIGAND_KING_SKULLSPLITTER_NAME
			+ BRIGAND_KING_TITLE_LINE_END;
	private final static String BRIGAND_KING_SKULLSPLITTER_EXTRA_LINE = BRIGAND_KING_EXTRA_LINE_1
		+ BRIGAND_KING_SKULLSPLITTER_NAME + BRIGAND_KING_EXTRA_LINE_2
		+ BRIGAND_KING_EXTENDED_PALETTE;
	private final static String BRIGAND_KING_AZARBAD_TITLE_LINE = BRIGAND_KING_TITLE_LINE_START
		+ BRIGAND_KING_AZARBAD_NAME + BRIGAND_KING_TITLE_LINE_END;
	private final static String BRIGAND_KING_AZARBAD_EXTRA_LINE = BRIGAND_KING_EXTRA_LINE_1
		+ BRIGAND_KING_AZARBAD_NAME + BRIGAND_KING_EXTRA_LINE_2 + BRIGAND_KING_EXTENDED_PALETTE;
	private final static String BRIGAND_KING_VARGAS_TITLE_LINE = BRIGAND_KING_TITLE_LINE_START
		+ BRIGAND_KING_VARGAS_NAME + BRIGAND_KING_TITLE_LINE_END;
	private final static String BRIGAND_KING_VARGAS_EXTRA_LINE = BRIGAND_KING_EXTRA_LINE_1
		+ BRIGAND_KING_VARGAS_NAME + BRIGAND_KING_EXTRA_LINE_2 + BRIGAND_KING_BASIC_PALETTE;
	private final static String BRIGAND_KING_MADAM_TITLE_LINE = BRIGAND_KING_TITLE_LINE_START
		+ BRIGAND_KING_MADAM_NAME + BRIGAND_KING_TITLE_LINE_END;
	private final static String BRIGAND_KING_MADAM_EXTRA_LINE = BRIGAND_KING_EXTRA_LINE_1
		+ BRIGAND_KING_MADAM_NAME + BRIGAND_KING_EXTRA_LINE_2 + BRIGAND_KING_EXTENDED_PALETTE;
	private final static String BRIGAND_KING_GRETCHEN_TITLE_LINE = BRIGAND_KING_TITLE_LINE_START
		+ BRIGAND_KING_GRETCHEN_NAME + BRIGAND_KING_TITLE_LINE_END;
	private final static String BRIGAND_KING_GRETCHEN_EXTRA_LINE = BRIGAND_KING_EXTRA_LINE_1
		+ BRIGAND_KING_GRETCHEN_NAME + BRIGAND_KING_EXTRA_LINE_2 + BRIGAND_KING_BASIC_PALETTE;
	private final static String BRIGAND_KING_FINIUS_TITLE_LINE = BRIGAND_KING_TITLE_LINE_START
		+ BRIGAND_KING_FINIUS_NAME + BRIGAND_KING_TITLE_LINE_END;
	private final static String BRIGAND_KING_FINIUS_EXTRA_LINE = BRIGAND_KING_EXTRA_LINE_1
		+ BRIGAND_KING_FINIUS_NAME + BRIGAND_KING_EXTRA_LINE_2 + BRIGAND_KING_BASIC_PALETTE;

	private final static String ATLANTIS_PALETTE_LINK = "[[Trinket_palette#Atlantean|Atlantean"
		+ " palette]]";
	private final static String ALTANTIS_EXTENDED_PALETTE_LINK = "[[Trinket_palette#Atlantean_"
		+ ".2B_Lime|Atlantean + Lime Palette]]";
	private final static String CLOTH_PALETTE_LINK = "[[Trinket_palette#Cloth-based|cloth "
		+ "colors]]";
	private final static String FOX_BOX_PROMOTION_LINK = "[[Official:Apr14_Promotion|Fox Box "
		+ "Promotion]]";
	private final static String GOLD_BOX_PALETTE_LINK = "[[Trinket_palette#Gold_Box_Trinkets|gold "
		+ "box palette]]";
	private final static String HAUNTED_SEAS_PALETTE_LINK = "[[Trinket_palette#Haunted_Seas_"
		+ "Trinkets|Haunted Seas palette]]";
	private final static String IMPERIAL_OUTPOST_PALETTE_LINK = "[[Trinket_palette#Imperial_"
		+ "Outpost_trinkets|Imperial Outpost palette]].";
	private final static String KRAKEN_PALETTE_LINK = "[[Trinket_palette#Kraken|kraken palette]]";
	private final static String PROMOTIONAL_COLORS_LINK = "[[Color#Awarded_in_promotions|"
		+ "promotional colors]]";
	private final static String VIKING_RAID_PALETTE_LINK = "[[Trinket_palette#Viking_Raid_"
		+ "Trinkets|Viking raid palette]]";

	private final static String FOX_BOX_PROMOTION_LINE = "It was also available in sea green as "
		+ "part of the " + FOX_BOX_PROMOTION_LINK + " in April 2014.";

	private final static String ATLANTIS_EGG_TITLE_LINE = TITLE_LINE_START + "Archelon egg"
		+ TITLE_LINE_END;
	private final static String ATLANTIS_TITLE_LINE = TITLE_LINE_START + "Atlantis palette"
		+ TITLE_LINE_END;
	private final static String BEACHCOMBER_CONCH_TITLE_LINE = TITLE_LINE_START + "Conch shell"
		+ TITLE_LINE_END;
	private final static String BEACHCOMBER_STARFISH_TITLE_LINE = TITLE_LINE_START + "Starfish"
		+ TITLE_LINE_END;
	private final static String BEACHCOMBER_TITLE_LINE = TITLE_LINE_START + "Beachcomber palette"
		+ TITLE_LINE_END;
	private final static String BLACK_BOX_TITLE_LINE = TITLE_LINE_START + "Black box palette"
		+ TITLE_LINE_END;
	private final static String BLACK_BOX_KIT_TITLE_LINE = TITLE_LINE_START + "Disguise kit"
		+ TITLE_LINE_END;
	private final static String CURSED_ISLES_TITLE_LINE = TITLE_LINE_START + "Cursed isles palette"
		+ TITLE_LINE_END;
	private final static String CURSED_ISLES_BROOCH_TITLE_LINE = TITLE_LINE_START + "Chaos brooch"
		+ TITLE_LINE_END;
	private final static String GOLD_BOX_TITLE_LINE = TITLE_LINE_START + "Gold box palette"
		+ TITLE_LINE_END;
	private final static String GOLD_BOX_FEATHER_TITLE_LINE = TITLE_LINE_START
		+ "Radiant phoenix feather" + TITLE_LINE_END;
	private final static String HAUNTED_SEAS_TITLE_LINE = TITLE_LINE_START + "Haunted seas palette"
		+ TITLE_LINE_END;
	private final static String HAUNTED_SEAS_TOME_TITLE_LINE = TITLE_LINE_START + "Haunted tome"
		+ TITLE_LINE_END;
	private final static String IMPERIAL_OUTPOST_TITLE_LINE = TITLE_LINE_START + "Imperial Outpost"
		+ " palette" + TITLE_LINE_END;
	private final static String VIKING_RAID_TITLE_LINE = TITLE_LINE_START + "Viking raid palette"
		+ TITLE_LINE_END;

	private final static String BEACHCOMBER_EXTRA_LINE = "These Beachcomber trinkets are available"
		+ " from [[Atlantis]], the [[Cursed Isles]], the [[Haunted Seas]], and the [[Kraken]] in "
		+ "all of the " + CLOTH_PALETTE_LINK + " except indigo, inky, and night blue.  They have "
		+ "also been available from promotions in all the " + PROMOTIONAL_COLORS_LINK + ".";
	private final static String BEACHCOMBER_STARFISH_EXTRA_LINE = "The Starfish trinket is "
		+ "available from [[Atlantis]], the [[Cursed Isles]], and the [[Haunted Seas]] in all of "
		+ "the " + CLOTH_PALETTE_LINK + " except inky and night blue.  It has also been available "
		+ "from promotions in all the " + PROMOTIONAL_COLORS_LINK + ".  The indigo starfish was "
		+ "only available from tournaments around the time that the Cursed Isles were initially "
		+ "released.";
	private final static String BEACHCOMBER_CONCH_EXTRA_LINE = "The Conch shell trinket is "
		+ "available in many of the " + CLOTH_PALETTE_LINK + ".  Specifically, it is not available"
		+ " in Atlantean, Mint, Magenta, Indigo, Peach, Light blue, Inky, Persimmon, or Night "
		+ "Blue.";
	private final static String ATLANTIS_EXTRA_LINE = "These trinkets are available from "
		+ "[[Atlantis]] in the colors of the " + ATLANTIS_PALETTE_LINK + ".";
	private final static String ATLANTIS_EGG_EXTRA_LINE = "The Archelon egg trinket is available "
		+ "from [[Atlantis]] in the colors of the " + ALTANTIS_EXTENDED_PALETTE_LINK + ".  "
		+ FOX_BOX_PROMOTION_LINE;
	private final static String CURSED_ISLES_EXTRA_LINE = "These trinkets are available from the "
		+ "[[Cursed Isles]] in the colors of the [[Trinket_palette#Cursed_Isles_Trinkets|Cursed "
		+ "Isles palette]].";
	private final static String CURSED_ISLES_BROOCH_EXTRA_LINE = "The chaos brooch is available "
		+ "from the [[Cursed Isles]] in the colors of the [[Trinket_palette#Cursed_Isles_Trinkets"
		+ "|Cursed Isles palette]].  It was also available in sea green as part of the [[Official:"
		+ "Apr14_Promotion|Fox Box Promotion]] in April 2014.";
	private final static String HAUNTED_SEAS_EXTRA_LINE = "These trinkets are available from the "
		+ "[[Haunted Seas]] in the colors of the " + HAUNTED_SEAS_PALETTE_LINK + ".";
	private final static String HAUNTED_SEAS_TOME_EXTRA_LINE = "The Haunted tome is available from"
		+ " the [[Haunted Seas]] in the colors of the " + HAUNTED_SEAS_PALETTE_LINK + ".  "
		+ FOX_BOX_PROMOTION_LINE;
	private final static String KRAKEN_TITLE_LINE = TITLE_LINE_START + "Kraken palette"
		+ TITLE_LINE_END;
	private final static String KRAKEN_EXTRA_LINE = "These trinkets are available from the "
		+ "[[Kraken]] in the colors of the " + KRAKEN_PALETTE_LINK + ".";
	private final static String IMPERIAL_OUTPOST_EXTRA_LINE = "All [[Imperial Outpost]] trinkets "
		+ "are available in the colors of the " + IMPERIAL_OUTPOST_PALETTE_LINK + ".";
	private final static String VIKING_RAID_EXTRA_LINE = "All [[Viking raid]] trinkets are "
		+ "available in the colors of the " + VIKING_RAID_PALETTE_LINK + ".";
	private final static String BLACK_BOX_EXTRA_LINE = "These [[black box]] trinkets are available"
		+ " in all of the " + CLOTH_PALETTE_LINK + " except atlantean, indigo, inky, and night "
		+ "blue.";
	private final static String BLACK_BOX_KIT_EXTRA_LINE = "The disguise kit is available from "
		+ "[[black box]]es in the hair colors.";
	private final static String GOLD_BOX_EXTRA_LINE = "These [[gold box]] trinkets are available "
		+ "in the colors of the " + GOLD_BOX_PALETTE_LINK + ".";
	private final static String GOLD_BOX_FEATHER_EXTRA_LINE = "The Radiant phoenix feather trinket"
		+ " was never meant to be available in more than one color.  However, when the first set "
		+ "of [[gold box]] trinkets was released (in April 2012), the game released some "
		+ "\"booched\" colors.  During that month only, it was possible to get the Radiant phoenix"
		+ " feather in all of the gold box trinket colors (at the time).  When the next month of "
		+ "gold boxes became available (in June 2012), and in all subsequent sets, the feather "
		+ "only came in its designed color: red.  This is why the feather doesn't come in grey, "
		+ "persimmon, or LE colors (other than spring green).";

	private final static String TRINKET_NAME_START = "'''<big>[[";
	private final static String TRINKET_NAME_END = "]]</big>'''";

	private final static String BETWEEN_SECTIONS_LINE = "\n\n";

	private final static String TABLE_START = "{|style=\"border:2px solid #898b79; "
		+ "background:#c5c7ae; text-align:center; font-family:Tahoma; font-size:10px;\"";
	private final static String TABLE_END = "|}";
	private final static String TABLE_ROW_START = "|-\n";
	private final static String TABLE_ROW_END = "\n";
	private final static String TABLE_EMPTY_CELL = "|\n";
	private final static String TABLE_CELL_START = "|";
	private final static String TABLE_CELL_END = "\n";
	private final static String TABLE_CELL_TRINKET_START = "{{Colorize trinket|";
	private final static String TABLE_CELL_TRINKET_MIDDLE = "|";
	private final static String TABLE_CELL_TRINKET_END = "}}";
	private final static String TABLE_HORIZONTAL_LINE_START = "| colspan=";
	private final static String TABLE_HORIZONTAL_LINE_END = " |\n----\n";
	private final static String TABLE_MULTI_ROW_START = "|- valign=\"center\"\n! rowspan=";
	private final static String TABLE_MULTI_ROW_END = "|-\n";
	private final static String TABLE_MULTI_ROW_CELL_START = " | ";
	private final static String TABLE_MULTI_ROW_CELL_MIDDLE = "<br/>";

	private final static String EMPTY_BOX = "[[Image:Item_box_unequip.png]]";

	public static void main(String[] args) {
		Arguments input = new Arguments(args);
		if (input.isValid) {
			if (input.processBrigandKing) {
				processBrigandKing(input.listTrinkets);
			}
			if (input.processBeachcomber) {
				processBeachcomber(input.listTrinkets);
			}
			if (input.processAtlantis) {
				processAtlantis(input.listTrinkets);
			}
			if (input.processCursedIsles) {
				processCursedIsles(input.listTrinkets);
			}
			if (input.processHauntedSeas) {
				processHauntedSeas(input.listTrinkets);
			}
			if (input.processKraken) {
				processKraken(input.listTrinkets);
			}
			if (input.processVikingRaid) {
				processVikingRaid(input.listTrinkets);
			}
			if (input.processBlackBox) {
				processBlackBox(input.listTrinkets);
			}
			if (input.processGoldBox) {
				processGoldBox(input.listTrinkets);
			}
		} else {
			System.out.println("Usage Statement Goes Here");
			System.exit(0);
		}
	}

	private static void processBrigandKing(boolean listTrinkets) {
		System.out.print("Reading Brigand King input files... ");
		String file_path = INPUT_FILE_PATH + DIVIDER + BRIGAND_KING_INPUT_FILE_PATH_MINE;
		File file = new File(file_path);
		List<List<Trinket>> myTrinkets = readBrigandKingTrinkets(file);

		file_path = INPUT_FILE_PATH + DIVIDER + BRIGAND_KING_INPUT_FILE_PATH_OTHERS;
		file = new File(file_path);
		List<List<Trinket>> notMyTrinkets = readBrigandKingTrinkets(file);
		System.out.println("Done!");

		if (listTrinkets) {
			System.out.println("Start of Jamesh's Brigand King trinkets");
			listTrinkets(myTrinkets);
			System.out.println("End of Jamesh's Brigand King trinkets");
			System.out.println("Start of other's Brigand King trinkets");
			listTrinkets(notMyTrinkets);
			System.out.println("End of other's Brigand King trinkets");
		}

		System.out.print("Writing Brigand King output files... ");
		file_path = OUTPUT_FILE_PATH + DIVIDER + BRIGAND_KING_POSSESS_OWN_FILE_PATH;
		file = new File(file_path);
		writeBrigandKingFile(myTrinkets, notMyTrinkets, file, OutputType.BRIGAND_KING_MINE);

		file_path = OUTPUT_FILE_PATH + DIVIDER + BRIGAND_KING_POSSESS_TOTAL_FILE_PATH;
		file = new File(file_path);
		writeBrigandKingFile(myTrinkets, notMyTrinkets, file, OutputType.BRIGAND_KING_TOTAL);

		file_path = OUTPUT_FILE_PATH + DIVIDER + BRIGAND_KING_POSSESS_TOTAL_FILE_PATH;
		file = new File(file_path);
		writeBrigandKingFile(myTrinkets, notMyTrinkets, file, OutputType.BRIGAND_KING_TOTAL);

		file_path = OUTPUT_FILE_PATH + DIVIDER + BRIGAND_KING_POSSESS_TOTAL_FILE_PATH;
		file = new File(file_path);
		writeBrigandKingFile(myTrinkets, notMyTrinkets, file, OutputType.BRIGAND_KING_TOTAL);

		file_path = OUTPUT_FILE_PATH + DIVIDER + BRIGAND_KING_EXTRAS_FILE_PATH;
		file = new File(file_path);
		writeBrigandKingFile(myTrinkets, notMyTrinkets, file, OutputType.BRIGAND_KING_DUPLICATES);
		System.out.println("Done!");
	}

	private static void processBeachcomber(boolean listTrinkets) {
		System.out.print("Reading Beachcomber input file... ");
		String file_path = INPUT_FILE_PATH + DIVIDER + BEACHCOMBER_INPUT_FILE_PATH;
		File file = new File(file_path);
		List<List<Trinket>> trinkets = readBeachcomberTrinkets(file);
		System.out.println("Done!");

		if (listTrinkets) {
			listTrinkets(trinkets);
		}

		System.out.print("Writing Beachcomber output files... ");
		file_path = OUTPUT_FILE_PATH + DIVIDER + BEACHCOMBER_POSSESS_FILE_PATH;
		file = new File(file_path);
		writeBeachcomberFile(trinkets, file, OutputType.POSSESS);

		file_path = OUTPUT_FILE_PATH + DIVIDER + BEACHCOMBER_EXTRAS_FILE_PATH;
		file = new File(file_path);
		writeBeachcomberFile(trinkets, file, OutputType.POSSESS_WITH_DUPLICATES);
		System.out.println("Done!");
	}

	private static void processAtlantis(boolean listTrinkets) {
		System.out.print("Reading Atlantis input file... ");
		String file_path = INPUT_FILE_PATH + DIVIDER + ATLANTIS_INPUT_FILE_PATH;
		File file = new File(file_path);
		List<List<Trinket>> trinkets = readAtlantisTrinkets(file);
		System.out.println("Done!");

		if (listTrinkets) {
			listTrinkets(trinkets);
		}

		System.out.print("Writing Atlantis output files... ");
		file_path = OUTPUT_FILE_PATH + DIVIDER + ATLANTIS_POSSESS_FILE_PATH;
		file = new File(file_path);
		writeAtlantisFile(trinkets, file, OutputType.POSSESS);

		file_path = OUTPUT_FILE_PATH + DIVIDER + ATLANTIS_EXTRAS_FILE_PATH;
		file = new File(file_path);
		writeAtlantisFile(trinkets, file, OutputType.POSSESS_WITH_DUPLICATES);
		System.out.println("Done!");
	}

	private static void processCursedIsles(boolean listTrinkets) {
		System.out.print("Reading Cursed Isles input file... ");
		String file_path = INPUT_FILE_PATH + DIVIDER + CURSED_ISLES_INPUT_FILE_PATH;
		File file = new File(file_path);
		List<List<Trinket>> trinkets = readCursedIslesTrinkets(file);
		System.out.println("Done!");

		if (listTrinkets) {
			listTrinkets(trinkets);
		}

		System.out.print("Writing Cursed Isles output files... ");
		file_path = OUTPUT_FILE_PATH + DIVIDER + CURSED_ISLES_POSSESS_FILE_PATH;
		file = new File(file_path);
		writeCursedIslesFile(trinkets, file, OutputType.POSSESS);

		file_path = OUTPUT_FILE_PATH + DIVIDER + CURSED_ISLES_EXTRAS_FILE_PATH;
		file = new File(file_path);
		writeCursedIslesFile(trinkets, file, OutputType.POSSESS_WITH_DUPLICATES);
		System.out.println("Done!");
	}

	private static void processHauntedSeas(boolean listTrinkets) {
		System.out.print("Reading Haunted Seas input file... ");
		String file_path = INPUT_FILE_PATH + DIVIDER + HAUNTED_SEAS_INPUT_FILE_PATH;
		File file = new File(file_path);
		List<List<Trinket>> trinkets = readHauntedSeasTrinkets(file);
		System.out.println("Done!");

		if (listTrinkets) {
			listTrinkets(trinkets);
		}

		System.out.print("Writing Haunted Seas output files... ");
		file_path = OUTPUT_FILE_PATH + DIVIDER + HAUNTED_SEAS_POSSESS_FILE_PATH;
		file = new File(file_path);
		writeHauntedSeasFile(trinkets, file, OutputType.POSSESS);

		file_path = OUTPUT_FILE_PATH + DIVIDER + HAUNTED_SEAS_EXTRAS_FILE_PATH;
		file = new File(file_path);
		writeHauntedSeasFile(trinkets, file, OutputType.POSSESS_WITH_DUPLICATES);
		System.out.println("Done!");
	}

	private static void processKraken(boolean listTrinkets) {
		System.out.print("Reading Kraken input file... ");
		String file_path = INPUT_FILE_PATH + DIVIDER + KRAKEN_INPUT_FILE_PATH;
		File file = new File(file_path);
		List<List<Trinket>> trinkets = readKrakenTrinkets(file);
		System.out.println("Done!");

		if (listTrinkets) {
			listTrinkets(trinkets);
		}

		System.out.print("Writing Kraken output files... ");
		file_path = OUTPUT_FILE_PATH + DIVIDER + KRAKEN_POSSESS_FILE_PATH;
		file = new File(file_path);
		writeKrakenFile(trinkets, file, OutputType.POSSESS);

		file_path = OUTPUT_FILE_PATH + DIVIDER + KRAKEN_EXTRAS_FILE_PATH;
		file = new File(file_path);
		writeKrakenFile(trinkets, file, OutputType.POSSESS_WITH_DUPLICATES);
		System.out.println("Done!");
	}

	private static void processVikingRaid(boolean listTrinkets) {
		System.out.print("Reading Viking Raid input file... ");
		String file_path = INPUT_FILE_PATH + DIVIDER + VIKING_RAID_INPUT_FILE_PATH;
		File file = new File(file_path);
		List<List<Trinket>> trinkets = readVikingRaidTrinkets(file);
		System.out.println("Done!");

		if (listTrinkets) {
			listTrinkets(trinkets);
		}

		System.out.print("Writing Viking Raid output files... ");
		file_path = OUTPUT_FILE_PATH + DIVIDER + VIKING_RAID_POSSESS_FILE_PATH;
		file = new File(file_path);
		writeVikingRaidFile(trinkets, file, OutputType.POSSESS);

		file_path = OUTPUT_FILE_PATH + DIVIDER + VIKING_RAID_EXTRAS_FILE_PATH;
		file = new File(file_path);
		writeVikingRaidFile(trinkets, file, OutputType.POSSESS_WITH_DUPLICATES);
		System.out.println("Done!");
	}

	private static void processBlackBox(boolean listTrinkets) {
		System.out.print("Reading Black Market input file... ");
		String file_path = INPUT_FILE_PATH + DIVIDER + BLACK_BOX_INPUT_FILE_PATH;
		File file = new File(file_path);
		List<List<Trinket>> trinkets = readBlackBoxTrinkets(file);
		System.out.println("Done!");

		if (listTrinkets) {
			listTrinkets(trinkets);
		}

		System.out.print("Writing Black Market output files... ");
		file_path = OUTPUT_FILE_PATH + DIVIDER + BLACK_BOX_POSSESS_FILE_PATH;
		file = new File(file_path);
		writeBlackBoxFile(trinkets, file, OutputType.POSSESS);

		file_path = OUTPUT_FILE_PATH + DIVIDER + BLACK_BOX_EXTRAS_FILE_PATH;
		file = new File(file_path);
		writeBlackBoxFile(trinkets, file, OutputType.POSSESS_WITH_DUPLICATES);
		System.out.println("Done!");
	}

	private static void processGoldBox(boolean listTrinkets) {
		System.out.print("Reading Gold Box input file... ");
		String file_path = INPUT_FILE_PATH + DIVIDER + GOLD_BOX_INPUT_FILE_PATH;
		File file = new File(file_path);
		List<List<Trinket>> trinkets = readGoldBoxTrinkets(file);
		System.out.println("Done!");

		if (listTrinkets) {
			listTrinkets(trinkets);
		}

		System.out.print("Writing Gold Box output files... ");
		file_path = OUTPUT_FILE_PATH + DIVIDER + GOLD_BOX_POSSESS_FILE_PATH;
		file = new File(file_path);
		writeGoldBoxFile(trinkets, file, OutputType.POSSESS);

		file_path = OUTPUT_FILE_PATH + DIVIDER + GOLD_BOX_EXTRAS_FILE_PATH;
		file = new File(file_path);
		writeGoldBoxFile(trinkets, file, OutputType.POSSESS_WITH_DUPLICATES);
		System.out.println("Done!");
	}

	private static List<List<Trinket>> readBrigandKingTrinkets(File file) {
		List<List<Trinket>> trinkets = new ArrayList<List<Trinket>>();
		List<Trinket> row = new ArrayList<Trinket>();
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			scanner.useDelimiter("\n");
			// Three header rows
			scanner.next();
			scanner.next();
			scanner.next();
			int columnIndex = 0;
			int rowIndex = 0;
			String name = null;
			Trinket trinket = null;
			int count = -1;
			BrigandKingTrinketType BKType = null;
			while (scanner.hasNext()) {
				columnIndex = 0;
				scanner.useDelimiter("\t");
				if (rowIndex != 0 && rowIndex != 7 && rowIndex != 14 && rowIndex != 21
					&& rowIndex != 28 && rowIndex != 35 && rowIndex != 42 && rowIndex != 49) {

					// TP value
					scanner.next();
					name = scanner.next().replace('\n', ' ').trim();
					trinket = null;
					String next = "";
					boolean extended = false;

					// Figure out BK type
					if (BrigandKingTrinket.BarnabasTrinkets.contains(name)) {
						BKType = BrigandKingTrinketType.BARNABAS;
					} else if (BrigandKingTrinket.WidowTrinkets.contains(name)) {
						BKType = BrigandKingTrinketType.WIDOW;
					} else if (BrigandKingTrinket.SkullsplitterTrinkets.contains(name)) {
						BKType = BrigandKingTrinketType.SKULLSPLITTER;
					} else if (BrigandKingTrinket.AzarbadTrinkets.contains(name)) {
						BKType = BrigandKingTrinketType.AZARBAD;
					} else if (BrigandKingTrinket.VargasTrinkets.contains(name)) {
						BKType = BrigandKingTrinketType.VARGAS;
					} else if (BrigandKingTrinket.MadamTrinkets.contains(name)) {
						BKType = BrigandKingTrinketType.MADAM;
					} else if (BrigandKingTrinket.GretchenTrinkets.contains(name)) {
						BKType = BrigandKingTrinketType.GRETCHEN;
					} else if (BrigandKingTrinket.FiniusTrinkets.contains(name)) {
						BKType = BrigandKingTrinketType.FINIUS;
					}

					// Basic
					if (BKType == BrigandKingTrinketType.BARNABAS
						|| BKType == BrigandKingTrinketType.WIDOW
						|| BKType == BrigandKingTrinketType.VARGAS
						|| BKType == BrigandKingTrinketType.GRETCHEN
						|| BKType == BrigandKingTrinketType.FINIUS) {
						extended = false;
					}
					// Extended
					else if (BKType == BrigandKingTrinketType.SKULLSPLITTER
						|| BKType == BrigandKingTrinketType.AZARBAD
						|| BKType == BrigandKingTrinketType.MADAM) {
						extended = true;
					}

					if (extended) {
						row =
							new ArrayList<Trinket>(
								BrigandKingTrinket.availableExtendedColors.size());
						for (int i = 0; i < BrigandKingTrinket.availableExtendedColors.size(); i++) {
							next = scanner.next();
							if (next.equals(""))
								count = 0;
							else
								count = Integer.parseInt(next);

							trinket =
								new BrigandKingTrinket(name,
									BrigandKingTrinket.availableExtendedColors.get(columnIndex++),
									count);
							row.add(trinket);
						}
					} else {
						row =
							new ArrayList<Trinket>(BrigandKingTrinket.availableBasicColors.size());
						for (int i = 0; i < BrigandKingTrinket.availableBasicColors.size(); i++) {
							next = scanner.next();
							if (next.equals(""))
								count = 0;
							else
								count = Integer.parseInt(next);

							trinket =
								new BrigandKingTrinket(name,
									BrigandKingTrinket.availableBasicColors.get(columnIndex++),
									count);
							row.add(trinket);
						}
					}

					trinkets.add(row);
				}
				scanner.useDelimiter("\n");
				name = scanner.next();
				rowIndex++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find Beachcomber input file.");
		} finally {
			scanner.close();
		}

		return trinkets;
	}

	private static List<List<Trinket>> readBeachcomberTrinkets(File file) {
		List<List<Trinket>> trinkets = new ArrayList<List<Trinket>>();
		List<Trinket> row = new ArrayList<Trinket>();
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			scanner.useDelimiter("\n");
			// Two header rows
			scanner.next();
			scanner.next();
			int index = 0;
			while (scanner.hasNext()) {
				index = 0;
				scanner.useDelimiter("\t");
				String name = scanner.next().replace('\n', ' ').trim();
				// TP value
				scanner.next();
				Trinket trinket = null;
				String next = "";
				int count = -2;

				// Colorless
				if (BeachcomberTrinket.colorlessTrinkets.contains(name)) {
					row = new ArrayList<Trinket>(1);

					next = scanner.next();
					if (next.equals("")) {
						count = 0;
					} else {
						count = Integer.parseInt(next);
					}

					trinket = new BeachcomberTrinket(name, null, count);
					row.add(trinket);
				}
				// Starfish
				else if (name.equalsIgnoreCase(BeachcomberTrinket.starfishName)) {
					row = new ArrayList<Trinket>(BeachcomberTrinket.availableStarfishColors.size());
					for (int i = 0; i < BeachcomberTrinket.availableStarfishColors.size(); i++) {

						next = scanner.next();
						if (next.equals(""))
							count = 0;
						else
							count = Integer.parseInt(next);

						trinket =
							new BeachcomberTrinket(name,
								BeachcomberTrinket.availableStarfishColors.get(index++), count);
						row.add(trinket);
					}
				}
				// Conch shell
				else if (name.equalsIgnoreCase(BeachcomberTrinket.conchName)) {
					row = new ArrayList<Trinket>(BeachcomberTrinket.availableConchColors.size());
					for (int i = 0; i < BeachcomberTrinket.availableStarfishColors.size(); i++) {
						if (i == 18 || i == 21 || i == 23 || i == 24 || i > 25) {
							scanner.next();
							continue;
						}

						next = scanner.next();
						if (next.equals(""))
							count = 0;
						else
							count = Integer.parseInt(next);

						trinket =
							new BeachcomberTrinket(name,
								BeachcomberTrinket.availableConchColors.get(index++), count);
						row.add(trinket);
					}
				}
				// Colored (normal)
				else {
					row = new ArrayList<Trinket>(BeachcomberTrinket.availableColors.size());
					for (int i = 0; i < BeachcomberTrinket.availableStarfishColors.size(); i++) {
						if (i == 24) {
							scanner.next();
							continue;
						}

						next = scanner.next();
						if (next.equals(""))
							count = 0;
						else
							count = Integer.parseInt(next);

						trinket =
							new BeachcomberTrinket(name,
								BeachcomberTrinket.availableColors.get(index++), count);
						row.add(trinket);
					}
				}

				trinkets.add(row);
				scanner.useDelimiter("\n");
				name = scanner.next();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find Beachcomber input file.");
		} finally {
			scanner.close();
		}

		return trinkets;
	}

	private static List<List<Trinket>> readAtlantisTrinkets(File file) {
		List<List<Trinket>> trinkets = new ArrayList<List<Trinket>>();
		List<Trinket> row = new ArrayList<Trinket>();
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			// Two header rows
			scanner.useDelimiter("\n");
			scanner.next();
			scanner.next();
			int index = 0;
			while (scanner.hasNext()) {
				index = 0;
				scanner.useDelimiter("\t");
				String name = scanner.next().replace('\n', ' ').trim();
				// TP value
				scanner.next();
				Trinket trinket = null;
				String next = "";
				int count = -2;

				// Colorless
				if (AtlantisTrinket.colorlessTrinkets.contains(name)) {
					row = new ArrayList<Trinket>(1);

					next = scanner.next();
					if (next.equals("")) {
						count = 0;
					} else {
						count = Integer.parseInt(next);
					}

					trinket = new AtlantisTrinket(name, count);
					row.add(trinket);
				}
				// Archelon Egg
				else if (name.equalsIgnoreCase(AtlantisTrinket.eggName)) {
					row = new ArrayList<Trinket>(AtlantisTrinket.availableEggColors.size());
					for (int i = 0; i < AtlantisTrinket.availableEggColors.size(); i++) {

						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}

						trinket =
							new AtlantisTrinket(name,
								AtlantisTrinket.availableEggColors.get(index++), count);
						row.add(trinket);
					}
				}
				// Colored (normal)
				else {
					row = new ArrayList<Trinket>(AtlantisTrinket.availableColors.size());
					for (int i = 0; i < AtlantisTrinket.availableEggColors.size(); i++) {
						if (i == 3 || i == 6) {
							scanner.next();
							continue;
						}

						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}

						trinket =
							new AtlantisTrinket(name, AtlantisTrinket.availableColors.get(index++),
								count);
						row.add(trinket);
					}
				}

				trinkets.add(row);
				scanner.useDelimiter("\n");
				name = scanner.next();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find Atlantis input file.");
		} finally {
			scanner.close();
		}

		return trinkets;
	}

	private static List<List<Trinket>> readCursedIslesTrinkets(File file) {
		List<List<Trinket>> trinkets = new ArrayList<List<Trinket>>();
		List<Trinket> row = new ArrayList<Trinket>();
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			// Two header rows
			scanner.useDelimiter("\n");
			scanner.next();
			scanner.next();
			int index = 0;
			while (scanner.hasNext()) {
				index = 0;
				scanner.useDelimiter("\t");
				String name = scanner.next().replace('\n', ' ').trim();
				// TP value
				scanner.next();
				Trinket trinket = null;
				String next = "";
				int count = -2;
				// Colorless
				if (CursedIslesTrinket.colorlessTrinkets.contains(name)) {
					row = new ArrayList<Trinket>(1);

					next = scanner.next();
					if (next.equals("")) {
						count = 0;
					} else {
						count = Integer.parseInt(next);
					}

					trinket = new CursedIslesTrinket(name, count);
					row.add(trinket);
				}
				// Chaos Brooch
				else if (name.equals(CursedIslesTrinket.broochName)) {
					row = new ArrayList<Trinket>(CursedIslesTrinket.availableBroochColors.size());
					for (int i = 0; i < CursedIslesTrinket.availableBroochColors.size(); i++) {
						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}

						trinket =
							new CursedIslesTrinket(name,
								CursedIslesTrinket.availableBroochColors.get(index++), count);
						row.add(trinket);
					}
				}
				// Colored (normal)
				else {
					row = new ArrayList<Trinket>(CursedIslesTrinket.availableColors.size());
					for (int i = 0; i < CursedIslesTrinket.availableColors.size(); i++) {
						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}

						trinket =
							new CursedIslesTrinket(name,
								CursedIslesTrinket.availableColors.get(index++), count);
						row.add(trinket);
					}
				}
				trinkets.add(row);
				scanner.useDelimiter("\n");
				name = scanner.next();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find Cursed Isles input file.");
		} finally {
			scanner.close();
		}

		return trinkets;
	}

	private static List<List<Trinket>> readHauntedSeasTrinkets(File file) {
		List<List<Trinket>> trinkets = new ArrayList<List<Trinket>>();
		List<Trinket> row = new ArrayList<Trinket>();
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			// Two header rows
			scanner.useDelimiter("\n");
			scanner.next();
			scanner.next();
			int index = 0;
			while (scanner.hasNext()) {
				index = 0;
				scanner.useDelimiter("\t");
				String name = scanner.next().replace('\n', ' ').trim();
				// TP value
				scanner.next();
				Trinket trinket = null;
				String next = "";
				int count = -2;

				// Colorless
				if (HauntedSeasTrinket.colorlessTrinkets.contains(name)) {
					row = new ArrayList<Trinket>(1);

					next = scanner.next();
					if (next.equals("")) {
						count = 0;
					} else {
						count = Integer.parseInt(next);
					}

					trinket = new HauntedSeasTrinket(name, count);
					row.add(trinket);
				}
				// Haunted tome
				else if (name.equalsIgnoreCase(HauntedSeasTrinket.tomeName)) {
					row = new ArrayList<Trinket>(HauntedSeasTrinket.availableTomeColors.size());
					for (int i = 0; i < HauntedSeasTrinket.availableTomeColors.size(); i++) {

						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}

						trinket =
							new HauntedSeasTrinket(name,
								HauntedSeasTrinket.availableTomeColors.get(index++), count);
						row.add(trinket);
					}
				}
				// Colored (normal)
				else {
					row = new ArrayList<Trinket>(HauntedSeasTrinket.availableColors.size());
					for (int i = 0; i < HauntedSeasTrinket.availableTomeColors.size(); i++) {
						if (i == 8) {
							scanner.next();
							continue;
						}

						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}

						trinket =
							new HauntedSeasTrinket(name,
								HauntedSeasTrinket.availableColors.get(index++), count);
						row.add(trinket);
					}
				}

				trinkets.add(row);
				scanner.useDelimiter("\n");
				name = scanner.next();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find Haunted Seas input file.");
		} finally {
			scanner.close();
		}

		return trinkets;
	}

	private static List<List<Trinket>> readKrakenTrinkets(File file) {
		List<List<Trinket>> trinkets = new ArrayList<List<Trinket>>();
		List<Trinket> row = new ArrayList<Trinket>();
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			scanner.useDelimiter("\n");
			// Two header rows
			scanner.next();
			scanner.next();
			int index = 0;
			while (scanner.hasNext()) {
				index = 0;
				scanner.useDelimiter("\t");
				String name = scanner.next().replace('\n', ' ').trim();
				// TP value
				scanner.next();
				Trinket trinket = null;
				String next = "";
				int count = -2;
				// Colorless
				if (KrakenTrinket.colorlessTrinkets.contains(name)) {
					row = new ArrayList<Trinket>(1);

					next = scanner.next();
					if (next.equals("")) {
						count = 0;
					} else {
						count = Integer.parseInt(next);
					}

					trinket = new KrakenTrinket(name, count);
					row.add(trinket);
				}
				// Colored
				else {
					row = new ArrayList<Trinket>(KrakenTrinket.availableColors.size());
					for (int i = 0; i < KrakenTrinket.availableColors.size(); i++) {
						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}

						trinket =
							new KrakenTrinket(name, KrakenTrinket.availableColors.get(index++),
								count);
						row.add(trinket);
					}
				}
				trinkets.add(row);
				scanner.useDelimiter("\n");
				name = scanner.next();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find Kraken input file.");
		} finally {
			scanner.close();
		}

		return trinkets;
	}

	private static List<List<Trinket>> readVikingRaidTrinkets(File file) {
		List<List<Trinket>> trinkets = new ArrayList<List<Trinket>>();
		List<Trinket> row = new ArrayList<Trinket>();
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			scanner.useDelimiter("\n");
			// Two header rows
			scanner.next();
			scanner.next();
			int index = 0;
			while (scanner.hasNext()) {
				index = 0;
				scanner.useDelimiter("\t");
				String name = scanner.next().replace('\n', ' ').trim();
				row = new ArrayList<Trinket>(VikingRaidTrinket.availableColors.size());
				// TP value
				scanner.next();
				// If Some trinkets were colorless, code here to handle them
				Trinket trinket = null;
				String next = "";
				int count = -2;
				for (int i = 0; i < VikingRaidTrinket.availableColors.size(); i++) {
					next = scanner.next();
					if (next.equals("")) {
						count = 0;
					} else {
						count = Integer.parseInt(next);
					}

					trinket =
						new VikingRaidTrinket(name, VikingRaidTrinket.availableColors.get(index++),
							count);
					row.add(trinket);
				}
				trinkets.add(row);
				scanner.useDelimiter("\n");
				name = scanner.next();
				// scanner.useDelimiter("\t");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find Viking Raid input file.");
		} finally {
			scanner.close();
		}

		return trinkets;
	}

	private static List<List<Trinket>> readBlackBoxTrinkets(File file) {
		List<List<Trinket>> trinkets = new ArrayList<List<Trinket>>();
		List<Trinket> row = new ArrayList<Trinket>();
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			scanner.useDelimiter("\n");
			// Two header rows
			scanner.next();
			scanner.next();
			int index = 0;
			while (scanner.hasNext()) {
				index = 0;
				scanner.useDelimiter("\t");
				String name = scanner.next().replace('\n', ' ').trim();
				// TP value
				scanner.next();
				Trinket trinket = null;
				String next = "";
				int count = -2;

				// Empty line above Disguise kit
				if (name.equalsIgnoreCase("")) {
					row = null;
				}
				// Disguise kit
				else if (name.equalsIgnoreCase(BlackBoxTrinket.kitName)) {
					row = new ArrayList<Trinket>(BlackBoxTrinket.availableKitColors.size());
					for (int i = 0; index < BlackBoxTrinket.availableKitColors.size(); i++) {
						if (i == 2 || i == 7 || i == 9 || i == 10) {
							scanner.next();
							continue;
						}

						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}
						trinket =
							new BlackBoxTrinket(name,
								BlackBoxTrinket.availableKitColors.get(index++), count);
						row.add(trinket);
					}
				}
				// Colored
				else {
					row = new ArrayList<Trinket>(BlackBoxTrinket.availableColors.size());
					for (int i = 0; i < BlackBoxTrinket.availableColors.size(); i++) {
						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}

						trinket =
							new GoldBoxTrinket(name, BlackBoxTrinket.availableColors.get(index++),
								count);
						row.add(trinket);
					}
				}
				if (row != null) {
					trinkets.add(row);
				}
				scanner.useDelimiter("\n");
				name = scanner.next();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find Gold Box input file.");
		} finally {
			scanner.close();
		}

		return trinkets;
	}

	private static List<List<Trinket>> readGoldBoxTrinkets(File file) {
		List<List<Trinket>> trinkets = new ArrayList<List<Trinket>>();
		List<Trinket> row = new ArrayList<Trinket>();
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			scanner.useDelimiter("\n");
			// Two header rows
			scanner.next();
			scanner.next();
			int index = 0;
			while (scanner.hasNext()) {
				index = 0;
				scanner.useDelimiter("\t");
				String name = scanner.next().replace('\n', ' ').trim();
				// TP value
				scanner.next();
				Trinket trinket = null;
				String next = "";
				int count = -2;
				// Colorless
				if (GoldBoxTrinket.colorlessTrinkets.contains(name)) {
					row = new ArrayList<Trinket>(1);

					next = scanner.next();
					if (next.equals("")) {
						count = 0;
					} else {
						count = Integer.parseInt(next);
					}

					trinket = new GoldBoxTrinket(name, count);
					row.add(trinket);
				}
				// Radiant phoenix feather
				else if (name.equalsIgnoreCase(GoldBoxTrinket.featherName)) {
					row =
						new ArrayList<Trinket>(GoldBoxTrinket.availablePhoenixFeatherColors.size());
					for (int i = 0; i < GoldBoxTrinket.availableColors.size(); i++) {
						if (i == 2 || i == 15 | i == 16 | i == 18 | i == 19 | i == 20 | i == 21) {
							scanner.next();
							index++;
							continue;
						}

						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}

						trinket =
							new GoldBoxTrinket(name, GoldBoxTrinket.availableColors.get(index++),
								count);
						row.add(trinket);
					}
				}
				// Colored
				else {
					row = new ArrayList<Trinket>(GoldBoxTrinket.availableColors.size());
					for (int i = 0; i < GoldBoxTrinket.availableColors.size(); i++) {
						next = scanner.next();
						if (next.equals("")) {
							count = 0;
						} else {
							count = Integer.parseInt(next);
						}

						trinket =
							new GoldBoxTrinket(name, GoldBoxTrinket.availableColors.get(index++),
								count);
						row.add(trinket);
					}
				}
				trinkets.add(row);
				scanner.useDelimiter("\n");
				name = scanner.next();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find Gold Box input file.");
		} finally {
			scanner.close();
		}

		return trinkets;
	}

	private static void writeBrigandKingFile(List<List<Trinket>> myTrinkets,
		List<List<Trinket>> otherTrinkets, File file, OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Opening stuff
		switch (outputType) {
			case BRIGAND_KING_DUPLICATES:
				sb.append(SUBPAGE_BK_DUPLICATE_LINE);
				break;
			case BRIGAND_KING_MINE:
				sb.append(SUBPAGE_BK_OWN_LINE);
				break;
			case BRIGAND_KING_MISSING_MINE:
				sb.append(SUBPAGE_BK_MISSING_MINE_LINE);
				break;
			case BRIGAND_KING_MISSING_NOT_MINE:
				sb.append(SUBPAGE_BK_MISSING_NOT_MINE_LINE);
				break;
			case BRIGAND_KING_TOTAL:
				sb.append(SUBPAGE_BK_TOTAL_LINE);
				break;
			default:
				return;
		}
		sb.append(BETWEEN_SECTIONS_LINE);

		// Split into sixteen lists (one for each of eight BK, mine vs. not mine)
		List<List<Trinket>> BarnabasMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> WidowMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> SkullsplitterMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> AzarbadMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> VargasMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> MadamMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> GretchenMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> FiniusMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> BarnabasNotMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> WidowNotMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> SkullsplitterNotMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> AzarbadNotMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> VargasNotMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> MadamNotMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> GretchenNotMine = new ArrayList<List<Trinket>>();
		List<List<Trinket>> FiniusNotMine = new ArrayList<List<Trinket>>();
		String name = null;
		for (List<Trinket> l : myTrinkets) {
			name = l.get(0).name;
			if (BrigandKingTrinket.BarnabasTrinkets.contains(name)) {
				BarnabasMine.add(l);
			} else if (BrigandKingTrinket.WidowTrinkets.contains(name)) {
				WidowMine.add(l);
			} else if (BrigandKingTrinket.SkullsplitterTrinkets.contains(name)) {
				SkullsplitterMine.add(l);
			} else if (BrigandKingTrinket.AzarbadTrinkets.contains(name)) {
				AzarbadMine.add(l);
			} else if (BrigandKingTrinket.VargasTrinkets.contains(name)) {
				VargasMine.add(l);
			} else if (BrigandKingTrinket.MadamTrinkets.contains(name)) {
				MadamMine.add(l);
			} else if (BrigandKingTrinket.GretchenTrinkets.contains(name)) {
				GretchenMine.add(l);
			} else if (BrigandKingTrinket.FiniusTrinkets.contains(name)) {
				FiniusMine.add(l);
			}
		}
		for (List<Trinket> l : otherTrinkets) {
			name = l.get(0).name;
			if (BrigandKingTrinket.BarnabasTrinkets.contains(name)) {
				BarnabasNotMine.add(l);
			} else if (BrigandKingTrinket.WidowTrinkets.contains(name)) {
				WidowNotMine.add(l);
			} else if (BrigandKingTrinket.SkullsplitterTrinkets.contains(name)) {
				SkullsplitterNotMine.add(l);
			} else if (BrigandKingTrinket.AzarbadTrinkets.contains(name)) {
				AzarbadNotMine.add(l);
			} else if (BrigandKingTrinket.VargasTrinkets.contains(name)) {
				VargasNotMine.add(l);
			} else if (BrigandKingTrinket.MadamTrinkets.contains(name)) {
				MadamNotMine.add(l);
			} else if (BrigandKingTrinket.GretchenTrinkets.contains(name)) {
				GretchenNotMine.add(l);
			} else if (BrigandKingTrinket.FiniusTrinkets.contains(name)) {
				FiniusNotMine.add(l);
			}
		}

		// Barnabas
		sb.append(BRIGAND_KING_BARNABAS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BRIGAND_KING_BARNABAS_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColorBrigandKing(BarnabasMine, BarnabasNotMine,
			BrigandKingTrinket.availableBasicColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Widow
		sb.append(BRIGAND_KING_WIDOW_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BRIGAND_KING_WIDOW_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColorBrigandKing(WidowMine, WidowNotMine,
			BrigandKingTrinket.availableBasicColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Skullsplitter
		sb.append(BRIGAND_KING_SKULLSPLITTER_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BRIGAND_KING_SKULLSPLITTER_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColorBrigandKing(SkullsplitterMine, SkullsplitterNotMine,
			BrigandKingTrinket.availableExtendedColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Azarbad
		sb.append(BRIGAND_KING_AZARBAD_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BRIGAND_KING_AZARBAD_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColorBrigandKing(AzarbadMine, AzarbadNotMine,
			BrigandKingTrinket.availableExtendedColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Vargas
		sb.append(BRIGAND_KING_VARGAS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BRIGAND_KING_VARGAS_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColorBrigandKing(VargasMine, VargasNotMine,
			BrigandKingTrinket.availableBasicColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Madam
		sb.append(BRIGAND_KING_MADAM_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BRIGAND_KING_MADAM_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColorBrigandKing(MadamMine, MadamNotMine,
			BrigandKingTrinket.availableExtendedColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Gretchen
		sb.append(BRIGAND_KING_GRETCHEN_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BRIGAND_KING_GRETCHEN_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColorBrigandKing(GretchenMine, GretchenNotMine,
			BrigandKingTrinket.availableBasicColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Finius
		sb.append(BRIGAND_KING_FINIUS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BRIGAND_KING_FINIUS_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColorBrigandKing(FiniusMine, FiniusNotMine,
			BrigandKingTrinket.availableBasicColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Ending stuff
		sb.append(CATEGORY_LINE);

		String output = sb.toString();

		FileWriter out = null;
		try {
			out = new FileWriter(file);
			out.write(output);
		} catch (IOException e) {
			System.out.println("Error writing the Brigand King wiki code file.");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	private static void writeBeachcomberFile(List<List<Trinket>> trinkets, File file,
		OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Opening stuff
		switch (outputType) {
			case POSSESS:
				sb.append(SUBPAGE_POSSESS_LINE);
				break;
			case DO_NOT_POSSESS:
				sb.append(SUBPAGE_MISSING_LINE);
				break;
			case POSSESS_WITH_DUPLICATES:
				sb.append(SUBPAGE_DUPLICATE_LINE);
				break;
			default:
				return;
		}
		sb.append(BETWEEN_SECTIONS_LINE);

		// Split into four lists (normal, colorless, starfish, and conch shell)
		List<List<Trinket>> normal = new ArrayList<List<Trinket>>();
		List<List<Trinket>> colorless = new ArrayList<List<Trinket>>();
		List<List<Trinket>> starfish = new ArrayList<List<Trinket>>();
		List<List<Trinket>> conch = new ArrayList<List<Trinket>>();
		String name = null;
		for (List<Trinket> l : trinkets) {
			name = l.get(0).name;
			if (BeachcomberTrinket.colorlessTrinkets.contains(name)) {
				colorless.add(l);
			} else {
				switch (name) {
					case BeachcomberTrinket.starfishName:
						starfish.add(l);
						break;
					case BeachcomberTrinket.conchName:
						conch.add(l);
						break;
					default:
						normal.add(l);
						break;
				}
			}
		}

		// Starfish
		sb.append(BEACHCOMBER_STARFISH_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BEACHCOMBER_STARFISH_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(starfish, BeachcomberTrinket.availableStarfishColors,
			outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Normal
		sb.append(BEACHCOMBER_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BEACHCOMBER_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(normal, BeachcomberTrinket.availableColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Conch shell
		sb.append(BEACHCOMBER_CONCH_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BEACHCOMBER_CONCH_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(conch, BeachcomberTrinket.availableConchColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Colorless
		sb.append(COLORLESS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(COLORLESS_SINGLE_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableColorless(colorless, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Ending stuff
		sb.append(CATEGORY_LINE);

		String output = sb.toString();

		FileWriter out = null;
		try {
			out = new FileWriter(file);
			out.write(output);
		} catch (IOException e) {
			System.out.println("Error writing the beachcomber wiki code file.");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	private static void writeAtlantisFile(List<List<Trinket>> trinkets, File file,
		OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Opening stuff
		switch (outputType) {
			case POSSESS:
				sb.append(SUBPAGE_POSSESS_LINE);
				break;
			case DO_NOT_POSSESS:
				sb.append(SUBPAGE_MISSING_LINE);
				break;
			case POSSESS_WITH_DUPLICATES:
				sb.append(SUBPAGE_DUPLICATE_LINE);
				break;
			default:
				return;
		}
		sb.append(BETWEEN_SECTIONS_LINE);

		// Split into three lists (normal, colorless, and archelon egg)
		List<List<Trinket>> normal = new ArrayList<List<Trinket>>();
		List<List<Trinket>> colorless = new ArrayList<List<Trinket>>();
		List<List<Trinket>> egg = new ArrayList<List<Trinket>>();
		String name = null;
		for (List<Trinket> l : trinkets) {
			name = l.get(0).name;
			if (AtlantisTrinket.colorlessTrinkets.contains(name)) {
				colorless.add(l);
			} else {
				switch (name) {
					case AtlantisTrinket.eggName:
						egg.add(l);
						break;
					default:
						normal.add(l);
						break;
				}
			}
		}

		// Normal
		sb.append(ATLANTIS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(ATLANTIS_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(normal, AtlantisTrinket.availableColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Archelon Egg
		sb.append(ATLANTIS_EGG_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(ATLANTIS_EGG_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(egg, AtlantisTrinket.availableEggColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Colorless
		sb.append(COLORLESS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(COLORLESS_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableColorless(colorless, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Ending stuff
		sb.append(CATEGORY_LINE);

		String output = sb.toString();

		FileWriter out = null;
		try {
			out = new FileWriter(file);
			out.write(output);
		} catch (IOException e) {
			System.out.println("Error writing the Atlantis wiki code file.");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	private static void writeCursedIslesFile(List<List<Trinket>> trinkets, File file,
		OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Opening stuff
		switch (outputType) {
			case POSSESS:
				sb.append(SUBPAGE_POSSESS_LINE);
				break;
			case DO_NOT_POSSESS:
				sb.append(SUBPAGE_MISSING_LINE);
				break;
			case POSSESS_WITH_DUPLICATES:
				sb.append(SUBPAGE_DUPLICATE_LINE);
				break;
			default:
				return;
		}
		sb.append(BETWEEN_SECTIONS_LINE);

		// Split into three lists (normal, chaos brooch, and colorless)
		List<List<Trinket>> normal = new ArrayList<List<Trinket>>();
		List<List<Trinket>> brooch = new ArrayList<List<Trinket>>();
		List<List<Trinket>> colorless = new ArrayList<List<Trinket>>();
		String name = null;
		for (List<Trinket> l : trinkets) {
			name = l.get(0).name;
			if (CursedIslesTrinket.colorlessTrinkets.contains(name)) {
				colorless.add(l);
			} else if (name.equals(CursedIslesTrinket.broochName)) {
				brooch.add(l);
			} else {
				normal.add(l);
			}
		}

		// Normal
		sb.append(CURSED_ISLES_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(CURSED_ISLES_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(normal, CursedIslesTrinket.availableColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Chaos brooch
		sb.append(CURSED_ISLES_BROOCH_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(CURSED_ISLES_BROOCH_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(brooch, CursedIslesTrinket.availableBroochColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Colorless
		sb.append(COLORLESS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(COLORLESS_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableColorless(colorless, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Ending stuff
		sb.append(CATEGORY_LINE);

		String output = sb.toString();

		FileWriter out = null;
		try {
			out = new FileWriter(file);
			out.write(output);
		} catch (IOException e) {
			System.out.println("Error writing the Cursed Isles wiki code file.");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	private static void writeHauntedSeasFile(List<List<Trinket>> trinkets, File file,
		OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Opening stuff
		switch (outputType) {
			case POSSESS:
				sb.append(SUBPAGE_POSSESS_LINE);
				break;
			case DO_NOT_POSSESS:
				sb.append(SUBPAGE_MISSING_LINE);
				break;
			case POSSESS_WITH_DUPLICATES:
				sb.append(SUBPAGE_DUPLICATE_LINE);
				break;
			default:
				return;
		}
		sb.append(BETWEEN_SECTIONS_LINE);

		// Split into three lists (normal, colorless, and haunted tome)
		List<List<Trinket>> normal = new ArrayList<List<Trinket>>();
		List<List<Trinket>> colorless = new ArrayList<List<Trinket>>();
		List<List<Trinket>> tome = new ArrayList<List<Trinket>>();
		String name = null;
		for (List<Trinket> l : trinkets) {
			name = l.get(0).name;
			if (HauntedSeasTrinket.colorlessTrinkets.contains(name)) {
				colorless.add(l);
			} else {
				switch (name) {
					case HauntedSeasTrinket.tomeName:
						tome.add(l);
						break;
					default:
						normal.add(l);
						break;
				}
			}
		}

		// Normal
		sb.append(HAUNTED_SEAS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(HAUNTED_SEAS_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(normal, HauntedSeasTrinket.availableColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Haunted tome
		sb.append(HAUNTED_SEAS_TOME_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(HAUNTED_SEAS_TOME_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(tome, HauntedSeasTrinket.availableTomeColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Colorless
		sb.append(COLORLESS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(COLORLESS_SINGLE_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableColorless(colorless, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Ending stuff
		sb.append(CATEGORY_LINE);

		String output = sb.toString();

		FileWriter out = null;
		try {
			out = new FileWriter(file);
			out.write(output);
		} catch (IOException e) {
			System.out.println("Error writing the Atlantis wiki code file.");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	private static void writeKrakenFile(List<List<Trinket>> trinkets, File file,
		OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Opening stuff
		switch (outputType) {
			case POSSESS:
				sb.append(SUBPAGE_POSSESS_LINE);
				break;
			case DO_NOT_POSSESS:
				sb.append(SUBPAGE_MISSING_LINE);
				break;
			case POSSESS_WITH_DUPLICATES:
				sb.append(SUBPAGE_DUPLICATE_LINE);
				break;
			default:
				return;
		}
		sb.append(BETWEEN_SECTIONS_LINE);

		// Split into two lists (normal and colorless)
		List<List<Trinket>> normal = new ArrayList<List<Trinket>>();
		List<List<Trinket>> colorless = new ArrayList<List<Trinket>>();
		String name = null;
		for (List<Trinket> l : trinkets) {
			name = l.get(0).name;
			if (KrakenTrinket.colorlessTrinkets.contains(name)) {
				colorless.add(l);
			} else {
				normal.add(l);
			}
		}

		// Normal
		sb.append(KRAKEN_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(KRAKEN_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(normal, KrakenTrinket.availableColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Colorless
		sb.append(COLORLESS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(COLORLESS_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableColorless(colorless, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Ending stuff
		sb.append(CATEGORY_LINE);

		String output = sb.toString();

		FileWriter out = null;
		try {
			out = new FileWriter(file);
			out.write(output);
		} catch (IOException e) {
			System.out.println("Error writing the Kraken wiki code file.");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	private static void writeVikingRaidFile(List<List<Trinket>> trinkets, File file,
		OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Opening stuff
		switch (outputType) {
			case POSSESS:
				sb.append(SUBPAGE_POSSESS_LINE);
				break;
			case DO_NOT_POSSESS:
				sb.append(SUBPAGE_MISSING_LINE);
				break;
			case POSSESS_WITH_DUPLICATES:
				sb.append(SUBPAGE_DUPLICATE_LINE);
				break;
			default:
				return;
		}
		sb.append(BETWEEN_SECTIONS_LINE);

		// Viking raid only has one section (normal)
		sb.append(VIKING_RAID_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(VIKING_RAID_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(trinkets, VikingRaidTrinket.availableColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Ending stuff
		sb.append(CATEGORY_LINE);

		String output = sb.toString();

		FileWriter out = null;
		try {
			out = new FileWriter(file);
			out.write(output);
		} catch (IOException e) {
			System.out.println("Error writing the viking raid wiki code file.");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	private static void writeBlackBoxFile(List<List<Trinket>> trinkets, File file,
		OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Opening stuff
		switch (outputType) {
			case POSSESS:
				sb.append(SUBPAGE_POSSESS_LINE);
				break;
			case DO_NOT_POSSESS:
				sb.append(SUBPAGE_MISSING_LINE);
				break;
			case POSSESS_WITH_DUPLICATES:
				sb.append(SUBPAGE_DUPLICATE_LINE);
				break;
			default:
				return;
		}
		sb.append(BETWEEN_SECTIONS_LINE);

		// Split into two lists (normal and disguise kit)
		List<List<Trinket>> normal = new ArrayList<List<Trinket>>();
		List<List<Trinket>> kit = new ArrayList<List<Trinket>>();
		String name = null;
		for (List<Trinket> l : trinkets) {
			name = l.get(0).name;
			switch (name) {
				case BlackBoxTrinket.kitName:
					kit.add(l);
					break;
				default:
					normal.add(l);
					break;
			}
		}

		// Normal
		sb.append(BLACK_BOX_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BLACK_BOX_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(normal, BlackBoxTrinket.availableColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Disguise Kit
		sb.append(BLACK_BOX_KIT_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(BLACK_BOX_KIT_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(kit, BlackBoxTrinket.availableKitColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Ending stuff
		sb.append(CATEGORY_LINE);

		String output = sb.toString();

		FileWriter out = null;
		try {
			out = new FileWriter(file);
			out.write(output);
		} catch (IOException e) {
			System.out.println("Error writing the black market wiki code file.");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	private static void writeGoldBoxFile(List<List<Trinket>> trinkets, File file,
		OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Opening stuff
		switch (outputType) {
			case POSSESS:
				sb.append(SUBPAGE_POSSESS_LINE);
				break;
			case DO_NOT_POSSESS:
				sb.append(SUBPAGE_MISSING_LINE);
				break;
			case POSSESS_WITH_DUPLICATES:
				sb.append(SUBPAGE_DUPLICATE_LINE);
				break;
			default:
				return;
		}
		sb.append(BETWEEN_SECTIONS_LINE);

		// Split into three lists (normal, colorless, and phoenix feather)
		List<List<Trinket>> normal = new ArrayList<List<Trinket>>();
		List<List<Trinket>> colorless = new ArrayList<List<Trinket>>();
		List<List<Trinket>> feather = new ArrayList<List<Trinket>>();
		String name = null;
		for (List<Trinket> l : trinkets) {
			name = l.get(0).name;
			if (GoldBoxTrinket.colorlessTrinkets.contains(name)) {
				colorless.add(l);
			} else {
				switch (l.get(0).name) {
					case GoldBoxTrinket.featherName:
						feather.add(l);
						break;
					default:
						normal.add(l);
						break;
				}
			}
		}

		// Normal
		sb.append(GOLD_BOX_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(GOLD_BOX_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(normal, GoldBoxTrinket.availableColors, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Phoenix feather
		sb.append(GOLD_BOX_FEATHER_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(GOLD_BOX_FEATHER_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableOneColor(feather, GoldBoxTrinket.availablePhoenixFeatherColors,
			outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Colorless
		sb.append(COLORLESS_TITLE_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(COLORLESS_EXTRA_LINE);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(writeTableColorless(colorless, outputType));
		sb.append(BETWEEN_SECTIONS_LINE);

		// Ending stuff
		sb.append(CATEGORY_LINE);

		String output = sb.toString();

		FileWriter out = null;
		try {
			out = new FileWriter(file);
			out.write(output);
		} catch (IOException e) {
			System.out.println("Error writing the gold box wiki code file.");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
	}

	private static String writeTableColorless(List<List<Trinket>> trinkets, OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		sb.append(TABLE_START);
		sb.append(BETWEEN_SECTIONS_LINE);
		for (List<Trinket> l : trinkets) {
			Trinket t = l.get(0);
			sb.append(TABLE_ROW_START);
			sb.append(TABLE_CELL_START);
			sb.append(TRINKET_NAME_START);
			sb.append(t.name);
			sb.append(TRINKET_NAME_END);
			sb.append(TABLE_CELL_END);

			sb.append(TABLE_CELL_START);

			if (showTrinket(t, outputType)) {
				sb.append(TABLE_CELL_TRINKET_START);
				sb.append(t.name);
				sb.append(TABLE_CELL_TRINKET_MIDDLE);
				sb.append("ignore");
				sb.append(TABLE_CELL_TRINKET_END);
			} else {
				sb.append(EMPTY_BOX);
			}
			sb.append(TABLE_CELL_END);
		}

		return sb.toString();
	}

	private static String writeTableOneColor(List<List<Trinket>> trinkets, List<Color> colors,
		OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Not too many columns
		if (colors.size() <= MAX_ROW_WIDTH) {
			sb.append(TABLE_ROW_START);
			sb.append(TABLE_EMPTY_CELL);
			for (Color c : colors) {
				sb.append(TABLE_CELL_START);
				sb.append(c);
				sb.append(TABLE_CELL_END);
			}
			sb.append(TABLE_ROW_END);
			String colorKey = sb.toString();

			sb = new StringBuilder();
			sb.append(TABLE_START);
			sb.append(BETWEEN_SECTIONS_LINE);

			// Not too many columns, Not too many rows
			if (trinkets.size() <= ROWS_BETWEEN_COLOR_LIST) {
				sb.append(colorKey);
				// Per trinket type
				for (List<Trinket> l : trinkets) {
					// Trinket name
					sb.append(TABLE_ROW_START);
					sb.append(TABLE_CELL_START);
					sb.append(TRINKET_NAME_START);
					sb.append(l.get(0).name);
					sb.append(TRINKET_NAME_END);
					sb.append(TABLE_CELL_END);

					// Per color
					for (Trinket t : l) {
						sb.append(TABLE_CELL_START);

						if (showTrinket(t, outputType)) {
							sb.append(TABLE_CELL_TRINKET_START);
							sb.append(t.name);
							sb.append(TABLE_CELL_TRINKET_MIDDLE);
							sb.append(t.primaryColor.getFullName());
							sb.append(TABLE_CELL_TRINKET_END);
						} else {
							sb.append(EMPTY_BOX);
						}

						sb.append(TABLE_CELL_END);
					}
				}
			}

			// Not too many columns, Too many rows
			else {
				int trinketIndex = 0;
				// int rowsRemaining = -1;
				// int lastColorKeyRow = -2;
				for (List<Trinket> l : trinkets) {

					// Color Key Row
					/*
					 * Attempt to evenly space ending ones rowsRemaining = trinkets.size() -
					 * trinketIndex; // Last 2*ROWS_BETWEEN_COLOR_LIST rows if ((rowsRemaining - 2 *
					 * ROWS_BETWEEN_COLOR_LIST) < 0) { // First time here? if (lastColorKeyRow ==
					 * -2) { // Put the key once at the top sb.append(colorKey); lastColorKeyRow =
					 * trinketIndex + (int) (rowsRemaining / 2); } else { // Put the key once in the
					 * middle if (trinketIndex == lastColorKeyRow) { sb.append(colorKey); } } } else
					 * { if (trinketIndex % ROWS_BETWEEN_COLOR_LIST == 0) { sb.append(colorKey); } }
					 */

					if (trinketIndex % ROWS_BETWEEN_COLOR_LIST == 0) {
						sb.append(colorKey);
					}

					// Trinket name
					sb.append(TABLE_ROW_START);
					sb.append(TABLE_CELL_START);
					sb.append(TRINKET_NAME_START);
					sb.append(l.get(0).name);
					sb.append(TRINKET_NAME_END);
					sb.append(TABLE_CELL_END);

					// Per color
					for (Trinket t : l) {
						sb.append(TABLE_CELL_START);

						if (showTrinket(t, outputType)) {
							sb.append(TABLE_CELL_TRINKET_START);
							sb.append(t.name);
							sb.append(TABLE_CELL_TRINKET_MIDDLE);
							sb.append(t.primaryColor.getFullName());
							sb.append(TABLE_CELL_TRINKET_END);
						} else {
							sb.append(EMPTY_BOX);
						}

						sb.append(TABLE_CELL_END);
					}

					trinketIndex++;
				}
			}
			sb.append(TABLE_END);
		}

		// Too many columns
		else {
			int rowsPerTrinket = (int) Math.ceil(1.0 * colors.size() / MAX_ROW_WIDTH);
			int columnsPerRow = (int) Math.ceil(1.0 * colors.size() / rowsPerTrinket);

			sb.append(TABLE_START);
			sb.append(BETWEEN_SECTIONS_LINE);
			int colorIndex = 0;
			int trinketIndex = 0;
			// Per trinket type
			for (List<Trinket> l : trinkets) {
				colorIndex = 0;

				// Horizontal line
				if (trinketIndex > 0) {
					sb.append(TABLE_HORIZONTAL_LINE_START);
					sb.append((columnsPerRow + 1));
					sb.append(TABLE_HORIZONTAL_LINE_END);
				}

				// Trinket name
				sb.append(TABLE_MULTI_ROW_START);
				sb.append(rowsPerTrinket);
				sb.append(TABLE_MULTI_ROW_CELL_START);
				sb.append(TRINKET_NAME_START);
				sb.append(l.get(0).name);
				sb.append(TRINKET_NAME_END);
				sb.append(TABLE_CELL_END);

				// Per color
				for (Trinket t : l) {
					sb.append(TABLE_CELL_START);

					if (showTrinket(t, outputType)) {
						sb.append(TABLE_CELL_TRINKET_START);
						sb.append(t.name);
						sb.append(TABLE_CELL_TRINKET_MIDDLE);
						sb.append(t.primaryColor.getFullName());
						sb.append(TABLE_CELL_TRINKET_END);
					} else {
						sb.append(EMPTY_BOX);
						sb.append(TABLE_MULTI_ROW_CELL_MIDDLE);
					}

					// Put the color under each box
					sb.append(t.primaryColor);
					sb.append(TABLE_CELL_END);

					// End of row?
					if ((colorIndex + 1) % columnsPerRow == 0) {
						sb.append(TABLE_MULTI_ROW_END);
					}
					colorIndex++;
				}

				while (colorIndex % columnsPerRow != 0) {
					if ((colorIndex + 1) % columnsPerRow != 0) {
						sb.append(TABLE_EMPTY_CELL);
					} else {
						sb.append(TABLE_ROW_START);
					}
					colorIndex++;
				}

				trinketIndex++;
			}
			sb.append(TABLE_END);
		}
		return sb.toString();
	}

	private static String writeTableOneColorBrigandKing(List<List<Trinket>> myTrinkets,
		List<List<Trinket>> otherTrinkets, List<Color> colors, OutputType outputType) {
		StringBuilder sb = new StringBuilder();

		// Color key
		sb.append(TABLE_ROW_START);
		sb.append(TABLE_EMPTY_CELL);
		for (Color c : colors) {
			sb.append(TABLE_CELL_START);
			sb.append(c);
			sb.append(TABLE_CELL_END);
		}
		sb.append(TABLE_ROW_END);
		String colorKey = sb.toString();

		sb = new StringBuilder();
		sb.append(TABLE_START);
		sb.append(BETWEEN_SECTIONS_LINE);
		sb.append(colorKey);

		// Setup
		List<Trinket> mine = null;
		List<Trinket> theirs = null;
		int columnIndex = 0;

		// Brigand King is limited to (currently) seven types, and seven colors. This is not too
		// many rows or columns, so we'll hard-code to a 6x6, 6x7, or 7x6 table (depending on which
		// BK)
		for (int rowIndex = 0; rowIndex < myTrinkets.size(); rowIndex++) {
			mine = myTrinkets.get(rowIndex);
			theirs = otherTrinkets.get(rowIndex);
			Trinket t = mine.get(0);
			sb.append(TABLE_ROW_START);

			// Trinket name cell
			sb.append(TABLE_CELL_START);
			sb.append(TRINKET_NAME_START);
			sb.append(t.name);
			sb.append(TRINKET_NAME_END);
			sb.append(TABLE_CELL_END);

			// Trinket cells (by color)
			for (columnIndex = 0; columnIndex < mine.size(); columnIndex++) {
				t = mine.get(columnIndex);
				sb.append(TABLE_CELL_START);

				if (showBrigandKingTrinket(mine.get(columnIndex), theirs.get(columnIndex),
					outputType)) {
					sb.append(TABLE_CELL_TRINKET_START);
					sb.append(t.name);
					sb.append(TABLE_CELL_TRINKET_MIDDLE);
					sb.append(t.primaryColor.getFullName());
					sb.append(TABLE_CELL_TRINKET_END);
				} else {
					sb.append(EMPTY_BOX);
				}

				sb.append(TABLE_CELL_END);
			}
		}

		sb.append(TABLE_END);

		return sb.toString();
	}

	private static boolean showTrinket(Trinket t, OutputType outputType) {
		// Which type of output file do we want?
		switch (outputType) {
			case POSSESS:
				if (t.count > 0) { return true; }
				break;
			case POSSESS_WITH_DUPLICATES:
				if (t.count >= MINIMUM_DUPLICATE_NUMBER) { return true; }
				break;
			case DO_NOT_POSSESS:
				if (t.count == 0) { return true; }
				break;
			default:
				break;
		}
		return false;
	}

	private static boolean showBrigandKingTrinket(Trinket myTrinket, Trinket otherTrinket,
		OutputType outputType) {
		switch (outputType) {
			case BRIGAND_KING_DUPLICATES:
				if (otherTrinket.count >= MINIMUM_DUPLICATE_NUMBER
					|| (myTrinket.count > 0 && otherTrinket.count > (MINIMUM_DUPLICATE_NUMBER - 1))) { return true; }
				break;
			case BRIGAND_KING_MINE:
				if (myTrinket.count > 0) { return true; }
				break;
			case BRIGAND_KING_MISSING_MINE:
				if (myTrinket.count == 0) { return true; }
				break;
			case BRIGAND_KING_MISSING_NOT_MINE:
				if ((myTrinket.count == 0) && (otherTrinket.count == 0)) { return true; }
				break;
			case BRIGAND_KING_TOTAL:
				if ((myTrinket.count > 0) || (otherTrinket.count > 0)) { return true; }
				break;
			default:
				break;
		}
		return false;
	}

	private static void listTrinkets(List<List<Trinket>> trinkets) {
		for (List<Trinket> l : trinkets) {
			for (Trinket t : l) {
				System.out.println(t);
			}
		}
	}
}
