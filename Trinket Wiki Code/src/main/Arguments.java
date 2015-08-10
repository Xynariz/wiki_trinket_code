package main;

public class Arguments {

	boolean isValid;

	boolean listTrinkets;

	boolean processBrigandKing;
	boolean processBeachcomber;
	boolean processAtlantis;
	boolean processCursedIsles;
	boolean processHauntedSeas;
	boolean processImperialOutpost;
	boolean processKraken;
	boolean processVikingRaid;
	boolean processBlackBox;
	boolean processGoldBox;

	public Arguments(String[] args) {
		isValid = false;
		listTrinkets = false;
		processBrigandKing = false;
		processBeachcomber = false;
		processAtlantis = false;
		processCursedIsles = false;
		processHauntedSeas = false;
		processKraken = false;
		processVikingRaid = false;
		processBlackBox = false;
		processGoldBox = false;
		processArgs(args);
	}

	private void processArgs(String[] args) {

		if (args.length == 0) {
			isValid = false;
			return;
		}

		for (String s : args) {
			s =
				s.replace('-', ' ').replace('\\', ' ').replace('/', ' ').replace('_', ' ').trim()
					.toLowerCase();
			switch (s) {
				case "a":
				case "all":
					processBrigandKing =
						processBeachcomber =
							processAtlantis =
								processCursedIsles =
									processHauntedSeas =
										processKraken =
											processVikingRaid =
												processBlackBox = processGoldBox = true;
					break;

				case "atl":
				case "atlantis":
					processAtlantis = true;
					break;

				case "b_b":
				case "b_m":
				case "black_box":
				case "black_market":
				case "blackmarket":
				case "bb":
				case "bm":
					processBlackBox = true;
					break;

				case "b_c":
				case "bc":
				case "beachcomber":
					processBeachcomber = true;
					break;

				case "b_k":
				case "bk":
				case "brigand_king":
				case "brigandking":
					processBrigandKing = true;
					break;

				case "c_i":
				case "ci":
				case "cursed_isles":
					processCursedIsles = true;
					break;

				case "g_b":
				case "gb":
				case "gold_box":
				case "goldbox":
					processGoldBox = true;
					break;

				case "h_s":
				case "haunted_seas":
				case "hauntedseas":
				case "hs":
					processHauntedSeas = true;
					break;

				case "i_o":
				case "io":
				case "imperial_outpost":
				case "imperialoutpost":
					processImperialOutpost = true;
					break;

				case "k":
				case "kraken":
					processKraken = true;
					break;

				case "list":
				case "listtrinkets":
				case "verbose":
					listTrinkets = true;
					break;

				case "v_r":
				case "viking_raid":
				case "vikingraid":
				case "vr":
					processVikingRaid = true;
					break;

				default:
					isValid = false;
					return;
			}
		}

		isValid = true;
	}
}
