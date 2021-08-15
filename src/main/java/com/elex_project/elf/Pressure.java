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

public enum Pressure implements IConvertableUnit<Pressure> {

	ATM("pressure.atm", 1D),
	PASCAL("pressure.pascal", 101325D),
	HECTOPASCAL("pressure.hectopascal", PASCAL.factor / 100),
	KILOPASCAL("pressure.kilopascal", PASCAL.factor / 1000),
	MEGAPASCAL("pressure.megapascal", KILOPASCAL.factor / 1000),
	DYNE_PER_SQ_CENTIMETER("pressure.dynePerSquareCentimeter", PASCAL.factor * 10),
	MILLIBAR("pressure.millibar", DYNE_PER_SQ_CENTIMETER.factor / 1000),
	BAR("pressure.bar", MILLIBAR.factor / 1000),
	KILOGRAM_FORCE_PER_SQ_CENTIMETER("pressure.kilogramForcePerSquareCentimeter", 1.033227D),
	POUND_PER_SQ_INCH("pressure.poundPerSquareInch", PASCAL.factor / 6894.757293),
	POUND_PER_SQ_FEET("pressure.poundPerSquareFeet", 2116D),
	MILLIMETER_HG("pressure.millimeterHg", 760D),
	INCH_HG("pressure.inchHg", MILLIMETER_HG.factor / 25.4),
	MILLIMETER_H2O("pressure.millimeterH2o", 10332.275D),
	INCH_H2O("pressure.inchH2o", 406.782188D);

	private final double factor;
	private String name, html;

	Pressure(String key, double factor) {
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
