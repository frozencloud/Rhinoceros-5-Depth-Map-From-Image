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
        
        //Duke Learn To Program library that allows the user to select the image they would like to process. 
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            
        //Stores the file you chose in a variable
        ImageResource inImage = new ImageResource(f);
        
        //gets the image dimensions
        int X = inImage.getWidth();
        int Y = inImage.getHeight();
        
        //creates a new array to store the luminance of the pixels in with the dimensions of the image
        int grayArray[][] = new int[X][Y];
        
        //For loop that uses the .pixels() method to...
        for (Pixel pixel: inImage.pixels()){
            
            //store current coordinates
            int curX = pixel.getX();
            int curY = pixel.getY();
            
            //create a variable that stores the attributes of the current pixel
            Pixel inPixel = inImage.getPixel(curX, curY);
            
            //extract those attributes and takes the average of sum of the RGB values
            int grayValue = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            
            //stores the average luminance in the previously defined z-coordinate array
            grayArray[curX][curY] = grayValue;
            
            //repeat for all pixels
        }
        
        //creates a new array to store the effects of the...
        int blurredArray[][] = new int [X][Y];
        
        //Simple blur algorithm
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
        
        //sets up the text document writer and defines the name of the file
        try( PrintWriter writer = new PrintWriter("textFile.txt")){
            
            //loop for writing the curve points to the command text document in the x direction
        for (int i = 0; i < X; i++){
            
            //set the modulus operand to how many curves you want per row or column of the image coordinates    
            if(i%1 == 0){
                    writer.println("InterpCrv");
                for (int j = 0; j < (Y); j++){
                    String curveX = (i + "," + j + "," + blurredArray[i][j]);
                    writer.println(curveX);
                }
                    writer.println("Enter");
                }
            }
        
            //loop for writing the curve points to the command text document in the y direction
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
        
        //series of code that...
        //---creates a curve network
        writer.println("SelCrv");
        writer.println("-_NetworkSrf Enter");
        
        //---selects the surface
        writer.println("SelNone");
        writer.println("SelSrf");
        
        //---and scales the surface based on the height of your image in luminance
        writer.println("-_Scale1D 0 " + ("0,0," + maxBlurredArray) + " 0,0,10");
        writer.close();
    }catch (IOException e){
        System.out.println("failure");
        }
    }
}
}
