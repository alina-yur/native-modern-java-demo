package dev.nipafx.demo.modern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Util {


	public static String join(String format, Object... args) {
		return String.format(format, args);
	}


	public static Document asHTML(String format, Object... args) {
		String htmlString = String.format(format, args);
		return Jsoup.parse(htmlString);
	}
}
