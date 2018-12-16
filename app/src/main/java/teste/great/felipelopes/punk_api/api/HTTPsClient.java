package teste.great.felipelopes.punk_api.api;

import java.util.List;

import teste.great.felipelopes.punk_api.beans.Beer;
import teste.great.felipelopes.punk_api.beans.SortParameters;
import teste.great.felipelopes.punk_api.parsing.BeerParser;

import static teste.great.felipelopes.punk_api.Constants.ABV_GT;
import static teste.great.felipelopes.punk_api.Constants.ABV_LT;
import static teste.great.felipelopes.punk_api.Constants.BEERS;
import static teste.great.felipelopes.punk_api.Constants.BEER_NAME;
import static teste.great.felipelopes.punk_api.Constants.EBC_GT;
import static teste.great.felipelopes.punk_api.Constants.EBC_LT;
import static teste.great.felipelopes.punk_api.Constants.IBU_GT;
import static teste.great.felipelopes.punk_api.Constants.IBU_LT;
import static teste.great.felipelopes.punk_api.Constants.PAGE;

public class HTTPsClient {

    private HTTPs https;
    private BeerParser parser;

    public HTTPsClient() {
        https = new HTTPs();
        parser = new BeerParser();
    }

    public void get(final int page, final SortParameters param, final Callback<List<Beer>> listCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                sb.append(BEERS).append(PAGE).append(page);
                if (param != null) {
                    if (param.getAbv_gt() > 0) sb.append(ABV_GT).append(param.getAbv_gt());
                    if (param.getAbv_lt() > 0) sb.append(ABV_LT).append(param.getAbv_lt());
                    if (param.getEbc_gt() > 0) sb.append(EBC_GT).append(param.getEbc_gt());
                    if (param.getEbc_lt() > 0) sb.append(EBC_LT).append(param.getEbc_lt());
                    if (param.getIbu_gt() > 0) sb.append(IBU_GT).append(param.getIbu_gt());
                    if (param.getIbu_lt() > 0) sb.append(IBU_LT).append(param.getIbu_lt());
                    if (param.getBeer_name() != null && !param.getBeer_name().isEmpty()) sb.append(BEER_NAME).append(param.getBeer_name());
                }
                String url = sb.toString();

                https.get(url, new Callback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        listCallback.onSuccess(parser.beers(s));
                    }
                });
            }
        }).start();
    }
}
