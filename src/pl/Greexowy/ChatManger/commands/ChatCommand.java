package pl.Greexowy.ChatManger.commands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;

import static pl.Greexowy.ChatManger.listeners.AsyncPlayerChatListener.chat;

public class ChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("Chat.open")) {

                // Utworzenie Inventory
                Inventory inventory = Bukkit.createInventory(null, 36, ChatColor.RED + "Chat " + ChatColor.GRAY + "- " + ChatColor.YELLOW + "Opcje");

                // Item dodatkowy - tło
                ItemStack glassPanes = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
                ItemMeta glassMeta = glassPanes.getItemMeta();
                glassMeta.setDisplayName(" ");
                glassPanes.setItemMeta(glassMeta);
                inventory.addItem(glassPanes);
                for (int i = 0; i < 36; i++) {
                    inventory.setItem(i, glassPanes);
                }

                // Item czyszczenia chatu
                ItemStack paper = new ItemStack(Material.PAPER, 1);
                ItemMeta paperMeta = paper.getItemMeta();
                paperMeta.setDisplayName(ChatColor.YELLOW + "Wyczysc");
                ArrayList<String> paperLore = new ArrayList<String>();
                paperLore.add(ChatColor.GRAY + "Wyczysc chat dla wszystkich");
                paperLore.add(ChatColor.GRAY + "graczy na twoim podserwerze!");
                paperMeta.setLore(paperLore);
                inventory.addItem(paper);
                paper.setItemMeta(paperMeta);
                inventory.setItem(13, paper);

                // Item włączania chatu.
                ItemStack greenWool = new ItemStack(Material.WOOL, 1, (short) 5);
                ItemMeta greenWoolMeta = greenWool.getItemMeta();
                greenWoolMeta.setDisplayName(ChatColor.DARK_GREEN + "Odblokuj");
                ArrayList<String> greenWoolLore = new ArrayList<String>();
                greenWoolLore.add(ChatColor.GRAY + "Odblokuj chat dla wszystkich");
                greenWoolLore.add(ChatColor.GRAY + "graczy na twoim podserwerze!");
                greenWoolMeta.setLore(greenWoolLore);
                inventory.addItem(greenWool);
                greenWool.setItemMeta(greenWoolMeta);
                inventory.setItem(11, greenWool);

                // Item wyłączania chatu.
                ItemStack redWool = new ItemStack(Material.WOOL, 1, (short) 14);
                ItemMeta redWoolMeta = redWool.getItemMeta();
                redWoolMeta.setDisplayName(ChatColor.RED + "Zablokuj");
                ArrayList<String> redWoolLore = new ArrayList<String>();
                redWoolLore.add(ChatColor.GRAY + "Zablokuj chat dla wszystkich");
                redWoolLore.add(ChatColor.GRAY + "graczy na twoim podserwerze!");
                redWoolMeta.setLore(redWoolLore);
                inventory.addItem(redWool);
                redWool.setItemMeta(redWoolMeta);
                inventory.setItem(15, redWool);

                // Item pokazywania stanu chatu
                ItemStack magmaCream = new ItemStack(Material.MAGMA_CREAM, 1);
                ItemMeta magmaCreamMeta = magmaCream.getItemMeta();
                if(chat) {
                    magmaCreamMeta.setDisplayName(ChatColor.GOLD + "Stan chatu: " + ChatColor.DARK_GREEN + ChatColor.BOLD + "WLACZONY");
                    ArrayList<String> magmaCreamLore = new ArrayList<String>();
                    magmaCreamLore.add(ChatColor.GRAY + "Na chacie moga pisac tylko");
                    magmaCreamLore.add(ChatColor.GRAY + "osoby z ranga administracyjna!");
                    magmaCreamMeta.setLore(magmaCreamLore);
                } else {
                    magmaCreamMeta.setDisplayName(ChatColor.GOLD + "Stan chatu: " + ChatColor.RED + ChatColor.BOLD + "WYLACZONY");
                    ArrayList<String> magmaCreamLore = new ArrayList<String>();
                    magmaCreamLore.add(ChatColor.GRAY + "Na chacie moze");
                    magmaCreamLore.add(ChatColor.GRAY + "pisac kazdy gracz!");
                    magmaCreamMeta.setLore(magmaCreamLore);
                }
                inventory.addItem(magmaCream);
                magmaCream.setItemMeta(magmaCreamMeta);
                inventory.setItem(31, magmaCream);

                // Otwarcie Inventory
                player.openInventory(inventory);
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Nieznana Komenda!");
                return false;
            }
        } else {
            System.out.println("Tylko gracze maja dostep do tej komendy!");
            return false;
        }
    }
}
