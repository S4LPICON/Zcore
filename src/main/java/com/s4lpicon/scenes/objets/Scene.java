package com.s4lpicon.scenes.objets;

public class Scene {
    private int cant_frames;
    private int fps;
    private String id;
    private String unicode_start;
    private String unicode_end;

    public Scene(String start, String end, int cant_frames, int fps) {
        this.fps = fps;
        this.cant_frames = cant_frames;
        this.unicode_start = start;
        this.unicode_end = end;
    }

}
