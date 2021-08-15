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

public enum Area implements IConvertableUnit<Area> {
	SQUARE_METER("area.squareMeter", Math.pow(Length.METER.getFactor(), 2)),
	ARE("area.are", SQUARE_METER.factor / 100),
	HECTARE("area.hectare", ARE.factor / 100),
	SQUARE_KILOMETER("area.squareKilometer", Math.pow(Length.KILOMETER.getFactor(), 2)),
	SQUARE_FEET("area.squareFeet", Math.pow(Length.FOOT.getFactor(), 2)),
	SQUARE_YARD("area.squareYard", Math.pow(Length.YARD.getFactor(), 2)),
	ACRE("area.acre", SQUARE_YARD.factor / 4840),
	PYUNGBANG_JA("area.pyungbangJa", Math.pow(Length.JA.getFactor(), 2)),
	PYUNG("area.pyung", Math.pow(Length.GAN.getFactor(), 2)),
	DANBO("area.danbo", PYUNG.factor / 300),
	JUNGBO("area.jungbo", DANBO.factor / 10);

	private final double factor;
	private String name, html;

	Area(String key, double factor) {
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
	public double convertTo(double val, @NotNull Area another) {
		return val * another.factor / this.factor;
	}

	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Area item : Area.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Area another) {
		return ResultSet.generate(val, this, another);
	}
}
