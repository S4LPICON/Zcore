package com.s4lpicon.scenes.scenebuilder;

import java.io.File;
import java.io.IOException;

import org.jcodec.api.JCodecException;

import com.s4lpicon.scenes.objets.Scene;

/*Clase SceneBuilder 
 * 
 * 
 * 
 * 
 * 
 */

public class SceneBuilder {
    private File filePathOrigin;
    private File filePathDetination;
    private int fps;

    public SceneBuilder(File filePathOrigin, File filePathOutput, int fps) {
        this.filePathDetination = filePathOutput;
        this.filePathOrigin = filePathOrigin;
        this.filePathDetination = null;
        this.fps = fps;
    }

    public Scene buildScene() throws IOException, JCodecException {
        extractFrames();

        // ya tengo los frames en la carpeta de salida, ahora tengo que crear la escena
        Scene scene = new Scene(filePathDetination.getName(), null, fps);

        // Logic to build the scene using the provided file paths and fps
        System.out.println(
                "Building scene from " + filePathOrigin + " to " + filePathDetination + " at " + fps + " fps.");
        // Simulate building process
        return null; // Return true if successful, false otherwise
    }

    public boolean extractFrames() throws IOException, JCodecException {
        File framesFolder = new File(filePathDetination, "Zcore/scenes/scene_");
        if (!framesFolder.exists()) {
            framesFolder.mkdirs();
        }
        VideoFrameExtractor.extractFrames(filePathOrigin, filePathDetination, 24.0);
        return true;
    }
}
