package src.Algoritma;

import java.util.Scanner;

import src.ADTMatrix.*;
import src.InputOutput.*;
import src.Primitif.Primitif;

public class Cramer {
    public static void cramer() {
        PasanganMatrix inputan;
        Matrix matriksFile;
        Scanner input = new Scanner(System.in);
        System.out.println("Ketik 1 untuk input keyboard, Ketik lainnya untuk input file");
        System.out.println("Dan Pastikan file ada di folder test jika pilih input file");
        int pilihan = input.nextInt();
        if(pilihan==1){
            matriksFile = InputOutputKeyboard.inputSPL();
            inputan = InputOutputFile.pisah(matriksFile);
        }else{
            matriksFile =  InputOutputFile.bacaFile();
            inputan = InputOutputFile.pisah(matriksFile);
        }
        Matrix m = inputan.m;
        Matrix x = inputan.x;

        double det, hasil[];
        hasil = new double[x.row]; 
        det = Determinant.getDeterminantByCofactor(m);

        for(int i=0;i<x.row;i++){
            hasil[i] = Determinant.getDeterminantByCofactor(substitusiX(m, x, i))/det;
        }

        for(int i=0;i<x.row;i++){
            System.out.println(hasil[i]);
        }
    }

    public static Matrix substitusiX(Matrix m, Matrix x, int kol){
        Matrix mTemp;
        double[][] temp;
        temp = new double[m.row][m.col];
        for(int i=0;i<x.row;i++){
            for(int j=0;j<m.col;j++){
                if(j!=kol){
                    temp[i][j] = m.contents[i][j];
                }else{
                    temp[i][j] = x.contents[i][0];
                }
            }
        }
        mTemp = new Matrix(temp, m.row, m.col);
        return mTemp;
    }
}
