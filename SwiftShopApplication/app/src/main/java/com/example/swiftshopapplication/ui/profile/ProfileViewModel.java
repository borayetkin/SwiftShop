package com.example.swiftshopapplication.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mName;
    private MutableLiveData<String> mEmail;

    public ProfileViewModel() {
        mName = new MutableLiveData<>();
        mEmail = new MutableLiveData<>();

        fetchUserData();
    }

    private void fetchUserData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Check if the user's name is available
            String name = user.getDisplayName(); // This could be null
            String email = user.getEmail(); // This is likely to be non-null

            mName.setValue(name != null ? name : "No Name Provided");
            mEmail.setValue(email != null ? email : "No Email Available");
        } else {
            mName.setValue("No User Logged In");
            mEmail.setValue("No Email Available");
        }
    }

    public LiveData<String> getName() {
        return mName;
    }

    public LiveData<String> getEmail() {
        return mEmail;
    }
}
