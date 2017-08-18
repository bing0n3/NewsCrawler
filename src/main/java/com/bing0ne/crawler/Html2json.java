package com.bing0ne.crawler;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Html2json {
	public Html2json(){}
	
	private static boolean ENABLE_JAVASCRIPT = false;
	
	public static String special_sanitizer(String html){
		html = html.replaceAll("<g:plusone>(?:.+?)?<\\/g:plusone>","");
		html = html.replaceAll("\"title","\" title");
		return html;
	}
	
	public static String sanitize_html(String ahtml){
		String html = new String(ahtml);
		html = html.replaceAll("(?:<!.+?>)",""); // Remove Doctype and comments
		
		html = html.replaceAll("(?<!\\\\)\"", "\\\\\"");
		if(!ENABLE_JAVASCRIPT)
			html = html.replaceAll("<script(?:.+?)<\\/script>","");
		html = special_sanitizer(html);
		html = "<div id='html_wrap'>"+html+"</div>";
		
		return html;
	}
	
	public static String getJSON(String html){

		return convert(sanitize_html(html));

	}


	public static String convert(String html) {
		StringBuilder json = new StringBuilder("{");
		Document document = Jsoup.parse(html);
		Elements elements = document.body().select("*");
		for(Element element:elements){
			if(element.ownText().length() == 0){
				continue;
			} else {
				json.append("\""+element.tagName()+"\":");
				json.append("\""+element.ownText()+"\",");
			}
		}

		json.deleteCharAt(json.length() -1);
        if(json.length() != 0)
            json.append("}");

		return json.toString();
	}

	public static void main(String[] args) {
		String html = "<div><a>fff</a></div><h1>bbb</h1>";
		System.out.println(getJSON(html));
	}

}
