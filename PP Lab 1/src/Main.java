import Matrix.Matrix;
import Matrix.NoSquareException;

import static Matrix.MatrixMathematics.getInversedMatrix;

public class Main {

    public static void main(String[] args){

        Matrix mx = new Matrix (10, 10);

        // Single-threaded getInversedMatrix matrix calculation

        //System.out.println(Arrays.deepToString(mx.getValues()));
        long startTime = System.currentTimeMillis();
        try{
            Matrix singleThreadMatrix = getInversedMatrix(mx);
           // System.out.println(Arrays.deepToString(getInversedMatrix(mx).getValues()));
        } catch (NoSquareException exc){

        }
        long endTime   = System.currentTimeMillis();
        long iterativeTotalTime = endTime - startTime;
        System.out.println("Iterative time " + iterativeTotalTime);

    }
}
