package Matrix;

public class MatrixMathematics {

	public MatrixMathematics(){}

	public static Matrix getInversedMatrix(Matrix matrix) throws NoSquareException, InterruptedException {
		return (getTransposedMatrix(getCofactorMatrix(matrix)).multiplyByConstant(1.0/ getDeterminant(matrix)));
	}

	public static Matrix getTransposedMatrix(Matrix matrix) {
		Matrix transposedMatrix = new Matrix(matrix.getNcols(), matrix.getNrows());
		for (int i=0;i<matrix.getNrows();i++) {
			for (int j=0;j<matrix.getNcols();j++) {
				transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
			}
		}
		return transposedMatrix;
	}

	// getInversedMatrix matrix calculation formula taken from:
	// http://mathprofi.ru/kak_naiti_obratnuyu_matricu.html

	public static double getDeterminant(Matrix matrix) throws NoSquareException {
		if (!matrix.isSquare())
			throw new NoSquareException("matrix need to be square.");
		if (matrix.size() == 1){
			return matrix.getValueAt(0, 0);
		}
			
		if (matrix.size()==2) {
			return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) -
					( matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
		}
		double sum = 0.0;
		for (int i=0; i<matrix.getNcols(); i++) {
			sum += changeSign(i) * matrix.getValueAt(0, i) *
					getDeterminant(createSubMatrix(matrix, 0, i));
		}
		return sum;
	}

	public static int changeSign(int i) {
		if (i%2==0)
			return 1;
		return -1;
	}

	public static Matrix createSubMatrix(Matrix matrix, int excluding_row, int excluding_col) {
		Matrix mat = new Matrix(matrix.getNrows()-1, matrix.getNcols()-1);
		int r = -1;
		for (int i=0;i<matrix.getNrows();i++) {
			if (i==excluding_row)
				continue;
				r++;
				int c = -1;
			for (int j=0;j<matrix.getNcols();j++) {
				if (j==excluding_col)
					continue;
				mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
			}
		}
		return mat;
	}
	
	public static Matrix getCofactorMatrix(Matrix matrix) throws NoSquareException, InterruptedException {
		Matrix mat = new Matrix(matrix.getNrows(), matrix.getNcols());
		for (int i=0;i<matrix.getNrows();i++) {
			for (int j=0; j<matrix.getNcols();j++) {
				mat.setValueAt(i, j, changeSign(i) * changeSign(j) *
						getDeterminant(createSubMatrix(matrix, i, j)));
			}
		}
		return mat;
	}
}
