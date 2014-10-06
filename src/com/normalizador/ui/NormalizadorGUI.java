package com.normalizador.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import com.normalizador.logic.NormalizadorBernstein;
import com.normalizador.util.XmlParser;

public class NormalizadorGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private NormalizadorBernstein normalizadorBernstein;
	
	/**
	 * Instancia de la clase Evento
	 */
	private Evento evento;
	
	/**
	 * Es el panel que contiene los botones en la parte inferior de la aplicacion
	 */
	private JPanel panelBotones;
	
	/**
	 * Es el panel que contiene resultados de la normalización
	 */
	private JPanel panelResultados;
	
	/**
	 * Botón que despliega el componente para buscar el archivo a cargar
	 */
	private JButton jbtnCargar;
	
	/**
	 * 
	 */
	private JFileChooser abrirArchivo;
	
	/**
	 * 
	 */
	private JLabel jlblEtiAtributos;	
	
	/**
	 * 
	 */
	private JLabel jlblAtributos;
	
	/**
	 * 
	 */
	private JLabel jlblEtiDependencias;	
	
	/**
	 * 
	 */
	private JLabel jlblDependencias;
	
	/**
	 * 
	 */
	private JLabel jlblEtiDepElementales;	
	
	/**
	 * 
	 */
	private JTextArea jlblDepElementale;
	
	/**
	 * 
	 */
	private JLabel jlblEtiAtrExtranios;	
	
	/**
	 * 
	 */
	private JTextArea jlblAtrExtranios;
	
	/**
	 * 
	 */
	private JLabel jlblEtiDepInnecesarias;	
	
	/**
	 * 
	 */
	private JTextArea jlblDepInnecesarias;
	
	/**
	 * 
	 */
	private JLabel jlblEtiAgrupamiento;	
	
	/**
	 * 
	 */
	private JTextArea jlblAgrupamiento;
	
	/**
	 * 
	 */
	private JLabel jlblEtiSuperLLave;	
	
	/**
	 * 
	 */
	private JTextArea jlblSuperLLave;
	
	/**
	 * 
	 */
	private JLabel jlblEtiSubesquemas;	
	
	/**
	 * 
	 */
	private JTextArea jlblSubesquemas;
	
	public NormalizadorGUI() {
		evento = new Evento(this);
		
		setSize(1000, 720);
		setTitle("Normalizador Bernstein");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		
		
		panelBotones = new JPanel();
		panelBotones.setSize(1000, 720);		
		TitledBorder titulo = new TitledBorder("Cargar XML");
		panelBotones.setBorder(titulo);
		panelBotones.setBounds(0, 0, 994, 678);
		panelBotones.setLayout(new FlowLayout());
		add(panelBotones);
		
		
		jbtnCargar = new JButton("Cargar Archivo");
		jbtnCargar.setFont( new Font( "Calibri (Cuerpo)", Font.BOLD, 12));
		jbtnCargar.setBackground(new Color(0,86,84));
		jbtnCargar.setForeground(new Color(255,153,0));
		jbtnCargar.addActionListener(this.getEvento());
		jbtnCargar.setActionCommand(Evento.ACCION_CARGAR_XML);
		panelBotones.add(jbtnCargar);
		
		abrirArchivo=new JFileChooser();
		
		panelResultados = new JPanel();
		panelResultados.setSize(1000, 720);		
		TitledBorder tituloResultados = new TitledBorder("Resultado de la Normalización");
		panelResultados.setBorder(titulo);
		panelResultados.setBounds(0, 0, 994, 678);
		panelResultados.setBorder(tituloResultados);
		panelResultados.setLayout(null);
		panelResultados.setVisible(false);
		add(panelResultados);
		
		
		jlblEtiAtributos = new JLabel("Atributos: ");
		jlblEtiAtributos.setForeground(Color.BLUE);
		jlblEtiAtributos.setBounds(10, 25, 100, 25);
		panelResultados.add(jlblEtiAtributos);	
		
		jlblAtributos = new JLabel();
		jlblAtributos.setBounds(110, 25, 600, 25);
		panelResultados.add(jlblAtributos);
		
		jlblEtiDependencias = new JLabel("Dependencias: ");
		jlblEtiDependencias.setForeground(Color.BLUE);
		jlblEtiDependencias.setBounds(10, 55, 100, 25);
		panelResultados.add(jlblEtiDependencias);
		
		jlblDependencias = new JLabel();
		jlblDependencias.setBounds(110, 55, 600, 25);
		panelResultados.add(jlblDependencias);
		
		jlblEtiDepElementales = new JLabel("1. Calculo de dependencias elementales");
		jlblEtiDepElementales.setForeground(Color.BLUE);
		jlblEtiDepElementales.setBounds(10, 85, 600, 25);
		panelResultados.add(jlblEtiDepElementales);
				
		jlblDepElementale = new JTextArea();
		jlblDepElementale.setEditable(false);
		
		JScrollPane scrollDepElementale = new JScrollPane (jlblDepElementale);
		scrollDepElementale.setBounds(10, 115, 900, 45);		
		panelResultados.add(scrollDepElementale);
		
		jlblEtiAtrExtranios = new JLabel("2. Eliminar elementos extraños");
		jlblEtiAtrExtranios.setForeground(Color.BLUE);
		jlblEtiAtrExtranios.setBounds(10, 165, 600, 25);
		panelResultados.add(jlblEtiAtrExtranios);

		jlblAtrExtranios = new JTextArea();
		jlblAtrExtranios.setEditable(false);
		
		JScrollPane scrollAtrExtranios = new JScrollPane (jlblAtrExtranios);
		scrollAtrExtranios.setBounds(10, 195, 900, 45);		
		panelResultados.add(scrollAtrExtranios);
		
		jlblEtiDepInnecesarias = new JLabel("3. Eliminar dependencias innecesarias");
		jlblEtiDepInnecesarias.setForeground(Color.BLUE);
		jlblEtiDepInnecesarias.setBounds(10, 245, 600, 25);
		panelResultados.add(jlblEtiDepInnecesarias);
		
		jlblDepInnecesarias = new JTextArea();
		jlblDepInnecesarias.setEditable(false);
		
		JScrollPane scrollDepInnecesarias = new JScrollPane (jlblDepInnecesarias);
		scrollDepInnecesarias.setBounds(10, 275, 900, 45);		
		panelResultados.add(scrollDepInnecesarias);
		
		jlblEtiAgrupamiento = new JLabel("4. Agrupar dependencias por llave");
		jlblEtiAgrupamiento.setForeground(Color.BLUE);
		jlblEtiAgrupamiento.setBounds(10, 325, 600, 25);
		panelResultados.add(jlblEtiAgrupamiento);

		jlblAgrupamiento = new JTextArea();
		jlblAgrupamiento.setEditable(false);
		
		JScrollPane scrollAgrupamiento = new JScrollPane (jlblAgrupamiento);
		scrollAgrupamiento.setBounds(10, 355, 900, 45);		
		panelResultados.add(scrollAgrupamiento);

		jlblEtiSuperLLave = new JLabel("5. Super llave");
		jlblEtiSuperLLave.setForeground(Color.BLUE);
		jlblEtiSuperLLave.setBounds(10, 405, 600, 25);
		panelResultados.add(jlblEtiSuperLLave);

		jlblSuperLLave = new JTextArea();
		jlblSuperLLave.setEditable(false);
				
		JScrollPane scrollSuperLLave = new JScrollPane (jlblSuperLLave);
		scrollSuperLLave.setBounds(10, 435, 900, 45);		
		panelResultados.add(scrollSuperLLave);
		
		jlblEtiSubesquemas = new JLabel("SubEsquemas");	
		jlblEtiSubesquemas.setForeground(Color.BLUE);
		jlblEtiSubesquemas.setBounds(10, 485, 600, 25);
		panelResultados.add(jlblEtiSubesquemas);

		jlblSubesquemas = new JTextArea();
		jlblSubesquemas.setEditable(false);
		
		JScrollPane scrollSubesquemas = new JScrollPane (jlblSubesquemas);
		scrollSubesquemas.setBounds(10, 515, 900, 160);		
		panelResultados.add(scrollSubesquemas);
	}
	
	public void ejecutarNormalizacion() {
		XmlParser xmlParser = new XmlParser();				
		normalizadorBernstein = new NormalizadorBernstein();
		normalizadorBernstein.normalizar(xmlParser.readFile(evento.getRuta()));
		inicializarElementosGUI();
	}
	
	private void inicializarElementosGUI() {
		jlblAtributos.setText(normalizadorBernstein.getAtributos());
		jlblDependencias.setText(normalizadorBernstein.getDependenciasFuncionales());		
		jlblDepElementale.setText(normalizadorBernstein.getDependenciasElementales());
		jlblAtrExtranios.setText(normalizadorBernstein.getDepSinExtranios());
		jlblDepInnecesarias.setText(normalizadorBernstein.getDepNoRedundantes());
		jlblAgrupamiento.setText(normalizadorBernstein.getAgruparPorLlave());
		jlblSuperLLave.setText(normalizadorBernstein.getSuperLlave());
		jlblSubesquemas.setText(normalizadorBernstein.getSubesquemas());
		
		panelBotones.setVisible(false);
		panelResultados.setVisible(true);
	}

	/**
	 * @return the evento
	 */
	public Evento getEvento() {
		return evento;
	}

	/**
	 * @param evento the evento to set
	 */
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	/**
	 * @return the panelBotones
	 */
	public JPanel getPanelBotones() {
		return panelBotones;
	}

	/**
	 * @param panelBotones the panelBotones to set
	 */
	public void setPanelBotones(JPanel panelBotones) {
		this.panelBotones = panelBotones;
	}

	/**
	 * @return the panelResultados
	 */
	public JPanel getPanelResultados() {
		return panelResultados;
	}

	/**
	 * @param panelResultados the panelResultados to set
	 */
	public void setPanelResultados(JPanel panelResultados) {
		this.panelResultados = panelResultados;
	}

	/**
	 * @return the jbtnCargar
	 */
	public JButton getJbtnCargar() {
		return jbtnCargar;
	}

	/**
	 * @param jbtnCargar the jbtnCargar to set
	 */
	public void setJbtnCargar(JButton jbtnCargar) {
		this.jbtnCargar = jbtnCargar;
	}

	/**
	 * @return the abrirArchivo
	 */
	public JFileChooser getAbrirArchivo() {
		return abrirArchivo;
	}

	/**
	 * @param abrirArchivo the abrirArchivo to set
	 */
	public void setAbrirArchivo(JFileChooser abrirArchivo) {
		this.abrirArchivo = abrirArchivo;
	}

	/**
	 * @return the jlblEtiAtributos
	 */
	public JLabel getJlblEtiAtributos() {
		return jlblEtiAtributos;
	}

	/**
	 * @param jlblEtiAtributos the jlblEtiAtributos to set
	 */
	public void setJlblEtiAtributos(JLabel jlblEtiAtributos) {
		this.jlblEtiAtributos = jlblEtiAtributos;
	}

	/**
	 * @return the jlblAtributos
	 */
	public JLabel getJlblAtributos() {
		return jlblAtributos;
	}

	/**
	 * @param jlblAtributos the jlblAtributos to set
	 */
	public void setJlblAtributos(JLabel jlblAtributos) {
		this.jlblAtributos = jlblAtributos;
	}

	/**
	 * @return the jlblEtiDependencias
	 */
	public JLabel getJlblEtiDependencias() {
		return jlblEtiDependencias;
	}

	/**
	 * @param jlblEtiDependencias the jlblEtiDependencias to set
	 */
	public void setJlblEtiDependencias(JLabel jlblEtiDependencias) {
		this.jlblEtiDependencias = jlblEtiDependencias;
	}

	/**
	 * @return the jlblDependencias
	 */
	public JLabel getJlblDependencias() {
		return jlblDependencias;
	}

	/**
	 * @param jlblDependencias the jlblDependencias to set
	 */
	public void setJlblDependencias(JLabel jlblDependencias) {
		this.jlblDependencias = jlblDependencias;
	}

	/**
	 * @return the jlblEtiDepElementales
	 */
	public JLabel getJlblEtiDepElementales() {
		return jlblEtiDepElementales;
	}

	/**
	 * @param jlblEtiDepElementales the jlblEtiDepElementales to set
	 */
	public void setJlblEtiDepElementales(JLabel jlblEtiDepElementales) {
		this.jlblEtiDepElementales = jlblEtiDepElementales;
	}

	/**
	 * @return the jlblDepElementale
	 */
	public JTextArea getJlblDepElementale() {
		return jlblDepElementale;
	}

	/**
	 * @param jlblDepElementale the jlblDepElementale to set
	 */
	public void setJlblDepElementale(JTextArea jlblDepElementale) {
		this.jlblDepElementale = jlblDepElementale;
	}

	/**
	 * @return the jlblEtiAtrExtranios
	 */
	public JLabel getJlblEtiAtrExtranios() {
		return jlblEtiAtrExtranios;
	}

	/**
	 * @param jlblEtiAtrExtranios the jlblEtiAtrExtranios to set
	 */
	public void setJlblEtiAtrExtranios(JLabel jlblEtiAtrExtranios) {
		this.jlblEtiAtrExtranios = jlblEtiAtrExtranios;
	}

	/**
	 * @return the jlblAtrExtranios
	 */
	public JTextArea getJlblAtrExtranios() {
		return jlblAtrExtranios;
	}

	/**
	 * @param jlblAtrExtranios the jlblAtrExtranios to set
	 */
	public void setJlblAtrExtranios(JTextArea jlblAtrExtranios) {
		this.jlblAtrExtranios = jlblAtrExtranios;
	}

	/**
	 * @return the jlblEtiDepInnecesarias
	 */
	public JLabel getJlblEtiDepInnecesarias() {
		return jlblEtiDepInnecesarias;
	}

	/**
	 * @param jlblEtiDepInnecesarias the jlblEtiDepInnecesarias to set
	 */
	public void setJlblEtiDepInnecesarias(JLabel jlblEtiDepInnecesarias) {
		this.jlblEtiDepInnecesarias = jlblEtiDepInnecesarias;
	}

	/**
	 * @return the jlblDepInnecesarias
	 */
	public JTextArea getJlblDepInnecesarias() {
		return jlblDepInnecesarias;
	}

	/**
	 * @param jlblDepInnecesarias the jlblDepInnecesarias to set
	 */
	public void setJlblDepInnecesarias(JTextArea jlblDepInnecesarias) {
		this.jlblDepInnecesarias = jlblDepInnecesarias;
	}

	/**
	 * @return the jlblEtiAgrupamiento
	 */
	public JLabel getJlblEtiAgrupamiento() {
		return jlblEtiAgrupamiento;
	}

	/**
	 * @param jlblEtiAgrupamiento the jlblEtiAgrupamiento to set
	 */
	public void setJlblEtiAgrupamiento(JLabel jlblEtiAgrupamiento) {
		this.jlblEtiAgrupamiento = jlblEtiAgrupamiento;
	}

	/**
	 * @return the jlblAgrupamiento
	 */
	public JTextArea getJlblAgrupamiento() {
		return jlblAgrupamiento;
	}

	/**
	 * @param jlblAgrupamiento the jlblAgrupamiento to set
	 */
	public void setJlblAgrupamiento(JTextArea jlblAgrupamiento) {
		this.jlblAgrupamiento = jlblAgrupamiento;
	}

	/**
	 * @return the jlblEtiSuperLLave
	 */
	public JLabel getJlblEtiSuperLLave() {
		return jlblEtiSuperLLave;
	}

	/**
	 * @param jlblEtiSuperLLave the jlblEtiSuperLLave to set
	 */
	public void setJlblEtiSuperLLave(JLabel jlblEtiSuperLLave) {
		this.jlblEtiSuperLLave = jlblEtiSuperLLave;
	}

	/**
	 * @return the jlblSuperLLave
	 */
	public JTextArea getJlblSuperLLave() {
		return jlblSuperLLave;
	}

	/**
	 * @param jlblSuperLLave the jlblSuperLLave to set
	 */
	public void setJlblSuperLLave(JTextArea jlblSuperLLave) {
		this.jlblSuperLLave = jlblSuperLLave;
	}

	public JLabel getJlblEtiSubesquemas() {
		return jlblEtiSubesquemas;
	}

	public void setJlblEtiSubesquemas(JLabel jlblEtiSubesquemas) {
		this.jlblEtiSubesquemas = jlblEtiSubesquemas;
	}

	public JTextArea getJlblSubesquemas() {
		return jlblSubesquemas;
	}

	public void setJlblSubesquemas(JTextArea jlblSubesquemas) {
		this.jlblSubesquemas = jlblSubesquemas;
	}

	
	
	
}
