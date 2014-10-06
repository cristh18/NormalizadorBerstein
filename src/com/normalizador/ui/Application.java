package com.normalizador.ui;

/**
 * Bootstraps the application, instantiates the logic objects and kickstart the application
 * @author guillermo
 *
 */
public class Application {
	
	/**
	 * Main application entry point
	 * @param args
	 */
	public static void main(String[] args) {		
		NormalizadorGUI gui = new NormalizadorGUI();
		gui.setVisible(true);
	}

}
