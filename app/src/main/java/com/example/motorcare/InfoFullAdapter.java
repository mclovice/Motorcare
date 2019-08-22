package com.example.motorcare;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class InfoFullAdapter extends RecyclerView.Adapter<InfoFullAdapter.ViewHolder> {
    private Context mContext;
    private InfoFullAdapter.ClickHandler mClickHandler;
    private ArrayList<Info_RetrieveAdapter_driver> mData;
    private ArrayList<String> mDataId;
    private ArrayList<String> mSelectedItem;
    private View mEmptyView;
    //private final static int REQUEST_CODE_1 =1;
    //private CatalogAdapter.AdapterCallback adapterCallback;



    public InfoFullAdapter(Context context, ArrayList<Info_RetrieveAdapter_driver> data, ArrayList<String> dataId, View emptyView,
                           InfoFullAdapter.ClickHandler handler) {


        mContext = context;
        mData = data;
        mDataId = dataId;
        mEmptyView = emptyView;
        mSelectedItem = new ArrayList<>();
        mClickHandler = handler;
        //this.adapterCallback = onImageClickListener;

    }
    public  void updateEmptyView(){
        if(mData.size()==0)
            mEmptyView.setVisibility(View.VISIBLE);
        else
            mEmptyView.setVisibility(View.GONE);


    }

    @Override
    public InfoFullAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.info_retrieve_adapter_,viewGroup, false);
        return new InfoFullAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder myholder, int i) {
        final Info_RetrieveAdapter_driver info_RetrieveAdapter_driver = mData.get(i);
        myholder.ThemeTextView.setText(((Info_RetrieveAdapter_driver) info_RetrieveAdapter_driver).getTheme());
        myholder.Full_InfoTextView.setText(((Info_RetrieveAdapter_driver) info_RetrieveAdapter_driver).getCarMaintenanceInfo());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        final TextView ThemeTextView;
        final TextView Full_InfoTextView;

        private DatabaseReference mReference;

        ViewHolder(View itemView){

            super(itemView);
            ThemeTextView = itemView.findViewById(R.id.my_info_theme);
            Full_InfoTextView = itemView.findViewById(R.id.my_full_info);

            mReference = FirebaseDatabase.getInstance().getReference().child("MaintenanceInfo");

            //mReference.addChildEventListener(childEventListener);
            mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
//                        Map<String,Object> map = (Map<String, Object>)dataSnapshot.getValue();
//                     if(map.get("CarMaintenaceInfo")!=null){
//                         Full_InfoTextView.setText(map.get("CarMaintenaceInfo").toString());
//
//                        }
//                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

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
//    public static interface AdapterCallback{
//        void onMethodCallback(String imageData);
//    }
//
}

