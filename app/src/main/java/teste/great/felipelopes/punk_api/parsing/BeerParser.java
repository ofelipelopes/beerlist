package teste.great.felipelopes.punk_api.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import teste.great.felipelopes.punk_api.App;
import teste.great.felipelopes.punk_api.beans.Beer;

public class BeerParser {
    public List<Beer> beers(String json) {

        List<Beer> list = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(json);

            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);
                Beer beer = new Beer();

                beer.setId(object.getInt("id"));
                beer.setName(object.getString("name"));
                beer.setTagline(object.getString("tagline"));
                beer.setFirstBrewed(object.getString("first_brewed"));
                beer.setDescription(object.getString("description"));
                beer.setImageUrl(object.getString("image_url"));
                beer.setAbv(object.getDouble("abv"));
                beer.setIbu(object.getDouble("ibu"));
                beer.setTargetFg(object.getDouble("target_fg"));
                beer.setTargetOg(object.getDouble("target_og"));
                beer.setEbc(object.getDouble("ebc"));
                beer.setSrm(object.getDouble("srm"));
                beer.setPh(object.getDouble("ph"));
                beer.setAttenuationLevel(object.getDouble("attenuation_level"));
                beer.setBrewersTips(object.getString("brewers_tips"));
                beer.setContributedBy(object.getString("contributed_by"));

                App.getDataBeer().createOrUpdate(beer);

                list.add(beer);
            }
        } catch (JSONException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
