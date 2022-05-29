package kit.phoebe.opencvtest;

import android.util.Log;

import com.orhanobut.logger.Logger;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Scalar;

public class BasicLinearAlgebra {
    private static final String LOG_TAG = "BasicLinearAlgebra";

    public BasicLinearAlgebra(){

    }

    public void printAllCauculate(){
        Cauculate();
    }

    private void Cauculate() {
        openCVBuildFirstMat();
        openCVBuildSecondMat();
        openCVBuildThirdMat();
        openCVBuildForthMat();
        openCVBuildFirthMat();
        openCVBuildSixthMat();

        openCVLinearAlgebra1();
        openCVLinearAlgebra2();
        openCVLinearAlgebra3();

        openCVStatistics1();
        openCVStatistics2();

        openCVArithmetic();
    }

    ///以陣列指定
    private void openCVBuildFirstMat(){
        Mat m1 = new Mat(3,3, CvType.CV_8UC1);
        m1.put(0,0,new byte[]{1,2,3});
        m1.put(1,0,new byte[]{4,5,6});
        m1.put(2,0,new byte[]{7,8,9});

        Logger.d("Firstm1.rows() : " + m1.rows());
        Logger.d("Firstm1.cols() : " + m1.cols());
        Logger.d("Firstm1.total() : " + m1.total());
        Logger.d("Firstm1.size() : " + m1.size());
        Logger.d("Firstm1(1,1) : " + m1.get(0,0)[0]);
        Logger.d("Firstm1(3,2) : " + m1.get(2,1)[0]);
        Logger.d("Firstm1 所有元素 : " + m1.dump());

        Mat m2 = new Mat();
        m2.create(2,2,CvType.CV_8UC1);
        Log.d(LOG_TAG, "Firstm2 所有元素 : " + m2.dump());
    }

    /// 以矩陣指定
    private void openCVBuildSecondMat(){
        byte[][] matData = {{1,2},{3,4},{5,6},{7,8},{9,10}};
        Mat m2 = new Mat(5,2, CvType.CV_8UC1);
        m2.put(0,0,matData[0]);
        m2.put(1,0,new byte[]{4,5,6});
        m2.put(2,0,new byte[]{7,8,9});

        Logger.d("Secondm2.rows() : " + m2.rows());
        Logger.d("Secondm2.cols() : " + m2.cols());
        Logger.d("Secondm2.total() : " + m2.total());
        Logger.d("Secondm2.size() : " + m2.size());
        Logger.d("Secondm2(1,1) : " + m2.get(0,0)[0]);
        Logger.d("Secondm2(3,2) : " + m2.get(2,1)[0]);
        Logger.d("Secondm2 所有元素 : " + m2.dump());
    }

    /// 以單一元素加入
    private void openCVBuildThirdMat(){
        Mat m3 = new Mat(2,2, CvType.CV_8UC1);
        m3.put(0,0,1);
        m3.put(0,1,2);
        m3.put(1,0,3);
        m3.put(1,1,4);

        Logger.d("Thirdm2.rows() : " + m3.rows());
        Logger.d("Thirdm2.cols() : " + m3.cols());
        Logger.d("Thirdm2.total() : " + m3.total());
        Logger.d("Thirdm2.size() : " + m3.size());
        Logger.d("Thirdm2(1,1) : " + m3.get(1,1)[0]);
        Logger.d("Thirdm2(0,1) : " + m3.get(0,1)[0]);
        Logger.d("Thirdm2 所有元素 : " + m3.dump());
    }

    ///以相同元素建立
    private void openCVBuildForthMat(){
        Mat m1 = new Mat(2, 2, CvType.CV_8UC1, new Scalar(9));
        Logger.d("Forthm1.rows() : " + m1.rows());
        Logger.d("Forthm1.cols() : " + m1.cols());
        Logger.d("Forthm2.total() : " + m1.total());
        Logger.d("Forthm2.size() : " + m1.size());
        Logger.d("Forthm2(1,1) : " + m1.get(1,1)[0]);
        Logger.d("Forthm2(0,1) : " + m1.get(0,1)[0]);
        Logger.d("Forthm2 所有元素 : " + m1.dump());
    }

    ///以個別單一元素指定
    private void openCVBuildFirthMat(){
        Mat m5a = Mat.zeros(3, 3, CvType.CV_8UC1);
        Logger.d("m5a 所有元素 : " + m5a.dump());
        Mat m5b = Mat.ones(5, 5, CvType.CV_8UC1);
        Logger.d("m5b 所有元素 : " + m5b.dump());
    }

    /// 以一維陣列元素指定
    private void openCVBuildSixthMat(){
        Mat matrix = new Mat(4, 4, CvType.CV_32F, new Scalar(0));
        float[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        matrix.put(0,0, data);
        Logger.d("matrix 所有元素 : " + matrix);
    }

    //轉移矩陣, 逆矩陣, 單位矩陣
    private void openCVLinearAlgebra1(){
        Mat m1 = new Mat(2, 2, CvType.CV_32FC1);
        m1.put(0,0,1);
        m1.put(0,1,2);
        m1.put(1,0,3);
        m1.put(1,1,4);

        Mat m2 = Mat.eye(2,2,CvType.CV_8UC1);
        Logger.d("m1 的所有元素 : " + m1.dump());
        Logger.d("m1 的轉置矩陣 1: " + m1.t().dump());
        Mat transpose = new Mat();
        Core.transpose(m1, transpose);
        Logger.d("m1 的轉置矩陣 2: " + transpose.dump());
        Logger.d("m1 的單位矩陣 => m2 的所有元素 : " + m2.dump());

        Logger.d("m1 的反矩陣 1: " + m1.inv().dump());
        Mat invert = new Mat();
        Core.invert(m1, invert);
        Logger.d("m1 的反矩陣 2: " + invert.dump());

        Mat m3 = m1.reshape(4,1);
        Logger.d("m3 為m1轉一列四行的矩陣 : " + m3.dump());
        Mat m4 = m1.reshape(1,4);
        Logger.d("m4 為m1轉四列一行的矩陣 : " + m4.dump());

        Mat m5 = new Mat();
        m5.push_back(m1);
        m5.push_back(m1);
        Logger.d("m5 為m1加到m5後的矩陣 : " + m5.dump());
    }

    //複製　加　減　乘　除　遮罩位置
    private void openCVLinearAlgebra2(){
        Mat m1 = new Mat(2,2,CvType.CV_32FC1);
        m1.put(0,0,1);
        m1.put(0,1,2);
        m1.put(1,0,3);
        m1.put(1,1,4);
        Mat m2 = m1.clone();
        Logger.d("m1 的複製矩陣 m2 : " + m2);

        Mat m3 = new Mat();
        Core.add(m1,m2,m3);
        Logger.d("m1 + m2 = m3, m3 = " + m3);

        Mat m4 = new Mat();
        Core.scaleAdd(m1,2,m2,m4);
        Logger.d("m1 * 2 + m2 = m4, m4 = " + m4);

        Mat m5 =new Mat();
        Core.subtract(m1,m2,m5);
        Logger.d("m1 - m2 = m5, m5 = " + m5);

        // 乘
        Mat m6 = new Mat();
        Core.gemm(m1,m2,2,m3, 2, m6);
        Logger.d("2 * m1 * m2 + 2 * m3 = m6, m6 = " + m6);

        Mat m7 = m1.mul(m2);
        Logger.d("m1 * m2 = m7, m7 = " + m7);

        Mat m8 = new Mat(2,2,CvType.CV_32FC1,new Scalar(3));
        Mat m9 = new Mat();
        m9 = m1.mul(m8);
        Logger.d("m1 * m8 = m9, m9 = " + m9);

        // 除
        Mat m10 = new Mat(2,2,CvType.CV_32FC1);
        Core.divide(m1,Scalar.all(3),m10);
        Logger.d("m1 / 3 = m10, m10 = " + m10);

        Mat m11 = new Mat(2,2,CvType.CV_32FC1,new Scalar(2));
        Mat m12 = new Mat();
        Core.divide(m1, m11, m12);
        Logger.d("m1 / m11 = m12, m12 = " + m12);

        // 遮罩位置
        Mat mask = new Mat(2,2,CvType.CV_8UC1);
        mask.put(0,0,1);
        mask.put(0,1,1);
        mask.put(1,0,1);
        mask.put(1,1,0);
        Mat m13 = new Mat();
        Core.add(m1, m2, m13, mask);
        Logger.d("m1 + m2 = m13, 使用遮罩mask (1,1), m13 = " + m13);

    }

    //trace 跡　特徵值　特徵向量　斜方差/共變數矩陣　倆倆比較
    private void openCVLinearAlgebra3(){
        Mat m1 = new Mat(2,2,CvType.CV_32FC1);
        m1.put(0,0,1);
        m1.put(0,1,2);
        m1.put(1,0,3);
        m1.put(1,1,4);

        Mat m2 = m1.clone();
        Logger.d("m1 的複製矩陣 m2 : " + m2);
        // 跡
        Logger.d("m2 trace : " + Core.trace(m2).val[0]);

        // 特徵值 特徵向量
        Mat eigenvalues = new Mat();
        Mat eigenvectors = new Mat();
        Core.eigen(m1, eigenvalues, eigenvectors);
        Logger.d("m1 特徵值 : " + eigenvalues);
        Logger.d("m1 特徵向量 : " + eigenvectors);

        // 協方差/共變數矩陣
        Mat covar = new Mat(2,2,CvType.CV_32FC1);
        Mat mean = new Mat(1,2,CvType.CV_32F);
        Core.calcCovarMatrix(m2, covar,mean, Core.COVAR_ROWS|Core.COVAR_NORMAL,CvType.CV_32F);
        Logger.d("m2 的協方差 : " + covar + " by col 平均 : " + mean);

        // 比較
        Mat compare = new Mat();
        Mat m3 = new Mat(2,2,CvType.CV_32FC1, new Scalar(9));
        Core.compare(m1, m3, compare, Core.CMP_GT);  // 是否大於
        Logger.d("m1 是否大於 m3 所有元素 : " + compare.dump());

        compare = new Mat();
        Core.compare(m1, m3, compare, Core.CMP_LT);  // 是否小於
        Logger.d("m1 是否小於 m3 所有元素 : " + compare.dump());

        compare = new Mat();
        Core.compare(m1, m3, compare, Core.CMP_EQ);  // 是否等於
        Logger.d("m1 是否等於 m3 所有元素 : " + compare.dump());

        compare = new Mat();
        Core.compare(m1, m3, compare, Core.CMP_GE);  // 是否大於等於
        Logger.d("m1 是否大於等於 m3 所有元素 : " + compare.dump());

        compare = new Mat();
        Core.compare(m1, m3, compare, Core.CMP_LE);  // 是否小於等於
        Logger.d("m1 是否小於等於 m3 所有元素 : " + compare.dump());

    }

    //平均　總和　最大　最小　升序　降序
    private void openCVStatistics1(){
        Mat m1 = new Mat(2,2,CvType.CV_32FC1);
        m1.put(0,0,1);
        m1.put(0,1,2);
        m1.put(1,0,3);
        m1.put(1,1,4);

        Mat m2 = m1.clone();
        Logger.d("m1 的複製矩陣 m2 : " + m2);

        Mat v1 = new Mat();
        Mat v2 = new Mat();
        Mat v3 = new Mat();
        Mat v4 = new Mat();
        Core.reduce(m2, v1, 0, Core.REDUCE_AVG);
        Core.reduce(m2, v2, 0, Core.REDUCE_SUM);
        Core.reduce(m2, v3, 0, Core.REDUCE_MAX);
        Core.reduce(m2, v4, 0, Core.REDUCE_MIN);

        Mat sortMat = new Mat();
        Core.sort(m1, sortMat, Core.SORT_ASCENDING);
        Logger.d("m1 以　row 為主升序　: " + sortMat.dump());
        Core.sort(m1, sortMat, Core.SORT_DESCENDING);
        Logger.d("m1 以　row 為主降序　: " + sortMat.dump());

        m1.put(0,0,4);
        m1.put(0,1,2);
        m1.put(1,0,1);
        m1.put(1,1,3);
        Core.sort(m1, sortMat, Core.SORT_EVERY_COLUMN);
        Logger.d("m1 以　col 為主升序　: " + sortMat.dump());
    }

    //各自值最大最小值 平均　標準差　總和　非零元素　基本範數　均勻分布的隨機矩陣　常態分佈的矩陣
    private void openCVStatistics2(){
        Mat m1 = new Mat(2,2,CvType.CV_32FC1);
        m1.put(0,0,1);
        m1.put(0,1,2);
        m1.put(1,0,3);
        m1.put(1,1,4);

        Mat m2 = m1.clone();
        Mat m4 = new Mat(2,2, CvType.CV_32FC1,new Scalar(9));

        Mat m3 = new Mat();
        Core.max(m2,m4,m3);
        Mat m5 = new Mat();
        Core.min(m2,m4,m5);

        MatOfDouble mean = new MatOfDouble();
        MatOfDouble stddev = new MatOfDouble();
        Core.meanStdDev(m2,mean,stddev);
        Logger.d("矩陣m2 平均值 : " + mean.get(0,0)[0]);
        Logger.d("矩陣m2 標準差 : " + stddev.get(0,0)[0]);
        Logger.d("矩陣m2 總和 : " + Core.sumElems(m2).val[0]);
        Logger.d("矩陣m2 非零個數 : " + Core.countNonZero(m2));

        Core.MinMaxLocResult m6 = new Core.MinMaxLocResult();
        m6 = Core.minMaxLoc(m2);
        Logger.d("矩陣 m2 最大值 : " + m6.maxVal + ", 最小值 : " + m6.minVal);
        Logger.d("m2 基本範數 : " + Core.norm(m1));

        //均勻分布
        Mat uniformlyDist = new Mat(2,2,CvType.CV_32FC1);
        Core.randu(uniformlyDist, 100, 150);
        Logger.d("建立隨機 3 * 3 矩陣 : " + uniformlyDist.dump());

        //常態分布
        Mat normallyDist = new Mat(2,2,CvType.CV_32FC1);
        Core.randn(normallyDist, 10, 7.5);
        Logger.d("建立常態分布 3 * 3 矩陣 (標準差: 7.5, 平均: 10): " + normallyDist.dump());

    }

    //統計的綜合計算
    private void openCVArithmetic(){
        Mat m1 = new Mat(2,2,CvType.CV_32FC1);
        m1.put(0,0,1);
        m1.put(0,1,2);
        m1.put(1,0,3);
        m1.put(1,1,4);

        Mat m2 = new Mat(2,2,CvType.CV_32FC1,new Scalar(2));

        //每一元素相差標準值 absdiff
        Mat absdiffMat = new Mat();
        Core.absdiff(m1,m2,absdiffMat);

        //權重相加
        Mat weightMat = new Mat();
        Core.addWeighted(m1,5,m2,3, 4, weightMat);
        //圖形邏輯運算
        /// bitwise_and
        Mat bitwiseAndMat = new Mat();
        Core.bitwise_and(m1,m2,bitwiseAndMat);

        /// bitwise_or
        Mat bitwiseOrMat = new Mat();
        Core.bitwise_or(m1,m2,bitwiseOrMat);

        /// bitwise_not
        Mat bitwiseNotMat = new Mat();
        Core.bitwise_not(m1,bitwiseNotMat);

        /// bitwise_xor
        Mat bitwiseXorMat = new Mat();
        Core.bitwise_xor(m1,m2,bitwiseXorMat);

        //立方根 cuberoot
        Logger.d("8 的立方根 : " + Core.cubeRoot(8));

        //指數 exp
        Mat expMat = new Mat();
        Core.exp(m1, expMat);

        //對數 log
        Mat logMat = new Mat();
        Core.log(m1, logMat);

        //確認範圍 inRange
        Mat inRangeMat = new Mat();
        Scalar lowerb = new Scalar(2);
        Scalar upperb = new Scalar(3);
        Core.inRange(m1, lowerb, upperb, inRangeMat);

        //正歸化 normalize
        Mat normalizeMat = new Mat();
        Core.normalize(m1, normalizeMat, 1, 1, Core.NORM_L1);
        Logger.d("正歸化 L1 : " + normalizeMat.dump());

        Core.normalize(m1, normalizeMat, 1, 1, Core.NORM_L2);
        Logger.d("正歸化 L2 : " + normalizeMat.dump());

        Core.normalize(m1, normalizeMat, 1, 1, Core.NORM_INF);
        Logger.d("正歸化 INF : " + normalizeMat.dump());

        Core.normalize(m1, normalizeMat, 1, 1, Core.NORM_MINMAX);
        Logger.d("正歸化 MINMAX : " + normalizeMat.dump());

        //平方 sqrt
        Mat sqrtMat = new Mat();
        Core.sqrt(m1, sqrtMat);

        //計算2D 向量的長度與角度 極座標?
        //{1,1},{3,4},{6,8},{10,10}
        Mat polar_m1 = new Mat(1,4,CvType.CV_32FC1);
        polar_m1.put(0,0,1);
        polar_m1.put(0,1,3);
        polar_m1.put(1,0,6);
        polar_m1.put(1,1,10);
        Mat polar_m2 = new Mat(1,4,CvType.CV_32FC1);
        polar_m2.put(0,0,1);
        polar_m2.put(0,1,4);
        polar_m2.put(1,0,8);
        polar_m2.put(1,1,10);

        Mat magnitude = new Mat();
        Mat angle = new Mat();
        Core.cartToPolar(polar_m1,polar_m2,magnitude,angle);
        Logger.d("矩陣向量運算出的極座標 以矩陣表示 magnitude , angle : " + magnitude.dump() + ", " + angle.dump());

        //可逆方程式 求出原2D向量
        Mat findM1 = new Mat();
        Mat findM2 = new Mat();
        Core.polarToCart(magnitude,angle,findM1,findM2);
        Logger.d("原始座標矩陣 : M1 = " + findM1.dump() + ", M2 = " + findM2);

    }
}
