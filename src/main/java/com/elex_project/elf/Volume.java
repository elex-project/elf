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

public enum Volume implements IConvertableUnit<Volume> {
	LITER("liter", 1D),
	CC("cc", LITER.factor * 1000),
	MILLILITER("milliliter", LITER.factor * 1000),
	DECILITER("deciliter", LITER.factor * 10),
	CUBIC_CENTIMETER("cubicCentimeter", CC.factor),
	CUBIC_METER("cubicMeter", 0.001D),
	CUBIC_INCH("cubicInch", Math.pow(Length.INCH.getFactor(), 3) / 1000),
	CUBIC_FEET("cubicFeet", CUBIC_INCH.factor / 12 / 12 / 12),
	CUBIC_YARD("cubicYard", CUBIC_FEET.factor / 3 / 3 / 3),
	ACRE_FOOT("acreFoot", CUBIC_FEET.factor / 43560), //ac ft

	OUNCE("ounce", MILLILITER.factor / 29.57353),//fl oz
	OUNCE_BR("ounceBr", MILLILITER.factor / 28.41306),//fl oz
	PINT("pint", LITER.factor / 0.4731765),//liq pt
	PINT_DRY("pintDry", LITER.factor / 0.5506105),//dry pt
	PINT_BR("pintBr", 1.7598D),
	QUART("quart", LITER.factor / 0.9463529),// liq qt
	QUART_DRY("quartDry", LITER.factor / 1.101221),// dry qt
	QUART_BR("quartBr", 1.7597539863927),//qt
	GALLON("gallon", 0.26417205235815),//gal
	GALLON_DRY("gallonDry", 0.22702074606721),
	GALLON_BR("gallonBr", 0.21996924829909),
	BARREL("barrel", GALLON.factor/31.5),//bl
	BARREL_DRY("barrelDry", GALLON_DRY.factor/26.25),
	BARREL_BR("barrelBr", GALLON_BR.factor /36),
	BARREL_PETRO("barrelPetro", GALLON.factor /42),//bbl
	DOI("doi", 1331D / 2401),
	HOB("hob", DOI.factor * 10),
	JAK("jak", HOB.factor * 10),
	MAL("mal", DOI.factor / 10),
	SUM("sum", MAL.factor / 10);

	private String name, html;
	private double factor;

	Volume(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/volume.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/volume-unit.properties", getClass());
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
	public double convertTo(double val, @NotNull Volume another) {
		return val * another.factor / this.factor;
	}


	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Volume item : Volume.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Volume another) {
		return ResultSet.generate(val, this, another);
	}

}
