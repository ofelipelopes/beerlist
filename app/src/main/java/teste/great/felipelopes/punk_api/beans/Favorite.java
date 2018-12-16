package teste.great.felipelopes.punk_api.beans;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static teste.great.felipelopes.punk_api.Constants.FAVORITE_TABLE;

@DatabaseTable(tableName = FAVORITE_TABLE)
public class Favorite {

    @DatabaseField(columnName = "id", id = true)
    private int id;

    public Favorite() {
    }

    public Favorite(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
