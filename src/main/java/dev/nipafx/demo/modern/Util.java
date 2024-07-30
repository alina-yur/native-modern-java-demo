package dev.nipafx.demo.modern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Util {

	public static String join(StringTemplate stringTemplate) {
		return stringTemplate.interpolate();
	}

	public static Document asHTML(StringTemplate stringTemplate) {
		return Jsoup.parse(stringTemplate.interpolate());
	}

}
