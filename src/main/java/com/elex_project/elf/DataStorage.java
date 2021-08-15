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

public enum DataStorage implements IConvertableUnit<DataStorage> {
	BIT("storage.bit", 8),
	BYTE("storage.byte", 1),
	KILOBYTE("storage.kilobyte", BYTE.factor / 1024),
	MEGABYTE("storage.megabyte", KILOBYTE.factor / 1024),
	GIGABYTE("storage.gigabyte", MEGABYTE.factor / 1024),
	TERABYTE("storage.terabyte", GIGABYTE.factor / 1024),
	//PETABYTE("petabyte", TERABYTE.factor/1024),
	;

	private final double factor;
	private String name, html;

	DataStorage(String key, double factor) {
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
	public double convertTo(double val, @NotNull DataStorage another) {
		return val * another.factor / this.factor;
	}

	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (DataStorage item : DataStorage.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, DataStorage another) {
		return ResultSet.generate(val, this, another);
	}
}
