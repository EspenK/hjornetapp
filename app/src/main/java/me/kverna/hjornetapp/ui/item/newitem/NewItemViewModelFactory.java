package me.kverna.hjornetapp.ui.item.newitem;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import lombok.NonNull;

public class NewItemViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewItemViewModel.class)) {
            return (T) new NewItemViewModel();
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
