package ca.on.senecac.pricefinder.util;

import java.net.URLEncoder;

public class UrlFactory {

	private static final String url = "https://www.googleapis.com/customsearch/v1";
	private static final String cx = "003280839564340620705:6kudjqdnrqw";
	// android key:  
	//private static final String key = "AIzaSyADOKg4m52lFJhWycNdapNnSbDYbN8IVoI";
	//browser key:
	//private static final String key = "AIzaSyBk6a_2lTL0wHg8Uwnb5da-lcV_76nX-nY";
	
	//server key:
	private static final String key = "AIzaSyDaddrWA3hQjGCvJ7UrumwQs_64WMg-cok";
	
	public static String buildUrl(String keywords) throws Exception{		
		return url + "?key=" + key + "&cx=" + cx + "&q=" + URLEncoder.encode(keywords)+"&alt=json";
	}
	
}

