package src.Algoritma;

import src.ADTMatrix.Matrix;
import src.Primitif.Primitif;

public class Invers {
    public static void inversSPL(Matrix m, double[] x){
        m = invers(m);
        Primitif.displayMatrix(m);
    }

    public static Matrix invers(Matrix m){
        // kamus
        double[][] hasil = new double[m.row][m.col];
        double[][] identitas = new double[m.row][m.col];

        // inisialisasi
        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                identitas[i][j] = m.contents[i][j];
                if(i==j){
                    hasil[i][j] = 1;
                }else{
                    hasil[i][j] = 0;
                }
            }
        }

        // gauss
        for(int i=0;i<m.row;i++){
            // mengubah ujung kiri menjadi 0
            if(i<m.col){
                if(identitas[i][i] == 0){
                    int j=i+1;
                    while(j<m.row && identitas[j][i] == 0){
                        j++;
                    }
                    if(j<m.row){
                        double temp;
                        for(int k=0;k<m.col;k++){
                            temp = identitas[i][k];
                            identitas[i][k] = identitas[j][k];
                            identitas[j][k] = temp;
                            temp = hasil[i][k];
                            hasil[i][k] = hasil[j][k];
                            hasil[j][k] = temp;
                        }
                    }
                }
    
                // membuat ujung kiri menjadi 1
                double pembagi = identitas[i][i];
                if(pembagi != 0){
                    for(int j=0;j<m.col;j++){
                        identitas[i][j] /= pembagi;
                        hasil[i][j] /= pembagi;
                    }
                }
    
                // membuat kolom di bawah angka 1 menjadi 0
                for(int j=i+1;j<m.row;j++){
                    double pengurang = identitas[j][i];
                    for(int k=0;k<m.col;k++){
                        identitas[j][k] -= identitas[i][k]*pengurang;
                        hasil[j][k] -= hasil[i][k]*pengurang;
                    }
                }
            }
        }

        // jordan
        for(int i=m.row-1;i>=0;i--){
            for(int j=i-1;j>=0;j--){
                double pengurang = identitas[j][i];
                for(int k=0;k<m.col;k++){
                    identitas[j][k] -= pengurang*identitas[i][k];
                    hasil[j][k] -= pengurang*hasil[i][k];
                }
            }
        }

        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                System.out.print(identitas[i][j]);
            }
            System.out.println("");
        }

        m.contents = hasil;
        return m;
    }
    
}