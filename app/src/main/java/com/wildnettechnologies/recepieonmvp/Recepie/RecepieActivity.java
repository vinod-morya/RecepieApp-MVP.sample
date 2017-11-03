package com.wildnettechnologies.recepieonmvp.Recepie;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wildnettechnologies.recepieonmvp.R;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;
import com.wildnettechnologies.recepieonmvp.Recepie.View.RecepieAdapter;
import com.wildnettechnologies.recepieonmvp.base.Constants.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecepieActivity extends AppCompatActivity implements RecepieContracts.IRecepieView, RecepieAdapter.RecyclerViewClickListener {


    @BindView(R.id.tvWelcomeText)
    TextView mTvWelcomeText;
    @BindView(R.id.recyclerViewRecepie)
    RecyclerView mRecyclerViewRecepie;
    @BindView(R.id.progressbarRecepie)
    ProgressBar mProgressbarRecepie;
    private RecepieAdapter mRecepieAdapter;
    private ArrayList<RecepieModel.Result> mRecepieModel = new ArrayList<>();
    private SearchView mSearchView;
    private RecepieContracts.RecepiePresenter mRecepiePresenter;
    private RecepieRequestModel mRecepieRequestModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepie);
        ButterKnife.bind(this);
        initializeViews();
    }

    private void initializeViews() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewRecepie.setLayoutManager(layoutManager);
        mRecepieAdapter = new RecepieAdapter(this, mRecepieModel);
        mRecyclerViewRecepie.setAdapter(mRecepieAdapter);
        mRecepiePresenter = new RecepiePresenterImpl(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        initSearch();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressbarRecepie.setVisibility(View.VISIBLE);
                mRecyclerViewRecepie.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void hideProgress() {
        mProgressbarRecepie.setVisibility(View.INVISIBLE);
        mRecyclerViewRecepie.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDefaultText() {
        mTvWelcomeText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setItems(List<RecepieModel.Result> items, int pageNumber) {
        if (pageNumber == AppConstants.DEFAULT_PAGE) {
            mRecepieModel.clear();
        }
        mRecepieModel.addAll(items);
    }

    private void initSearch() {
        if (mSearchView != null) {
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                Timer timer = new Timer();
                final long DELAY = 1000; // milliseconds


                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (query.length() > 0) {
                        mRecepieRequestModel = new RecepieRequestModel();
                        mRecepieRequestModel.setRecepie(query);
                        mRecepieRequestModel.setPage(1);
                        mRecepiePresenter.onSearchStarted(mRecepieRequestModel);
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(final String newText) {
                    if (newText.length() > 0) {
                        Log.e("Search text", "Search Text : " + newText);
                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        mRecepieRequestModel = new RecepieRequestModel();
                                        mRecepieRequestModel.setRecepie(newText);
                                        mRecepieRequestModel.setPage(1);
                                        mRecepiePresenter.onSearchStarted(mRecepieRequestModel);
                                    }
                                },
                                DELAY
                        );
                    }
                    return false;
                }
            });

            mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    return false;
                }
            });
        }
    }

    @OnClick(R.id.tvWelcomeText)
    public void onViewClicked() {

    }

    @OnClick({R.id.tvWelcomeText, R.id.progressbarRecepie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvWelcomeText:
                Log.e("welcome Text Clicked", "Welcome clicked");
                mRecepiePresenter.onWelcomeClicked();
                break;
            case R.id.recyclerViewRecepie:
                break;
            case R.id.progressbarRecepie:
                break;
        }
    }

    @Override
    public void onCellClick(RecepieModel.Result result)
        {
          if(result != null){
              Log.e("Click data","Click data is null.");
              mRecepiePresenter.navigateToRowDetailModule(result);
          }
        }
}
