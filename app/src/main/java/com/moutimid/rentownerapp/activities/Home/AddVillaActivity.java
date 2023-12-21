package com.moutimid.rentownerapp.activities.Home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.icu.lang.UCharacter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.moutimid.rentownerapp.R;
import com.moutimid.rentownerapp.model.Property;
import com.moutimid.rentownerapp.model.Rules;
import com.moutimid.rentownerapp.model.UserModel;
import com.moutimid.rentownerapp.model.Villa;
import com.moutimid.rentownerapp.model.VillaAmenities;

import java.util.Objects;

public class AddVillaActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_GALLERY = 111;
    ImageView profile_pic;
    Uri image_profile_str = null;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText areaEditText;
    private EditText bedroomEditText;
    private EditText bathroomEditText;
    private EditText latEditText;
    private EditText lngEditText;
    private EditText locationTitleEditText;
    private EditText country_edit_text;
    private EditText town_edit_text;
    private EditText billEditText;
    private EditText person_edit_text;
    private CheckBox petFriendlyCheckbox;
    private CheckBox smokerFriendlyCheckbox;
    private CheckBox billsIncludedCheckbox;
    private CheckBox airConditioningCheckbox;
    private CheckBox dryerCheckbox;
    private CheckBox kitchenCheckbox;
    private CheckBox furnishedCheckbox;
    private CheckBox gardenCheckbox;
    private CheckBox heatingCheckbox;
    private CheckBox parkingCheckbox;
    private CheckBox tvCheckbox;
    private CheckBox washingMachineCheckbox;
    private CheckBox wifiCheckbox;
    private Spinner fullBathroomSpinner;
    private Spinner steamShowerSpinner;
    private Spinner toiletSpinner;
    private Spinner roomTypeSpinner;
    private Button addButton;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_villa);
        name=Stash.getString("userModel");
        profile_pic = findViewById(R.id.image_view);
        country_edit_text = findViewById(R.id.country_edit_text);
        town_edit_text = findViewById(R.id.town_edit_text);
        nameEditText = findViewById(R.id.name_edit_text);
        descriptionEditText = findViewById(R.id.description_edit_text);
        areaEditText = findViewById(R.id.area_edit_text);
        bedroomEditText = findViewById(R.id.bedroom_edit_text);
        bathroomEditText = findViewById(R.id.bathroom_edit_text);
        latEditText = findViewById(R.id.lat_edit_text);
        lngEditText = findViewById(R.id.lng_edit_text);
        locationTitleEditText = findViewById(R.id.location_title_edit_text);
        billEditText = findViewById(R.id.bill_edit_text);
        petFriendlyCheckbox = findViewById(R.id.pet_friendly_checkbox);
        smokerFriendlyCheckbox = findViewById(R.id.smoker_friendly_checkbox);
        billsIncludedCheckbox = findViewById(R.id.bills_included_checkbox);
        airConditioningCheckbox = findViewById(R.id.air_conditioning_checkbox);
        dryerCheckbox = findViewById(R.id.dryer_checkbox);
        kitchenCheckbox = findViewById(R.id.kitchen_checkbox);
        furnishedCheckbox = findViewById(R.id.furnished_checkbox);
        gardenCheckbox = findViewById(R.id.garden_checkbox);
        heatingCheckbox = findViewById(R.id.heating_checkbox);
        parkingCheckbox = findViewById(R.id.parking_checkbox);
        tvCheckbox = findViewById(R.id.tv_checkbox);
        washingMachineCheckbox = findViewById(R.id.washing_machine_checkbox);
        wifiCheckbox = findViewById(R.id.wifi_checkbox);
        fullBathroomSpinner = findViewById(R.id.full_bathroom_spinner);
        steamShowerSpinner = findViewById(R.id.steam_shower_spinner);
        toiletSpinner = findViewById(R.id.toilet_spinner);
        roomTypeSpinner = findViewById(R.id.room_type_spinner);
        addButton = findViewById(R.id.btnAddOwner);
        person_edit_text = findViewById(R.id.person_edit_text);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if no checkbox is unchecked
//                if (!petFriendlyCheckbox.isChecked() || !smokerFriendlyCheckbox.isChecked() || !billsIncludedCheckbox.isChecked() ||
//                        !airConditioningCheckbox.isChecked() || !dryerCheckbox.isChecked() || !kitchenCheckbox.isChecked() ||
//                        !furnishedCheckbox.isChecked() || !gardenCheckbox.isChecked() || !heatingCheckbox.isChecked() ||
//                        !parkingCheckbox.isChecked() || !tvCheckbox.isChecked() || !washingMachineCheckbox.isChecked() ||
//                        !wifiCheckbox.isChecked()) {
//                    Toast.makeText(AddVillaActivity.this, "Please give complete details", Toast.LENGTH_SHORT).show();
//                    return;
//                    // Handle the case when a checkbox is unchecked
//                    // Add your logic here
//                }

                // Check if no EditText is empty
                if (nameEditText.getText().toString().isEmpty() || descriptionEditText.getText().toString().isEmpty() ||
                        areaEditText.getText().toString().isEmpty() || bedroomEditText.getText().toString().isEmpty() ||
                        bathroomEditText.getText().toString().isEmpty() || latEditText.getText().toString().isEmpty() ||
                        lngEditText.getText().toString().isEmpty() || locationTitleEditText.getText().toString().isEmpty() ||
                        billEditText.getText().toString().isEmpty() ||  person_edit_text.getText().toString().isEmpty() || country_edit_text.getText().toString().isEmpty() || town_edit_text.getText().toString().isEmpty()) {
                    Toast.makeText(AddVillaActivity.this, "Please give complete details", Toast.LENGTH_SHORT).show();
                    return;
                    // Handle the case when an EditText is empty
                    // Add your logic here
                }

                // Store each value in each variable and assign values to variables of checkboxes
                String name = nameEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String area = areaEditText.getText().toString();
                String bedroom = bedroomEditText.getText().toString();
                String bathroom = bathroomEditText.getText().toString();
                String lat = latEditText.getText().toString();
                String lng = lngEditText.getText().toString();
                String locationTitle = locationTitleEditText.getText().toString();
                String bill = billEditText.getText().toString();
                boolean isPetFriendly = petFriendlyCheckbox.isChecked();
                boolean isSmokerFriendly = smokerFriendlyCheckbox.isChecked();
                boolean areBillsIncluded = billsIncludedCheckbox.isChecked();
                boolean hasAirConditioning = airConditioningCheckbox.isChecked();
                boolean hasDryer = dryerCheckbox.isChecked();
                boolean hasKitchen = kitchenCheckbox.isChecked();
                boolean isFurnished = furnishedCheckbox.isChecked();
                boolean hasGarden = gardenCheckbox.isChecked();
                boolean hasHeating = heatingCheckbox.isChecked();
                boolean hasParking = parkingCheckbox.isChecked();
                boolean hasTV = tvCheckbox.isChecked();
                boolean hasWashingMachine = washingMachineCheckbox.isChecked();
                boolean hasWiFi = wifiCheckbox.isChecked();
                String fullBathroom = fullBathroomSpinner.getSelectedItem().toString();
                String steamShower = steamShowerSpinner.getSelectedItem().toString();
                String toilet = toiletSpinner.getSelectedItem().toString();
                String roomType = roomTypeSpinner.getSelectedItem().toString();

// Create a new Property object
                Property property = new Property();

// Set the property details
                property.name = nameEditText.getText().toString();
                property.description = descriptionEditText.getText().toString();
                property.area = Integer.parseInt(areaEditText.getText().toString());
                property.bedroom = Integer.parseInt(bedroomEditText.getText().toString());
                property.bathRoom = Integer.parseInt(bathroomEditText.getText().toString());
                property.lat = Double.parseDouble(latEditText.getText().toString());
                property.lng = Double.parseDouble(lngEditText.getText().toString());
                property.title = locationTitleEditText.getText().toString();
                property.bill = Integer.parseInt(billEditText.getText().toString());
                Rules houseRules = new Rules();

// Set the house rules
//                HouseRules houseRules = new HouseRules();
                houseRules.petFriendly = petFriendlyCheckbox.isChecked();
                houseRules.smokerFriendly = smokerFriendlyCheckbox.isChecked();

// Set the property amenities
                VillaAmenities propertyAmenities = new VillaAmenities();
                propertyAmenities.airConditioning = airConditioningCheckbox.isChecked();
                propertyAmenities.dryer = dryerCheckbox.isChecked();
                propertyAmenities.equippedKitchen = kitchenCheckbox.isChecked();
                propertyAmenities.furnished = furnishedCheckbox.isChecked();
                propertyAmenities.garden = gardenCheckbox.isChecked();
                propertyAmenities.heating = heatingCheckbox.isChecked();
                propertyAmenities.parking = parkingCheckbox.isChecked();
                propertyAmenities.tv = tvCheckbox.isChecked();
                propertyAmenities.washingMachine = washingMachineCheckbox.isChecked();
                propertyAmenities.wifi = wifiCheckbox.isChecked();


// Store the property data in Firebase Realtime Database
                Dialog lodingbar = new Dialog(AddVillaActivity.this);
                lodingbar.setContentView(R.layout.loading);
                Objects.requireNonNull(lodingbar.getWindow()).setBackgroundDrawable(new ColorDrawable(UCharacter.JoiningType.TRANSPARENT));
                lodingbar.setCancelable(false);
                lodingbar.show();
                String filePathName = "villas/";
                final String timestamp = "" + System.currentTimeMillis();
                StorageReference storageReference = FirebaseStorage.getInstance().getReference(filePathName + timestamp);
                UploadTask urlTask = storageReference.putFile(image_profile_str);
                Task<Uri> uriTask = urlTask.continueWithTask(task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(AddVillaActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    return storageReference.getDownloadUrl();
                }).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Uri downloadImageUri = task.getResult();
                        if (downloadImageUri != null) {

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            property.town_name = town_edit_text.getText().toString();
                            property.city_name = country_edit_text.getText().toString();
                            property.image = downloadImageUri.toString();
                            property.userName = name;
                            property.no_of_persons = Integer.parseInt(person_edit_text.getText().toString());
                            property.available = "not_available";
                            property.available_dates = "";
                            property.ownerID= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                            property.verified = false;
                            DatabaseReference propertyRef = database.getReference("RentApp").child("Villas");
                            String propertyKey = propertyRef.push().getKey();
                            property.key = propertyKey;
                            propertyRef.child(propertyKey).setValue(property);

                            propertyRef.child(propertyKey).child("PropertyAmenities").setValue(propertyAmenities);
                            propertyRef.child(propertyKey).child("HouseRules").setValue(houseRules).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(AddVillaActivity.this, "Succsessfully added", Toast.LENGTH_SHORT).show();
                                    finish();
                                    lodingbar.dismiss();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddVillaActivity.this, "Error " + e.toString(), Toast.LENGTH_SHORT).show();

                                    lodingbar.dismiss();
                                }
                            });
                        }
                    }
                });
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_GALLERY && resultCode == RESULT_OK) {
            image_profile_str = data.getData();
            profile_pic.setImageURI(image_profile_str);
            profile_pic.setVisibility(View.VISIBLE);
        }
    }

    public void profile_image(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_GALLERY);
    }

    public void BackPress(View view) {
        onBackPressed();
    }
}


