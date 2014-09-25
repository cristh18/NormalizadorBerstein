package com.normalizador.entities;

import java.util.List;

/**.
 * @author Fabio Bernal
 * @author Guillermo Correa
 * @author Cristhian Tolosa
 */
public class DependenciaFuncional {
	
	/**
	 * Default Constructor
	 */
	public DependenciaFuncional() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The Determinants
	 */
	private List<Atributo> determinantes;
	
	/**
	 * 
	 */
	private List<Atributo> implicados;

	/**
	 * @return the determinantes
	 */
	public List<Atributo> getDeterminantes() {
		return determinantes;
	}

	/**
	 * @param determinantes the determinantes to set
	 */
	public void setDeterminantes(List<Atributo> determinantes) {
		this.determinantes = determinantes;
	}

	/**
	 * @return the implicados
	 */
	public List<Atributo> getImplicados() {
		return implicados;
	}

	/**
	 * @param implicados the implicados to set
	 */
	public void setImplicados(List<Atributo> implicados) {
		this.implicados = implicados;
	}

}
