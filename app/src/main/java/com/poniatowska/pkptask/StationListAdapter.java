package com.poniatowska.pkptask;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.poniatowska.pkptask.databinding.ActivityMainBinding;
import com.poniatowska.pkptask.databinding.RowStationListBinding;

import java.util.ArrayList;
import java.util.List;

public class StationListAdapter extends RecyclerView.Adapter<StationListAdapter.ViewHolder> {

    private ArrayList<Station> data = new ArrayList<>();
    private OnClickListenerCallBack onClickListenerCallBack;
    private Context context;

    public StationListAdapter(Context context, OnClickListenerCallBack onClickListenerCallBack){
        this.onClickListenerCallBack = onClickListenerCallBack;
        this.context = context;
    }

    @Override
    public StationListAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_station_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StationListAdapter.ViewHolder holder, int position) {
        final Station station = data.get(position);
        holder.nameTV.setText(station.getName());
        holder.typeTV.setText(station.getStationType().name());
        holder.ibnrCodeTV.setText("IBNR: "+station.getIbnrCode());

        if(null != station.getEpaCode()){
            holder.epaCodeTV.setText("EPA: "+station.getEpaCode());
            holder.epaCodeTV.setVisibility(View.VISIBLE);
        }else holder.epaCodeTV.setVisibility(View.INVISIBLE);

        if(station.getStationType().equals(StationType.normalna))
            holder.typeTV.setTextColor(Color.BLUE);
        else
            holder.typeTV.setTextColor(Color.RED);

        holder.itemContainer.setOnClickListener(v ->
                onClickListenerCallBack.onItemClick(station));
    }

    public void setData(ArrayList<Station> data){
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTV, typeTV, ibnrCodeTV, epaCodeTV;
        ConstraintLayout itemContainer;

        ViewHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTV);
            typeTV = itemView.findViewById(R.id.typeTV);
            ibnrCodeTV = itemView.findViewById(R.id.ibnrCodeTV);
            epaCodeTV = itemView.findViewById(R.id.epaCodeTV);
            itemContainer = itemView.findViewById(R.id.itemContainer);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}


