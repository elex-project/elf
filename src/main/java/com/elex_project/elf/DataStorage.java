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

public enum DataStorage implements IConvertableUnit<DataStorage> {
	BIT("bit", 8),
	BYTE("byte", 1),
	KILOBYTE("kilobyte", BYTE.factor / 1024),
	MEGABYTE("megabyte", KILOBYTE.factor / 1024),
	GIGABYTE("gigabyte", MEGABYTE.factor / 1024),
	TERABYTE("terabyte", GIGABYTE.factor / 1024),
	//PETABYTE("petabyte", TERABYTE.factor/1024),
	;

	private String name, html;
	private double factor;

	DataStorage(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/storage.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/storage-unit.properties", getClass());
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
