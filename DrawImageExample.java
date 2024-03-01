import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
public class DrawImageExample {

    // 워터마크 이미지 크기 20px
    private final static int WATERMARK_SIZE = 20;

    // 여유 공간 10px
    private final static int WHITE_SPACE = 10;

    // WIDTH가 20일 때 여유 공간을 10으로 주게되면 10px만큼 잘리게 됨
    private final static int WHITE_SPACE_WIDTH = WATERMARK_SIZE + WHITE_SPACE;
    public static void main(String[] args) throws IOException {
        // 필요한 파일들을 정의
        File file = new File("./Moon.png");
        File watermarkFile = new File("./Check.png");
        File processedFile = new File("Result.png");

        // 파일을 이미지로 Read
        Image image = readImage(file); // 원본 이미지
        Image watermarkImage = readImage(watermarkFile); // 워터마크 이미지
        Image resizedWaterMarkImage = resizeImage(watermarkImage); // 워터마크 이미지 축소

        // 워터마크를 추가하고 파일로 저장
        BufferedImage processedImage = addWaterMark(image,resizedWaterMarkImage);
        ImageIO.write(processedImage,"png",processedFile);
    }
    public static Image readImage(File file) throws IOException {
        return ImageIO.read(file);
    }
    public static Image resizeImage(Image image){
        return image.getScaledInstance(WATERMARK_SIZE,WATERMARK_SIZE,Image.SCALE_REPLICATE);
    }
    public static BufferedImage addWaterMark(Image image, Image watermark){
        BufferedImage processImage = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics graphics = processImage.getGraphics();
        graphics.drawImage(image,0,0,null);
        graphics.drawImage(watermark,image.getWidth(null) - WHITE_SPACE_WIDTH, WHITE_SPACE, null);
        graphics.dispose();
        return processImage;
    }
}
