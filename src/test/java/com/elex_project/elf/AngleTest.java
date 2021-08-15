/*
 * Project Elf
 * http://www.elex-project.com/
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.elf;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AngleTest {
	@Test
	public void calc() throws Exception {
		ArrayList<ResultSet> results = Angle.RADIAN.calcAll(1);
		for (ResultSet r : results) {
			System.out.println(r);
		}
	}

	@Test
	public void arcDegree() throws Exception {
		double val = 35.382145634;
		Angle.ArcDegree a = new Angle.ArcDegree(val);
		System.out.println(a.toString());

		Angle.ArcDegree b = new Angle.ArcDegree(38, 22, 55.724);
		System.out.println(b.degree);
	}
}
