package com.moutimid.rentownerapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutimid.rentownerapp.R;
import com.moutimid.rentownerapp.activities.Authentication.LoginActivity;
import com.moutimid.rentownerapp.helper.Constants;
import com.moutimid.rentownerapp.model.UserModel;

public class ProfileFragment extends Fragment {
    TextView name, dob, email, phone_number;
    String userID;
    Button logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile2, container, false);
        logout = view.findViewById(R.id.logout);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phone_number = view.findViewById(R.id.phone_number);
        userID = Stash.getString("userID");
        Constants.OwnerReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userNew = snapshot.getValue(UserModel.class);
                name.setText(userNew.getName());
                email.setText(userNew.getEmail());
                phone_number.setText(userNew.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                getActivity().startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finishAffinity();
            }
        });

        return view;
    }


}