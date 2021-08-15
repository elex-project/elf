/*
 * Project Elf
 * http://www.elex-project.com/
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.elf;

import com.elex_project.abraxas.LanguageBundle;
import com.elex_project.abraxas.ResourceBundle;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public enum Acceleration implements IConvertableUnit<Acceleration> {
	METER_PER_SQ_SEC("meterPerSquareSecond", 1D),
	INCH_PER_SQ_SEC("inchPerSquareSecond", Length.INCH.getFactor()),
	FOOT_PER_SQ_SEC("footPerSquareSecond", Length.FOOT.getFactor()),
	ACC_OF_GRAVITY("accelerationOfGravity", METER_PER_SQ_SEC.factor / 9.80665),;

	//private String uid;
	private String name, html;
	private double factor;

	Acceleration(String key, double factor) {
		//this.uid = key;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/acceleration.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/acceleration-unit.properties", getClass());
			this.name = bundle.get(key).get();
			System.out.println("name => "+this.name);
			this.html = bundleHtml.get(key).get();
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
