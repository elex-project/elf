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

public enum FuelEfficiency implements IConvertableUnit<FuelEfficiency> {
	KILOMETER_PER_LITER("fuel.kilometerPerLiter", 1D),
	MILE_PER_GALLON("fuel.milePerGallon", 2.352146D),
	LITER_PER_100KILOMETER("fuel.literPer100Kilometer", 0),
	;
	private final double factor;
	private String name, html;

	FuelEfficiency(String key, double factor) {
		try {
			this.name = Bundle.get(key);
			this.html = Bundle.getUnit(key);
		} catch (Throwable e) {
			this.name = this.name();
			this.html = this.name();
		}
		this.factor = factor;
	}

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public String getUnit() {
		return html;
	}

	@Override
	public double getFactor() {
		return factor;
	}

	@Override
	public double convertTo(double val, FuelEfficiency another) {
		if (another == LITER_PER_100KILOMETER && this == LITER_PER_100KILOMETER) {
			return val;
		} else if (another == LITER_PER_100KILOMETER) {
			double k = val * KILOMETER_PER_LITER.factor / this.factor;
			return 1D / k * 100;
		} else if (this == LITER_PER_100KILOMETER) {
			double k = val / 100;
			return another.factor / KILOMETER_PER_LITER.factor / k;
		} else {
			return val * another.factor / this.factor;
		}

	}

	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (FuelEfficiency item : FuelEfficiency.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, FuelEfficiency another) {
		return ResultSet.generate(val, this, another);
	}
}
