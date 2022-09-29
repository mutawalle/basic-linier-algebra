package src.ADTMatrix;
import java.util.*;
import src.Algoritma.Invers;
public class Matrix {
      /* Attribute */
    static Scanner input = new Scanner(System.in);
    public double contents[][];
    public int row;
    public int col;

    public Matrix(int r, int c) {
        this.contents = new double[r][c];
        this.row = r;
        this.col = c;
    }
      /* Method */
    /* ======== GETTER ======== */
    public int getRow() {
        return this.row;
    }

    public double getELMT(int i, int j) {
        return (this.contents[i][j]);
      }

    public void setELMT(int i, int j, double value) {
        this.contents[i][j] = value;
    }
    public int getCol() {
        return this.col;
    }
      /* Membentuk Matrix Koefisien */
    public static Matrix getMatKoef(Matrix m) {
    /* KAMUS LOKAL */
      Matrix matKoef = new Matrix(m.row, m.col - 1);
      int i, j;
    /* ALGORITMA */
      for (i = 0; i < matKoef.row; i++) {
        for (j = 0; j < matKoef.col; j++) {
          matKoef.contents[i][j] = m.contents[i][j];
        }
      }

      return matKoef;
    }
        /* Membentuk Matrix Konstanta */
    public static Matrix getMatCons(Matrix m) {
      /* KAMUS LOKAL */
      Matrix matCons = new Matrix(m.row, 1);
      int i;
      /* ALGORITMA */
      for (i = 0; i < matCons.row; i++) {
        matCons.contents[i][0] = m.contents[i][m.col - 1];
      }
      return matCons;
    }
    

    public Matrix multiplyMatrix(Matrix m1, Matrix m2) {
      /* KAMUS LOKAL */
      int i, j, k = 0;
      Matrix m3 = new Matrix(m1.getRow(), m2.getCol());
      double temp = 0;
      /* ALGORITMA */
      for (i = 0; i < m1.getRow(); i++) {
        for (j = 0; j < m2.getCol(); j++) {
          temp = 0;
          for (k = 0; k < m1.getCol(); k++) {
            temp += m1.getELMT(i, k) * m2.getELMT(k, j);
          }
          m3.setELMT(i, j, temp);
        }
      }
      return m3;
    }
    
    public static Matrix inversSPL(Matrix M) {
      /* KAMUS LOKAL */
      Matrix kons = new Matrix(M.getRow(), 1);
      Matrix koef = new Matrix(M.getRow(), M.getCol() - 1);
      Matrix hasil;
      /* ALGORITMA */
      kons = Matrix.getMatCons(M);
      koef = Matrix.getMatKoef(M);
      koef = Invers.invers(koef);
      hasil = M.multiplyMatrix(koef, kons);
      return hasil;
    }
}