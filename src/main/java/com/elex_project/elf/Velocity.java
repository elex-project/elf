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

public enum Velocity implements IConvertableUnit<Velocity> {
	METER_PER_SEC("meterPerSec", 1D),
	METER_PER_HOUR("meterPerHour", 3600D),
	KILOMETER_PER_SEC("kilometerPerSec", Length.KILOMETER.getFactor()),
	KILOMETER_PER_HOUR("kilometerPerHour", Length.KILOMETER.getFactor() * 3600D),
	INCH_PER_SEC("inchPerSec", Length.INCH.getFactor()),
	INCH_PER_HOUR("inchPerHour", Length.INCH.getFactor() * 3600D),
	FEET_PER_SEC("feetPerSec", Length.FOOT.getFactor()),
	FEET_PER_HOUR("feetPerHour", Length.FOOT.getFactor() * 3600D),
	MILE_PER_SEC("milePerSec", Length.MILE.getFactor()),
	MILE_PER_HOUR("milePerHour", Length.MILE.getFactor() * 3600D),
	KNOT("knot", Length.NAUTICALMILE.getFactor() * 3600D),
	MACH("mach", 0.002941D),
	SPEED_OF_LIGHT("c", METER_PER_SEC.factor/299792458);

	private String name, html;
	private double factor;

	Velocity(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/velocity.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/velocity-unit.properties", getClass());
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
