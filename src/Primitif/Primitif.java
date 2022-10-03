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
                    if(kol == m.col-2){
                        bar++;
                    }
                    kol = (kol+1)%(m.col-1);
                }
            }
        }
        Matrix hasil = new Matrix(temp, m.row-1, m.col-1);
        // System.out.println(a+" "+b);
        // displayMatrix(hasil);
        // System.out.println();
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

    public static Matrix matriksKaliMatriks(Matrix a, Matrix b){
        Matrix hasil;
        double[][] temp = new double[a.row][b.col];
        for(int i=0;i<a.row;i++){
            for(int j=0;j<b.col;j++){
                temp[i][j] = 0;
            }
        }
        hasil = new Matrix(temp, a.row, b.col);
        for(int i=0;i<a.row;i++){
            for(int j=0;j<b.col;j++){
                for(int k=0;k<a.col;k++){
                    temp[i][j] += a.contents[i][k]*b.contents[k][j];
                }
            }
        }

        return hasil;
    }

    public static Matrix tambahBaris(Matrix m, double[] barisBaru){
        Matrix temp;
        double[][] mTemp;
        mTemp = new double[m.row+1][m.col];
        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                mTemp[i][j] = m.contents[i][j];
            }
        }
        for(int i=0;i<m.col;i++){
            mTemp[m.row][i] = barisBaru[i];
        }
        temp = new Matrix(mTemp, m.row+1, m.col);
        return temp;
    }

    public static Matrix tambahKolom(Matrix m, double[] kolomBaru){
        Matrix temp;
        double[][] mTemp;
        mTemp = new double[m.row][m.col+1];
        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                mTemp[i][j] = m.contents[i][j];
            }
        }
        for(int i=0;i<m.row;i++){
            mTemp[i][m.col] = kolomBaru[i];
        }
        temp = new Matrix(mTemp, m.row, m.col+1);
        return temp;
    }

    public static Matrix readMatrix1(int baris, int kolom){
        Scanner input = new Scanner(System.in);
        double[][] mTemp;
        Matrix temp;
        mTemp = new double[baris][kolom];
        for(int i=0;i<baris;i++){
            for(int j=0;j<kolom;j++){
                if(j!=kolom-1){
                System.out.printf("%s%d,%d: ", "Masukkan x", i+1, j+1);
                mTemp[i][j] = input.nextDouble();
                }else{
                    System.out.printf("%s%d: ", "Masukkan y", i+1);
                    mTemp[i][j] = input.nextDouble();  
                }
            }
        }
        temp = new Matrix(mTemp, baris, kolom);
        return temp;
    }
    public static Matrix readMatrixtaksir(int baris, int kolom){
        Scanner input = new Scanner(System.in);
        double[][] mTemp;
        Matrix temp;
        mTemp = new double[baris][kolom];
        for(int i=0;i<baris;i++){
            for(int j=0;j<kolom;j++){
                System.out.printf("%s%d: ", "Masukkan x", i+1);
                mTemp[i][j] = input.nextDouble();
            }
        }
        temp = new Matrix(mTemp, baris, kolom);
        return temp;
    }
}

