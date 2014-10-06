package com.normalizador.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFileChooser;

/**
 * 
 * @author Cristhian
 *
 */
public class Evento implements ActionListener, KeyListener {

	private NormalizadorGUI normalizadorGUI;

	private String ruta;

	/**
	 * Constante que maneja el evento de cargar xml
	 */
	public static final String ACCION_CARGAR_XML = "cargar xml";
	
	/**
	 * Constante que maneja el evento de volver
	 */
	public static final String ACCION_VOLVER = "volver";

	public Evento(NormalizadorGUI normalizadorGUI) {
		this.normalizadorGUI = normalizadorGUI;
		ruta = "";
	}

	@Override
	public void keyPressed(KeyEvent e) {
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String accion = e.getActionCommand();

		if (accion.equals(ACCION_CARGAR_XML)) {
			if (normalizadorGUI.getAbrirArchivo() == null) {
				normalizadorGUI.setAbrirArchivo(new JFileChooser());
			}

			normalizadorGUI.getAbrirArchivo().setFileSelectionMode(
					JFileChooser.FILES_ONLY);

			int seleccion = normalizadorGUI.getAbrirArchivo().showOpenDialog(
					normalizadorGUI);

			File abre = normalizadorGUI.getAbrirArchivo().getSelectedFile();
			ruta = abre.getAbsolutePath();
			normalizadorGUI.ejecutarNormalizacion();
		}else if (accion.equals(ACCION_VOLVER)) {
			normalizadorGUI.volver();
		}

	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

}
