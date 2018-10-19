package com.example.adrianduarte.androidchallenge.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.adrianduarte.androidchallenge.R;

public class GeneralProgressBar {

    // Attributes
    private Boolean blockUI = false;
    private View childView;
    private Activity context;
    private boolean isLoading = false;
    private TextView progressBackground;
    private ViewGroup viewGroup;

    // Constructors
    public GeneralProgressBar(Activity context) {
        initialize(context, false);
    }

    private void initialize(Activity context, boolean blockUI) {
        this.context = context;
        this.blockUI = blockUI;
        viewGroup = context.findViewById(android.R.id.content);
        childView = context.getLayoutInflater().inflate(R.layout.view_general_progress_bar, null);
        progressBackground =  childView.findViewById(R.id.progress_background);
    }

    // Getters
    public boolean isLoading() {
        return isLoading;
    }

    // Public methods
    public void hide() {
        if(blockUI) {
            unblockUI();
        }
        isLoading = false;
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewGroup.removeView(childView);
            }
        });
    }

    public void show(Boolean blockUI) {
        this.blockUI = blockUI;
        show();
    }


    public void show() {
        if(blockUI) {
            blockUI();
        } else {
            unblockUI();
        }
        isLoading = true;
        viewGroup.addView(childView);
    }

    // Private methods
    private void blockUI() {
        progressBackground.setVisibility(View.VISIBLE);
        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void unblockUI() {
        progressBackground.setVisibility(View.INVISIBLE);
        context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

}