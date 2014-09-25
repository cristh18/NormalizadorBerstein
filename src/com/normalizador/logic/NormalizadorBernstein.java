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
	public boolean cierreDescriptor(DependenciaFuncional descriptor,
			List<DependenciaFuncional> conjuntoDependencias) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dominio calcularDependenciasElementales(Dominio dominio) {
		int contador = 0;
		l1 = new ArrayList<DependenciaFuncional>();
		
		l1Temp = new ArrayList<DependenciaFuncional>();
		
		for (DependenciaFuncional dependenciaFuncional : dominio.getDependencias()) {
			for (Atributo implicados : dependenciaFuncional.getImplicados()) {
				if (implicados.getValor().length() == 1) {
					contador++;
					l1.add(dependenciaFuncional);
				}else {
					l1Temp.add(dependenciaFuncional);
				}
			}
		}
		System.out.println(contador);
		
		l1TempAxiomatizada = this.axiomaDescomposicion(l1Temp);
		
		this.agregarDependenciasL1(l1TempAxiomatizada);
		dominio.setDependencias(l1);
		
//		System.out.println("=======================L1=======================");
//		for (DependenciaFuncional dependenciaFuncional : l1) {
//			
//			
//			System.out.println();
//
//			for (Atributo determinante : dependenciaFuncional
//					.getDeterminantes()) {
//				System.out.print(determinante.getValor());
//			}
//
//			System.out.print("-->");
//
//			for (Atributo implicado : dependenciaFuncional.getImplicados()) {
//				System.out.println(implicado.getValor());
//			}
//			
//		}
//		
//		System.out.println("=======================L1Temp=======================");
//		for (DependenciaFuncional dependenciaFuncional : l1Temp) {
//			
//			
//			System.out.println();
//
//			for (Atributo determinante : dependenciaFuncional
//					.getDeterminantes()) {
//				System.out.print(determinante.getValor());
//			}
//
//			System.out.print("-->");
//
//			for (Atributo implicado : dependenciaFuncional.getImplicados()) {
//				System.out.println(implicado.getValor());
//			}
//			
//		}
		
		return dominio;
	}

	@Override
	public Dominio eliminarElementosExtranios(Dominio dominio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dominio eliminarDependenciasInnecesarias(Dominio dominio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dominio agruparPorLlave(Dominio dominio) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * @param dependenciasNoSimples
	 */
	public List<DependenciaFuncional> axiomaDescomposicion(
			List<DependenciaFuncional> dependenciasNoSimples) {
		List<DependenciaFuncional> l1TempNew = new ArrayList<DependenciaFuncional>();
		List<Atributo> atributosL1 = new ArrayList<Atributo>();
		for (DependenciaFuncional dependenciaFuncional : dependenciasNoSimples) {
			for (Atributo aImplicado : dependenciaFuncional.getImplicados()) {
				for (int i = 0; i < aImplicado.getValor().length(); i++) {
					DependenciaFuncional deFuncional = new DependenciaFuncional();
					deFuncional.setDeterminantes(dependenciaFuncional
							.getDeterminantes());
					Atributo atributoTemp = new Atributo();
					atributoTemp.setValor(String.valueOf(aImplicado.getValor()
							.charAt(i)));
					atributosL1 = new ArrayList<Atributo>();
					atributosL1.add(atributoTemp);
					deFuncional.setImplicados(atributosL1);
					l1TempNew.add(deFuncional);
				}
			}
		}
		return l1TempNew;
	}
	
	/**
	 * 
	 * @param dependenciasAxiomatizadas
	 * @return
	 */
	public List<DependenciaFuncional> agregarDependenciasL1(
			List<DependenciaFuncional> dependenciasAxiomatizadas) {
		for (DependenciaFuncional dependenciaFuncional : dependenciasAxiomatizadas) {
			l1.add(dependenciaFuncional);
		}
		return l1;
	}
	
	
	/**
	 * 
	 */
	public void imprimirAtributos() {

		for (Atributo atributo : dominio.getAtributos()) {
			System.out.println(atributo.getValor());
		}

	}

	/**
	 * 
	 */
	public void imprimirDependenciasFuncionales() {

		for (DependenciaFuncional dependenciaFuncional : dominio
				.getDependencias()) {

			System.out.println();

			for (Atributo determinante : dependenciaFuncional
					.getDeterminantes()) {
				System.out.print(determinante.getValor());
			}

			System.out.print("-->");

			for (Atributo implicado : dependenciaFuncional.getImplicados()) {
				System.out.println(implicado.getValor());
			}
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		XmlParser xmlParser = new XmlParser();
		NormalizadorBernstein normalizadorBernstein = new NormalizadorBernstein();
		System.out.println("Entro");
		normalizadorBernstein.setDominio(xmlParser.readFile("universal.xml"));
		System.out.println();
		System.out.println();
		normalizadorBernstein.imprimirAtributos();
		normalizadorBernstein.imprimirDependenciasFuncionales();
		
		normalizadorBernstein.calcularDependenciasElementales(normalizadorBernstein.getDominio());
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
