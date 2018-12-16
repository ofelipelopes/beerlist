package teste.great.felipelopes.punk_api.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import teste.great.felipelopes.punk_api.R;
import teste.great.felipelopes.punk_api.adapters.PageAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    private Toolbar toolbar;
    private Button btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Beer List");
        setSupportActionBar( toolbar );

        viewPager = findViewById(R.id.beer_pager);
        pagerAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Button favBtn;
        switch (item.getItemId()){
            case R.id.item_favorito:
                //TODO: chamar o fragment de cervejas favoritas
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.MainActivity, FavariteFragment);
                Log.i("TOOLBAR", "deu certo");
                return true;
            case R.id.item_pesquisa:
                return true;
             default:
                 return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        //TODO: IMPLEMENTAR!!!!!!

//        MenuItem searchItem = menu.findItem(R.id.item_pesquisa);
//       SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) searchItem.getActionView();


        return true;
    }
}
