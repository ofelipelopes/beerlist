package teste.great.felipelopes.punk_api.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.sql.SQLException;

import teste.great.felipelopes.punk_api.App;
import teste.great.felipelopes.punk_api.R;
import teste.great.felipelopes.punk_api.beans.Beer;
import teste.great.felipelopes.punk_api.beans.Favorite;

public class BeerActivity extends AppCompatActivity {

    private boolean isFavorite = false;
    private Beer beer;
    private FloatingActionButton btnFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        btnFavorite = findViewById(R.id.btn_favorite);
        btnFavorite.setOnClickListener(clickListener());

        try {
            beer = App.getDataBeer().queryForId(getIntent().getIntExtra("id", 1));
            isFavorite = App.getFavoriteData().idExists(beer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            setIconFloatingActionButton();
        }

        if (beer.getImagePath() == null) {
            Picasso.with(this).load(beer.getImageUrl());
        } else {
            Picasso.with(this).load(new File(beer.getImagePath()))
                    .into((ImageView) findViewById(R.id.beer_cover));
        }

        ((TextView) findViewById(R.id.name)).setText(beer.getName());
        ((TextView) findViewById(R.id.tagline)).setText(beer.getTagline());
        ((TextView) findViewById(R.id.first_brewed)).setText(beer.getFirstBrewed());
        ((TextView) findViewById(R.id.abv)).setText(String.valueOf(beer.getAbv()));
        ((TextView) findViewById(R.id.ibu)).setText(String.valueOf(beer.getAbv()));
        ((TextView) findViewById(R.id.ebc)).setText(String.valueOf(beer.getEbc()));
        ((TextView) findViewById(R.id.description)).setText(beer.getDescription());

    }

    private View.OnClickListener clickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isFavorite) {
                        App.getFavoriteData().delete(new Favorite(beer.getId()));
                        btnFavorite.setImageDrawable(getDrawable(R.drawable.btn_star_big_off1));
                    } else {
                        App.getFavoriteData().createOrUpdate(new Favorite(beer.getId()));
                        btnFavorite.setImageDrawable(getDrawable(R.drawable.btn_star_big_on1));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void setIconFloatingActionButton() {
        if (isFavorite) {
            btnFavorite.setImageDrawable(getDrawable(R.drawable.btn_star_big_on1));
        } else {
            btnFavorite.setImageDrawable(getDrawable(R.drawable.btn_star_big_off1));
        }
    }
}
