/*
 * Project Elf
 * http://www.elex-project.com/
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.elf;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LengthTest {
	private static final double DELTA = 0.01;
	@Test
	public void calc() throws Exception {
		ArrayList<ResultSet> results = Length.RI.calcAll(1);
		for (ResultSet r : results) {
			System.out.println(r);
		}
	}
	@Test
	public void poon() throws Exception {
		double mm = Length.POON.calc(1, Length.MILLIMETER).getValue();
		assertEquals(3.0303030, mm, DELTA);
	}
	@Test
	public void chi() throws Exception {
		/*
		치는 길이의 단위로 한 치는 한 자의 10분의 1을 뜻하며, 미터법으로 약 3.03cm에 해당한다.
		촌(寸)이라고도 한다. 곡식인 기장의 10배 길이라고 한다.
		 */
		double ja = Length.CHI.calc(1, Length.JA).getValue();
		assertEquals(1/10.0, ja, DELTA);
		double cm = Length.CHI.calc(1, Length.CENTIMETER).getValue();
		assertEquals(3.0303030, cm, DELTA);
	}
	@Test
	public void ja() throws Exception {
		/*
		자는 동아시아의 도량형인 척근법에서 쓰는 길이의 단위로 10 치에 해당하며
		한자어로 척(尺)으로도 쓴다.
		 */
		double chi = Length.JA.calc(1, Length.CHI).getValue();
		assertEquals(10, chi, DELTA);
		double cm = Length.JA.calc(1, Length.CENTIMETER).getValue();
		assertEquals(30.303030, cm, DELTA);
	}
	@Test
	public void gan() throws Exception {
		/*
		간(間)은 동아시아의 도량형인 척근법에서 쓰는 단위이다. 보(步)로도 쓴다.
		간이 길이로 쓰이면, 1간은 여섯 자이며, 이는 미터법으로 환산하면,
		60/33 미터로서 약 1.82 미터 또는 약 181.82 센티미터이다.
		 */
		double ja = Length.GAN.calc(1, Length.JA).getValue();
		assertEquals(6, ja, DELTA);
		double cm = Length.GAN.calc(1, Length.CENTIMETER).getValue();
		assertEquals(181.82, cm, DELTA);
	}
	@Test
	public void jung() throws Exception {
		/*
		길이 1정은 60간(間)이며, 미터법으로는 3600/33 미터로서 약 109.09 미터 또는 10909.1 센티미터이다.
		 */
		double gan = Length.JUNG.calc(1, Length.GAN).getValue();
		assertEquals(60, gan, DELTA);
		double m = Length.JUNG.calc(1, Length.METER).getValue();
		assertEquals(109.09, m, DELTA);
	}
	@Test
	public void ri() throws Exception {
		/*
		1 리가 약 3.9 km로서 10 리가 약 39 km 정도가 되어,
		우리가 통상적으로 10 리가 약 4 km라고 알고 있던 것과 크게 다른 것을 알 수 있다.
		이것은 우리 계량법이 일본 계량법에 영향을 받았기 때문이다.

		그러나 최초로 미터법을 도입한 광무 6년(서기 1902년)에 제정된 도량형규칙에
		1 자(尺)는 현재와 같이 10/33 m로 정의되었고,
		1 리는 420 m로 정해짐으로 1 리가 1386 자(또는 2100 주척)로 되어 있다.
		따라서 통상적 거리 10 리는 4.2 km로 보는 것이 옳다고 본다.
		우리 나라를 가리켜 "삼천리 반도 금수강산"이라고 할 때의 '리'는 이 값이라고 보아야 될 것이다.
		 */
		double gan = Length.RI.calc(1, Length.JUNG).getValue();
		assertEquals(36, gan, DELTA);
		double km = Length.RI.calc(1, Length.KILOMETER).getValue();
		assertEquals(3.9272727, km, DELTA);
	}
}
