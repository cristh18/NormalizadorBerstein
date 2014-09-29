package com.normalizador.ui;

import com.normalizador.logic.NormalizadorBernstein;
import com.normalizador.util.XmlParser;

/**
 * Bootstraps the application, instantiates the logic objects and kickstart the application
 * @author guillermo
 *
 */
public class Application {
	
	/**
	 * Main application entry point
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "universal.xml";
		if (args != null && args.length > 0) {
			filePath = args[0];
		}
		
		XmlParser xmlParser = new XmlParser();
		NormalizadorBernstein normalizadorBernstein = new NormalizadorBernstein();
		normalizadorBernstein.normalizar(xmlParser.readFile(filePath));
	}

}
