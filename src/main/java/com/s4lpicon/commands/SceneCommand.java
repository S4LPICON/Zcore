package com.s4lpicon.commands;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jcodec.api.JCodecException;

import com.s4lpicon.scenes.ScenesManager;

public class SceneCommand implements CommandExecutor {

    private final ScenesManager scenesManager;

    public SceneCommand(ScenesManager sceneManager) {
        this.scenesManager = sceneManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2 || !args[0].equalsIgnoreCase("start")) {
            sender.sendMessage("§cUso correcto: /scene start <id>");
            return true;
        }

        String sceneId = args[1];

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cEste comando solo puede ser usado por jugadores.");
            return true;
        }

        Player player = (Player) sender;
        try {
            scenesManager.buildScene();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JCodecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
         * boolean started = scenesManager.startScene(player, sceneId);
         * 
         * if (started) {
         * player.sendMessage("§aEscena iniciada: " + sceneId);
         * } else {
         * player.sendMessage("§cNo se pudo iniciar la escena '" + sceneId +
         * "'. ¿Existe?");
         * }
         */
        return true;
    }
}
