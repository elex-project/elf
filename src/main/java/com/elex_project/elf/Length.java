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

public enum Length implements IConvertableUnit<Length> {
	METER("length.meter", 1D),
	MILLIMETER("length.millimeter", METER.factor * 1000),
	CENTIMETER("length.centimeter", METER.factor * 100),
	KILOMETER("length.kilometer", METER.factor / 1000),

	INCH("length.inch", CENTIMETER.factor / 2.54), // 1 inch = 2.54 cm
	MIL("length.mil", INCH.factor * 1000),
	FOOT("length.foot", INCH.factor / 12),
	YARD("length.yard", FOOT.factor / 3),
	MILE("length.mile", FOOT.factor / 5280),
	NAUTICALMILE("length.nauticalMile", METER.factor / 1852),

	JA("length.ja", 3.3D), // 10/33meter
	CHI("length.chi", JA.factor * 10), // 10치=1자
	POON("length.poon", CHI.factor * 10), //10푼=1치
	GAN("length.gan", JA.factor / 6), // 1칸=6자
	JUNG("length.jung", GAN.factor / 60), //1정 = 60칸
	RI("length.ri", JUNG.factor / 36),//1리=36정
	;

	private final double factor;
	private String name, html;

	Length(String key, double factor) {
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
	public double convertTo(double val, @NotNull Length another) {
		return val * another.factor / this.factor;
	}

	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Length item : Length.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Length another) {
		return ResultSet.generate(val, this, another);
	}
}
