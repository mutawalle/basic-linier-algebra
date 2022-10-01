package src.Algoritma;

import src.ADTMatrix.Matrix;
import src.Primitif.*;

public class Gauss {
    public static void gaussSPL(Matrix m, double[] x){
        m = gauss(m);
        Primitif.displayMatrix(m);
    }

    public static Matrix gauss(Matrix m) {
        for(int i=0;i<m.row;i++){
            // mengubah ujung kiri menjadi 0
            if(i<m.col){
                if(m.contents[i][i] == 0){
                    int j=i+1;
                    while(j<m.row && m.contents[j][i] == 0){
                        j++;
                    }
                    if(j<m.row){
                        double temp;
                        for(int k=0;k<m.col;k++){
                            temp = m.contents[i][k];
                            m.contents[i][k] = m.contents[j][k];
                            m.contents[j][k] = temp;
                        }
                    }
                }
    
                // membuat ujung kiri menjadi 1
                double pembagi = m.contents[i][i];
                if(pembagi != 0){
                    for(int j=0;j<m.col;j++){
                        m.contents[i][j] /= pembagi;
                    }
                }
    
                // membuat kolom di bawah angka 1 menjadi 0
                for(int j=i+1;j<m.row;j++){
                    double pengurang = m.contents[j][i];
                    for(int k=0;k<m.col;k++){
                        m.contents[j][k] -= m.contents[i][k]*pengurang;
                    }
                }
            }
        }
        return m;
    }

    public static void GaussElim(Matrix M) {
        /* KAMUS */
        int i;
        /* ALGORITMA */
        // Jika semua diagonal isinya 1 dan ukuran matriks nxn+1, contohnya 3x4, maka
        // solusinya pasti unik
        if (Matrix.isDiagonalSatuUtama(M) && (M.getRow() == M.getCol() - 1)) {
            gauss(M);
            // Jika semua diagonal isinya 1 dan ukuran matriks tidak nxn+1, contohnya 4x7,
            // maka solusinya pasti banyak (berbentuk parametrik)
        } else if (Matrix.isDiagonalSatuUtama(M) && (M.getRow() != M.getCol() - 1)) {
            gauss(M);
            // Jika ada baris dibawah yang seluruh nilainya 0, maka solusinya pasti banyak
            // (berbentuk parametrik)
        } else {
            i = M.getRow() - 1;
            // Looping jika semua syarat masih dipenuhi
            while ((i >= 0) && (Matrix.isRowZero(M, i)) && (M.getELMT(i, M.getCol() - 1) == 0)) {
                i--;
            }
            // Jika elemen matriks normal isinya 0 semua dan elemen baris dan kolom paling
            // akhir bukan 0, maka solusinya tidak ada
            if (Matrix.isRowZero(M, i) && (M.getELMT(i, M.getCol() - 1) != 0)) {
                String m = "SPL tidak memiliki solusi";
                System.out.println(m);
                // IOFile.saveFile(m);
            } else {
                gauss(M);
            }
        }

    }





}
