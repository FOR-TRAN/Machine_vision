import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import java.util.concurrent.TimeUnit;

public class Main {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    static VideoCapture cap;

    public static void main(String[] args) {
        initcam();
    }

    public static void initcam() {
        cap = new VideoCapture(1, Videoio.CAP_DSHOW);
        cap.set(Videoio.CAP_PROP_FPS, 30); // Частота кадров
        cap.set(Videoio.CAP_PROP_AUTO_EXPOSURE, 3); // auto mode
        cap.set(Videoio.CAP_PROP_AUTO_EXPOSURE, 1); // manual mode
        cap.set(Videoio.CAP_PROP_EXPOSURE, -6);
        cap.set(Videoio.CAP_PROP_FRAME_WIDTH, 640.0);   //640
        cap.set(Videoio.CAP_PROP_FRAME_HEIGHT, 360.0);  //360
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Mat getkadr() {
        Mat img1 = new Mat();
        Mat img2 = new Mat();
        Mat img3 = new Mat();
        long lasttime1, lasttime2, lasttime3;

        for (int c = 0; c < 2; c++) {
            boolean ret1 = cap.read(img1);
            lasttime1 = System.currentTimeMillis();
            boolean ret2 = cap.read(img2);
            lasttime2 = System.currentTimeMillis();
            boolean ret3 = cap.read(img3);
            lasttime3 = System.currentTimeMillis();

            if (ret3) return img3;
            if (ret2) {
                System.out.println("NET ZAHVATA TRETIEGO KADRA");
                return img2;
            }
            if (ret1) {
                System.out.println("NET ZAHVATA VTOROGO I TRETIEGO KADRA");
                return img1;
            }

            System.out.println("NET ZAHVATA KADROV...PEREZAPUSK KAMERY");
            cap.release();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("PEREINICIALIZACIYA KAMERY " + (c + 1) + "-RAZ.");
            initcam();
        }
        System.exit(0);
        return null;
    }

    public static void tcpLOGO(String brak) {
        int q = 0;
        for (int i = 0; i < 4; i++) {
            if (brak.charAt(i) == ' ') {
                q += Math.pow(2, i);
            }
        }
        int i;
    }
}