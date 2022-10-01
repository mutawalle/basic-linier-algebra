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
    }

    // Mendapat determinan dari matriks segitiga setelah dilakukan OBE
    public static double getDeterminantByOBE(Matrix m){
        double hasil;

        hasil = 1;
        for(int i=0;i<m.row;i++){
            if(i<m.col){
                // mencari ujung yang 0
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
                hasil *= pembagi;
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
