package kit.phoebe.opencvtest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_UNCHANGED;
import static org.opencv.imgcodecs.Imgcodecs.imdecode;


public class MainActivity extends Activity {
    private static final String LOG_TAG = "MainActivity";

    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private Bitmap bmpUI;
    private ImageView img;

    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        Logger.addLogAdapter(new AndroidLogAdapter());

        Log.d(LOG_TAG,"Version: " + Core.VERSION);
        Log.d(LOG_TAG, "LIBRARY_NAME:" + Core.NATIVE_LIBRARY_NAME);
        //BasicLinearAlgebra basicLinearAlgebra = new BasicLinearAlgebra();
        //basicLinearAlgebra.printAllCauculate();
        img = findViewById(R.id.main_picachu);
        setContentListener();
        
    }

    private void setContentListener(){
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] picachu = getBitmatByte("picachu.png");
                switch (index % 7){
                    case 0:
                        toGrayUseCvtColor(picachu);
                        break;
                    case 1:
                        metConvertBrightnessByEveryPixel(picachu, 2, 50);
                        break;
                    case 2:
                        metConvertBrightnessByEveryPixel(picachu, 0.7, 76);
                        break;
                    case 3:
                        changeChannalBitmap(picachu);
                        break;
                    case 4:
                        toConstractUseBitwiseXor(picachu);
                        break;
                    case 5:
                        toConstractUseSubtract(picachu);
                        break;
                    case 6:
                        toConstractUseBitwiseNot(picachu);
                        break;
                    
                }
                index ++;
                putToUI();
            }
        });
    }
    
    private byte[] getBitmatByte(String picturnName) {
        BitmapDrawable abmp = (BitmapDrawable) img.getDrawable();
        byte[] bytes;
        try {
            InputStream picachuPicture = this.getAssets().open(picturnName);
            int nRead;
            byte[] data = new byte[16 * 1024];
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            while ((nRead = picachuPicture.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            bytes = buffer.toByteArray();
            return bytes;
            
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void putToUI(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                img.setImageBitmap(bmpUI);
            }
        });
    }
    
    private void changeChannalBitmap(byte[] pictureBytes){
        img = (ImageView)findViewById(R.id.main_picachu);
        BitmapDrawable  abmp = (BitmapDrawable)img.getDrawable();
    
        Mat imageSource = Imgcodecs.imdecode(new MatOfByte(pictureBytes), CV_LOAD_IMAGE_UNCHANGED);
//            List<Mat> bgrList = new ArrayList<Mat>(3);
//            Logger.d(imageSource.channels());
//            Logger.d(imageSource.total());
//            Logger.d(imageSource.cols());
//            Logger.d(imageSource.rows());
//            Logger.d(imageSource.size());
//            Logger.d(imageSource.depth());
//            Logger.d(imageSource.type());
//            Core.split(imageSource, bgrList);
//            Logger.d(bgrList.get(0).dump());
//            Logger.d(bgrList.get(1).dump());
//            Logger.d(bgrList.get(2).dump());
    
        byte[] RGBA = new byte[(int)(imageSource.total() * imageSource.channels() * imageSource.elemSize())]; ///imageSource.elemSize() => int : 4 byte
        // 4 channel : RGBA
        //int[] CMYK = new int[(int)(imageSource.total() * imageSource.channels())];
        Logger.d("byte array rgba : " + imageSource.total() + ", " + imageSource.channels() + ", " + imageSource.elemSize());
        byte b;
        for(int i = 0;i < RGBA.length ; i = i + imageSource.channels()){
            /// rgba => grba
            b = RGBA[i];
            RGBA[i] = RGBA[i + 1];
            RGBA[i + 1] = b;
        
            /// gb => 0
            if(RGBA[i] == 255 && RGBA[i + 1] == 255 && RGBA[i + 2] == 255){
                RGBA[i + 1] = 0;
                RGBA[i + 2] = 0;
            }
        }
    
        imageSource.get(0,0,RGBA);
        Mat matrixUI = new Mat(imageSource.rows(), imageSource.cols(), CvType.CV_8UC(imageSource.channels()), new Scalar(0));
        //matrixUI = new Mat(imageSource.rows(), imageSource.cols(), CvType.CV_16SC(imageSource.channels()), new Scalar(0));
        matrixUI.put(0, 0, RGBA);
        bmpUI = Bitmap.createBitmap(imageSource.cols(), imageSource.rows(),Bitmap.Config.ARGB_4444);
        Utils.matToBitmap(matrixUI, bmpUI);
    
        //Imgcodecs.imwrite(Environment.getExternalStorageDirectory().getPath() + "/TEST.png", imageSource);
    }

    private Mat convertToBrightness(Mat source, double alpha, double beta){
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        source.convertTo(destination, -1, alpha, beta);
        return  destination;
    }
    
    private Mat addWeight(Mat source1, Mat source2, double alpha, double gamma){
        Mat destination = new Mat(source1.rows(), source1.cols(),source1.type());
        Core.addWeighted(source1, alpha, source2, 0, gamma, destination);
        return destination;
    }
    
    private Mat metConvertBrightnessByEveryPixel(byte[] pictureBytes, double alpha, double gamma){
        Mat imageSource = Imgcodecs.imdecode(new MatOfByte(pictureBytes), CV_LOAD_IMAGE_UNCHANGED);
        Mat destination = new Mat(imageSource.rows(), imageSource.cols(), imageSource.type());
        for(int j = 0; j < imageSource.rows(); j++){
            for(int i = 0; i < imageSource.cols(); i++){
                double[] temp = imageSource.get(j, i);
                int cnt = 0;
                for(double temp_channal : temp){
                    temp[cnt] = temp_channal * alpha + gamma;
                    cnt ++;
                }
                destination.put(j, i, temp);
            }
        }
        bmpUI = Bitmap.createBitmap(imageSource.cols(), imageSource.rows(),Bitmap.Config.ARGB_4444);
        Utils.matToBitmap(destination, bmpUI);
        return destination;
    }
    
    private Mat toGrayUseCvtColor(byte[] pictureBytes){
        Mat imageSource = Imgcodecs.imdecode(new MatOfByte(pictureBytes), CV_LOAD_IMAGE_UNCHANGED);
        Mat dst = new Mat(imageSource.rows(), imageSource.cols(), imageSource.type());
        Imgproc.cvtColor(imageSource, dst, Imgproc.COLOR_RGB2GRAY);
        bmpUI = Bitmap.createBitmap(imageSource.cols(), imageSource.rows(), Bitmap.Config.ARGB_4444);
        Utils.matToBitmap(dst, bmpUI);
        
        return dst;
    }
    
    private Mat toConstractUseBitwiseXor(byte[] pictureBytes){
        Mat imageSource = Imgcodecs.imdecode(new MatOfByte(pictureBytes), CV_LOAD_IMAGE_UNCHANGED);
        Mat dst = new Mat(imageSource.rows(), imageSource.cols(), imageSource.type());
        Mat destination = new Mat(imageSource.rows(), imageSource.cols(), imageSource.type(), new Scalar(255, 255, 255));
        Core.bitwise_xor(imageSource, destination, dst);
        bmpUI = Bitmap.createBitmap(imageSource.cols(), imageSource.rows(), Bitmap.Config.ARGB_4444);
        Utils.matToBitmap(dst, bmpUI);
        
        return dst;
    }
    
    private Mat toConstractUseSubtract(byte[] pictureBytes){
        Mat imageSource = Imgcodecs.imdecode(new MatOfByte(pictureBytes), CV_LOAD_IMAGE_UNCHANGED);
        Mat dst = new Mat(imageSource.rows(), imageSource.cols(), imageSource.type());
        Mat destination = new Mat(imageSource.rows(), imageSource.cols(), imageSource.type(), new Scalar(255, 255, 255));
        Core.subtract(imageSource, destination, dst);
        bmpUI = Bitmap.createBitmap(imageSource.cols(), imageSource.rows(), Bitmap.Config.ARGB_4444);
        Utils.matToBitmap(dst, bmpUI);
        
        return dst;
    }
    
    private Mat toConstractUseBitwiseNot(byte[] pictureBytes){
        Mat imageSource = Imgcodecs.imdecode(new MatOfByte(pictureBytes), CV_LOAD_IMAGE_UNCHANGED);
        Mat dst = new Mat(imageSource.rows(), imageSource.cols(), imageSource.type());
        Core.bitwise_not(imageSource, dst);
        bmpUI = Bitmap.createBitmap(imageSource.cols(), imageSource.rows(), Bitmap.Config.ARGB_4444);
        Utils.matToBitmap(dst, bmpUI);
        
        return dst;
    }
}
