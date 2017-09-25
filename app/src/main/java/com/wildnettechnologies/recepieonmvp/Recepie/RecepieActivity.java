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
import android.widget.Toast;

import com.wildnettechnologies.recepieonmvp.R;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieModel;
import com.wildnettechnologies.recepieonmvp.Recepie.Model.RecepieRequestModel;
import com.wildnettechnologies.recepieonmvp.Recepie.Presenter.RecepieInteractorImpl;
import com.wildnettechnologies.recepieonmvp.Recepie.Presenter.RecepiePresenterImpl;
import com.wildnettechnologies.recepieonmvp.Recepie.View.RecepieAdapter;
import com.wildnettechnologies.recepieonmvp.Recepie.View.IRecepieView;
import com.wildnettechnologies.recepieonmvp.base.Constants.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecepieActivity extends AppCompatActivity implements IRecepieView {


    @BindView(R.id.tvWelcomeText)
    TextView mTvWelcomeText;
    @BindView(R.id.recyclerViewRecepie)
    RecyclerView mRecyclerViewRecepie;
    @BindView(R.id.progressbarRecepie)
    ProgressBar mProgressbarRecepie;
    private RecepiePresenterImpl mRecepiePresenterImpl;
    private RecepieAdapter mRecepieAdapter;
    private ArrayList<RecepieModel.Result> mRecepieModel = new ArrayList<>();
    private SearchView mSearchView;
    private RecepieRequestModel mRecepieRequestModel;
    private boolean loading;

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
//        initLoadMoreFeature();
        mRecepiePresenterImpl = new RecepiePresenterImpl(this, new RecepieInteractorImpl());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecepiePresenterImpl.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mRecepiePresenterImpl.onMenuLoaded(mSearchView);
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
    protected void onDestroy() {
        mRecepiePresenterImpl.onDestroy();
        super.onDestroy();
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
    public void showDefaultText() {
        mTvWelcomeText.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<RecepieModel.Result> items, int pageNumber) {
        if (pageNumber == AppConstants.DEFAULT_PAGE) {
            mRecepieModel.clear();
        }
        mRecepieModel.addAll(items);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startSearchText(String sreachText) {

    }


//    private void initLoadMoreFeature() {
//        mRecyclerViewRecepie.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            public int visibleThreshold = 7;
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                int totalItemCount = mRecyclerViewRecepie.getLayoutManager().getItemCount();
//                int lastVisibleItem = ((LinearLayoutManager) mRecyclerViewRecepie.getLayoutManager()).findLastVisibleItemPosition();
//                if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                    mRecepieRequestModel.setPage(mRecepieRequestModel.getPage() + 1);
//                    mRecepiePresenterImpl.onSearchStarted(mRecepieRequestModel);
//                    loading = true;
//                }
//            }
//        });
//    }

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
                        mRecepiePresenterImpl.onSearchStarted(mRecepieRequestModel);
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
                                        mRecepiePresenterImpl.onSearchStarted(mRecepieRequestModel);
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
                break;
            case R.id.recyclerViewRecepie:
                break;
            case R.id.progressbarRecepie:
                break;
        }
    }
}