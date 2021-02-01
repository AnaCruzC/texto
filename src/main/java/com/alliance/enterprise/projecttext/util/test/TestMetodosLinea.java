package com.alliance.enterprise.projecttext.util.test;

import com.alliance.enterprise.projecttext.bean.TextoBean;
import com.alliance.enterprise.projecttext.pojo.Linea;

import junit.framework.TestCase;

public class TestMetodosLinea extends TestCase {
	
	
	private TextoBean textoBean;
	
	public void excenario() {
		textoBean= new TextoBean();
	}
	
	public void testNumeroPalabras() {
		String linea="Esta linea tiene cinco palabras";
		excenario();
		//assertEquals(0, 0);
		assertEquals(5, textoBean.numeroDePalabras(linea));
		
	}
	
	public void testSeparacionDatos() {
		String linea="2\\Esta linea tiene cinco palabras";
		excenario();
		Linea objLinea = textoBean.separacionDatos(linea);
		assertNotNull(objLinea);
	}
	

}
