package com.moutimid.rentownerapp.activities.Home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.moutimid.rentownerapp.R;
import com.moutimid.rentownerapp.dailogues.AvailableCalenderDialogClass;
import com.moutimid.rentownerapp.helper.Config;
import com.moutimid.rentownerapp.model.Villa;

public class VillaDetailsActivity extends AppCompatActivity {
    Villa villaModel;
    TextView map;
    ImageView favourite_img, unfavourite_img, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villa_details);
        villaModel = (Villa) Stash.getObject(Config.currentModel, Villa.class);
        image = findViewById(R.id.image);
        map = findViewById(R.id.map);
        unfavourite_img = findViewById(R.id.unfavourite);
        favourite_img = findViewById(R.id.favourite);
        // Assign IDs to views
        Toolbar toolbar = findViewById(R.id.toolbar_);
        ImageView backpress = findViewById(R.id.backpress);
        TextView title = findViewById(R.id.title);
        ImageView image = findViewById(R.id.image);
        TextView name = findViewById(R.id.name);
        TextView price = findViewById(R.id.price);
        name.setText(villaModel.getName());
        price.setText(villaModel.getBill() + " $/month");
        Glide.with(VillaDetailsActivity.this).load(villaModel.getImage()).into(image);
        LinearLayout roomTypeLayout = findViewById(R.id.room_type_layout);
        TextView roomType = findViewById(R.id.room_type);
        LinearLayout roomAreaLayout = findViewById(R.id.room_area_layout);
        TextView roomArea = findViewById(R.id.room_area);
        roomArea.setText(villaModel.getArea()+"m²");
        LinearLayout noOfBedroomLayout = findViewById(R.id.no_of_bedroom_layout);
        TextView noOfBedroom = findViewById(R.id.no_of_bedroom);
        noOfBedroom.setText(villaModel.getBedroom()+"");
        LinearLayout noOfBathroomLayout = findViewById(R.id.no_of_bathroom_layout);
        TextView noOfBathroom = findViewById(R.id.no_of_bathroom);
        noOfBathroom.setText(villaModel.getBathRoom()+"");
        TextView descriptionTitle = findViewById(R.id.description_title);
        TextView description = findViewById(R.id.description);
        description.setText(villaModel.getDescription());
        TextView availability = findViewById(R.id.availability);
        TextView billIncluded = findViewById(R.id.bill_included);
        if (villaModel.isBills_included()) {
            billIncluded.setText("Included");
        } else {
            billIncluded.setText("Not Included");
        }
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
        TextView priceTitle = findViewById(R.id.price_title);
        TextView monthlyRent = findViewById(R.id.monthly_rent);
        monthlyRent.setText(villaModel.getBill() + " $/month");
        TextView bills = findViewById(R.id.bills);
        TextView included = findViewById(R.id.included);
        TextView location = findViewById(R.id.location);
        TextView house_rules = findViewById(R.id.house_rules);
        TextView pet_friendly = findViewById(R.id.pet_friendly);
        TextView smoke_friendly = findViewById(R.id.smoke_friendly);
        if (villaModel.getHouseRules() != null) {
            house_rules.setVisibility(View.VISIBLE);
        } else {
            house_rules.setVisibility(View.GONE);
        }
        if (villaModel.getHouseRules().isPetFriendly()) {
            pet_friendly.setVisibility(View.VISIBLE);
        } else {
            pet_friendly.setVisibility(View.GONE);
        }    if (villaModel.getHouseRules().isSmokerFriendly()) {
            smoke_friendly.setVisibility(View.VISIBLE);
        } else {
            smoke_friendly.setVisibility(View.GONE);
        }
        if (villaModel.isBills_included()) {
            included.setText("Included");
        } else {
            included.setText("Not Included");
        }

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


    }

    public void onBack(View view) {
        onBackPressed();
    }
}