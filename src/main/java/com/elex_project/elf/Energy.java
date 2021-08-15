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

public enum Energy implements IConvertableUnit<Energy> {
	JOULE("joule", 1D),
	KILOJOULE("kilojoule", JOULE.factor / 1000),
	MEGAJOULE("megajoule", KILOJOULE.factor / 1000),
	WATT_HOUR("wattHour", JOULE.factor / 3600),
	KILOWATT_HOUR("kilowattHour", WATT_HOUR.factor / 1000),
	ERG("erg", JOULE.factor * 10000000D),
	FOOT_POUND_FORCE("footPoundForce", JOULE.factor / 1.3558179483314004D),//0.73756D
	CALORIE("calorie", JOULE.factor / 4.1868D),;

	private String name, html;
	private double factor;

	Energy(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/energy.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/energy-unit.properties", getClass());
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
	public double convertTo(double val, @NotNull Energy another) {
		return val * another.factor / this.factor;
	}


	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Energy item : Energy.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Energy another) {
		return ResultSet.generate(val, this, another);
	}
}
