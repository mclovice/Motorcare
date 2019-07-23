package com.example.motorcare;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {

    private Context mContext;
    private ClickHandler mClickHandler;
    private ArrayList<MechanicAdapter> mData;
    private ArrayList<String> mDataId;
    private ArrayList<String> mSelectedItem;
    private View mEmptyView;



    public CatalogAdapter(Context context, ArrayList<MechanicAdapter> data, ArrayList<String> dataId, View emptyView,
                          ClickHandler handler) {


        mContext = context;
        mData = data;
        mDataId = dataId;
        mEmptyView = emptyView;
        mSelectedItem = new ArrayList<>();
        mClickHandler = handler;

    }
    public  void updateEmptyView(){
        if(mData.size()==0)
            mEmptyView.setVisibility(View.VISIBLE);
        else
            mEmptyView.setVisibility(View.GONE);


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder myholder, int i) {
        MechanicAdapter mechanicAdapter = mData.get(i);
        myholder.GNameTextView.setText(((MechanicAdapter) mechanicAdapter).getGarageName());
        myholder.GLocationTextView.setText(((MechanicAdapter) mechanicAdapter).getLocation());
        myholder.GPhone.setText(((MechanicAdapter) mechanicAdapter).getPhone());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        final TextView GNameTextView;
        final TextView GLocationTextView;
        final  TextView GPhone;

        ViewHolder(View itemView){

          super(itemView);
          GNameTextView = itemView.findViewById(R.id.garage_name);
          GLocationTextView = itemView.findViewById(R.id.garage_location);
          GPhone = itemView.findViewById(R.id.garage_phone);

          itemView.setFocusable(true);
          itemView.setOnClickListener(this);
          itemView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View v) {
            mClickHandler.onItemClick(getAdapterPosition());


        }

        @Override
        public boolean onLongClick(View v) {

            return mClickHandler.onItemLongClick(getAdapterPosition());
        }
    }
    interface ClickHandler{
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

}
