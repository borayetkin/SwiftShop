package com.example.swiftshopapplication.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.swiftshopapplication.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText editName = binding.editName;
        final EditText editEmail = binding.editEmail;
        final EditText editAddress = binding.editAddress;
        Button saveButton = binding.buttonSave;

        profileViewModel.getName().observe(getViewLifecycleOwner(), editName::setText);
        profileViewModel.getEmail().observe(getViewLifecycleOwner(), editEmail::setText);
        profileViewModel.getAddress().observe(getViewLifecycleOwner(), editAddress::setText);

        saveButton.setOnClickListener(v -> {
            String newName = editName.getText().toString();
            String newEmail = editEmail.getText().toString();
            String newAddress = editAddress.getText().toString();
            profileViewModel.updateUserProfile(newName, newEmail, newAddress);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
