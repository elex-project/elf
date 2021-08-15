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

public enum Length implements IConvertableUnit<Length> {
	METER("meter", 1D),
	MILLIMETER("millimeter", METER.factor * 1000),
	CENTIMETER("centimeter", METER.factor * 100),
	KILOMETER("kilometer", METER.factor / 1000),

	INCH("inch", CENTIMETER.factor / 2.54), // 1 inch = 2.54 cm
	MIL("mil", INCH.factor * 1000),
	FOOT("foot", INCH.factor / 12),
	YARD("yard", FOOT.factor / 3),
	MILE("mile", FOOT.factor / 5280),
	NAUTICALMILE("nauticalMile", METER.factor / 1852),

	JA("ja", 3.3D), // 10/33meter
	CHI("chi", JA.factor * 10), // 10치=1자
	POON("poon", CHI.factor * 10), //10푼=1치
	GAN("gan", JA.factor / 6), // 1칸=6자
	JUNG("jung", GAN.factor / 60), //1정 = 60칸
	RI("ri", JUNG.factor / 36),; //1리=36정

	private String name, html;
	private double factor;

	Length(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/length.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/length-unit.properties", getClass());
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
