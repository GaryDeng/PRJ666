package ca.on.senecac.pricefinder;

import ca.on.senecac.pricefinder.bean.Result;
import ca.on.senecac.pricefinder.util.HttpRequest;
import ca.on.senecac.pricefinder.util.ResultDeserializer;
import ca.on.senecac.pricefinder.util.UrlFactory;


public class Search {

	public Result doSearch(String keywords) throws Exception{
		final String urlRequest = UrlFactory.buildUrl(keywords);
		final String jsonObjet = HttpRequest.doRequest(urlRequest);
		return ResultDeserializer.deserialize(jsonObjet);
	}
}
