package teste.great.felipelopes.punk_api.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import teste.great.felipelopes.punk_api.beans.Beer;
import teste.great.felipelopes.punk_api.beans.Favorite;

import static teste.great.felipelopes.punk_api.Constants.DATABASE_NAME;
import static teste.great.felipelopes.punk_api.Constants.DATABASE_VERSION;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private Dao<Beer, Integer> beer = null;
    private Dao<Favorite, Integer> favorite = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Beer.class);
            TableUtils.createTable(connectionSource, Favorite.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // TODO: do proper table schema upgrade
            TableUtils.dropTable(connectionSource, Beer.class, true);
            TableUtils.dropTable(connectionSource, Favorite.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Beer, Integer> getBeerDao() throws SQLException {
        if (beer == null) beer = getDao(Beer.class);
        return beer;
    }

    public Dao<Favorite, Integer> getFavoriteDao() throws SQLException {
        if (favorite == null) favorite = getDao(Favorite.class);
        return favorite;
    }

    @Override
    public void close() {
        beer = null;
        favorite = null;
        super.close();
    }
}
