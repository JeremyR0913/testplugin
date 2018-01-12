package me.therealjeremy.mp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.selections.Selection;

public class SetMineCMD implements CommandExecutor {

	private Main plugin;

	public SetMineCMD(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("mineparty.setmineparty")) {
			return false;
		}
		Player player = (Player) sender;
		Selection selection = plugin.getWorldEditPlugin().getSelection(player);
		plugin.getConfig().set("mine.world", player.getWorld().getName());
		plugin.getConfig().set("mine.block-one.x", selection.getMinimumPoint().getBlockX());
		plugin.getConfig().set("mine.block-one.y", selection.getMinimumPoint().getBlockY());
		plugin.getConfig().set("mine.block-one.z", selection.getMinimumPoint().getBlockZ());
		plugin.getConfig().set("mine.block-two.x", selection.getMaximumPoint().getBlockX());
		plugin.getConfig().set("mine.block-two.y", selection.getMaximumPoint().getBlockY());
		plugin.getConfig().set("mine.block-two.z", selection.getMaximumPoint().getBlockZ());
		plugin.saveConfig();
		plugin.setMine(new Mine(selection.getMinimumPoint().getBlockX(), selection.getMinimumPoint().getBlockY(),
				selection.getMinimumPoint().getBlockZ(), selection.getMaximumPoint().getBlockX(),
				selection.getMaximumPoint().getBlockY(), selection.getMaximumPoint().getBlockZ(),
				player.getWorld().getName()));
		return false;
	}

}
