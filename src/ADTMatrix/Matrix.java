package src.ADTMatrix;
import java.util.*;

import javax.swing.text.AbstractDocument.BranchElement;
// import src.Algoritma.Invers;
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

    /* Mengecek apakah elemen-elemen diagonal pada Matrix bernilai 1 */
    public static boolean isDiagonalSatuUtama(Matrix M) {
      /* KAMUS LOKAL */
      int i;
      boolean bool = true;
      /* ALGORITMA */
      i = 0;
      for (i=0; i<M.getRow(); i++ ) {
          if (M.getELMT(i, i) != 1) {
            bool = false;
            break;
          }
      }
      return bool;
    }
    
    public static boolean isRowZero(Matrix M, int row) {
      int j;
      j=0;
      boolean bool = true;

      for (j=0;j<M.getCol() - 1;j++){
        if (M.getELMT(row, j) != 0) {
          bool = false;
          break;
        }
      }

      return bool;
    }

}    
