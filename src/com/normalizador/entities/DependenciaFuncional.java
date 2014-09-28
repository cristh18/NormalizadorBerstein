package com.normalizador.entities;

import java.util.List;

/**
 * .
 * 
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
	 * @param determinantes
	 *            the determinantes to set
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
	 * @param implicados
	 *            the implicados to set
	 */
	public void setImplicados(List<Atributo> implicados) {
		this.implicados = implicados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((determinantes == null) ? 0 : determinantes.hashCode());
		result = prime * result
				+ ((implicados == null) ? 0 : implicados.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DependenciaFuncional other = (DependenciaFuncional) obj;
		if (determinantes == null) {
			if (other.determinantes != null)
				return false;
		} else if (!determinantes.equals(other.determinantes))
			return false;
		if (implicados == null) {
			if (other.implicados != null)
				return false;
		} else if (!implicados.equals(other.implicados))
			return false;
		return true;
	}

}
