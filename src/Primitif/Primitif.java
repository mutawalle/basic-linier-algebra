package src.Primitif;

import java.util.Scanner;

import src.ADTMatrix.Matrix;

public class Primitif {
    public static Matrix readMatrix() {
        // Kamus
        Scanner input = new Scanner(System.in);
        int r,c;
        double[][] matriks;
        Matrix m;

        // input row dan column
        System.out.print("Masukkan jumlah baris: ");
        r = input.nextInt();
        System.out.print("Masukkan jumlah kolom: ");
        c = input.nextInt();

        // create matriks
        matriks = new double[r][c];

        // input matriks
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                matriks[i][j] = input.nextDouble();
            }
        }

        // create Matrix
        m = new Matrix(matriks, r, c);

        return m;
    }

    public static void displayMatrix(Matrix m){
        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                if(j>0){
                    System.out.print(" ");
                }
                System.out.printf("%.2f", m.contents[i][j]);
            }
            System.out.println();
        }
    }

    public static double[] readX(int n){
        // kamus
        double[] x;
        Scanner input = new Scanner(System.in);

        // input
        x = new double[n];
        for(int i=0;i<n;i++){
            x[i] = input.nextDouble();
        }

        return x;
    }

    // mengembalikan kofaktor dari matriks
    public static Matrix kofaktor(Matrix m, int a, int b){
        double[][] temp = new double[m.row-1][m.col-1];
        int bar,kol;

        bar = 0;
        kol = 0;

        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                if(i != a && j != b){
                    temp[bar][kol] = m.contents[i][j];
                    kol++;
                    if(kol == m.col-1){
                        bar++;
                        kol %= (m.col-1);
                    }
                }
            }
        }
        Matrix hasil = new Matrix(temp, m.row-1, m.col-1);
        return hasil;
    }

    // mengembalikan transpos dari sebuah matrix
    public static Matrix transpose(Matrix m){
        double[][] temp = new double[m.col][m.row];
        Matrix hasil;

        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                temp[j][i] = m.contents[i][j];
            }
        }

        hasil = new Matrix(temp, m.col, m.row);

        return hasil;
    }

    public static Matrix matrixKaliSkalar(Matrix m, double x){
        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                m.contents[i][j] *= x;
            }
        }
        return m;
    }

    public static Matrix readMatrix1(int r, int c) {
        // Kamus
        Scanner input = new Scanner(System.in);
        double[][] matriks;
        Matrix m;
        // create matriks
        matriks = new double[r][c];

        // input matriks
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                matriks[i][j] = input.nextDouble();
            }
        }

        // create Matrix
        m = new Matrix(matriks, r, c);

        return m;
    }
}

