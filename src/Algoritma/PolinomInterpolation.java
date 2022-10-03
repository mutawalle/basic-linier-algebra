package src.Algoritma;

import java.util.Scanner;

import src.ADTMatrix.*;
import src.InputOutput.*;
import src.Primitif.Primitif;

public class PolinomInterpolation {
    public static void polinomInterpolation(){
        Scanner input = new Scanner(System.in);
        PasanganMatrix temp;
        Matrix m, mtemp;
        double x, hasil;
        System.out.println("Ketik 1 untuk input keyboard, Ketik lainnya untuk input file");
        System.out.println("Dan Pastikan file ada di folder test jika pilih input file");
        int pilihan = input.nextInt();
        if(pilihan==1){
            m = InputOutputKeyboard.inputPolinom();
        }else{
            m =  InputOutputFile.bacaFile();
            double[][] mTemp;
            
            mTemp = new double[m.row][m.row+1];
            
            for(int i=0;i<m.row;i++){
                for(int j=0;j<m.row+1;j++){
                    if(j<m.row){
                        mTemp[i][j] = Math.pow(m.contents[i][0], j);
                    }else{
                        mTemp[i][j] = m.contents[i][1];
                    }
                }
            }
            
            m = new Matrix(mTemp, m.row, m.row+1);
        }

        System.out.println("Masukkan x yang akan ditaksir ");
        x = input.nextDouble();
        mtemp = Gauss.gauss(m);
        m = GaussJordan.gaussJordan(mtemp);
        temp = InputOutputFile.pisah(m);
        m = temp.x;

        hasil=0;
        System.out.print("f(x) = ");
        for(int i=0;i<m.row;i++){
            hasil += Math.pow(x, i)*m.contents[i][0];
        }
        for(int i=m.row-1;i>0;i--){
            System.out.print(m.contents[i][0]+"x^"+i+" + ");
        }
        System.out.println(m.contents[0][0]);
        System.out.println("hasilnya adalah "+hasil);
        InputOutputFile.OutputInterpolasiPolinom(m, hasil);
    }
}