/*
 * Project Elf
 * Unit Conversion
 *
 * Copyright (c) 2017-2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package com.elex_project.elf;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class Time {

	@NotNull
	public static String toDHMS(int d, int h, int m, int s) {
		long total = TimeUnit.SECONDS.convert(d, TimeUnit.DAYS)
				+ TimeUnit.SECONDS.convert(h, TimeUnit.HOURS)
				+ TimeUnit.SECONDS.convert(m, TimeUnit.MINUTES)
				+ s;

		long days = TimeUnit.DAYS.convert(total, TimeUnit.SECONDS);
		total -= TimeUnit.SECONDS.convert(days, TimeUnit.DAYS);
		long hours = TimeUnit.HOURS.convert(total, TimeUnit.SECONDS);
		total = total - TimeUnit.SECONDS.convert(hours, TimeUnit.HOURS);
		long minutes = TimeUnit.MINUTES.convert(total, TimeUnit.SECONDS);
		long seconds = total - TimeUnit.SECONDS.convert(minutes, TimeUnit.MINUTES);
		return days + ", " + hours + ", " + minutes + ", " + seconds;
	}

	@NotNull
	public static String toHMS(int d, int h, int m, int s) {
		long total = TimeUnit.SECONDS.convert(d, TimeUnit.DAYS)
				+ TimeUnit.SECONDS.convert(h, TimeUnit.HOURS)
				+ TimeUnit.SECONDS.convert(m, TimeUnit.MINUTES)
				+ s;

		//long days = TimeUnit.DAYS.convert(total, TimeUnit.SECONDS);
		//total -= TimeUnit.SECONDS.convert(days, TimeUnit.DAYS);
		long hours = TimeUnit.HOURS.convert(total, TimeUnit.SECONDS);
		total = total - TimeUnit.SECONDS.convert(hours, TimeUnit.HOURS);
		long minutes = TimeUnit.MINUTES.convert(total, TimeUnit.SECONDS);
		long seconds = total - TimeUnit.SECONDS.convert(minutes, TimeUnit.MINUTES);
		return hours + ", " + minutes + ", " + seconds;
	}

	@NotNull
	public static String toMS(int d, int h, int m, int s) {
		long total = TimeUnit.SECONDS.convert(d, TimeUnit.DAYS)
				+ TimeUnit.SECONDS.convert(h, TimeUnit.HOURS)
				+ TimeUnit.SECONDS.convert(m, TimeUnit.MINUTES)
				+ s;

		//long days = TimeUnit.DAYS.convert(total, TimeUnit.SECONDS);
		//total -= TimeUnit.SECONDS.convert(days, TimeUnit.DAYS);
		//long hours = TimeUnit.HOURS.convert(total, TimeUnit.SECONDS);
		//total = total - TimeUnit.SECONDS.convert(hours, TimeUnit.HOURS);
		long minutes = TimeUnit.MINUTES.convert(total, TimeUnit.SECONDS);
		long seconds = total - TimeUnit.SECONDS.convert(minutes, TimeUnit.MINUTES);
		return minutes + ", " + seconds;
	}

	@NotNull
	public static String toS(int d, int h, int m, int s) {
		long total = TimeUnit.SECONDS.convert(d, TimeUnit.DAYS)
				+ TimeUnit.SECONDS.convert(h, TimeUnit.HOURS)
				+ TimeUnit.SECONDS.convert(m, TimeUnit.MINUTES)
				+ s;

		//long days = TimeUnit.DAYS.convert(total, TimeUnit.SECONDS);
		//total -= TimeUnit.SECONDS.convert(days, TimeUnit.DAYS);
		//long hours = TimeUnit.HOURS.convert(total, TimeUnit.SECONDS);
		//total = total - TimeUnit.SECONDS.convert(hours, TimeUnit.HOURS);
		//long minutes = TimeUnit.MINUTES.convert(total, TimeUnit.SECONDS);
		//long seconds = total - TimeUnit.SECONDS.convert(minutes, TimeUnit.MINUTES);
		return total + "";
	}
}
