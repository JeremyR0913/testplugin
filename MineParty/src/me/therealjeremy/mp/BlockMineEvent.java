package me.therealjeremy.mp;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockMineEvent implements Listener{
	
	private Main plugin;
	
	public BlockMineEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void blockMineEvent(BlockBreakEvent e) {
		int x = e.getBlock().getX();
		int y = e.getBlock().getY();
		int z = e.getBlock().getZ();
		if (isInside(x, plugin.getMine().getX(), plugin.getMine().getX() + plugin.getMine().getDx())) {
			if (isInside(y, plugin.getMine().getY(), plugin.getMine().getY() + plugin.getMine().getDy())) {
				if (isInside(z, plugin.getMine().getZ(), plugin.getMine().getZ() + plugin.getMine().getDz())) {
					if (!plugin.isPartyRunning()) {
						e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&9Mine Party&8) &7There is no Mine Party currently running."));
						e.setCancelled(true);
						return;
					}
				}
			}
		}
	}
	
	private boolean isInside(int n, int bound1, int bound2) {
		return n >= bound1 && n <= bound2;
	}

}
