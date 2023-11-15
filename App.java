package client.verifica_client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try 
        { 
            Socket socket = new Socket("localhost", 4567); //creo il socket e lo connetto al server
            Scanner input = new Scanner(System.in); //creo scanner

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //creo bufferedreader che riceve dal server
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());//creo bufferedreader che invia al server
            
            System.out.println(in.readLine());
            while (socket.isConnected())
            {
                System.out.println( "Inserisci 1 per scegliere una lettera, 2 per scegliere una parola o 3 per uscire: " );
                String n = input.nextLine();
                out.writeBytes(n+"\n"); //invia al server

                String confronto = in.readLine(); //riceve dal server
                
                if(confronto.equals("S"))
                {
                    System.out.println( "Inserisci una lettera: " );
                    String lettera = input.nextLine();
                    out.writeBytes(lettera+"\n"); 
                    System.out.println( in.readLine() );
                }
                if(confronto.equals("P"))
                {
                    System.out.println( "Inserisci una parola: " );
                    String parola = input.nextLine();
                    out.writeBytes(parola+"\n");
                    String esito=in.readLine();
                    if (!esito.equals("0")) 
                    {
                        System.out.println( "Parola indovinata con "+esito+" tentativi" );
                    }
                    else
                    {
                        System.out.println( "Parola non indovinata" );
                    }
                }
                if(confronto.equals("E"))
                {
                    System.out.println( "stai uscendo dal gioco" );
                    socket.close(); //termino soket
                }

            } 
            
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }
}
