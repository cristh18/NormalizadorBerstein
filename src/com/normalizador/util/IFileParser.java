package com.normalizador.util;

import com.normalizador.entities.Dominio;

/**
 * Componente para leer a partir de un archivo y convertir a las clases de dominio
 * @author Cristhian
 *
 */
public interface IFileParser {
	
	/**
	 * Lee un archivo y devuelve una instancia de dominio
	 * @param filePath
	 * @return
	 */
	Dominio readFile(String filePath);
}
