/*
 * Project Elf
 * Unit Conversion
 *
 * Copyright (c) 2017-2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package com.elex_project.elf;

import java.util.ArrayList;

/**
 * Created by Elex on 2014-05-21.
 */
public interface IConvertableUnit<T> {
	String getTitle();

	String getUnit();

	double getFactor();

	double convertTo(double val, T another);

	ArrayList<ResultSet> calcAll(double val);

	ResultSet calc(double val, T another);
}
