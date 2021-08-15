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

/**
 * Created by Elex on 2014-05-22.
 */
public enum Temperature implements IConvertableUnit<Temperature> {
	CELSIUS("temperature.celsius"),
	FAHRENHEIT("temperature.fahrenheit"),
	KELVIN("temperature.kelvin"),
	RANKIN("temperature.rankin"),
	;

	private String name, html;

	Temperature(String key) {
		try {
			this.name = Bundle.get(key);
			this.html = Bundle.getUnit(key);
		} catch (Throwable e) {
			this.name = this.name();
			this.html = this.name();
		}
	}


	private static double c2f(double val) {
		return val * 1.8 + 32;
	}

	private static double c2k(double val) {
		return val + 273.15;
	}

	private static double c2r(double val) {
		return 1.8 * c2k(val);
	}

	private static double f2c(double val) {
		return (val - 32) / 1.8;
	}

	private static double f2k(double val) {
		return f2c(val) + 273.15;
	}

	private static double f2r(double val) {
		return val + 459.2;
	}

	private static double k2c(double val) {
		return val - 273.15;
	}

	private static double k2f(double val) {
		return k2c(val) * 1.8 + 32;
	}

	private static double k2r(double val) {
		return 1.8 * val;
	}

	private static double r2c(double val) {
		return r2k(val) - 273.15;
	}

	private static double r2f(double val) {
		return val - 459.2;
	}

	private static double r2k(double val) {
		return val / 1.8;
	}

	public String getTitle() {
		return name;
	}

	public String getUnit() {
		return html;
	}

	/**
	 * 의미없음...
	 *
	 * @return ...
	 */
	@Deprecated
	public double getFactor() {
		return CELSIUS.convertTo(1, this);
	}

	@Override
	public double convertTo(double val, Temperature another) {
		switch (this) {
			case FAHRENHEIT:
				switch (another) {
					case KELVIN:
						return f2k(val);
					case RANKIN:
						return f2r(val);
					case CELSIUS:
						return f2c(val);
				}
				break;
			case KELVIN:
				switch (another) {
					case FAHRENHEIT:
						return k2f(val);
					case RANKIN:
						return k2r(val);
					case CELSIUS:
						return k2c(val);
				}
				break;
			case RANKIN:
				switch (another) {
					case FAHRENHEIT:
						return r2f(val);
					case KELVIN:
						return r2k(val);
					case CELSIUS:
						return r2c(val);
				}
				break;
			case CELSIUS:
				switch (another) {
					case FAHRENHEIT:
						return c2f(val);
					case KELVIN:
						return c2k(val);
					case RANKIN:
						return c2r(val);
				}
				break;
		}
		return val;
	}


	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Temperature item : Temperature.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Temperature another) {
		return ResultSet.generate(val, this, another);
	}

}
