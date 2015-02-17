package ca.on.senecac.pricefinder.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.on.senecac.pricefinder.bean.Item;
import ca.on.senecac.pricefinder.bean.Result;


public class ResultDeserializer{

	public static Result deserialize(String object) throws JSONException 
	{
		Result result = new Result();
		
		final List<Item> items = new ArrayList<Item>();
		
		JSONObject jSonObject = new JSONObject(object);
		JSONArray jSonObjectArray = jSonObject.getJSONArray("items");
		for(int count = 0; count < jSonObjectArray.length();count++){
			JSONObject jsonItem = (JSONObject) jSonObjectArray.get(count);

			Item item = new Item();
			item.setTitle(jsonItem.getString("title"));
			item.setHtmlTitle(jsonItem.getString("htmlTitle"));
			item.setLink(jsonItem.getString("link"));
			item.setDisplayLink(jsonItem.getString("displayLink"));
			item.setSnippet(jsonItem.getString("snippet"));
			item.setHtmlSnippet(jsonItem.getString("htmlSnippet"));
			
			items.add(item);
		}

		result.setItems(items);
		
		return result;
	}
}
