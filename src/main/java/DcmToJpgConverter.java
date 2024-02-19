import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;
import org.dcm4che3.imageio.plugins.dcm.DicomImageReader;


import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DcmToJpgConverter {
    public static void main(String[] args) {
        //.dcm file path
        String dcmFilePath = "/home/nuchange/purna123/dcmfiles/dcmfile1.dcm";
        //.jpg file path to create image file
        String jpgFilePath = "/home/nuchange/purna123/dcmtojpgconvertedfile.jpg";
        convertDcmToJpg(dcmFilePath, jpgFilePath);
    }
    public static void convertDcmToJpg(String dcmFilePath, String jpgFilePath) {
        try {
            // Load DICOM image from file path
            FileImageInputStream stream = new FileImageInputStream(new File(dcmFilePath));
            DicomImageReader reader = new DicomImageReader(null);
            reader.setInput(stream);
            DicomImageReadParam param = (DicomImageReadParam) reader.getDefaultReadParam();
            BufferedImage image = reader.read(0, param);
            reader.dispose();

            // Write JPEG image
            File jpgFile = new File(jpgFilePath);
            ImageIO.write(image, "jpg", jpgFile);
            System.out.println("Conversion complete. JPEG image saved to: " + jpgFile.getPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
