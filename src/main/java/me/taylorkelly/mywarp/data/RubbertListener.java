package me.taylorkelly.mywarp.data;

import me.taylorkelly.mywarp.MyWarp;
import me.taylorkelly.mywarp.utils.CommandUtils;
import me.taylorkelly.mywarp.utils.commands.CommandException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by TimeTheCat on 10/6/2016.
 */
public class RubbertListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (MyWarp.players.contains(event.getPlayer())) {

            String name = event.getMessage().split(" ")[0];


            try {
                CommandUtils.checkTotalLimit(event.getPlayer());
                CommandUtils.checkPrivateLimit(event.getPlayer());
                CommandUtils.checkWarpname(event.getPlayer(), name);
            } catch (CommandException e) {
                e.printStackTrace();
                event.getPlayer().sendMessage("An error occurred and the warp could not be created.");
            }
            MyWarp.inst().getWarpManager().addWarpPrivate(name, event.getPlayer());
            event.getPlayer().sendMessage(MyWarp.inst().getLocalizationManager()
                    .getString("commands.create-private.created-successful", event.getPlayer(), name));
        } else if (MyWarp.pplayers.contains(event.getPlayer())) {

            String name = event.getMessage().split(" ")[0];

            try {
                CommandUtils.checkTotalLimit(event.getPlayer());
                CommandUtils.checkPublicLimit(event.getPlayer());
                CommandUtils.checkWarpname(event.getPlayer(), name);
            } catch (CommandException e) {
                e.printStackTrace();
                event.getPlayer().sendMessage("An error occurred and the warp could not be created.");
            }

            MyWarp.inst().getWarpManager().addWarpPublic(name, event.getPlayer());
            event.getPlayer().sendMessage(MyWarp.inst().getLocalizationManager()
                    .getString("commands.create.created-successful", event.getPlayer(), name));
        }
    }
}
