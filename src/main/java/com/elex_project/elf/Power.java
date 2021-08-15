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

public enum Power implements IConvertableUnit<Power> {
	WATT("watt", 1D),
	KILOWATT("kilowatt", 1 / 1000D),
	HORSE_POWER("horsePower", 1 / 735.49875D),;

	private String name, html;
	private double factor;

	Power(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/power.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/power-unit.properties", getClass());
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
	public double convertTo(double val, @NotNull Power another) {
		return val * another.factor / this.factor;
	}

	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Power item : Power.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Power another) {
		return ResultSet.generate(val, this, another);
	}
}
