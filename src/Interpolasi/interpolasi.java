import java.util.*;

import src.ADTMatrix.*;
import src.Algoritma.Invers;


public class interpolasi {
    public static void interpolasiPolinom(Matrix M, double x) {
        int i, j;
        double taksir = 0;
        String rumus, m;
        double a,koef ;
        Matrix hasil;
        Matrix polasi= new Matrix(M.getRow(),M.getRow()+1);

        for (i=0; i<M.getRow(); i++) {
            a = M.getELMT(i, 0);
            koef = 1;
            for (j=0; j<M.getRow();j++){
                polasi.setELMT(i,j,koef);
                koef*=a;
            }
            polasi.setELMT(i, j, M.getELMT(i, M.getCol() - 1));
        }

        hasil = inversSPL(polasi);
        // Membentuk rumus polinom
        rumus = "P" + (hasil.getRow() - 1) + "(X) = " + hasil.getELMT(0, 0);
        for (i = 1; i < M.getRow(); i++) {
            rumus += " + " + hasil.getELMT(i, 0) + " X^" + i;
        }
        for (i = 0; i < hasil.getRow(); i++) {
            taksir += Math.pow(x, i) * hasil.getELMT(i, 0);
        }
        System.out.println("\nPersamaan polinom yang terbentuk: ");
        System.out.println(rumus);
        System.out.println("\nHasil taksiran polinom: ");
        m = "P" + (hasil.getRow() - 1) + "(" + x + ")" + " = " + taksir;
        System.out.println(m);
        IOFile.saveFilePolinom(rumus, m);
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
