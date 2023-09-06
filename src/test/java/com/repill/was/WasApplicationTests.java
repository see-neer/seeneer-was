package com.repill.was;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class WasApplicationTests {

	@Test
	void contextLoads() {

		int[] alphabetCount = new int[11]; // 알파벳 개수를 저장할 배열

		int[] waiting = new int[10];
		waiting[0] = 1;
		waiting[1] = 5;
		waiting[2] = 8;
		waiting[3] = 2;
		waiting[4] = 10;
		waiting[5] = 5;
		waiting[6] = 4;
		waiting[7] = 6;
		waiting[8] = 4;
		waiting[9] = 8;

		List<Integer> result = new ArrayList<>();

		for (int i = 0 ; i <= waiting.length ; i++) {
			for (int c : waiting) {
				if(alphabetCount[c] == 0) {
					alphabetCount[c]++;
					result.add(c);
				}
				List<Integer> collect = result.stream().filter(one -> !one.equals(c)).collect(Collectors.toList());
				collect.add(c);
			}
		}
		int [] answer = new int[result.size()];
		for(int i = 0 ; i<result.size() ; i++) {
			answer[i] = result.get(i);
		}
		System.out.println(answer);
	}

}
