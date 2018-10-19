package com.example.adrianduarte.androidchallenge.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.adrianduarte.androidchallenge.adapters.RecyclerViewTagAdapter;
import com.example.adrianduarte.androidchallenge.api.APIClient;
import com.example.adrianduarte.androidchallenge.api.APIInterface;
import com.example.adrianduarte.androidchallenge.models.DataTag;
import com.example.adrianduarte.androidchallenge.models.Tag;
import com.example.adrianduarte.androidchallenge.utils.GeneralProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTagActivity extends AppCompatActivity {

    // Attributes
    private APIInterface apiInterface;
    private GeneralProgressBar generalProgressBar;
    private List<Tag> tags = new ArrayList<>();
    private RecyclerViewTagAdapter recyclerViewTagAdapter;
    private SwipeRefreshLayout swipeRefreshLayoutTags;

    // Override methods and callbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tag);

        init();
        getTags();
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
                recyclerViewTagAdapter.getFilter().filter(nextText);
                return false;
            }
        });
        searchView.setMaxWidth(Integer.MAX_VALUE);

        return true;
    }

    // Listeners
    private RecyclerViewTagAdapter.OnClickListener onClickListener = new RecyclerViewTagAdapter.OnClickListener() {
        @Override
        public void onClick(View view, Tag selectedTag) {
            startActivity(ListImageActivity.getStartIntent(ListTagActivity.this, selectedTag.getName()));
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            tags = new ArrayList<>();
            getTags();
        }
    };

    // Private methods
    private void init() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        RecyclerView recyclerViewTags = findViewById(R.id.rv_tags);
        swipeRefreshLayoutTags = findViewById(R.id.srl_tags);
        recyclerViewTagAdapter = new RecyclerViewTagAdapter(tags, onClickListener);
        recyclerViewTags.setAdapter(recyclerViewTagAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewTags.setLayoutManager(linearLayoutManager);
        recyclerViewTags.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        swipeRefreshLayoutTags.setOnRefreshListener(onRefreshListener);
        generalProgressBar = new GeneralProgressBar(this);
        generalProgressBar.show(false);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(false);
        }
    }

    private void getTags() {
        Call<DataTag> call = apiInterface.getTags();
        call.enqueue(new Callback<DataTag>() {
            @Override
            public void onResponse(Call<DataTag> call, Response<DataTag> response) {
                DataTag dataTag = response.body();
                if(dataTag!=null) {
                    tags.addAll(dataTag.getData().getTags());
                    recyclerViewTagAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ListTagActivity.this, getResources().getString(R.string.all_general_error), Toast.LENGTH_LONG).show();
                }
                swipeRefreshLayoutTags.setRefreshing(false);
                generalProgressBar.hide();
            }

            @Override
            public void onFailure(Call<DataTag> call, Throwable t) {
                generalProgressBar.hide();
            }
        });
    }

}
