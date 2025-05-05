package com.s4lpicon.scenes;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jcodec.api.JCodecException;

import com.s4lpicon.scenes.objets.Scene;
import com.s4lpicon.scenes.scenebuilder.SceneBuilder;

import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ScenesManager {

    private JavaPlugin plugin;
    private final Map<String, Scene> scenes;
    private SceneBuilder sBuilder;

    public ScenesManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.scenes = new HashMap<>();
        this.sBuilder = new SceneBuilder(new File(plugin.getDataFolder(), "videoInput/video.mp4"),
                new File(plugin.getDataFolder(), "outPut"), 24);
    }

    public void buildScene() throws IOException, JCodecException {

        sBuilder.buildScene();

    }

    public boolean startScene(Player player, String sceneId) {

        return true;

    }
}