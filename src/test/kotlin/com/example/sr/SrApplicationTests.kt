package com.example.sr

import io.qameta.allure.Description
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class SrApplicationTests: TestBase() {

	@Test
	@Description("Application context loads test")
	fun contextLoads() {
		assertTrue(true)
	}

}
