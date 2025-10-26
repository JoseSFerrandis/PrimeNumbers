import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    //Method for determinate if a number is prime
    public static boolean isPrime(int n){
        if(n<2) return false; //Only positive and greater than2
        if(n==2) return true; // The 2 is the firs prime number
        if(n%2==0) return false; // all the even numbers

        for (int i = 3; i <= Math.sqrt(n); i+=2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // In these method, whe storage the prime numbers in the string source, and later write in the document primos.dat
    public static void contador(int n){
        String source= "";
        FileOutputStream f1 = null;
        int contador = 0;
        for(int i = 2; contador < n; i++) {
            if(isPrime(i)) {
                if(contador == n-1)
                {
                    source += Integer.toString(i);
                    source = source;
                    contador++;
                }
                else{
                source += Integer.toString(i);
                source = source +", ";
                contador++;
                }
            }
        }
        byte[] buf = source.getBytes();
        try{
            f1=new FileOutputStream("datos/primos.dat");
            f1.write(buf);//write byte to byte
        }
        catch(IOException e){
            System.out.println("An I/O error ocurred while writing to file"); //handle errors
        }
    }

    // The method for create the folder and the file  with the throws Exception
    public static void createObjects() throws IOException {
        File folder = new File("datos");
        if (!folder.exists()) { // Check if the folder already exists
            folder.mkdir();
        }
        File file = new File("datos/primos.dat");
        if (!file.exists()) { // Check if the file already exists
            file.createNewFile();
        }
    }

    //method for read the file
    public static void mostrarArchivo() {
        FileInputStream writer = null;
        try {
            writer = new FileInputStream("datos/primos.dat");
            int data;
            while ((data = writer.read()) != -1) { //read byte a byte
                System.out.print((char) data); // write how a character
            }
            System.out.println(); // new final line
        } catch (IOException e) {
            System.out.println("Error to read the file: " + e.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close(); // for close when finish
                }
            } catch (IOException e) {
                System.out.println("Error to close the file: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        createObjects();

        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Tell me how many prime numbers do you want?: ");
        int cantidad = input.nextInt();
        contador(cantidad);
        System.out.println("File Content primos.dat:");
        mostrarArchivo();
    }
}