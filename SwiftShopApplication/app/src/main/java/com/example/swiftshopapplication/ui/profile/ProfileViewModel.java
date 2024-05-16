package com.example.swiftshopapplication.ui.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mName;
    private MutableLiveData<String> mEmail;
    private MutableLiveData<String> mAddress;
    private DatabaseReference databaseReference;

    public ProfileViewModel() {
        mName = new MutableLiveData<>();
        mEmail = new MutableLiveData<>();
        mAddress = new MutableLiveData<>();
        fetchUserData();
    }

    private void fetchUserData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            mName.setValue(user.getDisplayName());
            mEmail.setValue(user.getEmail());
            databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Check if the "address" node exists
                    if (dataSnapshot.hasChild("address")) {
                        String address = dataSnapshot.child("address").getValue(String.class);
                        mAddress.setValue(address);
                    } else {
                        mAddress.setValue("No Address Available");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle onCancelled
                }
            });
        } else {
            mName.setValue("No User Logged In");
            mEmail.setValue("No Email Available");
            mAddress.setValue("No Address Available");
        }
    }


    public void updateUserProfile(String name, String email, String address) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            mName.setValue(name);
                        }
                    });

            user.updateEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            mEmail.setValue(email);
                        }
                    });

            databaseReference.child("address").setValue(address)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            mAddress.setValue(address);
                        }
                    });
        }
    }

    public LiveData<String> getName() {
        return mName;
    }

    public LiveData<String> getEmail() {
        return mEmail;
    }

    public LiveData<String> getAddress() {
        return mAddress;
    }
}
