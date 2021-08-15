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

public enum Force implements IConvertableUnit<Force> {
	NEWTON("force.newton", 1D),
	DYNE("force.dyne", 100000D),
	POUND("force.pound", 1 / 4.4482216152605D),
	POUNDAL("force.poundal", POUND.factor * 32.17398),
	GRAM_FORCE("force.gramForce", 1D / 9.80665 * 1000),
	KILOGRAM_FORCE("force.kilogramForce", 1D / 9.80665),
	;

	private final double factor;
	private String name, html;

	Force(String key, double factor) {
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
	public double convertTo(double val, @NotNull Force another) {
		return val * another.factor / this.factor;
	}

	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Force item : Force.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Force another) {
		return ResultSet.generate(val, this, another);
	}
}
