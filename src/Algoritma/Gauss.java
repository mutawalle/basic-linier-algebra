package src.Algoritma;

import java.util.Scanner;

import src.ADTMatrix.*;
import src.InputOutput.*;
import src.Primitif.*;

public class Gauss {
    public static void gaussSPL(){
        Matrix m, hasil;
        double pengurang;
        Scanner input = new Scanner(System.in);
        System.out.println("Ketik 1 untuk input keyboard, Ketik lainnya untuk input file");
        System.out.println("Dan Pastikan file ada di folder test jika pilih input file");
        int pilihan = input.nextInt();
        if(pilihan==1){
            m = InputOutputKeyboard.inputSPL();
        }else{
            m =  InputOutputFile.bacaFile();
        }
        m = gauss(m);

        if(m.col-1>m.row){
            System.out.println("Tidak memiliki solusi");
        }else{
            // buat nyimpen hasil tiap x
            hasil = new Matrix(m.contents, m.row, 1);
            // asumsi solusi unik
            // jadi disini harusnya ada kondisi buat ngehandle solusi parametrik dibuat if else mungkin
            for(int i=m.row-1;i>=0;i--){
                pengurang = 0;
                for(int j=m.col-2;j>i;j--){
                    pengurang += m.contents[i][j]*hasil.contents[j][0];
                }
                hasil.contents[i][0] = m.contents[i][m.col-1] - pengurang;
            }
            Primitif.displayMatrix(hasil);
            InputOutputFile.OutputSPL(hasil);
        }
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
