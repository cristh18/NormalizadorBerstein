package com.normalizador.logic;

import java.util.List;

import com.normalizador.entities.Atributo;
import com.normalizador.entities.DependenciaFuncional;
import com.normalizador.entities.Dominio;
import com.normalizador.entities.Esquema;

public interface INormalizador {
	Esquema normalizar(Dominio dominio);

	/**
	 * La clausura del descriptor
	 * 
	 * @param descriptor
	 * @param conjuntoDependencias
	 * @return
	 */
	List<Atributo> clausura(List<Atributo> atributos);

	List<Atributo> clausura(List<Atributo> atributos,
			List<DependenciaFuncional> listDFTest);

	Dominio calcularDependenciasElementales(Dominio dominio);

	Dominio eliminarElementosExtranios(Dominio dominio);

	Dominio eliminarDependenciasInnecesarias(Dominio dominio);

	Dominio agruparPorLlave(Dominio dominio);
}
