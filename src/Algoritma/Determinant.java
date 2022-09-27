package src.Algoritma;

import src.ADTMatrix.Matrix;
import src.Primitif.*;

public class Determinant {
    public static void showDetOBE(Matrix m){
        System.out.printf("%.2f\n", getDeterminantByOBE(m));
    }

    public static void showDetCofactor(Matrix m){
        System.out.printf("%.2f\n", getDeterminantByCofactor(m));
    }

    // Mendapat determinan dari matriks segitiga setelah dilakukan OBE
    public static double getDeterminantByOBE(Matrix m){
        double hasil;

        hasil = 1;
        for(int i=0;i<m.row;i++){
            // mengubah ujung kiri menjadi 0
            if(i<m.col){
                if(m.contents[i][i] == 0){
                    int j=i+1;
                    while(j<m.row && m.contents[j][i] == 0){
                        j++;
                    }
                    if(j<m.row){
                        hasil *= (-1);
                        double temp;
                        for(int k=0;k<m.col;k++){
                            temp = m.contents[i][k];
                            m.contents[i][k] = m.contents[j][k];
                            m.contents[j][k] = temp;
                        }
                    }else{
                        hasil = 0;
                        break;
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

        return hasil;
    }

    // Mendapat determinan dengan kofaktor
    public static double getDeterminantByCofactor(Matrix m){
        if(m.col == 1 && m.row == 1){
            return m.contents[0][0];
        }else if(m.row == 2 && m.col == 2){
            return m.contents[0][0]*m.contents[1][1] - m.contents[0][1]*m.contents[1][0];
        }else{
            double hasil;
            hasil = 0;
            for(int i=0;i<m.col;i++){
                if(i%2==0){
                    hasil += m.contents[0][i]*getDeterminantByCofactor(Primitif.kofaktor(m, 0, i));
                }else{
                    hasil += (-1)*m.contents[0][i]*getDeterminantByCofactor(Primitif.kofaktor(m, 0, i));
                }
            }
            return hasil;
        }
    }
}
