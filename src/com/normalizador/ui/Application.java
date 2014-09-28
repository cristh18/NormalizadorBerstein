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
		normalizadorBernstein.setDominio(xmlParser.readFile(filePath));
		System.out.print("Carga de archivo con el dominio, conjunto de atributos y conjunto de dependencias funcionales");
		System.out.println();
		System.out.println();
		normalizadorBernstein.imprimirAtributos();
		normalizadorBernstein.imprimirDependenciasFuncionales();
		normalizadorBernstein.calcularDependenciasElementales(normalizadorBernstein.getDominio());
		normalizadorBernstein.imprimirDependenciasFuncionales();
		normalizadorBernstein.eliminarElementosExtranios(normalizadorBernstein.getDominio());
		normalizadorBernstein.imprimirDependenciasFuncionales();
		normalizadorBernstein.eliminarDependenciasInnecesarias(normalizadorBernstein.getDominio());
		normalizadorBernstein.imprimirDependenciasFuncionales();
		normalizadorBernstein.agruparPorLlave(normalizadorBernstein.getDominio());
		normalizadorBernstein.imprimirDependenciasFuncionales();
	}

}
