package pl.Greexowy.ChatManger.listeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static pl.Greexowy.ChatManger.listeners.AsyncPlayerChatListener.chat;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getTitle().equals(ChatColor.RED + "Chat " + ChatColor.GRAY + "- " + ChatColor.YELLOW + "Opcje")) {
            if(event.getCurrentItem().getItemMeta() != null) {
                if(event.getCurrentItem().getItemMeta().getDisplayName() != null){
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) {
                        event.setCancelled(true);
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Stan chatu: " + ChatColor.DARK_GREEN + ChatColor.BOLD + "WLACZONY")) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.GRAY + "Chat jest obecnie " + ChatColor.GREEN + ChatColor.ITALIC + "WLACZONY" + ChatColor.RESET + ChatColor.GRAY + "!");
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Stan chatu: " + ChatColor.RED + ChatColor.BOLD + "WYLACZONY")) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.GRAY + "Chat jest obecnie " + ChatColor.RED + ChatColor.ITALIC + "WYLACZONY" + ChatColor.RESET + ChatColor.GRAY + "!");
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Wyczysc")) {
                        event.setCancelled(true);
                        player.closeInventory();
                        for(int i = 0; i < 100; i++) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.sendMessage(" ");
                            }
                        }
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage(" ");
                            players.sendMessage(ChatColor.GRAY + "Chat zostal wyczyszczony!");
                            players.sendMessage(" ");
                        }
                        System.out.println("Chat zostal wyczyszczony przez " + player.getDisplayName());
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Odblokuj")) {
                        event.setCancelled(true);
                        if(!chat) {
                            chat = true;
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if(players.getOpenInventory().getTopInventory().getTitle().equals((ChatColor.RED + "Chat " + ChatColor.GRAY + "- " + ChatColor.YELLOW + "Opcje"))) {
                                    Inventory inventory = players.getOpenInventory().getTopInventory();
                                    ItemStack magmaCream = new ItemStack(Material.MAGMA_CREAM, 1);
                                    ItemMeta magmaCreamMeta = magmaCream.getItemMeta();
                                    magmaCreamMeta.setDisplayName(ChatColor.GOLD + "Stan chatu: " + ChatColor.DARK_GREEN + ChatColor.BOLD + "WLACZONY");
                                    ArrayList<String> magmaCreamLore = new ArrayList<String>();
                                    magmaCreamLore.add(ChatColor.GRAY + "Na chacie moze");
                                    magmaCreamLore.add(ChatColor.GRAY + "pisac kazdy gracz!");
                                    magmaCreamMeta.setLore(magmaCreamLore);
                                    inventory.addItem(magmaCream);
                                    magmaCream.setItemMeta(magmaCreamMeta);
                                    inventory.setItem(31, magmaCream);
                                }
                            }
                            Bukkit.broadcastMessage(ChatColor.GRAY + "Chat zostal " + ChatColor.DARK_GREEN + ChatColor.ITALIC + "odblokowany" + ChatColor.RESET + ChatColor.GRAY + "!");
                        } else {
                            player.sendMessage(ChatColor.GRAY + "Przeciez chat jest juz " + ChatColor.DARK_GREEN + ChatColor.ITALIC + "odblokowany" + ChatColor.RESET + ChatColor.GRAY + "!");
                        }
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Zablokuj")) {
                        event.setCancelled(true);
                        if(chat) {
                            chat = false;
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if(players.getOpenInventory().getTopInventory().getTitle().equals((ChatColor.RED + "Chat " + ChatColor.GRAY + "- " + ChatColor.YELLOW + "Opcje"))) {
                                    Inventory inventory = players.getOpenInventory().getTopInventory();
                                    ItemStack magmaCream = new ItemStack(Material.MAGMA_CREAM, 1);
                                    ItemMeta magmaCreamMeta = magmaCream.getItemMeta();
                                    magmaCreamMeta.setDisplayName(ChatColor.GOLD + "Stan chatu: " + ChatColor.RED + ChatColor.BOLD + "WYLACZONY");
                                    ArrayList<String> magmaCreamLore = new ArrayList<String>();
                                    magmaCreamLore.add(ChatColor.GRAY + "Na chacie moga pisac tylko");
                                    magmaCreamLore.add(ChatColor.GRAY + "osoby z ranga administracyjna!");
                                    magmaCreamMeta.setLore(magmaCreamLore);
                                    inventory.addItem(magmaCream);
                                    magmaCream.setItemMeta(magmaCreamMeta);
                                    inventory.setItem(31, magmaCream);
                                }
                            }
                            Bukkit.broadcastMessage(ChatColor.GRAY + "Chat zostal " + ChatColor.RED + ChatColor.ITALIC + "zablokowany" + ChatColor.RESET + ChatColor.GRAY + "!");
                        } else {
                            player.sendMessage(ChatColor.GRAY + "Przeciez chat jest juz " + ChatColor.RED + ChatColor.ITALIC + "zablokowany" + ChatColor.RESET + ChatColor.GRAY + "!");
                        }
                    }
                }
            }
        }
    }
}
