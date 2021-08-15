/*
 * Project Elf
 * http://www.elex-project.com/
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.elf;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class VolumeTest {
	@Test
	public void calc() throws Exception {
		ArrayList<ResultSet> results = Volume.LITER.calcAll(1);
		for (ResultSet r : results) {
			System.out.println(r);
		}
	}

}