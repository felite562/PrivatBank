package com.luba.todo.privatbank.parser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luba.todo.privatbank.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ModelRecycler> dataModelArrayList;

    public ListAdapter(Context ctx, ArrayList<ModelRecycler> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.MyViewHolder holder, int position) {

        holder.ccy.setText(dataModelArrayList.get(position).getCcy());
        holder.base_ccy.setText(dataModelArrayList.get(position).getbase_ccy());
        holder.buy.setText(dataModelArrayList.get(position).getBuy());
        holder.sale.setText(dataModelArrayList.get(position).getSale());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

      TextView ccy,base_ccy, buy, sale;


        public MyViewHolder(View itemView) {
            super(itemView);

          ccy = (TextView) itemView.findViewById(R.id.ccy);
            base_ccy = (TextView) itemView.findViewById(R.id.baseCcy);
            buy = (TextView) itemView.findViewById(R.id.buy);
            sale = (TextView) itemView.findViewById(R.id.sale);
        }

    }
}
