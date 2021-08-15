/*
 * Project Elf
 * http://www.elex-project.com/
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.elf;

import com.elex_project.abraxas.ResourceBundle;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public enum FuelEfficiency implements IConvertableUnit<FuelEfficiency> {
	KILOMETER_PER_LITER("kilometerPerLiter", 1D),
	MILE_PER_GALLON("milePerGallon", 2.352146D),
	LITER_PER_100KILOMETER("literPer100Kilometer", 0),;
	private String name, html;
	private double factor;

	FuelEfficiency(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/fuel.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/fuel-unit.properties", getClass());
			this.name = bundle.get(key).get();
			this.html = bundleHtml.get(key).get();
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
