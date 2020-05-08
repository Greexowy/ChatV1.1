package pl.Greexowy.ChatManger;
import org.bukkit.plugin.java.JavaPlugin;
import pl.Greexowy.ChatManger.commands.ChatCommand;
import pl.Greexowy.ChatManger.listeners.AsyncPlayerChatListener;
import pl.Greexowy.ChatManger.listeners.InventoryClickListener;

public class ChatManagerPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("chat").setExecutor(new ChatCommand());
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
    }
}
