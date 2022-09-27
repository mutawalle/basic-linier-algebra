import java.text.DecimalFormat;

public class SPLInvers {

    private static float[][] invers(float[][] matriks, int ukuran){
        // asumsi sudah matriks persegi
        float[][] hasil = new float[ukuran][ukuran];
        float[][] identitas = new float[ukuran][ukuran];
        boolean hasInvers = true;

        for(int i=0;i<ukuran;i++){
            for(int j=0;j<ukuran;j++){
                identitas[i][j] = matriks[i][j];
                if(i==j){
                    hasil[i][j] = 1;
                }else{
                    hasil[i][j] = 0;
                }
            }
        }

        // gauss
        for(int i=0;i<ukuran;i++){
            if(identitas[i][i] == 0){
                // cari yang ujungnya tidak 0 dan tukar
                int j=i+1;
                while(j<ukuran && identitas[j][i] == 0){
                    j++;
                }
                if(j<ukuran){
                    float temp;
                    for(int k=0;k<ukuran;k++){
                        temp = identitas[i][k];
                        identitas[i][k] = identitas[j][k];
                        identitas[j][k] = temp;
                        temp = hasil[i][k];
                        hasil[i][k] = hasil[j][k];
                        hasil[j][k] = temp;
                    }
                }else{
                    hasInvers = false;
                    break;
                }
            }
            float pembagi = identitas[i][i];
            for(int j=0;j<ukuran;j++){
                identitas[i][j] /= pembagi;
                hasil[i][j] /= pembagi;
            }
            for(int j=i+1;j<ukuran;j++){
                float pengurang = identitas[j][i];
                for(int k=0;k<ukuran;k++){
                    identitas[j][k] -= identitas[i][k]*pengurang;
                    hasil[j][k] -= hasil[i][k]*pengurang;
                }
            }
        }

        // jordan

        for(int i=ukuran-1;i>=0;i--){
            for(int j=i-1;j>=0;j--){
                float pengurang = identitas[j][i];
                for(int k=0;k<ukuran;k++){
                    identitas[j][k] -= pengurang*identitas[i][k];
                    hasil[j][k] -= pengurang*hasil[i][k];
                }
            }
        }
        if(hasInvers){
            return hasil;
        }else{
            return matriks;
        }
    }

    private static float[] SPLinvers(float[][] matriks, float[] x, int ukuran){
        float[] hasil= new float[ukuran];
        for(int i=0;i<ukuran;i++){
            hasil[i] = 0;
            for(int j=0;j<ukuran;j++){
                hasil[i] += matriks[i][j]*x[j];
            }
        }
        return hasil;
    }

    private static final DecimalFormat dfZero = new DecimalFormat("0.00");

    public static void main(String[] args) {
        // System.out.printf("Hello, World! %d\n", 897);
        float[][] arr = {{1,4,3},{4,5,6}, {7,8,9}};
        float[][] inver = invers(arr, 3);
        float[] x = {1,2,3};

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(inver[i][j]+" ");
            }
            System.out.println();
        }
        float[] arrBaru = SPLinvers(inver, x, 3);
        for(int i=0;i<3;i++){
            System.out.println(dfZero.format(arrBaru[i]));
        }
    }
    
}