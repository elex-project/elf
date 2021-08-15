/*
 * Project Elf
 * http://www.elex-project.com/
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.elf;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class VelocityTest {
	@Test
	public void calc() throws Exception {
		ArrayList<ResultSet> results = Velocity.METER_PER_SEC.calcAll(1);
		for (ResultSet r : results) {
			System.out.println(r);
		}
	}

}
