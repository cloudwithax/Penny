package me.cloud.penny;

import me.cloud.penny.commands.*;
import me.cloud.penny.events.OnPlayerJoin;
import me.cloud.penny.sql.Database;
import me.cloud.penny.sql.SQLite;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    private Database db;
    public Database getDatabase() {
        return this.db;
    }



    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("pay").setExecutor(new PayCommand(this));
        getCommand("balance").setExecutor(new BalanceCommand(this));
        getCommand("addmoney").setExecutor(new AddMoneyCommand(this));
        getCommand("removemoney").setExecutor(new RemoveMoneyCommand(this));
        getCommand("baltop").setExecutor(new TopBalanceCommand(this));
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        this.db = new SQLite(this);
        this.db.load();
        setupVault();
        getLogger().info("Penny has been loaded!");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Penny is now shutting down, goodbye!");
    }

    private void setupVault() {
        org.bukkit.plugin.Plugin vault = getServer().getPluginManager().getPlugin("Vault");

        if (vault == null) {
            return;
        }

        getServer().getServicesManager().register(Economy.class, new VaultAdapter(this), this, ServicePriority.Highest);
    }
}
