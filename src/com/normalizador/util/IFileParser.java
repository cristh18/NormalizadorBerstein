package com.normalizador.util;

import com.normalizador.entities.Dominio;

/**
 * 
 * @author Cristhian
 *
 */
public interface IFileParser {
	
	/**
	 * 
	 * @param filePath
	 * @return
	 */
	Dominio readFile(String filePath);
}
