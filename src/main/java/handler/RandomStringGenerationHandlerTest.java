package handler;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomStringGenerationHandlerTest {

	@Test
	public void test() {
		String result = RandomStringGenerationHandler.randomStringFactory(20);
		System.out.println(result);
		assertEquals(5, result.length());

	}

}
