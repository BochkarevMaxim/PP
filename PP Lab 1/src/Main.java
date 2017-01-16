import Matrix.Matrix;
import Matrix.MatrixParallelMathematics;
import Matrix.NoSquareException;

import static Matrix.MatrixMathematics.getInversedMatrix;
import static Matrix.MatrixParallelMathematics.parallelGetInversedMatrix;

public class Main {

    public static void main(String[] args){

        int threadsNum = 1;
        if (args.length == 0) {
            System.out.println("Application takes 1 cmd parameter:\n- threads amount\n\n" +
                    "Example: main 8 (app will start calcualtion with 8 threads.");
        }
        else{
            threadsNum = Integer.parseInt(args[0]);
        }

        Matrix mx = new Matrix (10, 10);

        // SINGLE-THREADED inverse matrix calculation
        System.out.println("SINGLE-THREADED inverse matrix calculation started.");
        long startTime = System.currentTimeMillis();
        try{
            Matrix singleThreadMatrix = getInversedMatrix(mx);
        } catch (NoSquareException ignored){
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime   = System.currentTimeMillis();
        long iterativeTotalTime = endTime - startTime;
        System.out.println("SINGLE-THREADED inverse matrix calculation ended.");
        System.out.println("SINGLE-THREADED inverse matrix calculation time: " + iterativeTotalTime + " ms\n\n");

        // MULTI-THREADED inverse matrix calculation
        System.out.println("MULTI-THREADED inverse matrix calculation started.");
        MatrixParallelMathematics matrixParallelMathematics = new MatrixParallelMathematics(threadsNum);
        long asyncStartTime = System.currentTimeMillis();
        try{
            Matrix asyncThreadMatrix = parallelGetInversedMatrix(mx);
        } catch (NoSquareException ignored){
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long asyncEndTime   = System.currentTimeMillis();
        long asyncTotalTime = asyncEndTime - asyncStartTime;
        System.out.println("MULTI-THREADED inverse matrix calculation ended.\n");
        System.out.println("MULTI-THREADED inverse matrix calculation time: " + asyncTotalTime + " ms\n\n");
    }
}
