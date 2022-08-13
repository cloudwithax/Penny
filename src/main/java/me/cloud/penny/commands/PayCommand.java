package me.cloud.penny.commands;

import me.cloud.penny.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {

    private final Plugin plugin;
    public PayCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender != null) {
            if (args.length > 0) {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (!(args.length > 1)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must specify an amount!"));
                    return true;
                }

                if (target == null) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player isn't online!"));
                    return true;
                }

                if (target == player) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot pay yourself!"));
                    return true;
                }

                int amount = Integer.parseInt(args[1]);

                int playerBal = plugin.getDatabase().getMoney(player);
                if (amount > playerBal) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe amount provided cannot be sent due to a insufficient balance."));
                    return true;
                }
                if (amount < 1) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must send at least one dollar."));
                    return true;
                }

                plugin.getDatabase().removeMoney(player, amount);
                plugin.getDatabase().addMoney(target, amount);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have sent &a$" + amount + " &7to &a" + target.getDisplayName()));
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + player.getDisplayName() + " &7has paid you &a$" + amount));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 0.5F);
                target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 2F);
            }
            else {
                return false;
            }

        }
        return true;
    }
}
