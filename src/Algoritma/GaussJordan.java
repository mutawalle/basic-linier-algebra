package src.Algoritma;

import src.ADTMatrix.*;
import src.Primitif.Primitif;

public class GaussJordan {
    public static void gaussJordanSPL(Matrix m, double[] x){
        m = gaussJordan(m);
        Primitif.displayMatrix(m);
    }
    public static Matrix gaussJordan(Matrix m) {
        m = Gauss.gauss(m);
        // membuat kolom di atas 1 menjadi 0
        int baris = m.row-1;
        while(m.contents[baris][m.col-1] != 1.00){
            baris--;
        }
        for(int i=baris;i>=0;i--){
            for(int j=i-1;j>=0;j--){
                double pengurang = m.contents[j][i];
                for(int k=0;k<m.col;k++){
                    m.contents[j][k] -= pengurang*m.contents[i][k];
                }
            }
        }

        return m;
    }
}
