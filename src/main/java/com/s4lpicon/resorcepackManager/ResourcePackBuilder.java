package com.s4lpicon.resorcepackManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ResourcePackBuilder {

    private static final int GLYPH_ASCII_START = 0xE000; // Inicio del rango Unicode privado
    private static final int FRAME_WIDTH = 128;
    private static final int FRAME_HEIGHT = 128;

    public static void createResourcePack(File inputImagesFolder, File outputPackFolder, String fontName)
            throws IOException {
        if (!inputImagesFolder.exists() || !inputImagesFolder.isDirectory()) {
            throw new IllegalArgumentException("La carpeta de imágenes no existe o no es válida.");
        }

        // Estructura del resource pack
        File assetsPath = new File(outputPackFolder, "assets/minecraft");
        File fontJsonFile = new File(assetsPath, "font/" + fontName + ".json");
        File texturesPath = new File(assetsPath, "textures/" + fontName);
        texturesPath.mkdirs();

        // Copiar imágenes y construir JSON
        JsonArray providers = new JsonArray();
        File[] imageFiles = inputImagesFolder.listFiles((dir, name) -> name.endsWith(".png"));
        Arrays.sort(imageFiles); // Asegura orden de frames

        int charCode = GLYPH_ASCII_START;
        for (File img : imageFiles) {
            String imageName = img.getName();
            Path targetPath = texturesPath.toPath().resolve(imageName);
            Files.copy(img.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            JsonObject provider = new JsonObject();
            provider.addProperty("type", "bitmap");
            provider.addProperty("file", fontName + "/" + imageName);
            provider.addProperty("ascent", FRAME_HEIGHT);
            provider.addProperty("height", FRAME_HEIGHT);

            JsonArray chars = new JsonArray();
            chars.add(String.format("\\u%04X", charCode));
            provider.add("chars", chars);

            providers.add(provider);
            charCode++;
        }

        // Escribir font JSON
        JsonObject fontJson = new JsonObject();
        fontJson.add("providers", providers);

        fontJsonFile.getParentFile().mkdirs();
        try (Writer writer = new FileWriter(fontJsonFile)) {
            new Gson().toJson(fontJson, writer);
        }

        // Crear pack.mcmeta
        File mcmeta = new File(outputPackFolder, "pack.mcmeta");
        try (Writer writer = new FileWriter(mcmeta)) {
            writer.write("{\n" +
                    "  \"pack\": {\n" +
                    "    \"pack_format\": 26,\n" +
                    "    \"description\": \"Generated Unicode Resource Pack\"\n" +
                    "  }\n" +
                    "}");
        }

        System.out.println(" Resource pack generado exitosamente en: " + outputPackFolder.getAbsolutePath());
    }
}
