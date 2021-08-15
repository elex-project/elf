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

public enum Pressure implements IConvertableUnit<Pressure> {

	ATM("atm", 1D),
	PASCAL("pascal", 101325D),
	HECTOPASCAL("hectopascal", PASCAL.factor / 100),
	KILOPASCAL("kilopascal", PASCAL.factor / 1000),
	MEGAPASCAL("megapascal", KILOPASCAL.factor / 1000),
	DYNE_PER_SQ_CENTIMETER("dynePerSquareCentimeter", PASCAL.factor * 10),
	MILLIBAR("millibar", DYNE_PER_SQ_CENTIMETER.factor / 1000),
	BAR("bar", MILLIBAR.factor / 1000),
	KILOGRAM_FORCE_PER_SQ_CENTIMETER("kilogramForcePerSquareCentimeter", 1.033227D),
	POUND_PER_SQ_INCH("poundPerSquareInch", PASCAL.factor/6894.757293),
	POUND_PER_SQ_FEET("poundPerSquareFeet", 2116D),
	MILLIMETER_HG("millimeterHg", 760D),
	INCH_HG("inchHg", MILLIMETER_HG.factor/25.4),
	MILLIMETER_H2O("millimeterH2o", 10332.275D),
	INCH_H2O("inchH2o", 406.782188D);

	private String name, html;
	private double factor;

	Pressure(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/pressure.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/pressure-unit.properties", getClass());
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
	public double convertTo(double val, @NotNull Pressure another) {
		return val * another.factor / this.factor;
	}

	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Pressure item : Pressure.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Pressure another) {
		return ResultSet.generate(val, this, another);
	}
}
