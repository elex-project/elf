/*
 * Project Elf
 * Unit Conversion
 *
 * Copyright (c) 2017-2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package com.elex_project.elf;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AreaTest {
	@Test
	public void calc() throws Exception {
		ArrayList<ResultSet> results = Area.SQUARE_METER.calcAll(1);
		for (ResultSet r : results) {
			System.out.println(r);
		}
	}

}
