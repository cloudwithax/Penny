package me.cloud.penny;


import me.cloud.penny.events.OnPlayerJoin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class VaultAdapter implements Economy {

    private final String name = "Penny";

    private final Plugin plugin;

    public VaultAdapter(Plugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(new OnPlayerJoin(plugin), plugin);

        plugin.getLogger().info("Vault support enabled.");
    }

    @Override
    public boolean isEnabled() {
        return plugin != null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double amount) {
        DecimalFormat formatter = new DecimalFormat("#.##");
        formatter.setGroupingUsed(true);
        formatter.setGroupingSize(3);
        return formatter.format(amount);

    }

    @Override
    public String currencyNamePlural() {
        return "dollars";
    }

    @Override
    public String currencyNameSingular() {
        return "dollar";
    }

    @Override
    public boolean hasAccount(String playerName) {
        Player player = plugin.getServer().getPlayer(playerName);
        return plugin.getDatabase().checkAccount(player);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return plugin.getDatabase().checkAccount((Player) player);
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return hasAccount(player);
    }

    @Override
    @SuppressWarnings("deprecation")
    public double getBalance(String playerName) {
        Player player = plugin.getServer().getPlayer(playerName);
        return plugin.getDatabase().getMoney(player);
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return plugin.getDatabase().getMoney((Player) player);
    }

    @Override
    public double getBalance(String playerName, String world) {
        return getBalance(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return getBalance(player);
    }

    @Override
    public boolean has(String playerName, double amount) {
        Player player = plugin.getServer().getPlayer(playerName);
        Integer balance = plugin.getDatabase().getMoney(player);
        return amount == balance;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        Integer balance = plugin.getDatabase().getMoney((Player) player);
        return amount == balance;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return has(player, amount);
    }

    @Override
    @SuppressWarnings("deprecation")
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        Player player = plugin.getServer().getPlayer(playerName);
        plugin.getDatabase().removeMoney(player, amount);
        return new EconomyResponse(amount, plugin.getDatabase().getMoney(player), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        plugin.getDatabase().removeMoney((Player) player, amount);
        return new EconomyResponse(amount, plugin.getDatabase().getMoney((Player) player), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return withdrawPlayer(player, amount);
    }

    @Override
    @SuppressWarnings("deprecation")
    public EconomyResponse depositPlayer(String playerName, double amount) {
        Player player = plugin.getServer().getPlayer(playerName);
        plugin.getDatabase().addMoney(player, amount);
        return new EconomyResponse(amount, plugin.getDatabase().getMoney(player), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        plugin.getDatabase().addMoney((Player) player, amount);
        return new EconomyResponse(amount, plugin.getDatabase().getMoney((Player) player), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return depositPlayer(player, amount);
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "Penny has not implemented bank accounts.");
    }

    @Override
    public List<String> getBanks() {
        return Collections.emptyList();
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        Player player = plugin.getServer().getPlayer(playerName);
        return plugin.getDatabase().checkAccount(player);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return plugin.getDatabase().checkAccount((Player) player);
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return createPlayerAccount(player);
    }
}
