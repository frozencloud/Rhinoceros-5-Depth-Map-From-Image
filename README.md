# Rhinoceros-5-Depth-Map-From-Image
#
# @author: Greg King
#
# This is a Java program that creates a depth map based on the luminance of a pixel in an image. The program does the following things.
#
#
#  --- 1. Accepts the user input for an image (Keep the image down to about 100 x 100 px unless you have a serious machine)
#  --- 2. Stores the image dimensions and creates a 2d array with the same dimensions of the image
#  --- 3. Stores the average luminance of each pixel in the respective coordinate in the 2d array that was created
#  --- 4. Runs a simple blur effect on the image. 
#  --- 5. Writes commands in a text document that creates curves of every row and column in the dimensions of the image and writes the average luminance as the coordinate of the z point in the curve
#  --- 6. Creates a Network Surface and scales it down to a usable size. The program leaves the wire mesh behind. 
# 
#
#
# There are a few things you need to easily run this program. There is no GUI as of yet, but I typically run this program with
# a version of BlueJ that has been provided by Duke Software Team for open source use that can be found here...
#
# "http://www.dukelearntoprogram.com/downloads/bluej.php?course=2"
#
# If you download this version of BlueJ it will come with the library provided by Duke's software team that is used in the program. ("edu.duke.*")
# If you know how to import that library into your usual IDE it can also be found on the same URL provided above. 
# 
#
# If you plan to use the BlueJ environment to implement this program and are new the the BlueJ environment you can implement this program through these steps...
#
#  --- 1. Download all the files in this branch of the repository on GitHub and put them all into a single folder.
#  --- 2. Open the BlueJ environment and press "ctrl + o" to open the folder you just put all of the assets in. 
#  --- ---(The folder should show up with the BlueJ logo on it)
#  --- 3. Compile the program by right clicking on the box that says "DepthMap" on it, and clicking Compile. 
#  --- 4. Create a new instance of the program by right clicking on the box again and select "new DepthMap()" and press "Ok" on the naming prompt.
#  --- 5. A red box on the bottom left of your window should appear that bears the name of what was in the naming prompt. 
#  --- 6. Right click on the red box and select "void makeMap()"
#  --- 7. A prompt will come up allowing you to select your image.
#  --- 8. The program will run and create a text file called "textFile.txt" in the same folder that you opened in BlueJ.
#  --- 9. Open Rhinoceros 5 and type "ReadCommandFile" into the command line. It will prompt you to select your new text file from the program folder
#  --- 10. The program will start to build your depth map.
#
#
#
#
