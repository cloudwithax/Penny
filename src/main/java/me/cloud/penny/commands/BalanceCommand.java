package me.cloud.penny.commands;

import me.cloud.penny.Plugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class BalanceCommand implements CommandExecutor {

    private final Plugin plugin;
    public BalanceCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (sender != null) {
            plugin.getDatabase().checkAccount(player);
            Integer balance = plugin.getDatabase().getMoney(player);
            DecimalFormat formatter = new DecimalFormat("#.##");
            formatter.setGroupingUsed(true);
            formatter.setGroupingSize(3);
            String moneyFormatted =  formatter.format(balance);
            player.sendMessage("§7Your balance is §a$" + moneyFormatted);
        }

        return true;
    }
}
