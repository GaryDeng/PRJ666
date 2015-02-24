package ca.on.senecac.pricefinder.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
            JSONObject pageMapJson = new JSONObject(jsonItem.getString("pagemap").toString());
            JSONArray productJsonObjectArray = pageMapJson.getJSONArray("product");
            JSONArray offerJsonObjectArray = pageMapJson.getJSONArray("offer");
            JSONArray metatagsJsonObjectArray = pageMapJson.getJSONArray("metatags");
            for(int i = 0; i < productJsonObjectArray.length();i++){
                JSONObject product = (JSONObject) productJsonObjectArray.get(i);
                Log.i("product-json",product.toString());
                /*
                * {
                    "name":"iPhone 4s 8GB - White - 2 Year Agreement",
                    "model":"IPHONE 4S 8GB BLK",
                    "productid":"10270075",
                    "image":"http:\/\/www.bestbuy.ca\/multimedia\/Products\/500x500\/102\/10270\/10270075.jpg"
                  }
                * */
            }
            for(int j = 0; j < offerJsonObjectArray.length();j++){
                JSONObject offer = (JSONObject) offerJsonObjectArray.get(j);
                Log.i("offer-json",offer.toString());
                /*
                * {
                   "pricecurrency":"CAD",
                   "price":"$0.00",
                   "availability":"Sold Out"
                }
                * */
            }

            for(int k = 0; k < metatagsJsonObjectArray.length();k++){
                JSONObject metatag = (JSONObject) metatagsJsonObjectArray.get(k);
                Log.i("metatag-json",metatag.toString());
                /*
                * {
                   "og:title":"iPhone 4s 8GB - White - 2 Year Agreement",
                   "og:type":"product",
                   "og:url":"http:\/\/www.bestbuy.ca\/en-CA\/product\/apple-iphone-4s-8gb-white-2-year-agreement-iphone-4s-8gb-blk\/10270075.aspx",
                   "og:image":"http:\/\/www.bestbuy.ca\/multimedia\/Products\/100x100\/102\/10270\/10270075.jpg",
                   "og:site_name":"Best Buy Canada",
                   "og:description":"With a gorgeous 3.5\" Retina display, 8MP iSight camera and iOS 7 running the show, the iPhone 4S will be nearly impossible to put down. It also features Siri, who can help you manage your entire life with the press of a button. Free shipping on orders over $20.",
                   "fb:admins":"100002191965735"
                }
                * */
            }

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
