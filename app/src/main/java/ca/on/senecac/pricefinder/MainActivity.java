package ca.on.senecac.pricefinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ca.on.senecac.pricefinder.bean.Result;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener{
	
	private final Search search = new Search();
	private ImageButton ibDoSearch;
	private TextView tvSearch;
	private ListView lvResults;
	private Result result = new Result();
	
	final Handler handler = new Handler();
	final Runnable updateItems = new Runnable(){
		public void run(){
			updateItemsInUI();
		}
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    
    protected Result updateResult(String keywords){
    	Result newResult = new Result();
		try {
			newResult = search.doSearch(keywords);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newResult;
    }
    
    protected void updateItemsInUI() {
    	if(0 == result.getItems().size()){
    		Toast.makeText(this, "NO Result", Toast.LENGTH_SHORT).show();
    	}
    	SearchResultsAdapter searchResultsAdapter = new SearchResultsAdapter(this, result.getItems());
        lvResults.setAdapter(searchResultsAdapter);
	}

	public void init(){
    	lvResults = (ListView) findViewById(R.id.lvResults);
    	lvResults.setOnItemClickListener(this);
    	tvSearch = (TextView) findViewById(R.id.tvSeach);
        ibDoSearch = (ImageButton) findViewById(R.id.ibSearch);
        ibDoSearch.setOnClickListener((OnClickListener) this);
    }

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId())
		{
			case R.id.ibSearch :
				final String textToSearch = tvSearch.getText().toString().trim();
				Thread t = new Thread(){
					public void run(){
						result = updateResult(textToSearch);
						handler.post(updateItems);
					}
				};
				Toast.makeText(this,"Searching....",Toast.LENGTH_SHORT).show();
				t.start();
				hideInputMethod();
				break;
		}
	}
	
	private void hideInputMethod(){
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(tvSearch.getWindowToken(), 0);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getItems().get(arg2).getLink()));
		startActivity(intent); 
	}

}