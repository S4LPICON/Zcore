package com.s4lpicon;

import org.bukkit.plugin.java.JavaPlugin;

import com.s4lpicon.commands.SceneCommand;
import com.s4lpicon.scenes.ScenesManager;

public final class Zcore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ScenesManager sceneManager = new ScenesManager(this);
        getCommand("scene").setExecutor(new SceneCommand(sceneManager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
