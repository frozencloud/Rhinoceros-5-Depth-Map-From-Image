/** Creates a depth map in Rhinoceros 5 by reading the pixels in an 
 *  image file and creating curves on the x and y axes that map the 
 *  luminance of the pixel to the z axis.
 * 
 * @author Greg King
 */
import edu.duke.*;
import java.io.*;
public class DepthMap {
    
    public void makeMap(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
        ImageResource inImage = new ImageResource(f);
        
        int X = inImage.getWidth();
        int Y = inImage.getHeight();
        
        int grayArray[][] = new int[X][Y];
        
        for (Pixel pixel: inImage.pixels()){
            
            int curX = pixel.getX();
            int curY = pixel.getY();
            
            Pixel inPixel = inImage.getPixel(curX, curY);
            
            int grayValue = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            
            grayArray[curX][curY] = grayValue;

        }
        int blurredArray[][] = new int [X][Y];
        for (int i = 1 ; i< (grayArray.length - 1); i++){
            for(int j = 1; j < (grayArray[i].length - 1); j++){
                int sum = 0;
                for(int kx = -1; kx <= 1; kx++){
                    for(int ky = -1; ky <= 1; ky++){
                        sum += grayArray[i + kx][j + ky];
                    }
                }
                int avgSum = (sum / 9);
                for(int kx = -1; kx <= 1; kx++){
                    for(int ky = -1; ky <= 1; ky++){
                        blurredArray[i + kx][j + ky] = avgSum;
                    }
                }
            }
        }
        //finds the max value in the grayArray for scaling purposes at the end
        int maxBlurredArray = 0;
        for(int i = 0; i < blurredArray.length; i++){
            for ( int j = 0; j < blurredArray[i].length; j++){
                if (blurredArray[i][j] > maxBlurredArray){
                    maxBlurredArray = blurredArray[i][j];
                }
            }
        }
        try( PrintWriter writer = new PrintWriter("textFile.txt")){
        for (int i = 0; i < X; i++){
            //set the modulus to how many curves you want per the image.    
            if(i%1 == 0){
                    writer.println("InterpCrv");
                for (int j = 0; j < (Y); j++){
                    String curveX = (i + "," + j + "," + blurredArray[i][j]);
                    writer.println(curveX);
                }
                    writer.println("Enter");
                }
            }
        
        for (int jj = 0; jj < Y; jj++){
            if(jj%1 == 0){
            writer.println("InterpCrv");
            for (int ii = 0; ii < (X); ii++){
                String curveY = (ii + "," + jj + "," + blurredArray[ii][jj]);
                writer.println(curveY);
            }
            writer.println("Enter");
        }
        }
        writer.println("SelCrv");
        writer.println("-_NetworkSrf Enter");
        writer.println("SelNone");
        writer.println("SelSrf");
        writer.println("-_Scale1D 0 " + ("0,0," + maxBlurredArray) + " 0,0,10");
        writer.close();
    }catch (IOException e){
        System.out.println("failure");
        }
    }
}
}
