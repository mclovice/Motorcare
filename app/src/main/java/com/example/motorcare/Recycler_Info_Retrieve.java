package com.example.motorcare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Recycler_Info_Retrieve extends AppCompatActivity {
    private ArrayList<Info_RetrieveAdapter_driver> mData;
    private ArrayList<String> mDataId;
    private InfoFullAdapter mAdapter;
    private ActionMode mActionMode;
   // TextView PhoneNumber;
    //ImageView CallImage;
    //private CatalogAdapter.AdapterCallback onImageClickListener;


    private DatabaseReference mReference;

    public Recycler_Info_Retrieve() {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            mData.add(dataSnapshot.getValue(Info_RetrieveAdapter_driver.class));
            mDataId.add(dataSnapshot.getKey());
            mAdapter.updateEmptyView();
            mAdapter.notifyDataSetChanged();

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            int mechanic = mDataId.indexOf(dataSnapshot.getKey());
            mData.set(mechanic, dataSnapshot.getValue(Info_RetrieveAdapter_driver.class));
            mAdapter.notifyDataSetChanged();

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            int mechanic = mDataId.indexOf(dataSnapshot.getKey());
            mDataId.remove(mechanic);
            mData.remove(mechanic);
            mAdapter.updateEmptyView();
            mAdapter.notifyDataSetChanged();

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__info__retrieve);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Car Maintenance info");
        //PhoneNumber = findViewById(R.id.phone);
        //CallImage = findViewById(R.id.callmechanic);

        mData = new ArrayList<>();
        mDataId = new ArrayList<>();
        mReference = FirebaseDatabase.getInstance().getReference().child("MaintenanceInfo");
        mReference.addChildEventListener(childEventListener);
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String,Object> map = (Map<String, Object>)dataSnapshot.getValue();
        //            if(map.get("phone")!=null){
         //               PhoneNumber.setText(map.get("phone").toString());

      //              }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        RecyclerView recyclerView = findViewById(R.id.info_recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        View emptyView = findViewById(R.id.empty_view_info_retrieve);
        mAdapter = new InfoFullAdapter(this, mData, mDataId, emptyView, new InfoFullAdapter.ClickHandler() {
            @Override
            public void onItemClick(int position) {

                if (mActionMode != null) {

                    //                    mAdapter.toggleSelection(mDataId.get(position));
                    //                    if (mAdapter.selectionCount()==0)
                    //                        mActionMode.finish();
                    //                    else
                    //                        mActionMode.invalidate();

                    return;
                }

                String pet = mData.get(position).toString();
                Toast.makeText(Recycler_Info_Retrieve.this, pet, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(int position) {

                if (mActionMode != null) return false;

                // mAdapter.toggleSelection(mDataId.get(position));
                // mActionMode = AvailableMechanics.this.startSupportActionMode(mActionModeCallBack);
                return true;

            }
        });

        recyclerView.setAdapter(mAdapter);

//        CallImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AvailableMechanics.this, CallMechanic.class);
//                intent.putExtra("phone","This is contact");
//                startActivity(intent);
//            }
//        });
    }

    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.mymenu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {


            // mActionMode = null;
            //  mAdapter.

        }


    };
}
