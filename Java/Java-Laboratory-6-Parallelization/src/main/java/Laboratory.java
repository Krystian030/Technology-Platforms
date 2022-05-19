import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.processing.SupportedSourceVersion;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Laboratory {

    public static void photoTransformation(int poolSize){
        ForkJoinPool pool = new ForkJoinPool(poolSize);
        long time = System.currentTimeMillis();
        try {
            pool.submit(()->{

                List<Path> files = null;
                Path source = Path.of("src/main/resources/load");

                try (Stream<Path> stream = Files.list(source).parallel()){
                    files = stream.collect(Collectors.toList());

                    Stream<Pair<String, BufferedImage>> load = (Stream<Pair<String, BufferedImage>>) files.stream().parallel()
                            .map(value -> {
                                Pair<String,BufferedImage> p = null;
                                String name = value.getFileName().toString();
                                try {
                                    p = Pair.of(name,ImageIO.read(value.toFile()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return p;
                            });

                    Stream<Pair<String, BufferedImage>> newPictures = load.parallel()
                            .map(value -> {
                                BufferedImage original = value.getRight();
                                BufferedImage image = new BufferedImage(original.getWidth(),
                                        original.getHeight(),
                                        original.getType());

                                for (int i = 0; i < original.getWidth(); i++) {
                                    for (int j = 0; j < original.getHeight(); j++) {
                                        int rgb = original.getRGB(i, j);
                                        Color color = new Color(rgb);
                                        int red = color.getRed();
                                        int green = color.getGreen();
                                        int blue = color.getBlue();
                                        Color outColor = new Color(red,blue,green);
                                        int outRgb = outColor.getRGB();
                                        image.setRGB(i, j, outRgb);
                                    }
                                }
                                Pair<String,BufferedImage> p = Pair.of(value.getLeft(),image);
                                return p;
                            });
                    newPictures.parallel().forEach(value -> {
                        File file = new File("src/main/resources/save/" + value.getLeft());
                        try {
                            ImageIO.write(value.getRight(),"jpg",file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).get();
        } catch (InterruptedException | ExecutionException ex) {}
        long endTime = System.currentTimeMillis() - time;
        pool.shutdown();

        System.out.println("============ Time ============");
        System.out.println("Pool size: " + Integer.toString(poolSize));
        System.out.println("Time: " + Long.toString(endTime) + " ms");
        System.out.println("====================================");

    }

    public static void main(String[] args) {

            photoTransformation(20);

    }
}
