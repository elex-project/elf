/*
 * Project Elf
 * http://www.elex-project.com/
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.elf;

import org.junit.jupiter.api.Test;
public class TimeTest {
	@Test
	public void toDHMS() throws Exception {
		//String s = Time.toDHMS(1, 0, 35, 0);
		System.out.println(Time.toDHMS(1, 0, 35, 0));
		System.out.println(Time.toHMS(1, 0, 35, 0));
		System.out.println(Time.toMS(1, 0, 35, 0));
		System.out.println(Time.toS(1, 0, 35, 0));
	}

}
