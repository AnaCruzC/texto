package com.alliance.enterprise.projecttext.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.alliance.enterprise.projecttext.pojo.Linea;
import com.alliance.enterprise.projecttext.util.constans.Constants;
import com.alliance.enterprise.projecttext.util.exceptions.FormatoException;

@ManagedBean
@RequestScoped
public class TextoBean {
	
	
	
	 private String text;
	 
	 private String textConverte;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTextConverte() {
		return textConverte;
	}

	public void setTextConverte(String textConverte) {
		this.textConverte = textConverte;
	}
	
	public void procesarTexto()  {
        String[] parts = text.split(Constants.LINE_BREAK);
        List<Linea> listaLineas = new ArrayList<>();
        for (String linea : parts) {
			boolean validacion=Pattern.matches(Constants.VALIDATION_REGEX, linea);                                                                     
		     if (validacion) {
		         listaLineas.add(separacionDatos(linea));
		     } else {
		    	  new FormatoException("Error");                                                                              
		     }
		}
        textConverte=TextoSalida(listaLineas);
        System.out.println(textConverte);
    }
	
	public Linea separacionDatos(String linea) {
		Linea objlinea= new Linea();
		String dato1=Constants.BLANK;
		String dato2=Constants.BLANK;
		int posicion=0;
        posicion=linea.indexOf(Constants.BACK_SLASH);
        dato1=linea.substring(0,posicion);
        dato2=linea.substring(posicion);
        dato2=dato2.replaceAll(Constants.REGEX_ONLY_LETTER, Constants.BLANK);
        objlinea.setNumPalabras(Integer.parseInt(dato1));
        objlinea.setTexto(dato2);
        return objlinea;
	}
	
	
	public String TextoSalida(List<Linea> lista) {
		String textoSalida=Constants.BLANK;
		for (Linea linea : lista) {
			if(linea.getNumPalabras() == numeroDePalabras(linea.getTexto()) ) {
				textoSalida=textoSalida+linea.getTexto()+ Constants.BACK_SLASH +Constants.TRUE; 
			}else {
				textoSalida=textoSalida+linea.getTexto()+ Constants.BACK_SLASH +Constants.FALSE;
			}
			textoSalida=textoSalida+Constants.LINE_BREAK;
		}
		return textoSalida;
	}
	
	public int numeroDePalabras(String linea) {
		String[] parts = linea.split(Constants.SPACE);
		return parts.length;
	}
}
