package dk.nydt.skriptsa.storage;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dk.nydt.skriptsa.SkriptSA;
import dk.nydt.skriptsa.storage.objects.Boosts;
import lombok.Getter;

import java.io.File;
import java.util.List;

public class DBManager {
    @Getter
    private static JdbcConnectionSource connectionSource;
    @Getter
    private static Dao<Boosts, Integer> boostsDao;

    public DBManager() {
        try {
            connectionSource = new JdbcConnectionSource(String.format("jdbc:sqlite:%s%s%s.db", SkriptSA.getInstance().getDataFolder(), File.separator, SkriptSA.getInstance().getDescription().getName()));
            setupDatabase(connectionSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setupDatabase(ConnectionSource connectionSource) throws Exception {
        TableUtils.createTableIfNotExists(connectionSource, Boosts.class);
        boostsDao = DaoManager.createDao(connectionSource, Boosts.class);
    }

    public static List<Boosts> getBoosts() {
        try {
            return boostsDao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void close() {
        try {
            connectionSource.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveBoost() {
        try {
            SkriptSA.getUpdater().saveData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
