package teste.great.felipelopes.punk_api;

public class Constants {

    /**
     * URL
     */
    public static final String ROOT_URL = "https://api.punkapi.com/%s";
    public static final String BEERS = String.format(ROOT_URL, "v2/beers");
    public static final String PAGE = "?page=";

    /**
     * PARAMETERS for sort
     */
    public static final String ABV_GT = "&abv_gt=";
    public static final String ABV_LT = "&abv_lt=";
    public static final String IBU_GT = "&ibu_gt=";
    public static final String IBU_LT = "&ibu_lt=";
    public static final String EBC_GT = "&ebc_gt=";
    public static final String EBC_LT = "&ebc_lt=";
    public static final String BEER_NAME = "&beer_name=";

    /**
     * DATA BASE
     */
    public static final String DATABASE_NAME = "beers.db";
    public static final Integer DATABASE_VERSION = 1;

    public static final String BEER_TABLE = "beer";
    public static final String FAVORITE_TABLE = "favorite";
}
