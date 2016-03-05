package com.example.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView listView;
	private Handler handler=new Handler();
	private MyAdapter adapter;
	private EditText input;
	private List<Ben>list=new ArrayList<Ben>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//new HttpThread().start();
		listView=(ListView) findViewById(R.id.listview);
		adapter=new MyAdapter(this);
		input=(EditText) findViewById(R.id.input);
		Toast.makeText(this, "亲，记得打开网络哟！", 1).show();
		findViewById(R.id.send).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new HttpThread(listView, input, MainActivity.this, handler, adapter).start();
				
			}
		});
	}

}
