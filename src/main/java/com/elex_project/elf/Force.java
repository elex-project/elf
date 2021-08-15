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

public enum Force implements IConvertableUnit<Force> {
	NEWTON("newton", 1D),
	DYNE("dyne", 100000D),
	POUND("pound", 1 / 4.4482216152605D),
	POUNDAL("poundal", POUND.factor * 32.17398),
	GRAM_FORCE("gramForce", 1D / 9.80665 * 1000),
	KILOGRAM_FORCE("kilogramForce", 1D / 9.80665),;

	private String name, html;
	private double factor;

	Force(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/force.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/force-unit.properties", getClass());
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
