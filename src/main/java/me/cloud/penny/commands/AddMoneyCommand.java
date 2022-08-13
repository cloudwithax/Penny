package me.cloud.penny.commands;

import me.cloud.penny.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddMoneyCommand implements CommandExecutor {

    private final Plugin plugin;
    public AddMoneyCommand(Plugin plugin) {
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


                int amount = Integer.parseInt(args[1]);


                if (amount < 1) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must add at least one dollar."));
                    return true;
                }

                plugin.getDatabase().addMoney(target, amount);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have added &a$" + amount + " &7to &a" + target.getDisplayName() + "'s &7balance"));
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + player.getDisplayName() + " &7has added &a$" + amount + " &7to your balance"));
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
