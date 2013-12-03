package edu.upc.eetac.dsa.rodrigo.sampedro.beeter.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.upc.eetac.dsa.rodrigo.sampedro.beeter.android.api.Sting;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StingAdaptar extends BaseAdapter {

	ArrayList<Sting> data;
	LayoutInflater inflater;
	
	public StingAdaptar(Context context, ArrayList<Sting> data) {
		super();
		inflater = LayoutInflater.from(context);
		this.data = data;
	}
	
	private static class ViewHolder {
		TextView tvSubject;
		TextView tvUsername;
		TextView tvDate;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}
	 
	@Override
	public Object getItem(int position) {
		return data.get(position);
	}
	 
	@Override
	public long getItemId(int position) {
		return Long.parseLong(((Sting)getItem(position)).getStingid());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//cada vez qeu hago scroll y subo o bajo lo que hago es mover lso datos no l so TV
		ViewHolder viewHolder = null;
		
		//si nohay nada que reciclar es decir mover datos CV es null
		if (convertView == null) {
			//creamo un viewholder
			convertView = inflater.inflate(R.layout.list_row_sting, null);
			viewHolder = new ViewHolder();
			viewHolder.tvSubject = (TextView) convertView
					.findViewById(R.id.tvSubject);
			viewHolder.tvUsername = (TextView) convertView
					.findViewById(R.id.tvUsername);
			viewHolder.tvDate = (TextView) convertView
					.findViewById(R.id.tvDate);
			//añadimos los datos anivel de vistas
			convertView.setTag(viewHolder);
		} else {
			//reciclamos
			viewHolder = (ViewHolder) convertView.getTag();
		}
		//introducimos los datos en el viewholder
		
		//obtenemos datos
		String subject = data.get(position).getSubject();
		String username = data.get(position).getUsername();
		String date = SimpleDateFormat.getInstance().format(
				data.get(position).getLastModified());
		//representacionde datos
		viewHolder.tvSubject.setText(subject);
		viewHolder.tvUsername.setText(username);
		viewHolder.tvDate.setText(date);
		return convertView;
	}

}
