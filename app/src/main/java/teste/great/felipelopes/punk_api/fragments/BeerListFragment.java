package teste.great.felipelopes.punk_api.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import teste.great.felipelopes.punk_api.App;
import teste.great.felipelopes.punk_api.R;
import teste.great.felipelopes.punk_api.adapters.BeerAdapter;
import teste.great.felipelopes.punk_api.api.Callback;
import teste.great.felipelopes.punk_api.beans.Beer;
import teste.great.felipelopes.punk_api.beans.SortParameters;
import teste.great.felipelopes.punk_api.utils.NetworkUtil;

public class BeerListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static BeerListFragment beerListFragment;
    private int mPagimation = 1;
    private SortParameters mSortParams = null; //TODO: Tirar quando implementar o search
    List<Beer> mBeerList = new ArrayList<>();
    RecyclerView recyclerView;
    BeerAdapter beerAdapter;
    LinearLayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    RangeSeekBar rangeABV;
    RangeSeekBar rangeIBU;
    RangeSeekBar rangeEBC;
    SearchView beerName;
    Button sortBtn;
    TextView openSortBtn;
    View layout;
    boolean isSortOpen = false;

    public static BeerListFragment newInstance() {
        if (beerListFragment == null) beerListFragment = new BeerListFragment();
        return beerListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_beer, container, false);
        layout = v.findViewById(R.id.sort_layout);
        openSortBtn = v.findViewById(R.id.open_sort_btn);
        openSortBtn.setOnClickListener(openCloseClickListener());
        rangeEBC = v.findViewById(R.id.range_ebc);
        rangeABV = v.findViewById(R.id.range_abv);
        rangeIBU = v.findViewById(R.id.range_ibu);
        beerName = v.findViewById(R.id.item_pesquisa);
        sortBtn = v.findViewById(R.id.sort_btn);
        sortBtn.setOnClickListener(sortClickListener());
        swipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = v.findViewById(R.id.beer_recycle_view);
        beerAdapter = new BeerAdapter(mBeerList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(beerAdapter);
        load();
        return v;
    }

    //TODO: Tirar isso dps
    private View.OnClickListener openCloseClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSortOpen) {
                    openSortBtn.setText("V");
                    isSortOpen = false;
                    layout.setVisibility(View.GONE);
                } else {
                    openSortBtn.setText("X");
                    isSortOpen = true;
                    layout.setVisibility(View.VISIBLE);
                }
            }
        };
    }


    private void load() {
        mSortParams = new SortParameters();
        //mSortParams.setBeer_name(("Buzz") ); //TODO: ALTERAR para receber a palavra a ser pesquisada
        if (NetworkUtil.isInternetAvailable(getActivity())) {
            if (mPagimation == 1) mBeerList.clear();
            recyclerView.addOnScrollListener(scrollListener());
            App.getAPI().get(mPagimation, mSortParams, new Callback<List<Beer>>() {
                @Override
                public void onSuccess(final List<Beer> beers) {
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            mBeerList.addAll(beers);
                            beerAdapter.notifyDataSetChanged();
                            mPagimation++;
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            });
        } else {

            Observable.fromCallable(callable())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<List<Beer>>() {

                        @Override
                        public void onNext(List<Beer> beers) {
                            mBeerList.clear();
                            mBeerList.addAll(beers);
                        }

                        @Override
                        public void onError(Throwable e) {
                            mBeerList.clear();
                            swipeRefreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void onComplete() {
                            beerAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                            mPagimation = 1;
                        }

                    });

        }
    }

    private Callable<List<Beer>> callable() {
        return new Callable<List<Beer>>() {
            @Override
            public List<Beer> call() throws Exception {
                return App.getDataBeer().queryForAll();
            }
        };
    }

    RecyclerView.OnScrollListener scrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.getItemCount() - 5 == layoutManager.findLastVisibleItemPosition()) {
                    load();
                }
            }
        };
    }

    //TODO: trocar isso pelo search
    private View.OnClickListener sortClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSortParams = new SortParameters();
                mSortParams.setAbv_gt((Integer) rangeABV.getSelectedMaxValue());
                mSortParams.setEbc_gt((Integer) rangeEBC.getSelectedMaxValue());
                mSortParams.setIbu_gt((Integer) rangeIBU.getSelectedMaxValue());
                mSortParams.setAbv_lt((Integer) rangeABV.getSelectedMinValue());
                mSortParams.setEbc_lt((Integer) rangeEBC.getSelectedMinValue());
                mSortParams.setIbu_lt((Integer) rangeIBU.getSelectedMinValue());
                mSortParams.setBeer_name((String) beerName.getQuery());
                //mSortParams.setBeer_name(("Buzz") );
                mPagimation = 1;
                load();
            }
        };
    }

    @Override
    public void onRefresh() {
        load();
    }
}
