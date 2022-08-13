package me.cloud.penny.commands;

import me.cloud.penny.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class TopBalanceCommand implements CommandExecutor {

    private final Plugin plugin;
    public TopBalanceCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        AtomicReference<Integer> position = new AtomicReference<>(1);

        if (sender != null) {
            player.sendMessage(ChatColor.GRAY + "---- Top Balances ----");
            player.sendMessage(" ");
            HashMap<UUID, Integer> topBal = plugin.getDatabase().getTopBalance();
            for (Map.Entry<UUID, Integer> entry : topBal.entrySet()) {
                OfflinePlayer target = plugin.getServer().getPlayer(entry.getKey());
                Integer amount = entry.getValue();
                DecimalFormat formatter = new DecimalFormat("#.##");
                formatter.setGroupingUsed(true);
                formatter.setGroupingSize(3);
                String moneyFormatted = formatter.format(amount);
                player.sendMessage(
                        ChatColor.GRAY + position.toString() + ". " + ChatColor.GREEN + target.getName()
                                + ChatColor.GRAY + " - " + ChatColor.GREEN + "$" + moneyFormatted
                );
                position.getAndSet(position.get() + 1);
            }
            player.sendMessage(" ");
            player.sendMessage(ChatColor.GRAY + "--------------------");

        }
        return true;
    }
}
