package com.normalizador.entities;

import java.util.List;

/**.
 * @author Fabio Bernal
 * @author Guillermo Correa
 * @author Cristhian Tolosa
 */
public class Dominio {
	
	private List<Atributo> atributos;
	
	private List<DependenciaFuncional> dependencias;
	
	private List<Atributo> superKey;
	
	public Dominio() {
		// TODO Auto-generated constructor stub
	}

	public List<Atributo> getSuperKey()
	{
		return superKey;
	}
	
	public void setSuperKey(List<Atributo> superKey){
		this.superKey = superKey;
	}
	
	/**
	 * @return the atributos
	 */
	public List<Atributo> getAtributos() {
		return atributos;
	}

	/**
	 * @param atributos the atributos to set
	 */
	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}

	/**
	 * @return the dependencias
	 */
	public List<DependenciaFuncional> getDependencias() {
		return dependencias;
	}

	/**
	 * @param dependencias the dependencias to set
	 */
	public void setDependencias(List<DependenciaFuncional> dependencias) {
		this.dependencias = dependencias;
	}
	
	

}
