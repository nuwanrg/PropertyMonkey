package com.pm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertyMonkeyApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("test");


	}

	@Test
	void testAssert(){
		Assertions.assertEquals("AAAA" , "AAAA");
	}

}
