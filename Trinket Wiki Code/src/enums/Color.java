package enums;

public enum Color {
	RED("Red"), SILVER("Silver"), TAN("Tan"), WHITE("White"), BLACK("Black"), GREY("Grey"), YELLOW(
		"Yellow"), PINK("Pink"), VIOLET("Violet"), PURPLE("Purple"), NAVY("Navy"), BLUE("Blue"),
	AQUA("Aqua"), LIME("Lime"), GREEN("Green"), ORANGE("Orange"), MAROON("Maroon"), BROWN("Brown"),
	GOLD("Gold"), ATLANTEAN("Atlant.", "atlantean"), ROSE("Rose"), LAVENDER("Lavender"), MINT(
		"Mint"), LIGHT_GREEN("Lt. Green", "light green"), MAGENTA("Magenta"), INDIGO("Indigo"),
	LEMON("Lemon"), PEACH("Peach"), LIGHT_BLUE("Lt. Blue", "light blue"), INKY("Inky"), PERSIMMON(
		"Persim.", "persimmon"), ROYAL_BLUE("Ryl. Blue", "royal blue"), ICE_BLUE("Ice Blue"),
	SPRING_GREEN("Spr. Grn.", "spring green"), BANANA("Banana"), WINE("Wine"), PLUM("Plum"),
	CHOCOLATE("Choco.", "chocolate"), SEA_GREEN("Sea Grn.", "sea green"), NIGHT_BLUE("Nt. Blue",
		"night blue"), LIGHT_BROWN("Lt. Brwn", "light brown"), STRAWBERRY_BLONDE("Strwbry.",
		"strawberry blonde"), SANDY("Sandy"), BLONDE("Blonde");

	private final String name;
	private final String fullName;

	Color(String name) {
		this.name = name;
		this.fullName = name.toLowerCase();
	}

	Color(String name, String fullName) {
		this.name = name;
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getFullName() {
		return fullName;
	}
}
