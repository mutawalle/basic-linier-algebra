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
    Matrix data, equation, xTaksir;
    double[][] content;
    Scanner input = new Scanner(System.in);
    System.out.print("Masukkan banyaknya x peubah:\n");
    double hasilTaksir;


    k = input.nextInt();
    System.out.print("Masukkan banyak sampel data: \n");
    row = input.nextInt();
    System.out.println("Masukkan Matrix");
    data = Primitif.readMatrix1(row, k+1);
    System.out.println("Masukkan x sebanyak k");
    xTaksir = Primitif.readMatrix1(k, 1);
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
    equation = GaussJordan.gaussJordan(data);

    hasilTaksir = 0;
    for(i=0;i<equation.row;i++){
        if(i==0){
            hasilTaksir += equation.contents[i][equation.col-1];
        }else{
            hasilTaksir += equation.contents[i][equation.col-1]*xTaksir.contents[i-1][0];
        }
    }
    System.out.println(hasilTaksir);

    }
}
