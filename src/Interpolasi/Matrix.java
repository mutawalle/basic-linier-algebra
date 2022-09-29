import java.util.*;

public class Matrix {
  /* Attribute */
  static Scanner input = new Scanner(System.in);
  double[][] M;
  int row;
  int col;

  /* Konstruktor */
  public Matrix(int r, int c) {
    this.M = new double[r][c];
    this.row = r;
    this.col = c;
  }

  /* Method */
  /* ======== GETTER ======== */
  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public double getELMT(int i, int j) {
    return (this.M[i][j]);
  }

  /* ======== SETTER ======== */
  public void setELMT(int i, int j, double value) {
    this.M[i][j] = value;
  }

  /* ======== INPUT/OUTPUT ======== */
  public void readMatrix() {
    /* KAMUS LOKAL */
    int i, j;
    /* ALGORITMA */
    for (i = 0; i < this.row; i++) {
      for (j = 0; j < this.col; j++) {
        this.M[i][j] = input.nextDouble();
      }
    }
  }

  public void readMatrixRegresi(int N) {
    /* KAMUS LOKAL */
    int i;
    /* ALGORITMA */
    for (i = 0; i < N; i++) {
      System.out.print(">X" + (i + 1) + ": ");
      this.M[0][i] = input.nextDouble();
    }
  }

  public void displayMatrix() {
    /* KAMUS LOKAL */
    int i, j;
    /* ALGORITMA */
    for (i = 0; i < this.row; i++) {
      for (j = 0; j < this.col; j++) {
        System.out.print(M[i][j]);
        if (j != this.col - 1) {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  /* ======== OPERATION ======== */
  public int Size() {
    /* ALGORITMA */
    return (this.row * this.col);
  }

  public void operationRow(int i1, int i2, double k) {
    /* KAMUS LOKAL */
    int j;
    /* ALGORITMA */
    for (j = 0; j < col; j++) {
      this.M[i1][j] -= k * this.M[i2][j];
    }
  }

  public void divideRow(int i, double k) {
    /* KAMUS LOKAL */
    int j;
    /* ALGORITMA */
    for (j = 0; j < col; j++) {
      this.M[i][j] /= k;
    }
  }

  public void switchRow(int i1, int i2) {
    /* KAMUS LOKAL */
    int j;
    double temp;
    /* ALGORITMA */
    for (j = 0; j < col; j++) {
      temp = this.M[i1][j];
      this.M[i1][j] = this.M[i2][j];
      this.M[i2][j] = temp;
    }
  }

  public void copyMatrix(Matrix outM) {
    /* KAMUS LOKAL */
    int i, j;
    /* ALGORITMA */
    for (i = 0; i < outM.getRow(); i++) {
      for (j = 0; j < outM.getCol(); j++) {
        outM.setELMT(i, j, this.getELMT(i, j));
      }
    }
  }

  public void setIdentity() {
    // Mengubah matriks menjadi matriks identitas
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (i == j) {
          this.M[i][j] = 1.0f;
        } else {
          this.M[i][j] = 0.0f;
        }
      }
    }
  }

  /* Menempatkan suatu baris kosong ke bagian bawah matrix tersebut */
  public static void switchRowEmpty(Matrix m) {
    for (int i = 0; i < m.row; i++) {
      if (isRowEmpty(m, i)) {
        for (int j = i + 1; j < m.row; j++) {
          if (!isRowEmpty(m, j)) {
            m.switchRow(i, j);
          }
        }
      }
    }
  }

  /* Menyesuaikan elemen bernilai nol menjadi absolut */
  public static void changeZerovalue(Matrix m) {
    for (int i = 0; i < m.row; i++) {
      for (int j = 0; j < m.col; j++) {
        if (m.M[i][j] == -0.0) {
          m.M[i][j] = Math.abs(m.M[i][j]);
        }
      }
    }
  }

  public Matrix transpose(Matrix M) {
    /* KAMUS LOKAL */
    int i, j;
    Matrix Mout = new Matrix(M.getRow(), M.getCol());
    /* ALGORITMA */
    for (i = 0; i < M.getRow(); i++) {
      for (j = 0; j < M.getCol(); j++) {
        Mout.setELMT(j, i, M.getELMT(i, j));
      }
    }
    return Mout;
  }

  /* Membentuk Matrix Koefisien */
  public static Matrix getMatKoef(Matrix m) {
    /* KAMUS LOKAL */
    Matrix matKoef = new Matrix(m.row, m.col - 1);
    int i, j;
    /* ALGORITMA */
    for (i = 0; i < matKoef.row; i++) {
      for (j = 0; j < matKoef.col; j++) {
        matKoef.M[i][j] = m.M[i][j];
      }
    }

    return matKoef;
  }

  /* Membentuk Matrix Augmented */
  public static Matrix createAug(Matrix koef, Matrix cons) {
    /* KAMUS LOKAL */
    Matrix result = new Matrix(koef.row, koef.col + 1);
    /* ALGORITMA */
    for (int i = 0; i < koef.row; i++) {
      for (int j = 0; j < result.col - 1; j++) {
        result.M[i][j] = koef.M[i][j];
      }
    }
    for (int k = 0; k < result.row; k++) {
      result.M[k][result.col - 1] = cons.M[k][0];
    }
    return result;
  }

  /* Membentuk Matrix Konstanta */
  public static Matrix getMatCons(Matrix m) {
    /* KAMUS LOKAL */
    Matrix matCons = new Matrix(m.row, 1);
    int i;
    /* ALGORITMA */
    for (i = 0; i < matCons.row; i++) {
      matCons.M[i][0] = m.M[i][m.col - 1];
    }
    return matCons;
  }

  /* Melakukan operasi perkalian Matrix (M1 * M2) */
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

  /* Mengecek apakah suatu baris kosong */
  public static boolean isRowEmpty(Matrix m, int i) {
    int count = 0;
    int index = 0;
    while (count == 0 && index < m.col) {
      if (m.M[i][index] != 0) {
        count++;
      }
      index++;
    }
    return (count == 0);
  }

  public static boolean isNRowZero(Matrix M, int row) {
    /* KAMUS */
    int j = 0;
    boolean flag = true;
    /* ALGORITMA */
    while (j < M.getCol() - 1 && flag) {
      if (M.getELMT(row, j) != 0) {
        flag = false;
      }
      j++;
    }
    return flag;
  }

  /* Mengecek elemen-elemen dibawah elemen terkini, kosong atau tidak */
  public static boolean isUnderEmpty(Matrix m, int i, int j) {
    int count, iRow;
    count = 0;
    iRow = i + 1;
    while (count == 0 && iRow < m.row) {
      if (m.M[iRow][j] != 0) {
        count++;
      }
      iRow++;
    }
    return (count == 0);
  }

  /* Mengecek apakah elemen-elemen diagonal pada Matrix bernilai 1 */
  public static boolean isDiagonalOne(Matrix M) {
    /* KAMUS LOKAL */
    int i;
    boolean flag = true;
    /* ALGORITMA */
    i = 0;
    while (i < M.getRow() && flag) {
      if (M.getELMT(i, i) != 1) {
        flag = false;
      }
      i++;
    }
    return flag;
  }

  /* Mendapatkan pivot Matrix yang bernilai 1 */
  public static int getLeadingOne(Matrix M, int row) {
    /* KAMUS */
    int j = 0;
    int idxLead = -1;
    boolean found = false;
    /* ALGORITMA */
    // Iterasi untuk mencari leading one di tiap baris
    while (j < M.getCol() && (found == false)) {
      if (M.getELMT(row, j) == 1) {
        idxLead = j;
        found = true;
      }
      j++;
    }
    return idxLead;
  }
}