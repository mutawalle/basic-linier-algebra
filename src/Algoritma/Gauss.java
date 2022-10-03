package src.Algoritma;

import java.util.Scanner;

import javax.print.attribute.standard.PrinterInfo;

import src.ADTMatrix.*;
import src.InputOutput.*;
import src.Primitif.*;

public class Gauss {
    public static void gaussSPL(){
        Matrix m, hasil, mJordan;
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
            while(GaussJordan.cekBarisTerakhirKosong(m)){
                m.row--;
            }

            if(GaussJordan.takBersolusi(m)){
                System.out.println("Tidak meiliki solusi");
            }else{
                mJordan = GaussJordan.gaussJordan(m);

                if(mJordan.col-1>mJordan.row){
                    // parametrik
                    for(int i=0;i<mJordan.row;i++){
                        System.out.print("x"+(i+1)+" = "+mJordan.contents[i][mJordan.col-1]);
                        for(int j=i+1;j<mJordan.col-1;j++){
                            if(mJordan.contents[i][j]!=0){
                                System.out.print(" + "+(-1*mJordan.contents[i][j])+"x"+(j+1));
                            }
                        }
                        System.out.println();
                    }
                    InputOutputFile.outputParametrik(mJordan);
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
                    for(int i=0;i<m.row;i++){
                        System.out.println("x"+(i+1)+" = "+m.contents[i][0]);
                    }
                    InputOutputFile.OutputSPL(hasil);
                }
            }
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
