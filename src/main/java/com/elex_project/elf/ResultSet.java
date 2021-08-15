/*
 * Project Elf
 * http://www.elex-project.com/
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.elf;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Elex on 2014-05-22.
 */
public final class ResultSet implements Comparable<ResultSet> {
	private String title;
	private double value;
	private String unit;

	ResultSet() {
	}

	public String getTitle() {
		return title;
	}

	public double getValue() {
		return value;
	}

	public String getUnit() {
		return unit;
	}

	@NotNull
	public static ArrayList<ResultSet> sort(@NotNull ArrayList<ResultSet> s) {
		s.sort(new Comparator<ResultSet>() {
			@Override
			public int compare(ResultSet o1, ResultSet o2) {
				return o1.compareTo(o2);
			}
		});
		return s;
	}

	@NotNull
	@Override
	public String toString() {
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumFractionDigits(6);
		return title + ": " + format.format(value) + " " + unit;
	}

	@Override
	public int compareTo(@NotNull ResultSet o) {
		return Double.compare(this.value, o.value);
	}


	@NotNull
	public static <T extends IConvertableUnit<?>> ResultSet generate(double val, @NotNull IConvertableUnit<T> from, @NotNull T another) {
		ResultSet resultSet = new ResultSet();
		resultSet.title = another.getTitle();
		resultSet.value = from.convertTo(val, another);
		resultSet.unit = another.getUnit();

		return resultSet;
	}
}
