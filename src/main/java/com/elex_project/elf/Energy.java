/*
 * Project Elf
 * Unit Conversion
 *
 * Copyright (c) 2017-2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package com.elex_project.elf;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public enum Energy implements IConvertableUnit<Energy> {
	JOULE("energy.joule", 1D),
	KILOJOULE("energy.kilojoule", JOULE.factor / 1000),
	MEGAJOULE("energy.megajoule", KILOJOULE.factor / 1000),
	WATT_HOUR("energy.wattHour", JOULE.factor / 3600),
	KILOWATT_HOUR("energy.kilowattHour", WATT_HOUR.factor / 1000),
	ERG("energy.erg", JOULE.factor * 10000000D),
	FOOT_POUND_FORCE("energy.footPoundForce", JOULE.factor / 1.3558179483314004D),//0.73756D
	CALORIE("energy.calorie", JOULE.factor / 4.1868D),
	;

	private final double factor;
	private String name, html;

	Energy(String key, double factor) {
		try {
			this.name = Bundle.get(key);
			this.html = Bundle.getUnit(key);
		} catch (Throwable e) {
			this.name = this.name();
			this.html = this.name();
		}
		this.factor = factor;
	}

	public String getTitle() {
		return name;
	}

	public String getUnit() {
		return html;
	}

	public double getFactor() {
		return factor;
	}

	@Override
	public double convertTo(double val, @NotNull Energy another) {
		return val * another.factor / this.factor;
	}


	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Energy item : Energy.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Energy another) {
		return ResultSet.generate(val, this, another);
	}
}
