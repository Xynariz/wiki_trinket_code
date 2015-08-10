package models;

import enums.Color;
import enums.TrinketType;

public abstract class Trinket {
	public String name;
	public boolean colorless;
	public Color primaryColor;
	public Color secondaryColor;
	public int count;

	public TrinketType type;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append("\n\tColor: ");
		sb.append(getColorString());
		sb.append("\n\tCount: ");
		sb.append(count);
		return sb.toString();
	}

	private String getColorString() {
		StringBuilder sb = new StringBuilder();
		if (colorless) {
			sb.append("(colorless)");
		} else {
			if (secondaryColor == null) {
				sb.append(primaryColor.getFullName());
			} else {
				sb.append("(");
				sb.append(primaryColor.getFullName());
				sb.append(", ");
				sb.append(secondaryColor.getFullName());
				sb.append(")");
			}
		}
		return sb.toString();
	}
}
