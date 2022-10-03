package src.Algoritma;

import java.io.ObjectInputFilter.Status;
import java.util.Scanner;

import src.ADTMatrix.Matrix;
import src.InputOutput.InputOutputFile;
import src.InputOutput.InputOutputKeyboard;
import src.Primitif.Primitif;

public class BicubicInterpolation {
    public static void bicubicInterpolate() {
        Scanner input = new Scanner(System.in);
        Matrix m;
        double a, b, hasil;
        int pilihan;
        System.out.println("Ketik 1 untuk input keyboard, Ketik lainnya untuk input file");
        System.out.println("Dan Pastikan file ada di folder test jika pilih input file");
        pilihan = input.nextInt();
        if(pilihan==1){
            m = InputOutputKeyboard.inputSquare();
            System.out.println("Masukkan x untuk ditaksir");
            a = input.nextDouble();
            System.out.println("Masukkan y untuk ditaksir");
            b = input.nextDouble();
        }else{
            m =  InputOutputFile.bacaBikubik();
            a = m.contents[m.row-1][0];
            b = m.contents[m.row-1][1];
            m = new Matrix(m.contents, m.row-1, m.col);
        }
        hasil = getOneInterpolate(m, a, b);
        System.out.println("f(" + a + "," + b+ ") = " + hasil);
        InputOutputFile.OutputSatuVariabel(hasil);
    }

    public static Matrix generateA(Matrix m, int baris1, int baris2, int kolom1, int kolom2){
        double[][] tempX = new double[16][16];
        double[][] tempA = new double[16][1];
        Matrix X, A;
        int baris, kolom;

        int indeks=0;
        for(int i=baris1;i<baris2;i++){
            for(int j=kolom1;j<kolom2;j++){
                tempA[indeks][0] = m.contents[i][j];
                indeks++;
            }
        }

        baris=0;
        kolom=0;
        for(int x=-1;x<3;x++){
            for(int y=-1;y<3;y++){
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        tempX[baris][kolom] = Math.pow(x,i)*Math.pow(y, j);
                        kolom++;
                    }
                }
                kolom=0;
                baris++;
            }
        }

        X = new Matrix(tempX, 16, 16);
        A = new Matrix(tempA, 16, 1);
        X = Invers.getInversByOBE(X);

        A = Primitif.matriksKaliMatriks(X, A);
        return A; 
    }

    public static double getOneInterpolate(Matrix m, double x, double y){
        double hasil;
        Matrix A = generateA(m, 0, m.row, 0, m.col);
        int indeks;
        hasil=0;
        indeks=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                hasil += A.contents[indeks][0]*Math.pow(x, i)*Math.pow(y, j);
                indeks++;
            }
        }

        return hasil;
    }
}
