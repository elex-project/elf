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

public enum Velocity implements IConvertableUnit<Velocity> {
	METER_PER_SEC("velocity.meterPerSec", 1D),
	METER_PER_HOUR("velocity.meterPerHour", 3600D),
	KILOMETER_PER_SEC("velocity.kilometerPerSec", Length.KILOMETER.getFactor()),
	KILOMETER_PER_HOUR("velocity.kilometerPerHour", Length.KILOMETER.getFactor() * 3600D),
	INCH_PER_SEC("velocity.inchPerSec", Length.INCH.getFactor()),
	INCH_PER_HOUR("velocity.inchPerHour", Length.INCH.getFactor() * 3600D),
	FEET_PER_SEC("velocity.feetPerSec", Length.FOOT.getFactor()),
	FEET_PER_HOUR("velocity.feetPerHour", Length.FOOT.getFactor() * 3600D),
	MILE_PER_SEC("velocity.milePerSec", Length.MILE.getFactor()),
	MILE_PER_HOUR("velocity.milePerHour", Length.MILE.getFactor() * 3600D),
	KNOT("velocity.knot", Length.NAUTICALMILE.getFactor() * 3600D),
	MACH("velocity.mach", 0.002941D),
	SPEED_OF_LIGHT("velocity.c", METER_PER_SEC.factor / 299792458);

	private final double factor;
	private String name, html;

	Velocity(String key, double factor) {
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

	public double convertTo(double val, @NotNull Velocity another) {
		return val * another.factor / this.factor;
	}

	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Velocity item : Velocity.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Velocity another) {
		return ResultSet.generate(val, this, another);
	}
}
