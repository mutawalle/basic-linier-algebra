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
}
