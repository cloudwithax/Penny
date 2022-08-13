package me.cloud.penny.sql;

import me.cloud.penny.Plugin;

import java.util.logging.Level;

public class Error {
    public static void execute(Plugin plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Couldn't execute SQL statement: ", ex);
    }
    public static void close(Plugin plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Failed to close SQL connection: ", ex);
    }
}