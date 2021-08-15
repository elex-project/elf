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

public enum Area implements IConvertableUnit<Area> {
	SQUARE_METER("squareMeter", Math.pow(Length.METER.getFactor(), 2)),
	ARE("are", SQUARE_METER.factor / 100),
	HECTARE("hectare", ARE.factor / 100),
	SQUARE_KILOMETER("squareKilometer", Math.pow(Length.KILOMETER.getFactor(), 2)),
	SQUARE_FEET("squareFeet", Math.pow(Length.FOOT.getFactor(), 2)),
	SQUARE_YARD("squareYard", Math.pow(Length.YARD.getFactor(), 2)),
	ACRE("acre", SQUARE_YARD.factor / 4840),
	PYUNGBANG_JA("pyungbangJa", Math.pow(Length.JA.getFactor(), 2)),
	PYUNG("pyung", Math.pow(Length.GAN.getFactor(), 2)),
	DANBO("danbo", PYUNG.factor / 300),
	JUNGBO("jungbo", DANBO.factor / 10);

	private String name, html;
	private double factor;

	Area(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/area.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/area-unit.properties", getClass());
			this.name = bundle.get(key).get();
			this.html = bundleHtml.get(key).get();
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
