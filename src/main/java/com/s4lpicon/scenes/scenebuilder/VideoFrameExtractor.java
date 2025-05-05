package com.s4lpicon.scenes.scenebuilder;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/* Clase VideoFrameExtractor 
 * 
 * * Esta clase se encarga de extraer frames de un video y guardarlos como imágenes PNG.
 *  Utiliza la biblioteca JCodec para manejar la decodificación de video.
 * es decir se le da la ruta de un video y se le da la ruta de salida donde se guardaran los frames
 * y la cantidad de frames por segundo que se desea extraer.
 */

public class VideoFrameExtractor {

    public static void extractFrames(File videoFile, File outputDir, double fps) throws IOException, JCodecException {
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(videoFile));
        int frameNumber = 0;
        double secondsPerFrame = 1.0 / fps;
        double currentTime = 0;

        while (true) {
            grab.seekToSecondPrecise(currentTime);
            Picture picture = grab.getNativeFrame();
            if (picture == null)
                break;

            BufferedImage image = AWTUtil.toBufferedImage(picture);
            File output = new File(outputDir, String.format("frame_%04d.png", frameNumber));
            ImageIO.write(image, "png", output);

            frameNumber++;
            currentTime += secondsPerFrame;
        }

        System.out.println("Extraídos " + frameNumber + " frames a " + fps + " FPS.");
    }
}
