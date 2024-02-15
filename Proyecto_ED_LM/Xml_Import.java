/**
 * Xml_Import
 * 
 * Importa un documento xml y lo muestra por pantalla.
 * El xml contiene monumentos de Zaragoza ofrecidos en https://www.zaragoza.es/sede/
 * @author Dpto. Informática CPIFP Bajo Aragón
 * @version 1.0, 2024/02/01
 * @see https://juanjosecanbus.wordpress.com/2014/10/05/introduccion-a-la-api-dom-de-java-ad/
 * @see https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/org/w3c/dom/package-summary.html
 * @see https://docs.oracle.com/javase/8/docs/api/index.html?javax/xml/parsers/package-summary.html
 */

import java.net.*;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.*; 


public class Xml_Import {

	public static void main(String[] args) { 
		
		// Objeto de la clase URL. Nos permite realizar una conexión a una URL
		URL url=null;
		
		
		String strUrl = null;
		int n;
		Scanner leer = new Scanner(System.in);
		
		// Pide el número de monumentos a mostar
		System.out.print("Número monumentos: ");
		n = leer.nextInt();
		leer.close();
		
		// Crea el String con la URL a conectarse (archivo .xml)
		strUrl = ("https://www.zaragoza.es/sede/servicio/monumento.xml?srsname=wgs84&rows=" + n + "&fl=id,title,description");
		
		// Conexión a la URL
		try {
			url = new URL (strUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
						
		try {
			
			// Crea un Document que recibirá el contenido de la URL (archivo .xml)
			//   Genera un árbol de objetos que representan cada elemento del XML
			DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
			DocumentBuilder creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
			Document documento = creadorDocumento.parse(url.openStream());
			
			// Accede al nodo raíz del documento y lo normaliza eliminando nodos vacios
			documento.getDocumentElement().normalize();
			
			// Lista de nodos que corresponden a los elementos "monumento"
			NodeList listaMonumentos = documento.getElementsByTagName("monumento");
			
			// Recorre la lista de nodos para mostrarlos
			for (int temp = 0; temp < listaMonumentos.getLength(); temp++) {
				System.out.println("----------------------");
	            Node nodo = listaMonumentos.item(temp);
	                     
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {

	                Element elemento = (Element) nodo;

	                System.out.println("id: \t" + elemento.getElementsByTagName("id").item(0).getTextContent() + "\n");
	                System.out.println("Titulo: \t" + elemento.getElementsByTagName("title").item(0).getTextContent());
	                System.out.println("Descripción: \t" + elemento.getElementsByTagName("description").item(0).getTextContent() + "\n");
	                
	            }
	            
			} // fin for recorre nodos
			
			

		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} // Fin de main
	
} // Fin Xml_Import
