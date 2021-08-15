/*
 * Project Elf
 * http://www.elex-project.com/
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.elf;

import java.util.ArrayList;

/**
 * Created by Elex on 2014-05-21.
 */
public interface IConvertableUnit<T> {
	public String getTitle();

	public String getUnit();

	public double getFactor();

	public double convertTo(double val, T another);

	public ArrayList<ResultSet> calcAll(double val);

	public ResultSet calc(double val, T another);
}
