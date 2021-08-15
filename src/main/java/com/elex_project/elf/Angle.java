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

public enum Angle implements IConvertableUnit<Angle> {
	DEGREE("degree", 360D),
	RADIAN("radian", 2D * Math.PI),
	GRADIAN("gradian", DEGREE.factor/0.9),
	MILL("mill", GRADIAN.factor*16),
	ROTATION("rotation", 1D);

	private String name, html;
	private double factor;

	Angle(String key, double factor) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("/angle.xml", getClass());
			ResourceBundle bundleHtml = ResourceBundle.getBundle("/angle-unit.properties", getClass());
			this.name = bundle.get(key).get();
			this.html = bundleHtml.get(key).get();
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
			return String.format("%d° %d′ %.3f″", (long) d, (int) m, s);
			//return (long)d + "° " + (int)m + "′ " + s+ "″";
		}
	}
}
