package upo20054368.ristorante;// Dario Stilo 20054368 ed Edoardo Scamuzzi 20054488

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Classe view (paradigma MVC) per il model ristorante
 * @version 31.01.2025
 * @author Dario Stilo 20054368
 * @author Edoardo Scamuzzi 20054488
 *
 */
public class RistoranteView {


    public static Scanner input = new Scanner(System.in);



    public void stampaListaClientiView(List<Cliente> listaClientiInput)
    {

        int lenghtClienti = listaClientiInput.size();
        int i =0;
        System.out.println("Clienti trovati:");
        System.out.println();
        System.out.println("Id--Anno Nascita--Giorno registrazione--Età");
        System.out.println();

        while(i<lenghtClienti)
        {
            Cliente c = listaClientiInput.get(i);
            System.out.println(c.toString()+"--"+calcolaEta(c.getAnnoNascita()));
            System.out.println();
            i++;
        }



    }

    public void stampaClienteSingoloView(Cliente cliente)
    {
        System.out.println("Id--Anno Nascita--Giorno registrazione--Età");
        System.out.println();

        System.out.println(cliente.toString()+"--"+calcolaEta(cliente.getAnnoNascita()));
    }

    public void statisticheNumeroPiattiView(int max, int min, float media)
    {

        System.out.println("Le statistiche dei piatti sono:");
        System.out.println("Numero massimo di piatti in un ordine: " + max);
        System.out.println("Numero minimo di piatti in un ordine: " + min);
        System.out.println("Media del numero di piatti degli ordini: " + media);
        System.out.println();
        System.out.println();
    }



    public void statisticheTipoMenuView(int piccolo, int medio, int grande)
    {
        System.out.println("Le statistiche dei menù sono:");
        System.out.println("Numero di ordini del menù piccolo: " + piccolo);
        System.out.println("Numero di ordini del menù medio: " + medio);
        System.out.println("Numero di ordini del menù grande: " + grande);
        System.out.println();
        System.out.println();
    }

    public void printBooleanResultView(boolean check)
    {
        if(check) System.out.println("Operazione Completata");
        else System.out.println("Operazione Fallita");

        System.out.println();
        System.out.println();
    }



    public String inserisciIdView()
    {
        String identif=null;


        //inserimento da tastiera dell'identificativo;
        System.out.println("Inserisci l'identificativo del nuovo cliente, altrimenti premi invio per generarne uno casuale.");
        identif = input.nextLine();
        System.out.println();


        return identif;
    }

    public int inserisciAnnoNascitaView()
    {
        System.out.println("Inserisci l'anno di nascita del nuovo cliente (intero)");
        int annoNascita= input.nextInt();
        input.nextLine();
        System.out.println();
        return annoNascita;
    }

    public LocalDate inserisciGiornoRegView()
    {
        System.out.println("Inserisci la data di registrazione");

        LocalDate giornoReg = dataInput();

        System.out.println();
        return giornoReg;
    }

    public LocalDate inserisciDataGenericaView()
    {
        System.out.println("Inserisci la data in input.");
        LocalDate data = dataInput();
        System.out.println();
        return data;
    }



    private static LocalDate dataInput() {

        System.out.println("Il formato della data è gg/mm/aaaa");
        String userInput = input.nextLine();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
        System.out.println();

        return LocalDate.parse(userInput, dateFormat);
    }


    public String ricercaClienteView()
    {

        System.out.println("Inserisci l'identificativo del cliente.");

        String iden = input.nextLine();
        System.out.println();

        return iden;

    }


    public int[] ricercaEtaClienteView()
    {
        int etaMin,etaMax;
        int[] risultati = new int [2];
        risultati[0]=0;
        risultati[1]=0;
        try{
            System.out.println("Inserisci l'età minima del cliente");
            etaMin = input.nextInt();
            while (etaMin < 0) {
                System.out.println("Inserisci un'età maggiore di 0.");
                etaMin = input.nextInt();
                input.nextLine();
            }
            System.out.println("Inserisci l'età massima del cliente");
            etaMax = input.nextInt();
            while (etaMax < etaMin) {
                System.out.println("Inserisci un età massima maggiore o uguale all'età minima");

                etaMax = input.nextInt();
                input.nextLine();
            }
            risultati[0] = etaMin;
            risultati[1] = etaMax;
            //chiama metodo ristorantecontroller

        }catch(InputMismatchException e){
            System.out.println("Errore di input, il tipo di input deve essere int. Riprovare.");
        };
        return risultati;

    }

    public int[] inputDatiOrdineView()
    {
        int[] arr = new int[2];


        try
        {
            System.out.println("Inserisci il numero di piatti (int>0)");
            System.out.println();
            arr[0]= input.nextInt();
            input.nextLine();
            System.out.println("Inserisci il tipo menù. (int compreso tra 1 e 3)");
            System.out.println();
            arr[1]= input.nextInt();
            input.nextLine();
        }catch(InputMismatchException e){System.out.println("Errore di input, il tipo di input deve essere int. Riprovare.");}

        return arr;
    }
    private static int calcolaEta(int annoNasc)
    {

        int year = Year.now().getValue();
        return year - annoNasc;

    }

    public int inserisciTipoMenu()
    {
        System.out.println("Inserisci il tipo menu (int compreso tra 1 e 3.");
        System.out.println();
        int num = input.nextInt();
        input.nextLine();
        if(num<1 || num>3) throw new IllegalArgumentException();
        return num;
    }
    public int inserisciNumeroPiatti()
    {
        System.out.println("Inserisci il numero di piatti (int>=0).");
        System.out.println();
        int num = input.nextInt();
        input.nextLine();
        if(num<0) throw new IllegalArgumentException();
        return num;
    }
    public LocalDate[] inputDoppiaDataView()
    {
        System.out.println("Inserisci la prima data in ordine cronologico");
        LocalDate data1 = dataInput();
        System.out.println("Inserisci la prima data in ordine cronologico");

        LocalDate data2 = dataInput();
        if(data1.isAfter(data2))throw new IllegalArgumentException();



        LocalDate[] arr = new LocalDate[2];
        arr[0] = data1;
        arr[1] = data2;
        return arr;

    }

    public void stampaListaOrdiniView(List<Ordine> listaOrdine)
    {
        int size=listaOrdine.size();
        int i=0;
        System.out.println("Data ordine\tNumero piatti\tTipo menu");
        while(i<size)
            System.out.println(listaOrdine.get(i).toString());


    }

    public void getNumberOfOrdersView(int num)
    {
        System.out.println("Il numero di ordini per questo cliente è: "+num);
    }


}
