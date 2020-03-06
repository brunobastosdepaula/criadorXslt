package entities;

import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class serviceXml {

	public static void listarTagsDoXml(NodeList nList, List<String> listTags) {

		for (int i = 0; i < nList.getLength(); i++) {

			if (nList.item(i).getNodeName() != "#text") {

				if (!listTags.contains(nList.item(i).getNodeName())) {
					listTags.add(nList.item(i).getNodeName());
				}
				listarTagsDoXml(nList.item(i).getChildNodes(), listTags);
			}

		}

	}

	public static void mapearCaminhoDaTag(Node nList, String caminho, String nodePai, List<String> caminhos) {

		if (nList.getNodeName() != nodePai) {
			if (caminho == "") {
				caminho = nList.getNodeName();
			} else {
				caminho = nList.getNodeName() + "/" + caminho;
			}
			mapearCaminhoDaTag(nList.getParentNode(), caminho, nodePai, caminhos);
		} else {
			if (!caminhos.contains(caminho)) {
				caminhos.add(caminho);
			}
		}

	}

}
