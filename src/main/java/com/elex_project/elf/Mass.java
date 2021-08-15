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

public enum Mass implements IConvertableUnit<Mass> {
	KILOGRAM("kilogram", 1D),
	GRAM("gram", KILOGRAM.factor * 1000),
	MILLIGRAM("milligram", GRAM.factor * 1000),
	TON("ton", KILOGRAM.factor / 1000),
	KILOTON("kiloton", TON.factor / 1000),

	POUND("pound", KILOGRAM.factor/0.45359237),
	DRAM("dram", POUND.factor*256),
	OUNCE("ounce", POUND.factor*16),
	GRAIN("grain", POUND.factor*7000),

	GWAN("gwan", KILOGRAM.factor / 3.75D),
	DON("don", GWAN.factor * 1000),
	NYANG("nyang", DON.factor / 10),
	GEUN("geun", DON.factor / 160),;


	private String name, html;
	private double factor;

	Mass(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/mass.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/mass-unit.properties", getClass());
			this.name = bundle.get(key).get();
			this.html = bundleHtml.get(key).get();
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
