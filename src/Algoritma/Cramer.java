package src.Algoritma;

import src.ADTMatrix.Matrix;

public class Cramer {
    public static void cramer(Matrix m, double[] x) {
        double det, hasil[];
        hasil = new double[x.length]; 
        det = Determinant.getDeterminantByCofactor(m);
        for(int i=0;i<x.length;i++){
            hasil[i] = Determinant.getDeterminantByCofactor(substitusiX(m, x, i))/det;
        }
        for(int i=0;i<x.length;i++){
            System.out.print(hasil[i]);
        }
        System.out.println();
    }

    public static Matrix substitusiX(Matrix m, double[] x, int kol){
        for(int i=0;i<x.length;i++){
            m.contents[i][kol] = x[i];
        }
        return m;
    }
}
