package com.noor.yasser.ps.githubapp.ui.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.noor.yasser.ps.githubapp.databinding.DialogLoadingBinding;

import org.jetbrains.annotations.NotNull;


public class IndeterminateProgressDialog extends DialogFragment {

    private DialogLoadingBinding mBinding;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        mBinding = DialogLoadingBinding.inflate(inflater, container, false);
        setCancelable(false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
    }

    @Override
    public void onDismiss(@NonNull @NotNull DialogInterface dialog) {
        super.onDismiss(dialog);
        onDestroy();
    }
}
