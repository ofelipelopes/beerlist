package teste.great.felipelopes.punk_api.beans;

public class SortParameters {

    private int abv_gt;
    private int abv_lt;
    private int ibu_gt;
    private int ibu_lt;
    private int ebc_gt;
    private int ebc_lt;
    private String beer_name;

    public int getAbv_gt() {
        return abv_gt;
    }

    public void setAbv_gt(int abv_gt) {
        this.abv_gt = abv_gt;
    }

    public int getAbv_lt() {
        return abv_lt;
    }

    public void setAbv_lt(int abv_lt) {
        this.abv_lt = abv_lt;
    }

    public int getIbu_gt() {
        return ibu_gt;
    }

    public void setIbu_gt(int ibu_gt) {
        this.ibu_gt = ibu_gt;
    }

    public int getIbu_lt() {
        return ibu_lt;
    }

    public void setIbu_lt(int ibu_lt) {
        this.ibu_lt = ibu_lt;
    }

    public int getEbc_gt() {
        return ebc_gt;
    }

    public void setEbc_gt(int ebc_gt) {
        this.ebc_gt = ebc_gt;
    }

    public int getEbc_lt() {
        return ebc_lt;
    }

    public void setEbc_lt(int ebc_lt) {
        this.ebc_lt = ebc_lt;
    }

    public String getBeer_name() {
        return beer_name;
    }

    public void setBeer_name(String beer_name) {
        this.beer_name = beer_name;
    }

}
