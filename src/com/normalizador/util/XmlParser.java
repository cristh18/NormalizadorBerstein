package com.normalizador.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.normalizador.entities.Atributo;
import com.normalizador.entities.DependenciaFuncional;
import com.normalizador.entities.Dominio;

/**
 * 
 * @author Cristhian
 *
 */
public class XmlParser implements IFileParser {

	/**
	 * 
	 */
	private Atributo atributo;

	/**
	 * 
	 */
	private List<Atributo> atributos;

	/**
	 * 
	 */
	private DependenciaFuncional dependenciaFuncional;

	/**
	 * 
	 */
	private List<DependenciaFuncional> dependenciasFuncionales;

	/**
	 * 
	 */
	private Dominio dominio;

	/**
	 * Default constructor
	 */
	public XmlParser() {
		atributos = new ArrayList<Atributo>();
		dependenciasFuncionales = new ArrayList<DependenciaFuncional>();

		dominio = new Dominio();
	}

	/**
	 * 
	 */
	public Dominio readFile(String filePath) {
		String z = "";
		String h = "";
		List<Atributo> atributosTemp1 = new ArrayList<Atributo>();
		List<Atributo> atributosTemp2 = new ArrayList<Atributo>(); 
		StringBuilder cadenaDeterminante;
		StringBuilder cadenaImplicado;
		String conjuntoAtributosDet = "";
		String conjuntoAtributosImp = "";

		// Se crea un SAXBuilder para poder parsear el archivo
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(filePath);
		try {
			// Se crea el documento a traves del archivo
			Document document = (Document) builder.build(xmlFile);

			// Se obtiene la raiz 'tables'
			Element rootNode = document.getRootElement();

			// Se obtiene la lista de hijos de la raiz 'root'
			List list = rootNode.getChildren("attributes");

			List dependencies = rootNode.getChildren("fds");

			// Se recorre la lista de hijos de 'root'
			for (int i = 0; i < list.size(); i++) {
				// Se obtiene el elemento 'attributes'
				Element attributes = (Element) list.get(i);

				// Se obtiene la lista de hijos del tag 'attributes'
				List lista_campos = attributes.getChildren();

				// Se recorre la lista de campos
				for (int j = 0; j < lista_campos.size(); j++) {
					// Se obtiene el elemento 'campo'
					Element campo = (Element) lista_campos.get(j);

					// Se obtienen los valores que estan entre los tags
					// '<attribute></attribute>'

					String d = campo.getTextTrim();

					System.out.println(d);

					// Se inicaliza y se setean datos al objeto atributo
					atributo = new Atributo();
					atributo.setValor(d);
					
					atributos.add(atributo);
					dominio.setAtributos(atributos);
				}
			}

			for (int k = 0; k < dependencies.size(); k++) {
								
				Element df = (Element) dependencies.get(k);

				List dfs = df.getChildren();

				for (int l = 0; l < dfs.size(); l++) {

					List<Atributo> listAtributosDeterminantes = new ArrayList<Atributo>();
					List<Atributo> listAtributosImplicados = new ArrayList<Atributo>();
					cadenaDeterminante = new StringBuilder();
					cadenaImplicado = new StringBuilder();
					conjuntoAtributosDet = "";
					conjuntoAtributosImp = "";
					
					Element d = (Element) dfs.get(l);

					List determinantes = d.getChildren("left");

					List implicados = d.getChildren("right");

					for (int m = 0; m < determinantes.size(); m++) {
						Element y = (Element) determinantes.get(m);

						List det = y.getChildren();

						// System.out.println("Determinantes");
						for (int n = 0; n < det.size(); n++) {
							Element q = (Element) det.get(n);
							z = q.getTextTrim();
//							System.out.println(z);
							
							Atributo atr = new Atributo();
							atr.setValor(z);
							listAtributosDeterminantes.add(atr);
							cadenaDeterminante.append(z);
						}
//						System.out.println(cadenaDeterminante);

					}

					for (int o = 0; o < implicados.size(); o++) {
						Element x = (Element) implicados.get(o);

						List imp = x.getChildren();

//						System.out.println("Implicados");
						for (int p = 0; p < imp.size(); p++) {
							Element t = (Element) imp.get(p);
							h = t.getTextTrim();
//							System.out.println(h);
							
							Atributo atr = new Atributo();
							atr.setValor(h);
							listAtributosImplicados.add(atr);
							cadenaImplicado.append(h);
						}
//						System.out.println(cadenaImplicado);
					}

					dependenciaFuncional = new DependenciaFuncional();
					
					for (Atributo atributo : atributos) {
						atributosTemp1 = new ArrayList<Atributo>();
						atributosTemp2 = new ArrayList<Atributo>();
						
						for (int i = 0; i < cadenaDeterminante.length(); i++) {
							
							if (atributo.getValor().charAt(0)==(cadenaDeterminante.charAt(i))) {
								conjuntoAtributosDet = conjuntoAtributosDet+atributo.getValor();
							}
						}
						
						for (int j = 0; j < cadenaImplicado.length(); j++) {
							if (atributo.getValor().charAt(0) == (cadenaImplicado.charAt(j))) {
								conjuntoAtributosImp = conjuntoAtributosImp+atributo.getValor();
							}
						}
					}

					Atributo atributoTemp = new Atributo();
					atributoTemp.setValor(conjuntoAtributosDet);
					atributosTemp1.add(atributoTemp);
//					dependenciaFuncional.setDeterminantes(atributosTemp1);
					dependenciaFuncional.setDeterminantes(listAtributosDeterminantes);
					
					Atributo atributoTemp2 = new Atributo();
					atributoTemp2.setValor(conjuntoAtributosImp);
					atributosTemp2.add(atributoTemp2);
//					dependenciaFuncional.setImplicados(atributosTemp2);
					dependenciaFuncional.setImplicados(listAtributosImplicados);
					
					dependenciasFuncionales.add(dependenciaFuncional);
					dominio.setDependencias(dependenciasFuncionales);
				}
			}

		} catch (IOException io) {
			io.printStackTrace();
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		return dominio;
	}

	
	/**
	 * @return the atributo
	 */
	public Atributo getAtributo() {
		return atributo;
	}

	/**
	 * @param atributo
	 *            the atributo to set
	 */
	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	/**
	 * @return the atributos
	 */
	public List<Atributo> getAtributos() {
		return atributos;
	}

	/**
	 * @param atributos
	 *            the atributos to set
	 */
	public void setAtributos(List<Atributo> atributos) {
		this.atributos = atributos;
	}

	/**
	 * @return the dependenciaFuncional
	 */
	public DependenciaFuncional getDependenciaFuncional() {
		return dependenciaFuncional;
	}

	/**
	 * @param dependenciaFuncional
	 *            the dependenciaFuncional to set
	 */
	public void setDependenciaFuncional(
			DependenciaFuncional dependenciaFuncional) {
		this.dependenciaFuncional = dependenciaFuncional;
	}

	/**
	 * @return the dependenciasFuncionales
	 */
	public List<DependenciaFuncional> getDependenciasFuncionales() {
		return dependenciasFuncionales;
	}

	/**
	 * @param dependenciasFuncionales
	 *            the dependenciasFuncionales to set
	 */
	public void setDependenciasFuncionales(
			List<DependenciaFuncional> dependenciasFuncionales) {
		this.dependenciasFuncionales = dependenciasFuncionales;
	}

	/**
	 * @return the dominio
	 */
	public Dominio getDominio() {
		return dominio;
	}

	/**
	 * @param dominio
	 *            the dominio to set
	 */
	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

}
