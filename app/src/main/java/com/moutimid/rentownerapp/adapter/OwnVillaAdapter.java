package com.moutimid.rentownerapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moutimid.rentownerapp.R;
import com.moutimid.rentownerapp.activities.Home.VillaDetailsActivity;
import com.moutimid.rentownerapp.dailogues.CalenderDialogClass;
import com.moutimid.rentownerapp.helper.Config;
import com.moutimid.rentownerapp.model.Villa;

import java.util.ArrayList;
import java.util.List;

public class OwnVillaAdapter extends RecyclerView.Adapter<OwnVillaAdapter.GalleryPhotosViewHolder> {


    Context ctx;
    List<Villa> productModels;
    Activity activity;

    public OwnVillaAdapter(Activity activity, Context ctx, List<Villa> productModels) {
        this.ctx = ctx;
        this.productModels = productModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public GalleryPhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.own_villa, parent, false);
        return new GalleryPhotosViewHolder(view);
    }

    public void filterList(ArrayList<Villa> filterlist) {
        productModels = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryPhotosViewHolder holder, final int position) {
        Villa villa = productModels.get(position);
        holder.villa_name.setText(villa.getName());
        if (villa.getAvailable().equals("not_available")) {
            holder.not_available.setChecked(true);
        } else {
            holder.available.setChecked(true);
        }
        holder.available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stash.put(Config.currentModel, villa);
                CalenderDialogClass calenderDialogClass = new CalenderDialogClass(activity);
                calenderDialogClass.show();
            }
        });
        holder.not_available.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    Stash.put(Config.currentModel, villa);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    Villa villaModel = (Villa) Stash.getObject(Config.currentModel, Villa.class);
                    DatabaseReference propertyRef = database.getReference("RentApp").child("Villas");
                    propertyRef.child(villaModel.getKey()).child("available").setValue("not_available".toString());

                }
            }
        });
        Glide.with(ctx).load(villa.getImage()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stash.put(Config.currentModel, villa);
                ctx.startActivity(new Intent(ctx, VillaDetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class GalleryPhotosViewHolder extends RecyclerView.ViewHolder {

        TextView villa_name, bill_included;
        ImageView image;

        RadioButton not_available, available;

        public GalleryPhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            villa_name = itemView.findViewById(R.id.villa_name);
            image = itemView.findViewById(R.id.image);
            bill_included = itemView.findViewById(R.id.bill_included);
            not_available = itemView.findViewById(R.id.not_available);
            available = itemView.findViewById(R.id.available);
        }
    }
}
