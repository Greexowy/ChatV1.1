package pl.Greexowy.ChatManger.listeners;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class AsyncPlayerChatListener implements Listener {
    public static boolean chat = true;
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onChat(org.bukkit.event.player.AsyncPlayerChatEvent event) {
        Player player = (Player) event.getPlayer();
        if(!chat) {
            if(!(player.hasPermission("Chat.bypass.blocked"))) {
                player.sendMessage(ChatColor.GRAY + "Chat jest obecnie " + ChatColor.ITALIC + ChatColor.RED + "zablokowany" + ChatColor.RESET + ChatColor.GRAY + "!");
                event.setCancelled(true);
            }
        }
    }
}
