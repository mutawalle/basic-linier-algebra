package src.Algoritma;

import java.lang.annotation.Retention;
import java.util.Scanner;
import java.util.function.ObjDoubleConsumer;

import javax.xml.transform.Source;

import src.ADTMatrix.*;
import src.Primitif.Primitif;
import src.InputOutput.*;

public class GaussJordan {
    public static void gaussJordanSPL(){
        Matrix m;
        Scanner input = new Scanner(System.in);
        System.out.println("Ketik 1 untuk input keyboard, Ketik lainnya untuk input file");
        System.out.println("Dan Pastikan file ada di folder test jika pilih input file");
        int pilihan = input.nextInt();
        if(pilihan==1){
            m = InputOutputKeyboard.inputSPL();
        }else{
            m =  InputOutputFile.bacaFile();
        }
        m = Gauss.gauss(m);

        
        if(m.col-1>m.row){
            System.out.println("Tidak meiliki solusi");
        }else{
            while(cekBarisTerakhirKosong(m)){
                m.row--;
            }

            if(takBersolusi(m)){
                System.out.println("Tidak meiliki solusi");
            }else{
                m = gaussJordan(m);
                if(m.col-1>m.row){
                    // parametrik
                    for(int i=0;i<m.row;i++){
                        System.out.print("x"+(i+1)+" = "+m.contents[i][m.col-1]);
                        for(int j=i+1;j<m.col-1;j++){
                            if(m.contents[i][j]!=0){
                                System.out.print(" + "+(-1*m.contents[i][j])+"x"+(j+1));
                            }
                        }
                        System.out.println();
                    }
                    InputOutputFile.outputParametrik(m);
                }else{
                    // unik
                    Matrix hasil;
                    hasil = new Matrix(m.contents, m.row, 1);
                    for(int i=0;i<m.row;i++){
                        System.out.println("x"+(i+1)+" = "+m.contents[i][m.col-1]);
                        hasil.contents[i][0] = m.contents[i][m.col-1];
                    }
                    InputOutputFile.OutputSPL(hasil);
                }
            }
        }

    }
    public static Matrix gaussJordan(Matrix m) {
        if(m.row>0){
            for(int i=m.row-1;i>=0;i--){
                for(int j=i-1;j>=0;j--){
                    double pengurang = m.contents[j][i];
                    for(int k=0;k<m.col;k++){
                        m.contents[j][k] -= pengurang*m.contents[i][k];
                    }
                }
            }
        }

        return m;
    }

    public static boolean cekBarisTerakhirKosong(Matrix m){
        boolean kosong = true;
        for(int i=0;i<m.col;i++){
            if(m.contents[m.row-1][i] != 0){
                kosong = false;
            }
        }
        return kosong;
    }

    public static boolean takBersolusi(Matrix m){
        boolean takSolusi = false;
        int jml = 0;
        for(int i=0;i<m.col;i++){
            if(m.contents[m.row-1][i]!=0){
                jml++;
            }

            if(m.contents[m.row-1][i]!=0 && i==m.col-1 && jml==1){
                takSolusi = true;
            }
        }
        return takSolusi;
    }

    // public static int cekUnik(Matrix m){
    //     if(m.contents[m.col-1][m.col-2]==1){
    //         return 1;
    //     }else if()
    //     return parametrik;
    // }

    // public static void cetakParametrik()
}
