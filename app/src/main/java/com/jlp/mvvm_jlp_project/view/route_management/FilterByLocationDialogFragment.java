package com.jlp.mvvm_jlp_project.view.route_management;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.DialogFragmentFilterByLocationBinding;
import com.jlp.mvvm_jlp_project.view.base.BaseDialogFragment;

public class FilterByLocationDialogFragment extends BaseDialogFragment implements View.OnClickListener {

    private DialogFragmentFilterByLocationBinding binding;

    private String pageName = new String();
    private String title = new String();
    private String message = new String();
    private String hint = new String();
    public static String filteredNameString = new String();

    private String positiveButton = new String();
    private String negativeButton = new String();


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            if (getArguments().getBoolean(PARAM_NOT_ALERT_DIALOG)) {
                return super.onCreateDialog(savedInstanceState);
            }
        }

        loadArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dummy_dialog_title);
        builder.setMessage(R.string.str_dummy_enter_input);

        builder.setPositiveButton(R.string.str_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        builder.setNegativeButton(R.string.str_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogFragmentFilterByLocationBinding.inflate(LayoutInflater.from(getContext()));


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*
        if (getArguments() != null && !TextUtils.isEmpty(getArguments().getString("email")))
            editText.setText(getArguments().getString("email"));*/

        initLabels();
        binding.btnOk.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean setFullScreen = false;
        if (getArguments() != null) {
            setFullScreen = getArguments().getBoolean(PARAM_BUNDLE_FULLSCREEN);
        }
        loadArguments();

        if (setFullScreen) {
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            //setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenDialog);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOk:

                if (!(TextUtils.isEmpty(binding.inputBarcode.getText().toString().trim()))) {
                    if (getListener() != null)
                        getListener().onFinishDialog(binding.inputBarcode.getText().toString(), false);
                    filteredNameString = "" + binding.inputBarcode.getText();

                    dismiss();
                }
                break;

            case R.id.btnClear:
                if (getListener() != null)
                    getListener().onFinishDialog(binding.inputBarcode.getText().toString(), true);
                filteredNameString = new String();
                dismiss();

                break;


        }
    }

    private void loadArguments() {
        if (getArguments() != null) {
            pageName = getArguments().getString(PARAM_PAGE_NAME);
            title = getArguments().getString(PARAM_TITLE);
            message = getArguments().getString(PARAM_MESSAGE);
            hint = getArguments().getString(PARAM_HINT);
            positiveButton = getArguments().getString(PARAM_POSITIVE_BUTTON);
            negativeButton = getArguments().getString(PARAM_NEGATIVE_BUTTON);


        }
    }

    private void initLabels() {
        if (!TextUtils.isEmpty(title)) {
            binding.txtDialogTitleValue.setText(title);
        }

        if (!TextUtils.isEmpty(pageName)) {
            binding.txtDialogTitle.setText(pageName);
        } else {
            binding.txtDialogTitle.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(message)) {
            binding.txtDialogMessage.setText(message);
            binding.txtDialogMessage.setVisibility(View.VISIBLE);
        } else {
            binding.txtDialogMessage.setVisibility(View.GONE);
        }


        if (!TextUtils.isEmpty(hint)) {
            binding.txtDialogHint.setText(hint);
            binding.inputBarcode.setHint(hint);
        }

        if (!TextUtils.isEmpty(message)) {
            binding.txtDialogMessage.setText(message);
        }

        if (!TextUtils.isEmpty(positiveButton)) {
            binding.btnOk.setText(positiveButton);
        }

        if (!TextUtils.isEmpty(negativeButton)) {
            binding.btnClear.setText(negativeButton);
        }

        if (!(TextUtils.isEmpty(filteredNameString))) {
            binding.inputBarcode.setText(filteredNameString);
        }

    }


    /*@Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }*/
}
