package com.normalizador.logic;

import java.util.ArrayList;
import java.util.List;

import com.normalizador.entities.Atributo;
import com.normalizador.entities.DependenciaFuncional;
import com.normalizador.entities.Dominio;
import com.normalizador.entities.Esquema;
import com.normalizador.util.XmlParser;

/**
 * 
 * @author Cristhian
 *
 */
public class NormalizadorBernstein implements INormalizador {
	
	/**
	 * 
	 */
	private Dominio dominio;
	
	private List<DependenciaFuncional> l1;
	
	private List<DependenciaFuncional> l1Temp;
	
	private List<DependenciaFuncional> l1TempAxiomatizada;
	
	/**
	 * Default constructor
	 */
	public NormalizadorBernstein() {
		l1 = new ArrayList<DependenciaFuncional>();
		l1Temp = new ArrayList<DependenciaFuncional>();
		l1TempAxiomatizada = new ArrayList<DependenciaFuncional>();
	}

	@Override
	public Esquema normalizar(Dominio dominio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dominio calcularDependenciasElementales(Dominio dominio) {
		
		System.out.println();
		System.out.print("Paso 1: Calcular dependencias elementales");
		System.out.println();
		
		l1 = new ArrayList<DependenciaFuncional>();
		l1Temp = new ArrayList<DependenciaFuncional>();
		
		for (DependenciaFuncional dependenciaFuncional : dominio.getDependencias()) {
			if (dependenciaFuncional.getImplicados().size() == 1) {
				l1.add(dependenciaFuncional);
			}
			else{
				l1Temp.add(dependenciaFuncional);
			}
		}
		
		l1TempAxiomatizada = this.axiomaDescomposicion(l1Temp);
		
		this.agregarDependenciasL1(l1TempAxiomatizada);
		dominio.setDependencias(l1);
		return dominio;
	}

	@Override
	public Dominio eliminarElementosExtranios(Dominio dominio) {
		
		System.out.println();
		System.out.print("Paso 2: Eliminar atributos extraños");
		System.out.println();
		
		for (int i = 0; i < dominio.getDependencias().size(); i++) {
			if(dominio.getDependencias().get(i).getDeterminantes().size() > 1){
				
				List<Atributo> listAttributes = new ArrayList<Atributo>();
				listAttributes.addAll(dominio.getDependencias().get(i).getDeterminantes());
				
				for (int j = 0; j < listAttributes.size(); j++) {
					List<Atributo> listTestAttributes = new ArrayList<Atributo>();
					listTestAttributes.addAll(listAttributes);
					listTestAttributes.remove(j);
					
					List<Atributo> listAtributosClausura = clausura(listTestAttributes);
					
					if(listAtributosClausura.containsAll(dominio.getDependencias().get(i).getImplicados()))
					{
						DependenciaFuncional df = new DependenciaFuncional();
						df.setDeterminantes(listTestAttributes);
						df.setImplicados(dominio.getDependencias().get(i).getImplicados());
						dominio.getDependencias().set(i, df);
						break;
					}
				}
			}
		}
	
		return dominio;
	}
	
	@Override
	public List<Atributo> clausura(List<Atributo> atributos)
	{
		List<Atributo> listAtributosClausura = new ArrayList<Atributo>();
		listAtributosClausura.addAll(atributos);

		List<DependenciaFuncional> listDependenciasFuncionales = new ArrayList<DependenciaFuncional>();
		listDependenciasFuncionales.addAll(dominio.getDependencias());
	
		List<DependenciaFuncional> listDependenciasEliminar = new ArrayList<DependenciaFuncional>();
		
		int cantidadInicial = 0, cantidadFinal = 0;
		
		do {
			//Cantidad atributos antes de iniciar el ciclo
			cantidadInicial = listAtributosClausura.size();
			
			for (DependenciaFuncional dependenciaFuncional : listDependenciasFuncionales) {
				if(listAtributosClausura.containsAll(dependenciaFuncional.getDeterminantes()))
				{
					listAtributosClausura.addAll(dependenciaFuncional.getImplicados());
					listDependenciasEliminar.add(dependenciaFuncional);
				}
			}
			
			//Cantidad atributos al finalizar un ciclo
			cantidadFinal = listAtributosClausura.size();
			
			//Eliminar de la lista de dependencias funcionales las que sus determinantes ya fueron adicionados a la lista de atributos
			listDependenciasFuncionales.removeAll(listDependenciasEliminar);
			//Vaciar lista dependencias funcionales a eliminar
			listDependenciasEliminar.clear();
			
			if(listAtributosClausura.size() == dominio.getAtributos().size())
				break;
			
		} while (cantidadInicial != cantidadFinal /*|| listDependenciasFuncionales.size() > 0 || */);

		return listAtributosClausura;
	}
	
	@Override
	public List<Atributo> clausura(List<Atributo> atributos, List<DependenciaFuncional> listDFTest)
	{
		List<Atributo> listAtributosClausura = new ArrayList<Atributo>();
		listAtributosClausura.addAll(atributos);

		List<DependenciaFuncional> listDependenciasFuncionales = new ArrayList<DependenciaFuncional>();
		listDependenciasFuncionales.addAll(listDFTest);
	
		List<DependenciaFuncional> listDependenciasEliminar = new ArrayList<DependenciaFuncional>();
		
		int cantidadInicial = 0, cantidadFinal = 0;
		
		do {
			//Cantidad atributos antes de iniciar el ciclo
			cantidadInicial = listAtributosClausura.size();
			
			for (DependenciaFuncional dependenciaFuncional : listDependenciasFuncionales) {
				if(listAtributosClausura.containsAll(dependenciaFuncional.getDeterminantes()))
				{
					listAtributosClausura.addAll(dependenciaFuncional.getImplicados());
					listDependenciasEliminar.add(dependenciaFuncional);
				}
			}
			
			//Cantidad atributos al finalizar un ciclo
			cantidadFinal = listAtributosClausura.size();
			
			//Eliminar de la lista de dependencias funcionales las que sus determinantes ya fueron adicionados a la lista de atributos
			listDependenciasFuncionales.removeAll(listDependenciasEliminar);
			//Vaciar lista dependencias funcionales a eliminar
			listDependenciasEliminar.clear();
			
			if(listAtributosClausura.size() == dominio.getAtributos().size())
				break;
			
		} while (cantidadInicial != cantidadFinal /*|| listDependenciasFuncionales.size() > 0 || */);

		return listAtributosClausura;
	}
	
	@Override
	public Dominio eliminarDependenciasInnecesarias(Dominio dominio) {
		System.out.println();
		System.out.print("Paso 3: Eliminar dependencias innecesarias");
		System.out.println();
		
		boolean flag = true;
		while(flag)
		{
			List<DependenciaFuncional> listaDFActualizadas = new ArrayList<DependenciaFuncional>(); 
			listaDFActualizadas.addAll(dominio.getDependencias()); 
			int i = 0;
			for (i = 0; i < listaDFActualizadas.size(); i++) {
				List<DependenciaFuncional> listdf = new ArrayList<DependenciaFuncional>();
				listdf.addAll(listaDFActualizadas);
				listdf.remove(i);
				
				List<Atributo> listAtributosClausura = clausura(listaDFActualizadas.get(i).getDeterminantes(), listdf);
				
				if(listAtributosClausura.containsAll(dominio.getDependencias().get(i).getImplicados()))
				{
					dominio.getDependencias().remove(i);
					break;
				}				
			}
			
			if(i == listaDFActualizadas.size())
				flag = false;
		}
		
		return dominio;
	}

	@Override
	public Dominio agruparPorLlave(Dominio dominio) {
		System.out.println();
		System.out.print("Paso 4: Agrupar dependencias por llave");
		System.out.println();
		
		List<DependenciaFuncional> listaDFFinal = new ArrayList<DependenciaFuncional>();
				
		for (int i = 0; i < dominio.getDependencias().size(); i++) {
			DependenciaFuncional obj = new DependenciaFuncional();
			List<Atributo> listAtributosDeterminantes = new ArrayList<Atributo>();
			List<Atributo> listAtributosImplicados = new ArrayList<Atributo>();
			
			listAtributosDeterminantes.addAll(dominio.getDependencias().get(i).getDeterminantes());
			listAtributosImplicados.addAll(dominio.getDependencias().get(i).getImplicados());
			
			for (int j = 0; j < dominio.getDependencias().size(); j++) {
				if(i==j)
					continue;
				if(dominio.getDependencias().get(i).getDeterminantes().equals(dominio.getDependencias().get(j).getDeterminantes())){
					listAtributosImplicados.addAll(dominio.getDependencias().get(j).getImplicados());
					
				}
			}
			
			obj.setDeterminantes(listAtributosDeterminantes);
			obj.setImplicados(listAtributosImplicados);
			
			boolean flagRepetidos = false;
			for (DependenciaFuncional df : listaDFFinal) {
				if(df.getDeterminantes().equals(obj.getDeterminantes())){
					flagRepetidos = true;
					break;
				}
			}
			
			if(!flagRepetidos)
				listaDFFinal.add(obj);
		}
		
		dominio.setDependencias(listaDFFinal);
		return dominio;
	}
	
	/**
	 * 
	 * @param dependenciasNoSimples
	 */
	private List<DependenciaFuncional> axiomaDescomposicion(List<DependenciaFuncional> dependenciasNoSimples) {
		List<DependenciaFuncional> l1TempNew = new ArrayList<DependenciaFuncional>();
		for (DependenciaFuncional dependenciaFuncional : dependenciasNoSimples) {
			for (Atributo aImplicado : dependenciaFuncional.getImplicados()) {
				DependenciaFuncional newDF = new DependenciaFuncional();
				newDF.setDeterminantes(dependenciaFuncional.getDeterminantes());
				List<Atributo> listAtr = new ArrayList<Atributo>();
				listAtr.add(aImplicado);
				newDF.setImplicados((listAtr));
				l1TempNew.add(newDF);
			}
		}
		return l1TempNew;
	}
	
	/**
	 * 
	 * @param dependenciasAxiomatizadas
	 * @return
	 */
	private List<DependenciaFuncional> agregarDependenciasL1(List<DependenciaFuncional> dependenciasAxiomatizadas) {
		for (DependenciaFuncional dependenciaFuncional : dependenciasAxiomatizadas) {
			l1.add(dependenciaFuncional);
		}
		return l1;
	}
	
	/**
	 * 
	 */
	public void imprimirAtributos() {

		StringBuilder sb = new StringBuilder();
		sb.append("A: {");
		for (Atributo atributo : dominio.getAtributos()) {
			sb.append(atributo.getValor());
			sb.append(", ");
		}
		String cadena = sb.toString();
		String sub = cadena.substring(0, cadena.lastIndexOf(", ")) + "}";
		System.out.println(sub);
	}

	/**
	 * 
	 */
	public void imprimirDependenciasFuncionales() {

		StringBuilder sb = new StringBuilder();
		sb.append("FD: {");
		for (DependenciaFuncional dependenciaFuncional : dominio.getDependencias()) {

			for (Atributo determinante : dependenciaFuncional.getDeterminantes()) {
				sb.append(determinante.getValor());
			}

			sb.append("-->");

			for (Atributo implicado : dependenciaFuncional.getImplicados()) {
				sb.append(implicado.getValor());
			}
			
			sb.append(", ");
		}
		String cadena = sb.toString();
		String sub = cadena.substring(0, cadena.lastIndexOf(", ")) + "}";
		System.out.println(sub);
	}

	/**
	 * @return the dominio
	 */
	public Dominio getDominio() {
		return dominio;
	}

	/**
	 * @param dominio the dominio to set
	 */
	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

	/**
	 * @return the l1
	 */
	public List<DependenciaFuncional> getL1() {
		return l1;
	}

	/**
	 * @param l1 the l1 to set
	 */
	public void setL1(List<DependenciaFuncional> l1) {
		this.l1 = l1;
	}

	/**
	 * @return the l1Temp
	 */
	public List<DependenciaFuncional> getL1Temp() {
		return l1Temp;
	}

	/**
	 * @param l1Temp the l1Temp to set
	 */
	public void setL1Temp(List<DependenciaFuncional> l1Temp) {
		this.l1Temp = l1Temp;
	}

	/**
	 * @return the l1TempAxiomatizada
	 */
	public List<DependenciaFuncional> getL1TempAxiomatizada() {
		return l1TempAxiomatizada;
	}

	/**
	 * @param l1TempAxiomatizada the l1TempAxiomatizada to set
	 */
	public void setL1TempAxiomatizada(List<DependenciaFuncional> l1TempAxiomatizada) {
		this.l1TempAxiomatizada = l1TempAxiomatizada;
	}



}
