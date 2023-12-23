package com.moutimid.rentownerapp.activities.Home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moutimid.rentownerapp.R;
import com.moutimid.rentownerapp.dailogues.AvailableCalenderDialogClass;
import com.moutimid.rentownerapp.helper.Config;
import com.moutimid.rentownerapp.model.Villa;

import io.github.glailton.expandabletextview.ExpandableTextView;

public class VillaDetailsActivity extends AppCompatActivity {
    Villa villaModel;
    Button map;
    ImageView image;
    String token_admin;

    ImageView edit;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference propertyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villa_details);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

// Set the status bar background color to white
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.WHITE);
        }
        image = findViewById(R.id.image);
        edit = findViewById(R.id.edit);
        map = findViewById(R.id.show_map);
        villaModel = (Villa) Stash.getObject(Config.currentModel, Villa.class);
        propertyRef = database.getReference("RentApp").child("Villas").child(villaModel.getKey());
        image = findViewById(R.id.image);
        Toolbar toolbar = findViewById(R.id.toolbar_);
        ImageView backpress = findViewById(R.id.backpress);
        ImageView image = findViewById(R.id.image);
        TextView name = findViewById(R.id.name);
        TextView price = findViewById(R.id.price);
        name.setText(villaModel.getName());
        price.setText(villaModel.getBill() + " $/month");
        Glide.with(VillaDetailsActivity.this).load(villaModel.getImage()).into(image);
        TextView noOfBedroom = findViewById(R.id.no_of_bedroom);
        noOfBedroom.setText(villaModel.getBedroom() + "");
        TextView noOfBathroom = findViewById(R.id.no_of_bathroom);
        noOfBathroom.setText(villaModel.getBathRoom() + "");
        TextView descriptionTitle = findViewById(R.id.description_title);
        ExpandableTextView description = findViewById(R.id.description);
        description.setText(villaModel.getDescription());
        TextView availability = findViewById(R.id.availability);
        TextView house_rules = findViewById(R.id.house_rules);
        TextView pet_friendly = findViewById(R.id.pet_friendly);
        TextView smoke_friendly = findViewById(R.id.smoke_friendly);
        TextView no_of_persons = findViewById(R.id.no_of_persons);
        no_of_persons.setText("Available for " + villaModel.no_of_persons + " persons");
        if (villaModel.getHouseRules() != null) {
            house_rules.setVisibility(View.VISIBLE);
        } else {
            house_rules.setVisibility(View.GONE);
        }
        if (villaModel.getHouseRules().isPetFriendly()) {
            pet_friendly.setVisibility(View.VISIBLE);
        } else {
            pet_friendly.setVisibility(View.GONE);
        }
        if (villaModel.getHouseRules().isSmokerFriendly()) {
            smoke_friendly.setVisibility(View.VISIBLE);
        } else {
            smoke_friendly.setVisibility(View.GONE);
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialogue(VillaDetailsActivity.this, villaModel);
            }
        });
        TextView propertyAmenitiesTitle = findViewById(R.id.property_amenities_title);
        LinearLayout dryerLayout = findViewById(R.id.dryer_layout);
        LinearLayout furnishedLayout = findViewById(R.id.furnished_layout);
        LinearLayout equippedKitchenLayout = findViewById(R.id.equipped_kitchen_layout);
        LinearLayout gardenLayout = findViewById(R.id.garden_layout);
        LinearLayout heaterLayout = findViewById(R.id.heater_layout);
        LinearLayout tvLayout = findViewById(R.id.tv_layout);
        LinearLayout wifiLayout = findViewById(R.id.wifi_layout);
        LinearLayout machine_layout = findViewById(R.id.machine_layout);
        LinearLayout parking_layout = findViewById(R.id.parking_layout);
        LinearLayout air_layout = findViewById(R.id.air_layout);
        TextView location = findViewById(R.id.location);
        location.setText(villaModel.getTitle());
// Request for Rent button
        Button requestButton = findViewById(R.id.request_button);

        if (villaModel.getPropertyAmenities() != null) {
            propertyAmenitiesTitle.setVisibility(View.VISIBLE);
        } else {
            propertyAmenitiesTitle.setVisibility(View.GONE);

        }
        if (villaModel.getPropertyAmenities().isDryer()) {
            dryerLayout.setVisibility(View.VISIBLE);
        } else {
            dryerLayout.setVisibility(View.GONE);
        }
        if (villaModel.getPropertyAmenities().isFurnished()) {
            furnishedLayout.setVisibility(View.VISIBLE);
        } else {
            furnishedLayout.setVisibility(View.GONE);
        }
        if (villaModel.getPropertyAmenities().isGarden()) {
            gardenLayout.setVisibility(View.VISIBLE);
        } else {
            gardenLayout.setVisibility(View.GONE);
        }
        if (villaModel.getPropertyAmenities().isEquippedKitchen()) {
            equippedKitchenLayout.setVisibility(View.VISIBLE);
        } else {
            equippedKitchenLayout.setVisibility(View.GONE);
        }
        if (villaModel.getPropertyAmenities().isHeating()) {
            heaterLayout.setVisibility(View.VISIBLE);
        } else {
            heaterLayout.setVisibility(View.GONE);
        }
        if (villaModel.getPropertyAmenities().isWifi()) {
            wifiLayout.setVisibility(View.VISIBLE);
        } else {
            wifiLayout.setVisibility(View.GONE);
        }
        if (villaModel.getPropertyAmenities().isTv()) {
            tvLayout.setVisibility(View.VISIBLE);
        } else {
            tvLayout.setVisibility(View.GONE);
        }
        if (villaModel.getPropertyAmenities().isWashingMachine()) {
            machine_layout.setVisibility(View.VISIBLE);
        } else {
            machine_layout.setVisibility(View.GONE);
        }
        if (villaModel.getPropertyAmenities().isParking()) {
            parking_layout.setVisibility(View.VISIBLE);
        } else {
            parking_layout.setVisibility(View.GONE);
        }
        if (villaModel.getPropertyAmenities().isAirConditioning()) {
            air_layout.setVisibility(View.VISIBLE);
        } else {
            air_layout.setVisibility(View.GONE);
        }

        availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AvailableCalenderDialogClass cdd = new AvailableCalenderDialogClass(VillaDetailsActivity.this);
                cdd.show();
            }
        });
        DatabaseReference z = FirebaseDatabase.getInstance().getReference().child("RentApp").child("Admin");
        z.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get the value of the "token" field
                token_admin = dataSnapshot.child("token").getValue().toString();

                // Use the token as needed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseError", "Failed to read value.", databaseError.toException());
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("latlng", villaModel.getLat() + "   " + villaModel.getLng());

                if (villaModel.getLat() > -90 && villaModel.getLat() < 90 && villaModel.getLng() > -180 && villaModel.getLng() < 180) {
                    Intent intent = new Intent(VillaDetailsActivity.this, MapActivity.class);
                    intent.putExtra("lat", villaModel.getLat());
                    intent.putExtra("lng", villaModel.getLng());
                    intent.putExtra("name", villaModel.getName());
                    startActivity(intent);

                } else {
                    Toast.makeText(VillaDetailsActivity.this, "Invalid Coordinates to show marker", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void onBack(View view) {
        onBackPressed();
    }

    public void showCustomDialogue(Context context, Villa villa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialogue_layout, null);
        builder.setView(view);

        final EditText editText1 = view.findViewById(R.id.editText1);
        final EditText editText2 = view.findViewById(R.id.editText2);

        editText1.setText(villa.getBill() + "");
        editText2.setText(villa.no_of_persons + "");

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle button click
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();
                propertyRef.child("bill").setValue(Integer.parseInt(text1));
                propertyRef.child("no_of_persons").setValue(Integer.parseInt(text2)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.dismiss();
                        finish();
                    }
                });
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle button click
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}