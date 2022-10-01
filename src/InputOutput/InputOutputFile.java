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
    
    public static void tulisFile(){
        try{
            BufferedWriter bw = new BufferedWriter(
                new FileWriter("C:\\Users\\HP\\Documents\\Koding\\AlGeo\\test\\output.txt")
            );
            bw.write("ijfewf");
            bw.close();
        }catch(Exception ex){
            return;
        }
    }
}
