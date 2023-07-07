import javax.sound.midi.Soundbank;
import java.io.*;

public class principal {
    public static void main(String[] args) throws FileNotFoundException{
        String filePath="datos.dat"; //Ruta donde se va a guardar el archivo
        MiClase miObject=new MiClase("Gilmar",20);

        try(FileOutputStream fileOut= new FileOutputStream(filePath);
            ObjectOutputStream obOut=new ObjectOutputStream(fileOut);

        ){
            obOut.writeObject(miObject);
            System.out.println("Archivo escrito correctamente");
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        /////////

        try(
                FileInputStream fileIn=new FileInputStream(filePath);
                ObjectInputStream objIn=new ObjectInputStream(fileIn);
                ) {
            MiClase readObject=(MiClase) objIn.readObject();
            System.out.println("El objeto en disco es: "+readObject);
            System.out.println(readObject.getEdad());
            System.out.println(readObject.getNombre());

        } catch (IOException e){
            throw new RuntimeException(e);
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
