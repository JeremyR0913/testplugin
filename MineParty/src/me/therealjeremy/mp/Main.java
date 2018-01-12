package me.therealjeremy.mp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		this.worldEditPlugin = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		registerConfig();
		registerMine();
		registerCommands();
		registerEvents();
		startTask();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private int count;
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	private boolean partyRunning;
	
	public boolean isPartyRunning() {
		return partyRunning;
	}
	
	private void startTask() {
		Random r = new Random();
		List<List<Material>> rounds = new ArrayList<>();
		List<Material> materials1 = new ArrayList<Material>();
		materials1.add(Material.EMERALD_BLOCK);
		List<Material> materials2 = new ArrayList<Material>();
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.AIR);
		materials2.add(Material.ENDER_CHEST);
		materials2.add(Material.ENCHANTMENT_TABLE);
		materials2.add(Material.ANVIL);
		materials2.add(Material.BEACON);
		List<Materials> materials3 = new ArrayList<Material>();
		materials3.add(Material.DIAMOND_BLOCK);
		rounds.add(materials1);
		rounds.add(materials2);
		rounds.add(materials3);
		this.getServer().getScheduler().runTaskTimer(this, new Runnable() {
			
			private long lastParty;
			private int counter;
			
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				if (count >= 100 && !partyRunning) {
					for (Player player : Bukkit.getOnlinePlayers()) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&9Mine Party&8) &7A Mine Party has started!"));
					}
					count = count - 100;
					partyRunning = true;
					this.lastParty = System.currentTimeMillis();
					for (int i1 = 0; i1 <= mine.getDx(); i1++) {
						for (int i2 = 0; i2 <= mine.getDy(); i2++) {
							for (int i3 = 0; i3 <= mine.getDz(); i3++) {
								Location location = new Location(Bukkit.getWorld(mine.getWorld()), mine.getX() + i1, mine.getY() + i2, mine.getZ() + i3);
								List<Material> materials = (List<Material>) rounds.toArray()[counter % rounds.size()];
								location.getBlock().setType((Material) materials.toArray()[r.nextInt(materials.size())]);
							}
						}
					}
					counter++;
				}
				if ((System.currentTimeMillis() - this.lastParty) > 1000*60*1) {
					if (partyRunning) {
						for (Player player : Bukkit.getOnlinePlayers()) {
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&9Mine Party&8) &7The Mine Party has ended."));
						}
						partyRunning = false;
						for (int i1 = 0; i1 <= mine.getDx(); i1++) {
							for (int i2 = 0; i2 <= mine.getDy(); i2++) {
								for (int i3 = 0; i3 <= mine.getDz(); i3++) {
									Location location = new Location(Bukkit.getWorld(mine.getWorld()), mine.getX() + i1, mine.getY() + i2, mine.getZ() + i3);
									location.getBlock().setType(Material.OBSIDIAN);
								}
							}
						}
					}
				}
			}
		}, 200L, 20L);
	}

	private Mine mine;
	
	public Mine getMine() {
		return mine;
	}
	
	
	public void setMine(Mine mine) {
		this.mine = mine;
	}

	private void registerMine() {
		int x1 = this.getConfig().getInt("mine.block-one.x");
		int y1 = this.getConfig().getInt("mine.block-one.y");
		int z1 = this.getConfig().getInt("mine.block-one.z");
		int x2 = this.getConfig().getInt("mine.block-two.x");
		int y2 = this.getConfig().getInt("mine.block-two.y");
		int z2 = this.getConfig().getInt("mine.block-two.z");
		String world = this.getConfig().getString("mine.world");
		this.mine = new Mine(x1, y1, z1, x2, y2, z2, world);
	}

	private void registerConfig() {
		if (this.getConfig().get("restore-defaults") == null || this.getConfig().getBoolean("restore-defaults")) {
			this.getConfig().set("restore-defaults", false);
			this.getConfig().set("mine.world", "world");
			this.getConfig().set("mine.block-one.x", 0);
			this.getConfig().set("mine.block-one.y", 0);
			this.getConfig().set("mine.block-one.z", 0);
			this.getConfig().set("mine.block-two.x", 0);
			this.getConfig().set("mine.block-two.y", 0);
			this.getConfig().set("mine.block-two.z", 0);
		}
		this.saveConfig();
	}

	private void registerCommands() {
		this.getCommand("addpoints").setExecutor(new AddPointsCMD(this));
		this.getCommand("setmineparty").setExecutor(new SetMineCMD(this));
	}

	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new BlockMineEvent(this), this);
	}
	
	private WorldEditPlugin worldEditPlugin;
	
	public WorldEditPlugin getWorldEditPlugin() {
		return worldEditPlugin;
	}
	
	
}
