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

public enum Mass implements IConvertableUnit<Mass> {
	KILOGRAM("mass.kilogram", 1D),
	GRAM("mass.gram", KILOGRAM.factor * 1000),
	MILLIGRAM("mass.milligram", GRAM.factor * 1000),
	TON("mass.ton", KILOGRAM.factor / 1000),
	KILOTON("mass.kiloton", TON.factor / 1000),

	POUND("mass.pound", KILOGRAM.factor / 0.45359237),
	DRAM("mass.dram", POUND.factor * 256),
	OUNCE("mass.ounce", POUND.factor * 16),
	GRAIN("mass.grain", POUND.factor * 7000),

	GWAN("mass.gwan", KILOGRAM.factor / 3.75D),
	DON("mass.don", GWAN.factor * 1000),
	NYANG("mass.nyang", DON.factor / 10),
	GEUN("mass.geun", DON.factor / 160),
	;


	private final double factor;
	private String name, html;

	Mass(String key, double factor) {
		try {
			this.name = Bundle.get(key);
			this.html = Bundle.getUnit(key);
		} catch (Exception e) {
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
	public double convertTo(double val, @NotNull Mass another) {
		return val * another.factor / this.factor;
	}


	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Mass item : Mass.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Mass another) {
		return ResultSet.generate(val, this, another);
	}
}
