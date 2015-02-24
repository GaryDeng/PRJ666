package ca.on.senecac.pricefinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ca.on.senecac.pricefinder.bean.Item;

public class SearchResultsAdapter extends BaseAdapter {

	private Context context;
	private final List<Item> items;
	
	public SearchResultsAdapter(Context context, List<Item> items){
		this.context = context;
		this.items = items;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout rowLayout;

		if(convertView == null)
		{					
			rowLayout = (RelativeLayout)LayoutInflater.from(context).inflate(R.layout.tutorialinfo, parent, false);
			Item item = (Item)getItem(position);
			
			TextView tvTitle = (TextView)rowLayout.findViewById(R.id.tvTitle);
			tvTitle.setText(item.getTitle());
			
			TextView tvLink = (TextView)rowLayout.findViewById(R.id.tvLink);
			tvLink.setText(item.getLink());
			
			TextView tvSnippet = (TextView)rowLayout.findViewById(R.id.tvSnippet);
			tvSnippet.setText(item.getSnippet());
			convertView = rowLayout;
		}
		
		return convertView;
	}

}
