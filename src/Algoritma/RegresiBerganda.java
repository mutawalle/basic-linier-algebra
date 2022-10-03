package src.Algoritma;

import java.util.Scanner;

import src.ADTMatrix.*;
import src.InputOutput.InputOutputFile;
import src.Primitif.Primitif;

public class RegresiBerganda {
    public static double colsum(Matrix m,int j){
        int i;
        double sum;
        sum = 0;
        for(i=0;i<m.row;i++){
            sum+=m.contents[i][j];
        }
        return sum;
    }

    public static double twocolsum(Matrix m, int j1, int j2){
        int i;
        double sum;
        sum = 0;
        for(i=0;i<m.row;i++){
            sum+=(m.contents[i][j1]*m.contents[i][j2]);
        }
        return sum;
    }

    //Multiple Linear Regression
    public static void MLR(){
    /*Prosedur membuat matrix data, kemudian membuat matrix estimation equation yang disimpan di equation*/
    /*Mengembalikan matrix SPL (belum diselesaikan)*/
    
    /*row format 
    [[x11,x21,..,xk1, y1]
    [x12, x22,..,xk2, y2]
    ...
    [x1k, x2k,..,xkk, yk]]*/
    
        int k, row, i, j;
        Matrix data, equation, xTaksir, mtemp;
        double hasilTaksir;
        double[][] content;
        Scanner input = new Scanner(System.in);
        System.out.println("Ketik 1 untuk input keyboard, Ketik lainnya untuk input file");
        System.out.println("Dan Pastikan file ada di folder test jika pilih input file");
        int pilihan = input.nextInt();
        if(pilihan==1){
            System.out.print("Masukkan banyaknya x peubah:\n");
        
            k = input.nextInt();
            System.out.print("Masukkan banyak sampel data: \n");
            row = input.nextInt();
            System.out.println("Masukkan Matrix");
            data = Primitif.readMatrix1(row, k+1);
            System.out.println("Masukkan x sebanyak k");
            xTaksir = Primitif.readMatrixtaksir(k, 1);
        }else{
            data = InputOutputFile.bacaBikubik();
            content = new double[data.col-1][1];
            xTaksir = new Matrix(content, data.col-1, 1);
            for(i=0;i<data.col-1;i++){
                xTaksir.contents[i][0] = data.contents[data.row-1][i];
            }
            data = new Matrix(data.contents, data.row-1, data.col);
            k = data.col-1;
            row = data.row-1;
        }

    //Pembuatan normal estimation equation
    
    content = new double[k+1][k+2];
    equation = new Matrix(content, k+1, k+2);
    for(i=0;i<equation.row; i++){
        for(j=0;j<equation.col; j++){
            if(i==0){
                if(j==0){
                    equation.contents[i][j] =row;
                }
                else{
                    equation.contents[i][j] = colsum(data, (j-1));
                };
            }
            else{
                if(j==0){
                    equation.contents[i][j]= colsum(data, (i-1));
                }
                else{
                    equation.contents[i][j]=twocolsum(data, (i-1), (j-1));
                };
            };   
        }
    }
    mtemp = Gauss.gauss(data);
    equation = GaussJordan.gaussJordan(mtemp);
    System.out.print("Persamaan Regresi berganda\ny = ");

    hasilTaksir = 0;
    for(i=0;i<equation.row;i++){
        System.out.print(equation.contents[i][equation.col-1]);
        if(i==0){
            hasilTaksir += equation.contents[i][equation.col-1];
        }else{
            hasilTaksir += equation.contents[i][equation.col-1]*xTaksir.contents[i-1][0];
            System.out.print("*x" + i);
        }
        if(i!= equation.row - 1){
            System.out.print(" + ");
        }else{
            System.out.print("\n");
        }
    }
    System.out.println(hasilTaksir);
    InputOutputFile.OutputRegresi(hasilTaksir, equation, xTaksir);

    }
}
