package com.s4lpicon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.s4lpicon.commands.SceneCommand;
import com.s4lpicon.scenes.ScenesManager;

public final class Zcore extends JavaPlugin {

    ScenesManager sceneManager = new ScenesManager(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getScheduler().runTask(this, () -> {
            getCommand("scene").setExecutor(new SceneCommand(sceneManager));
        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
