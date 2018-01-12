package me.therealjeremy.mp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AddPointsCMD implements CommandExecutor{
	
	private Main plugin;
	
	public AddPointsCMD(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("mineparty.addpoints")) {
			return false;
		}
		if (args.length < 1) {
			return false;
		}
		plugin.setCount(plugin.getCount() + Integer.parseInt(args[0]));
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&9Mine Party&8) &7" + Integer.parseInt(args[0]) + " points added."));
		return false;
	}

}
