package com.elex_project.elf;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class Units {
	private static final List<String> UNITS;// = Collections.unmodifiableList()
	static {
		ArrayList<String> list = new ArrayList<>();
		list.add("length");
		list.add("area");
		list.add("volume");
		list.add("angle");
		list.add("mass");
		list.add("velocity");
		list.add("acceleration");
		list.add("force");
		list.add("pressure");
		list.add("energy");
		list.add("power");
		list.add("temperature");
		list.add("data-storage");
		list.add("fuel-efficiency");
		UNITS = Collections.unmodifiableList(list);
	}

	private Units() {
	}

	public static List<String> getUnits() {
		return UNITS;
	}

	/**
	 * @param category enum이름
	 * @param name     enum 필드 이름 = 단위
	 * @param num      값
	 * @return 한방에 계산.
	 * @throws IllegalArgumentException ..
	 */
	@NotNull
	public static ArrayList<ResultSet> calcAll(@NotNull String category, String name, double num)
			throws IllegalArgumentException {

		switch (category) {
			case "acceleration":
				return Acceleration.valueOf(name).calcAll(num);
			case "angle":
				return Angle.valueOf(name).calcAll(num);
			case "area":
				return Area.valueOf(name).calcAll(num);
			case "data-storage":
				return DataStorage.valueOf(name).calcAll(num);
			case "energy":
				return Energy.valueOf(name).calcAll(num);
			case "force":
				return Force.valueOf(name).calcAll(num);
			case "fuel-efficiency":
				return FuelEfficiency.valueOf(name).calcAll(num);
			case "length":
				return Length.valueOf(name).calcAll(num);
			case "mass":
				return Mass.valueOf(name).calcAll(num);
			case "power":
				return Power.valueOf(name).calcAll(num);
			case "pressure":
				return Pressure.valueOf(name).calcAll(num);
			case "temperature":
				return Temperature.valueOf(name).calcAll(num);
			case "velocity":
				return Velocity.valueOf(name).calcAll(num);
			case "volume":
				return Volume.valueOf(name).calcAll(num);
			default:
				throw new IllegalArgumentException(category + " is not defined.");
		}
	}
}
