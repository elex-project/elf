/*
 * Project Elf
 * Unit Conversion
 *
 * Copyright (c) 2021-2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package com.elex_project.elf;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

@Slf4j
abstract class Bundle {
	static ResourceBundle bundles;
	static Properties units;

	static {
		try {
			bundles = ResourceBundle.getBundle("bundles");
			units = new Properties();
			units.load(new InputStreamReader(Objects.requireNonNull(Bundle.class
					.getResourceAsStream("/units.properties")), StandardCharsets.UTF_8));
		} catch (IOException e) {
			log.error("Couldn't load a properties file.", e);
		}
	}

	static String get(String key) {
		return bundles.getString(key);
	}

	static String getUnit(String key) {
		return units.getProperty(key);
	}
}
