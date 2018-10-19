package com.example.adrianduarte.androidchallenge.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrianduarte.androidchallenge.R;
import com.example.adrianduarte.androidchallenge.adapters.RecyclerViewCommentAdapter;
import com.example.adrianduarte.androidchallenge.api.APIClient;
import com.example.adrianduarte.androidchallenge.api.APIInterface;
import com.example.adrianduarte.androidchallenge.models.Comment;
import com.example.adrianduarte.androidchallenge.models.DataComment;
import com.example.adrianduarte.androidchallenge.models.Image;
import com.example.adrianduarte.androidchallenge.models.Item;
import com.example.adrianduarte.androidchallenge.utils.GeneralProgressBar;
import com.example.adrianduarte.androidchallenge.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailImageActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context, Item item) {
        Intent intent = new Intent(context, DetailImageActivity.class);
        intent.putExtra(EXTRA_ITEM, item);
        return intent;
    }

    // Constants
    private static final String EXTRA_ITEM = "extra_item";

    // Attributes
    private APIInterface apiInterface;
    private List<Comment> comments = new ArrayList<>();
    private GeneralProgressBar generalProgressBar;
    private Item item = null;
    private LinearLayout llDescription;
    private RecyclerViewCommentAdapter recyclerViewCommentAdapter;

    // Override methods and callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
            item = bundle.getParcelable(EXTRA_ITEM);

        if(item==null) {
            // Handle error
            return;
        }

        init();
        updateUI();
        getImageComments();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // Private methods
    private void getImageComments() {
        // Tag image exist so get images
        Call<DataComment> call = apiInterface.getImageComments(item.getId());
        call.enqueue(new Callback<DataComment>() {
            @Override
            public void onResponse(Call<DataComment> call, Response<DataComment> response) {
                DataComment dataComment = response.body();
                if(dataComment!=null) {
                    comments.addAll(dataComment.getData());
                    recyclerViewCommentAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(DetailImageActivity.this, getResources().getString(R.string.all_general_error), Toast.LENGTH_LONG).show();
                }
                generalProgressBar.hide();
            }

            @Override
            public void onFailure(Call<DataComment> call, Throwable t) {
                generalProgressBar.hide();
            }
        });
    }

    private void init() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        llDescription = findViewById(R.id.ll_description);
        RecyclerView recyclerViewComments = findViewById(R.id.rv_comments);
        recyclerViewCommentAdapter = new RecyclerViewCommentAdapter( comments);
        recyclerViewComments.setAdapter(recyclerViewCommentAdapter);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));
        generalProgressBar = new GeneralProgressBar(this);
        generalProgressBar.show(false);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        ActionBar actionbar = getSupportActionBar();

        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void updateUI() {
        List<Image> images = item.getImages();
        if(images!=null && images.size()>0) {
            ImageUtils.loadGlideImage(this, images.get(0).getLink(), (ImageView) findViewById(R.id.iv_image));
        }
        ((TextView)findViewById(R.id.tv_up_votes)).setText(String.valueOf(item.getUps()));
        ((TextView)findViewById(R.id.tv_down_votes)).setText(String.valueOf(item.getDowns()));
        ((TextView)findViewById(R.id.tv_views)).setText(String.valueOf(item.getViews()));
        if(item.getDescription()!=null) {
            ((TextView)findViewById(R.id.tv_description_text)).setText(item.getDescription());
            llDescription.setVisibility(View.VISIBLE);
        } else {
            llDescription.setVisibility(View.GONE);
        }
    }

}