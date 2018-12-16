package teste.great.felipelopes.punk_api.beans;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static teste.great.felipelopes.punk_api.Constants.BEER_TABLE;


@DatabaseTable(tableName = BEER_TABLE)
public class Beer {
    @DatabaseField(columnName = "id", id = true)
    private Integer id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "tagline")
    private String tagline;
    @DatabaseField(columnName = "first_brewed")
    private String firstBrewed;
    @DatabaseField(columnName = "description")
    private String description;
    @DatabaseField(columnName = "image_url")
    private String imageUrl;
    @DatabaseField(columnName = "image_path")
    private String imagePath;
    @DatabaseField(columnName = "abv")
    private double abv;
    @DatabaseField(columnName = "ibu")
    private double ibu;
    @DatabaseField(columnName = "target_fg")
    private double targetFg;
    @DatabaseField(columnName = "target_og")
    private double targetOg;
    @DatabaseField(columnName = "ebc")
    private double ebc;
    @DatabaseField(columnName = "srm")
    private double srm;
    @DatabaseField(columnName = "ph")
    private double ph;
    @DatabaseField(columnName = "attenuation_level")
    private double attenuationLevel;
    @DatabaseField(columnName = "brewers_tips")
    private String brewersTips;
    @DatabaseField(columnName = "contributer")
    private String contributedBy;

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public double getIbu() {
        return ibu;
    }

    public void setIbu(double ibu) {
        this.ibu = ibu;
    }

    public double getTargetFg() {
        return targetFg;
    }

    public void setTargetFg(double targetFg) {
        this.targetFg = targetFg;
    }

    public double getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(double targetOg) {
        this.targetOg = targetOg;
    }

    public double getEbc() {
        return ebc;
    }

    public void setEbc(double ebc) {
        this.ebc = ebc;
    }

    public double getSrm() {
        return srm;
    }

    public void setSrm(double srm) {
        this.srm = srm;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public Double getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(Double attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String brewersTips) {
        this.brewersTips = brewersTips;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setContributedBy(String contributedBy) {
        this.contributedBy = contributedBy;
    }
}
