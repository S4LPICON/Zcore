package com.s4lpicon.scenes.scenebuilder;

import java.io.File;
import java.io.IOException;

import org.jcodec.api.JCodecException;

/*Clase SceneBuilder 
 * 
 * 
 * 
 * 
 * 
 */

public class SceneBuilder {
    private File filePathOrigin;
    private File filePathDestination;
    private int fps;

    public SceneBuilder(File filePathOrigin, File filePathOutput, int fps) {
        this.filePathDestination = filePathOutput;
        this.filePathOrigin = filePathOrigin;
        this.fps = fps;
    }

    public boolean buildScene() throws IOException, JCodecException {

        extractFrames();

        // ya tengo los frames en la carpeta de salida, ahora tengo que crear la escena

        // Logic to build the scene using the provided file paths and fps
        System.out.println(
                "Building scene from " + filePathOrigin + " to " + filePathDestination + " at " + fps + " fps.");
        // Simulate building process
        return true; // Return true if successful, false otherwise
    }

    public boolean extractFrames() throws IOException, JCodecException {
        File framesFolder = new File(filePathDestination, "Zcore/scenes/scene_");
        if (!framesFolder.exists()) {
            framesFolder.mkdirs();
        }
        VideoFrameExtractor.extractFrames(filePathOrigin, filePathDestination, 24.0);
        return true;
    }

    public boolean organizeFrames() {

        return true;
    }
}
