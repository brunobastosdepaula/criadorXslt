package application;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entities.serviceXml;

public class Program {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File file = new File("c:\\Teste\\ISS SAIDA.xml");
		String xml = "";
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String linha = br.readLine();
			

			while (linha != null) {
				xml += linha.replace("\t", "");
				linha = br.readLine();
			}

			System.out.println(xml);

		} catch (IOException e) {
			e.getMessage();
		}

		
//		Document document = builder.parse(new File("c:\\Teste\\ISS SAIDA.xml"));
		Document document = builder.parse(new ByteArrayInputStream(xml.getBytes()));   
		document.getDocumentElement().normalize();

		
		

		List<String> listTags = new ArrayList<String>();
		List<String> listCaminhos = new ArrayList<String>();


		NodeList nList = document.getElementsByTagName("CompNfse").item(0).getChildNodes();

		serviceXml.listarTagsDoXml(nList, listTags);

//		for(String tag: listTags) {
//			System.out.println(tag);
//		}

		for (String tag : listTags) {

			for (int i = 0; i < document.getElementsByTagName(tag).getLength(); i++) {
				serviceXml.mapearCaminhoDaTag(document.getElementsByTagName(tag).item(i), "", "CompNfse", listCaminhos);
			}
		}

		for (String tag2 : listCaminhos) {
			System.out.println(tag2);
		}

	}
}
