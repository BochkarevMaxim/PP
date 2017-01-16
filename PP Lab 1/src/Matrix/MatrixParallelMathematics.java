package Matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixParallelMathematics extends MatrixMathematics {

    private static int m_threadCount;

    private static synchronized int getM_threadCount() {
        return m_threadCount;
    }

    public synchronized void setM_threadCount(int m_threadCount) {
        this.m_threadCount = m_threadCount;
    }

    public MatrixParallelMathematics(int threadCount) {
        setM_threadCount(threadCount);
    }

    public static Matrix parallelGetInversedMatrix(Matrix matrix) throws NoSquareException, InterruptedException {
        return (getTransposedMatrix(getCofactorMatrix(matrix)).multiplyByConstant(1.0 / getDeterminant(matrix)));
    }


    public static Matrix getCofactorMatrix(Matrix matrix) throws NoSquareException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(getM_threadCount());
        Matrix mat = new Matrix(matrix.getNrows(), matrix.getNcols());

        for (int i = 0; i < matrix.getNrows(); i++) {
            int finalI = i;
            threadPool.submit(new Runnable() {
                public void run() {
                    for (int j = 0; j < matrix.getNcols(); j++) {
                        try {
                            mat.setValueAt(finalI, j, changeSign(finalI) * changeSign(j) *
                                    getDeterminant(createSubMatrix(matrix, finalI, j)));
                        } catch (NoSquareException exc) {
                            exc.printStackTrace();
                        }
                    }
                }
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        return mat;
    }
}
