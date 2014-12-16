import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class TestOpencv {

    public TestOpencv() {

    }

    public static void main(String[] args) {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	
	Mat image;
	image = Highgui.imread("load",Highgui.CV_LOAD_IMAGE_COLOR);
	//namedWindow( "Display window", WINDOW_AUTOSIZE );// Create a window for display.
//	imshow( "Display window", image );                   // Show our image inside it.
//	waitKey(0);
    }

}
