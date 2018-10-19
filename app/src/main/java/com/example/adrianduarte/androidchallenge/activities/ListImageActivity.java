package com.example.adrianduarte.androidchallenge.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.adrianduarte.androidchallenge.R;
import com.example.adrianduarte.androidchallenge.adapters.RecyclerViewImageAdapter;
import com.example.adrianduarte.androidchallenge.api.APIClient;
import com.example.adrianduarte.androidchallenge.api.APIInterface;
import com.example.adrianduarte.androidchallenge.models.DataImage;
import com.example.adrianduarte.androidchallenge.models.Item;
import com.example.adrianduarte.androidchallenge.utils.GeneralProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListImageActivity extends AppCompatActivity {

    public static Intent getStartIntent(Context context, String tagName) {
        Intent intent = new Intent(context, ListImageActivity.class);
        intent.putExtra(EXTRA_TAG_NAME, tagName);
        return intent;
    }

    // Constants
    private static final String EXTRA_TAG_NAME = "extra_tag_name";

    // Attributes
    private APIInterface apiInterface;
    private GeneralProgressBar generalProgressBar;
    private String tagImage;
    private List<Item> items = new ArrayList<>();
    private RecyclerViewImageAdapter recyclerViewImageAdapter;
    private SwipeRefreshLayout swipeRefreshLayoutImages;

    // Override methods and callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_image);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
            tagImage = bundle.getString(EXTRA_TAG_NAME, null);

        init();
        getTagImages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem menuSearchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menuSearchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint(getString(R.string.all_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                recyclerViewImageAdapter.getFilter().filter(nextText);
                return false;
            }
        });
        searchView.setMaxWidth(Integer.MAX_VALUE);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // Listeners
    private RecyclerViewImageAdapter.OnClickListener onClickListener = new RecyclerViewImageAdapter.OnClickListener() {
        @Override
        public void onClick(View view, Item selectedItem) {
            startActivity(DetailImageActivity.getStartIntent(ListImageActivity.this, selectedItem));
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            items = new ArrayList<>();
            getTagImages();
        }
    };

    // Private methods
    private void getTagImages() {
        if(tagImage==null) {
            // Handle error
            return;
        }

        // Tag image exist so get images
        Call<DataImage> call = apiInterface.getTagImages(tagImage);
        call.enqueue(new Callback<DataImage>() {
            @Override
            public void onResponse(Call<DataImage> call, Response<DataImage> response) {
                DataImage dataImage = response.body();
                if(dataImage!=null) {
                    items.addAll(dataImage.getData().getItems());
                    recyclerViewImageAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ListImageActivity.this, getResources().getString(R.string.all_general_error), Toast.LENGTH_LONG).show();
                }
                swipeRefreshLayoutImages.setRefreshing(false);
                generalProgressBar.hide();
            }

            @Override
            public void onFailure(Call<DataImage> call, Throwable t) {
                generalProgressBar.hide();
            }
        });
    }

    private void init() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        RecyclerView recyclerViewImages = findViewById(R.id.rv_images);
        swipeRefreshLayoutImages = findViewById(R.id.srl_images);
        recyclerViewImageAdapter = new RecyclerViewImageAdapter(this, items, onClickListener);
        recyclerViewImages.setAdapter(recyclerViewImageAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewImages.setLayoutManager(linearLayoutManager);
        recyclerViewImages.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        swipeRefreshLayoutImages.setOnRefreshListener(onRefreshListener);
        generalProgressBar = new GeneralProgressBar(this);
        generalProgressBar.show(false);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
