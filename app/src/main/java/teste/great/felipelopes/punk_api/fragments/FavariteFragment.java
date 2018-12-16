package teste.great.felipelopes.punk_api.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import teste.great.felipelopes.punk_api.App;
import teste.great.felipelopes.punk_api.R;
import teste.great.felipelopes.punk_api.adapters.BeerAdapter;
import teste.great.felipelopes.punk_api.beans.Beer;
import teste.great.felipelopes.punk_api.beans.Favorite;

public class FavariteFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static FavariteFragment favariteFragment;
    RecyclerView recyclerView;
    BeerAdapter beerAdapter;
    LinearLayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Beer> mBeerList = new ArrayList<>();

    public static FavariteFragment newInstance() {
        if (favariteFragment == null) favariteFragment = new FavariteFragment();
        return favariteFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_favarite, container, false);
        swipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = v.findViewById(R.id.beer_recycle_view);
        beerAdapter = new BeerAdapter(mBeerList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(beerAdapter);
        update();
        return v;
    }

    private void update() {

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
                    }

                });

    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    private Callable<List<Beer>> callable() {
        return new Callable<List<Beer>>() {

            List<Beer> aBeerList = new ArrayList<>();
            Beer beer;

            @Override
            public List<Beer> call() throws Exception {

                try {
                    List<Favorite> fragments = App.getFavoriteData().queryForAll();
                    for (Favorite favorite : fragments) {
                        beer = App.getDataBeer().queryForId(favorite.getId());
                        aBeerList.add(beer);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return aBeerList;
            }
        };
    }

    @Override
    public void onRefresh() {
        update();
    }
}
