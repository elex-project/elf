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

public enum Volume implements IConvertableUnit<Volume> {
	LITER("volume.liter", 1D),
	CC("volume.cc", LITER.factor * 1000),
	MILLILITER("volume.milliliter", LITER.factor * 1000),
	DECILITER("volume.deciliter", LITER.factor * 10),
	CUBIC_CENTIMETER("volume.cubicCentimeter", CC.factor),
	CUBIC_METER("volume.cubicMeter", 0.001D),
	CUBIC_INCH("volume.cubicInch", Math.pow(Length.INCH.getFactor(), 3) / 1000),
	CUBIC_FEET("volume.cubicFeet", CUBIC_INCH.factor / 12 / 12 / 12),
	CUBIC_YARD("volume.cubicYard", CUBIC_FEET.factor / 3 / 3 / 3),
	ACRE_FOOT("volume.acreFoot", CUBIC_FEET.factor / 43560), //ac ft

	OUNCE("volume.ounce", MILLILITER.factor / 29.57353),//fl oz
	OUNCE_BR("volume.ounceBr", MILLILITER.factor / 28.41306),//fl oz
	PINT("volume.pint", LITER.factor / 0.4731765),//liq pt
	PINT_DRY("volume.pintDry", LITER.factor / 0.5506105),//dry pt
	PINT_BR("volume.pintBr", 1.7598D),
	QUART("volume.quart", LITER.factor / 0.9463529),// liq qt
	QUART_DRY("volume.quartDry", LITER.factor / 1.101221),// dry qt
	QUART_BR("volume.quartBr", 1.7597539863927),//qt
	GALLON("volume.gallon", 0.26417205235815),//gal
	GALLON_DRY("volume.gallonDry", 0.22702074606721),
	GALLON_BR("volume.gallonBr", 0.21996924829909),
	BARREL("volume.barrel", GALLON.factor / 31.5),//bl
	BARREL_DRY("volume.barrelDry", GALLON_DRY.factor / 26.25),
	BARREL_BR("volume.barrelBr", GALLON_BR.factor / 36),
	BARREL_PETRO("volume.barrelPetro", GALLON.factor / 42),//bbl
	DOI("volume.doi", 1331D / 2401),
	HOB("volume.hob", DOI.factor * 10),
	JAK("volume.jak", HOB.factor * 10),
	MAL("volume.mal", DOI.factor / 10),
	SUM("volume.sum", MAL.factor / 10);

	private final double factor;
	private String name, html;

	Volume(String key, double factor) {
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
