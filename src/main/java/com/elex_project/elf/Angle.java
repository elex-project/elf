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

public enum Angle implements IConvertableUnit<Angle> {
	DEGREE("angle.degree", 360D),
	RADIAN("angle.radian", 2D * Math.PI),
	GRADIAN("angle.gradian", DEGREE.factor / 0.9),
	MILL("angle.mill", GRADIAN.factor * 16),
	ROTATION("angle.rotation", 1D);

	private final double factor;
	private String name, html;

	Angle(String key, double factor) {
		try {
			this.name = Bundle.get(key);
			this.html = Bundle.getUnit(key);
		} catch (Throwable e) {
			this.name = this.name();
			this.html = this.name();
		}
		this.factor = factor;
	}

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public String getUnit() {
		return html;
	}

	@Override
	public double getFactor() {
		return factor;
	}

	@Override
	public double convertTo(double val, @NotNull Angle another) {
		return val * another.factor / this.factor;
	}

	@NotNull
	@Override
	public ArrayList<ResultSet> calcAll(double val) {
		ArrayList<ResultSet> result = new ArrayList<>();
		for (Angle item : Angle.values()) {
			result.add(calc(val, item));
		}

		return result;
	}

	@NotNull
	@Override
	public ResultSet calc(double val, Angle another) {
		return ResultSet.generate(val, this, another);
	}

	public static class ArcDegree {
		public double d, m, s;
		public double degree;

		/**
		 * degree to arc-degree
		 *
		 * @param degree 도
		 */
		public ArcDegree(double degree) {
			this.degree = degree;

			d = Math.floor(degree);
			double mD = (degree - d) * 60;
			m = Math.floor(mD);
			double sD = (mD - m) * 60;
			s = sD; //s = Math.round(sD);
			if (s >= 60) {
				m++;
				s -= 60;
			}
			if (m >= 60) {
				d++;
				m -= 60;
			}
		}

		/**
		 * arc-degree to degree
		 *
		 * @param d 도
		 * @param m 분
		 * @param s 초
		 */
		public ArcDegree(double d, double m, double s) {
			this.d = d;
			this.m = m;
			this.s = s;

			this.degree = d + m / 60 + s / 60 / 60;
		}

		@Override
		public String toString() {
			return String.format("angle.%d° %d′ %.3f″", (long) d, (int) m, s);
			//return (long)d + "° " + (int)m + "′ " + s+ "″";
		}
	}
}
