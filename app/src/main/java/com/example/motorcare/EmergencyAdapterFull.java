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

public class EmergencyAdapterFull extends RecyclerView.Adapter<EmergencyAdapterFull.ViewHolder> {
    private Context mContext;
    private EmergencyAdapterFull.ClickHandler mClickHandler;
    private ArrayList<Emerge_Retrieve_getters> mData;
    private ArrayList<String> mDataId;
    private ArrayList<String> mSelectedItem;
    private View mEmptyView;
    //private final static int REQUEST_CODE_1 =1;
    //private CatalogAdapter.AdapterCallback adapterCallback;



    public EmergencyAdapterFull(Context context, ArrayList<Emerge_Retrieve_getters> data, ArrayList<String> dataId, View emptyView,
                                EmergencyAdapterFull.ClickHandler handler) {


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
    public EmergencyAdapterFull.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.emerge_retrieve_mechanic,viewGroup, false);
        return new EmergencyAdapterFull.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder myholder, int i) {
        final Emerge_Retrieve_getters emerge_retrieve_getters = mData.get(i);
        myholder.ProblemTextView.setText(((Emerge_Retrieve_getters) emerge_retrieve_getters).getProblem());
        myholder.EmergencyLocationTextView.setText(((Emerge_Retrieve_getters) emerge_retrieve_getters).getLocation());
        myholder.ImmediateContactTextView.setText(((Emerge_Retrieve_getters) emerge_retrieve_getters).getContact());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        final TextView ProblemTextView;
        final TextView EmergencyLocationTextView;
        final TextView ImmediateContactTextView;

        private DatabaseReference mReference;

        ViewHolder(View itemView){

            super(itemView);
            ProblemTextView = itemView.findViewById(R.id.problem_name_mech);
            EmergencyLocationTextView = itemView.findViewById(R.id.problem_location_name_mech);
            ImmediateContactTextView = itemView.findViewById(R.id.problem_immediate_contact_mech);

            mReference = FirebaseDatabase.getInstance().getReference().child("Emergencies");

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

