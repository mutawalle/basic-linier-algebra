package src.Algoritma;

import java.util.Scanner;

import src.ADTMatrix.Matrix;
import src.InputOutput.*;
import src.Primitif.*;

public class Determinant {
    public static void showDetOBE(){
        double det;
        Matrix inputan;
        Scanner input = new Scanner(System.in);
        System.out.println("Ketik 1 untuk input keyboard, Ketik lainnya untuk input file");
        System.out.println("Dan Pastikan file ada di folder test jika pilih input file");
        int pilihan = input.nextInt();
        if(pilihan==1){
            inputan = InputOutputKeyboard.inputSquare();
        }else{
            Matrix matriksFile;
            matriksFile =  InputOutputFile.bacaFile();
            inputan = matriksFile;
        }
        det = getDeterminantByOBE(inputan);
        System.out.println(det);
        InputOutputFile.OutputSatuVariabel(det);
    }

    public static void showDetCofactor(){
        double det;
        Matrix inputan;
        Scanner input = new Scanner(System.in);
        System.out.println("Ketik 1 untuk input keyboard, Ketik lainnya untuk input file");
        System.out.println("Dan Pastikan file ada di folder test jika pilih input file");
        int pilihan = input.nextInt();
        if(pilihan==1){
            inputan = InputOutputKeyboard.inputSquare();
        }else{
            Matrix matriksFile;
            matriksFile =  InputOutputFile.bacaFile();
            inputan = matriksFile;
        }
        det = getDeterminantByCofactor(inputan);
        System.out.println(det);
        InputOutputFile.OutputSatuVariabel(det);
    }

    // Mendapat determinan dari matriks segitiga setelah dilakukan OBE
    public static double getDeterminantByOBE(Matrix m){
        double hasil;
        double[][] mtemp = new double[m.row][m.col];
        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                mtemp[i][j] = m.contents[i][j];
            }
        }

        hasil = 1;
        for(int i=0;i<m.row;i++){
            if(i<m.col){
                // mencari ujung yang 0
                if(mtemp[i][i] == 0){
                    int j=i+1;
                    while(j<m.row && mtemp[j][i] == 0){
                        j++;
                    }
                    if(j<m.row){
                        hasil *= (-1);
                        double temp;
                        for(int k=0;k<m.col;k++){
                            temp = mtemp[i][k];
                            mtemp[i][k] = mtemp[j][k];
                            mtemp[j][k] = temp;
                        }
                    }else{
                        hasil = 0;
                        break;
                    }
                }
    
                // membuat ujung kiri menjadi 1
                double pembagi = mtemp[i][i];
                hasil *= pembagi;
                if(pembagi != 0){
                    for(int j=0;j<m.col;j++){
                        mtemp[i][j] /= pembagi;
                    }
                }
    
                // membuat kolom di bawah angka 1 menjadi 0
                for(int j=i+1;j<m.row;j++){
                    double pengurang = mtemp[j][i];
                    for(int k=0;k<m.col;k++){
                        mtemp[j][k] -= mtemp[i][k]*pengurang;
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
        }else if(m.col*m.row == 4){
            return m.contents[0][0]*m.contents[1][1] - m.contents[0][1]*m.contents[1][0]; 
        }else{
            double hasil;
            hasil = 0;
            for(int i=0;i<m.row;i++){
                if(i%2==0){
                    hasil += m.contents[i][0]*getDeterminantByCofactor(Primitif.kofaktor(m, i, 0));
                }else{
                    hasil += (-1)*m.contents[i][0]*getDeterminantByCofactor(Primitif.kofaktor(m, i, 0));
                }
            }
            return hasil;
        }
    }
}
