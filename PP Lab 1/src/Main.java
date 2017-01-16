import Matrix.Matrix;
import Matrix.MatrixParallelMathematics;
import Matrix.NoSquareException;

import static Matrix.MatrixMathematics.getInversedMatrix;
import static Matrix.MatrixParallelMathematics.parallelGetInversedMatrix;

public class Main {

    public static void main(String[] args){

        final int threadsNum = 4;
        Matrix mx = new Matrix (10, 10);

        // SINGLE-THREADED inverse matrix calculation
        long startTime = System.currentTimeMillis();
        try{
            Matrix singleThreadMatrix = getInversedMatrix(mx);
        } catch (NoSquareException exc){
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime   = System.currentTimeMillis();
        long iterativeTotalTime = endTime - startTime;
        System.out.println("SINGLE-THREADED inverse matrix calculation time: " + iterativeTotalTime + " ms");

        MatrixParallelMathematics matrixParallelMathematics = new MatrixParallelMathematics(threadsNum);

        // MULTI-THREADED inverse matrix calculation
        long asyncStartTime = System.currentTimeMillis();
        try{
            Matrix asyncThreadMatrix = parallelGetInversedMatrix(mx);
        } catch (NoSquareException exc){
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long asyncEndTime   = System.currentTimeMillis();
        long asyncTotalTime = asyncEndTime - asyncStartTime;
        System.out.println("MULTI-THREADED inverse matrix calculation time: " + asyncTotalTime + " ms");

    }


}
