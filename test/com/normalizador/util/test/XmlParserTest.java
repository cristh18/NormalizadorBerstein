package com.normalizador.util.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.normalizador.entities.Dominio;
import com.normalizador.util.XmlParser;

public class XmlParserTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadFile() {
		// Arrange
		String filePath = "universal.xml";
		XmlParser xmlParser = new XmlParser();
		
		// Act
		Dominio result = xmlParser.readFile(filePath);
		
		// Assert
		assertNotNull(result);
		assertNotNull(result.getAtributos());
		assertNotNull(result.getDependencias());
		assertTrue(result.getAtributos().size() > 0);
		assertTrue(result.getDependencias().size() > 0);
	}

}
