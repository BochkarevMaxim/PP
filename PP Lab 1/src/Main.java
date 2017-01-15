import Matrix.Matrix;
import Matrix.NoSquareException;

import java.util.Arrays;

import static Matrix.MatrixMathematics.inverse;

public class Main {

    public static void main(String[] args){
        Matrix mx = new Matrix (3, 3);
        System.out.println(Arrays.deepToString(mx.getValues()));
        try{
            System.out.println(Arrays.deepToString(inverse(mx).getValues()));
        } catch (NoSquareException exc){

        }

    }
}
