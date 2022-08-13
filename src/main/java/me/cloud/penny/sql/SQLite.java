package me.cloud.penny.sql;

import me.cloud.penny.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class SQLite extends Database{
    String dbname;
    private final Plugin plugin;
    public SQLite(Plugin plugin){
        this.plugin = plugin;
        dbname = "economy";
    }

    public String SQLiteCreateTable = "CREATE TABLE IF NOT EXISTS player_economy (" +
            "`player` varchar(32) NOT NULL," +
            "`amount` int(11) NOT NULL," +
            "PRIMARY KEY (`player`)" +
            ");";


    public Connection getSQLConnection() {
        File databaseFile = new File(plugin.getDataFolder(), dbname + ".db");
        if (!databaseFile.exists()){
            try {
                databaseFile.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: "+ dbname +".db");
            }
        }
        try {
            if(connection!=null&&!connection.isClosed()){
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFile);
            return connection;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE,"SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "The SQLite JBDC library is missing.");
        }
        return null;
    }

    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(SQLiteCreateTable);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }
}
