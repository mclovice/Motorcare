package com.example.motorcare;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AvailableMechanics extends AppCompatActivity {
    private ArrayList<MechanicAdapter> mData;
    private ArrayList<String> mDataId;
    private CatalogAdapter mAdapter;
    private ActionMode mActionMode;

    private DatabaseReference mReference;

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            mData.add(dataSnapshot.getValue(MechanicAdapter.class));
            mDataId.add(dataSnapshot.getKey());
            mAdapter.updateEmptyView();
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            int mechanic = mDataId.indexOf(dataSnapshot.getKey());
            mData.set(mechanic, dataSnapshot.getValue(MechanicAdapter.class));
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
        setContentView(R.layout.activity_mechanic);

        mData = new ArrayList<>();
        mDataId = new ArrayList<>();
        mReference = FirebaseDatabase.getInstance().getReference().child("Mechanics");
        mReference.addChildEventListener(childEventListener);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        View emptyView = findViewById(R.id.empty_view);
        mAdapter = new CatalogAdapter(this, mData, mDataId, emptyView, new CatalogAdapter.ClickHandler() {
            @Override
            public void onItemClick(int position) {

                if (mActionMode != null) {

//                    mAdapter.toggleSelection(mDataId.get(position));
//                    if (mAdapter.selectionCount()==0)
//                        mActionMode.finish();
//                    else
//                        mActionMode.invalidate();
//                    return;
                }

                String pet = mData.get(position).toString();
                Toast.makeText(AvailableMechanics.this, pet, Toast.LENGTH_SHORT).show();
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
