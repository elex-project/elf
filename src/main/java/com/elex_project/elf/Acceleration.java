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

public enum Acceleration implements IConvertableUnit<Acceleration> {
	METER_PER_SQ_SEC("acceleration.meterPerSquareSecond", 1D),
	INCH_PER_SQ_SEC("acceleration.inchPerSquareSecond", Length.INCH.getFactor()),
	FOOT_PER_SQ_SEC("acceleration.footPerSquareSecond", Length.FOOT.getFactor()),
	ACC_OF_GRAVITY("acceleration.accelerationOfGravity", METER_PER_SQ_SEC.factor / 9.80665),
	;

	private final double factor;
	//private String uid;
	private String name, html;

	Acceleration(String key, double factor) {
		//this.uid = key;
		try {
			this.name = Bundle.get(key);
			System.out.println("name => " + this.name);
			this.html = Bundle.getUnit(key);
		} catch (Throwable e) {
			e.printStackTrace();
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
	public double convertTo(double val, @NotNull Acceleration another) {
		return val * another.factor / this.factor;
	}

	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Acceleration item : Acceleration.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Acceleration another) {
		return ResultSet.generate(val, this, another);
	}
}
