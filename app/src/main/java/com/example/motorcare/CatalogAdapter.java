package com.example.motorcare;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {

    private Context mContext;
    private ClickHandler mClickHandler;
    private ArrayList<MechanicAdapter> mData;
    private ArrayList<String> mDataId;
    private ArrayList<String> mSelectedItem;
    private View mEmptyView;
    private final static int REQUEST_CODE_1 =1;
    private AdapterCallback adapterCallback;



    public CatalogAdapter(Context context, ArrayList<MechanicAdapter> data, ArrayList<String> dataId, View emptyView,
                          ClickHandler handler, AdapterCallback onImageClickListener) {


        mContext = context;
        mData = data;
        mDataId = dataId;
        mEmptyView = emptyView;
        mSelectedItem = new ArrayList<>();
        mClickHandler = handler;
        this.adapterCallback = onImageClickListener;

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
        final MechanicAdapter mechanicAdapter = mData.get(i);
        myholder.GNameTextView.setText(((MechanicAdapter) mechanicAdapter).getGarageName());
        myholder.GLocationTextView.setText(((MechanicAdapter) mechanicAdapter).getLocation());
        myholder.GPhone.setText(((MechanicAdapter) mechanicAdapter).getPhone());

        myholder.Btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterCallback.onMethodCallback(mechanicAdapter.getPhone());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        final TextView GNameTextView;
        final TextView GLocationTextView;
        final  TextView GPhone;
        final ImageView Btn_call;
        TextView PhoneNumber;
        private DatabaseReference mReference;

        ViewHolder(View itemView){

          super(itemView);
          GNameTextView = itemView.findViewById(R.id.garage_name);
          GLocationTextView = itemView.findViewById(R.id.garage_location);
          GPhone = itemView.findViewById(R.id.garage_phone);
          Btn_call = itemView.findViewById(R.id.callmechanic);
            PhoneNumber = itemView.findViewById(R.id.phone);

            mReference = FirebaseDatabase.getInstance().getReference().child("Mechanics");

           // mReference.addChildEventListener(childEventListener);
            mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                        Map<String,Object> map = (Map<String, Object>)dataSnapshot.getValue();
                        if(map.get("phone")!=null){
                            PhoneNumber.setText(map.get("phone").toString());

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

          itemView.setFocusable(true);
          itemView.setOnClickListener(this);
          itemView.setOnLongClickListener(this);

//            Btn_call.setOnClickListener(new View.OnClickListener() {
//            @Override
//           public void onClick(View v) {
//                Intent intent = new Intent(mContext.getApplicationContext(), CallMechanic.class);
//               intent.putExtra("phone","0759833943");
//
//                mContext.startActivity(intent);
//          }
//        });
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
    public static interface AdapterCallback{
        void onMethodCallback(String imageData);
    }

}
