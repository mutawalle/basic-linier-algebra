package src.Algoritma;

import java.util.Scanner;

import src.ADTMatrix.*;
import src.Primitif.Primitif;
import src.InputOutput.*;

public class Invers {
    public static void inversSPL(){
        Matrix temp;
        PasanganMatrix inputan;
        Scanner input = new Scanner(System.in);
        System.out.println("Ketik 1 untuk input keyboard, Ketik lainnya untuk input file");
        System.out.println("Dan Pastikan file ada di folder test jika pilih input file");
        int pilihan = input.nextInt();
        if(pilihan==1){
            temp = InputOutputKeyboard.inputSPL();
            inputan = InputOutputFile.pisah(temp);
        }else{
            Matrix matriksFile;
            matriksFile =  InputOutputFile.bacaFile();
            inputan = InputOutputFile.pisah(matriksFile);
        }
        Matrix m = inputan.m;
        Matrix x = inputan.x;
        m = getInversByOBE(m);
        x = Primitif.matriksKaliMatriks(m, x);
        for(int i=0;i<x.row;i++){
            System.out.println(x.contents[i][0]);
        } 
    }

    public static void showInversOBE(){
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
        
        if(Determinant.getDeterminantByCofactor(inputan) == 0){
            System.out.println("Matrix tidak memiliki invers");
        }else{
            inputan = getInversByOBE(inputan);  
            Primitif.displayMatrix(inputan);
        }
    }

    public static void showInversCofactor(){
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
        if(Determinant.getDeterminantByCofactor(inputan) == 0){
            System.out.println("Matrix tidak memiliki invers");
        }else{
            inputan = getInversByCofactor(inputan);  
            Primitif.displayMatrix(inputan);
        }
    }

    public static Matrix getInversByOBE(Matrix m){
        // kamus
        double[][] hasil = new double[m.row][m.col];
        double[][] identitas = new double[m.row][m.col];
        Matrix hasilM;

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

        hasilM = new Matrix(hasil, m.row, m.col);
        return hasilM;
    }

    public static Matrix generateMatrixCofactor(Matrix m){
        Matrix hasil;
        double[][] temp = new double[m.row][m.col];

        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                temp[i][j] = 0;
            }
        }

        hasil = new Matrix(temp, m.row, m.col);
        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                hasil.contents[i][j] = Determinant.getDeterminantByCofactor(Primitif.kofaktor(m, i, j));
                if(i%2==0){
                    if(j%2==1){
                        hasil.contents[i][j] *= (-1);
                    }
                }else{
                    if(j%2==0){
                        hasil.contents[i][j] *= (-1);
                    }
                }
                // if(j>0){
                //     System.out.print(" ");
                // }
                // System.out.print(hasil.contents[i][j]);
            }
            // System.out.println();
        }
        return hasil;
    }

    public static Matrix getInversByCofactor(Matrix m) {
        // double det2 = Determinant.getDeterminantByOBE(m);
        double det = Determinant.getDeterminantByCofactor(m);
        m = Primitif.transpose(generateMatrixCofactor(m));
        // System.out.println(det);
        // System.out.println(det2);
        // Primitif.displayMatrix(m);
        m = Primitif.matrixKaliSkalar(m, 1/det);
        return m;
    }
}