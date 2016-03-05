package com.example.android;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	private List<Ben>list;
	public LayoutInflater inflater;
	public MyAdapter(Context context){
		inflater=LayoutInflater.from(context);
	}
	public void getlist(List<Ben>list){
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=null;
		ViewHoler viewHoler=null;
		Ben ben=list.get(position);
		int d=Ben.TYPE_RESULT;
		d=ben.getReslut();
		if(convertView==null){
			view=inflater.inflate(R.layout.item, null);
			viewHoler=new ViewHoler();
			viewHoler.lift=(LinearLayout) view.findViewById(R.id.lift);
			viewHoler.rigth=(LinearLayout) view.findViewById(R.id.rigth);
			viewHoler.t_lift=(TextView) view.findViewById(R.id.content_lift);
			viewHoler.t_rigth=(TextView) view.findViewById(R.id.content_rigth);
			view.setTag(viewHoler);
		}else{
			view=convertView;
			viewHoler=(ViewHoler) view.getTag();
		}
		if(ben.getType()==Ben.TYPE_SEND){
			//发送消息的时候，左边隐藏
			viewHoler.lift.setVisibility(View.GONE);
			viewHoler.rigth.setVisibility(View.VISIBLE);
			viewHoler.t_rigth.setText(ben.getContent());
		}
		
		else if(ben.getType()==Ben.TYPE_RESULT){
			//接收消息的时候，左边隐藏
			viewHoler.lift.setVisibility(View.VISIBLE);
			viewHoler.rigth.setVisibility(View.GONE);
			viewHoler.t_lift.setText(ben.getContent());
		}
		return view;
	}
	class ViewHoler{
		LinearLayout lift,rigth;
		TextView t_lift,t_rigth;
	}

}
