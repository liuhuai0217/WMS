package com._520it.generator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;

public class XmlUtil {

	/**
	 * 给XML文件追加一段内容
	 * @param xml			XML文件对象
	 * @param appendingXml	需要插入的内容
	 * @throws Exception
     */
	public static void appendXML(File xml, String appendingXml) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xml);

		Document flagment = DocumentHelper.parseText(appendingXml);
		Element flagEle = flagment.getRootElement();
		flagEle.setQName(new QName(flagEle.getName(), doc.getRootElement()
				.getNamespace()));
		if (flagEle.elements().size() > 0) {
			for (Object c : flagEle.elements()) {
				Element cel = (Element) c;
				cel.setQName(new QName(cel.getName(), flagEle.getNamespace()));
			}
		}
		doc.getRootElement().add(flagEle);

		XMLWriter writer = new XMLWriter(new FileWriter(xml));
		writer.write(doc.getRootElement());
		writer.close();
	}
}
