package src.InputOutput;

import java.io.*;
import java.nio.file.Paths;
import src.ADTMatrix.*;
import src.Primitif.Primitif;

public class InputOutputFile {
    public static PasanganMatrix pisah(Matrix m){
        PasanganMatrix temp;
        Matrix a,b;
        double[][] xTemp, mTemp;
        xTemp = new double[m.row][1];
        mTemp = new double[m.row][m.col-1];
        for(int i=0;i<m.row;i++){
            for(int j=0;j<m.col;j++){
                if(j<m.col-1){
                    mTemp[i][j] = m.contents[i][j];
                }else{
                    xTemp[i][0] = m.contents[i][j];
                }
            }
        }
        a = new Matrix(mTemp, m.row, m.col-1);
        b = new Matrix(xTemp, m.row, 1);
        temp = new PasanganMatrix(a, b);
        return temp;
    }

    public static String getPath(){
        String path = Paths.get("").toAbsolutePath().toString();
        String hasil = "";
        int panjang;
        panjang = path.length();
        for(int i=0;i<panjang;i++){
            if((int) path.charAt(i) == 92){
                hasil += (char) 92;
                hasil += (char) 92;
            }else{
                hasil += path.charAt(i);
            }
        }
        return hasil;
    }

    public static Matrix bacaFile(){
        Matrix temp;
        try{
            BufferedReader br = new BufferedReader(
                new FileReader(getPath()+"\\test\\input.txt")
            );
            String s;
            String[] arrayString;
            double[] arrayDouble;
            double[][] mTemp;

            s = br.readLine();
            arrayString = s.split("\\s+");
            arrayDouble = new double[arrayString.length];
            for(int i=0;i<arrayString.length;i++){
                arrayDouble[i] = Double.parseDouble(arrayString[i]);
            }
            mTemp = new double[1][arrayString.length];
            for(int i=0;i<arrayDouble.length;i++){
                mTemp[0][i] = arrayDouble[i];
            }
            temp = new Matrix(mTemp, 1, arrayDouble.length);

            while((s = br.readLine()) != null){
                arrayString = s.split("\\s+");
                arrayDouble = new double[arrayString.length];
                for(int i=0;i<arrayString.length;i++){
                    arrayDouble[i] = Double.parseDouble(arrayString[i]);
                }
                temp = Primitif.tambahBaris(temp, arrayDouble);
            }
            br.close();
            return temp;
        }catch(Exception ex){
            double[][] x = new double[0][0];
            temp = new Matrix(x, 0, 0);
            return temp;
        }
    }

    public static Matrix bacaBikubik(){
        Matrix temp;
        try{
            BufferedReader br = new BufferedReader(
                new FileReader(getPath()+"\\test\\input.txt")
            );
            String s;
            String[] arrayString;
            double[] arrayDouble;
            double[][] mTemp;

            // baca baris pertama
            s = br.readLine();
            // ubah baris pertama menjadi array of string
            arrayString = s.split("\\s+");
            arrayDouble = new double[arrayString.length];
            // ubah ke array of double
            for(int i=0;i<arrayString.length;i++){
                arrayDouble[i] = Double.parseDouble(arrayString[i]);
            }
            // pindah ke matriks dengan kolom 1
            mTemp = new double[1][arrayString.length];
            for(int i=0;i<arrayDouble.length;i++){
                mTemp[0][i] = arrayDouble[i];
            }
            // buat matriks 
            temp = new Matrix(mTemp, 1, arrayDouble.length);

            while((s = br.readLine()) != null){
                arrayString = s.split("\\s+");
                arrayDouble = new double[arrayString.length];
                for(int i=0;i<arrayString.length;i++){
                    arrayDouble[i] = Double.parseDouble(arrayString[i]);
                }
                if(arrayString.length<temp.col){
                    double[] arrayTemp = new double[temp.col];
                    for(int i=0;i<temp.col;i++){
                        if(i<arrayDouble.length){
                            arrayTemp[i] = arrayDouble[i];
                        }else{
                            arrayTemp[i] = 0;
                        }
                    }
                    temp = Primitif.tambahBaris(temp, arrayTemp);
                }else{
                    temp = Primitif.tambahBaris(temp, arrayDouble);
                }
            }
            br.close();
            return temp;
        }catch(Exception ex){
            double[][] x = new double[0][0];
            temp = new Matrix(x, 0, 0);
            return temp;
        }
    }

    public static void OutputSPL(Matrix m){
        try{
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(getPath()+"\\test\\output.txt")
            );
            for(int i=0;i<m.row;i++){
                bw.write("x"+String.valueOf(i+1)+" "+m.contents[i][0]+"\n");
            }
            bw.close();
        }catch(Exception ex){
            return;
        }
    }

    public static void OutputSatuVariabel(double x){
        try{
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(getPath()+"\\test\\output.txt")
            );
            bw.write("Hasilnya adalah "+x);
            bw.close();
        }catch(Exception ex){
            return;
        }
    }

    public static void OutputInvers(Matrix m){
        try{
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(getPath()+"\\test\\output.txt")
            );
            for(int i=0;i<m.row;i++){
                for(int j=0;j<m.col;j++){
                    if(j>0){
                        bw.write(" ");
                    }
                    bw.write(""+m.contents[i][j]);
                }
                bw.write("\n");
            }
            bw.close();
        }catch(Exception ex){
            return;
        }
    }    

    public static void OutputInterpolasiPolinom(Matrix m, double hasil){
        try{
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(getPath()+"\\test\\output.txt")
            );

            bw.write("f(x) = ");
            for(int i=m.row-1;i>0;i--){
                bw.write(m.contents[i][0]+"x^"+i+" + ");
            }
            bw.write(m.contents[0][0]+"");
            bw.write("hasilnya adalah "+hasil);

            bw.close();
        }catch(Exception ex){
            return;
        }
    }
}
