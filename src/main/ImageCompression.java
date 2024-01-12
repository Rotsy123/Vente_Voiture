// import java.io.ByteArrayOutputStream;
// import java.io.IOException;

// import javax.imageio.ImageIO;

// public class ImageCompression {
//     public static BufferedImage loadImage(String imageName) {
//         try {
//             return ImageIO.read(ImageCompression.class.getClassLoader().getResourceAsStream("ressources/" + imageName));
//         } catch (IOException exc) {
//             exc.printStackTrace();
//         }
//         return null;
//     }

//     public static byte[] compressImage(BufferedImage image) {
//         try {
//             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//             ImageIO.write(image, "jpg", byteArrayOutputStream);
//             return byteArrayOutputStream;
//         } catch (Exception e) {
//             // TODO: handle exception
//         }
//         return null;
//     }

//     public static byte[] decompressImage(byte[] compressedBytes) {
//         try {
//             ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedBytes);
//             ImageIO.read(image, "jpg", byteArrayOutputStream);
//             return byteArrayOutputStream;
//         } catch (Exception e) {
//             // TODO: handle exception
//         }
//         return null;
//     }
// }

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ImageCompression {
    public static void main(String[] args) {
        // Charger l'image depuis les ressources
        BufferedImage originalImage = loadImage("example.jpg");

        // Compresser l'image
        byte[] compressedBytes = compressImage(originalImage);

        // Stocker les bits compressés (vous pouvez les sauvegarder dans une base de
        // données ou autre)
        // Exemple: stockerBytesDansLaBaseDeDonnees(compressedBytes);

        // Charger les bits compressés depuis la source de stockage
        // Exemple: byte[] bitsRecuperes = recupererBytesDeLaBaseDeDonnees();

        // Afficher l'image à partir des bits compressés
        BufferedImage decompressedImage = decompressImage(compressedBytes);

        // Vous pouvez maintenant manipuler l'image décompressée selon vos besoins
    }

    private static BufferedImage loadImage(String imageName) {
        try {
            return ImageIO.read(ImageCompression.class.getClassLoader().getResourceAsStream("resources/" + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] compressImage(BufferedImage image) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage decompressImage(byte[] compressedBytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(compressedBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
