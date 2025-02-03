package upo20054368.ristorante;// Dario Stilo 20054368 ed Edoardo Scamuzzi 20054488

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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





    public List<String> listClienteToListString(List<Cliente> lista)
    {
        List<String>list = new ArrayList<>();
        list.add(lista.getFirst().toString());
        int i=1;
        int lenghtClienti = lista.size();
        while(i<lenghtClienti)
        {
            list.add(lista.get(i).toString());
            i++;
        }
        return list;
    }

    public void stampaListaClientiView(List<Cliente> listaClientiInput, Stage stage, Scene backscene)
    {
        Button back = new Button("Back");
        if(!listaClientiInput.isEmpty())
        {
            List<String> clienti= listClienteToListString(listaClientiInput);
            String stringaCompleta=clienti.getFirst();
            int i=1;
            int lenghtClienti = listaClientiInput.size();
            while(i<lenghtClienti)
            {
                stringaCompleta = String.join("\n",clienti.get(i));
                i++;
            }
            Label outputClienti = new Label();
            System.out.println(stringaCompleta);
            outputClienti.setText(stringaCompleta);

            VBox box = new VBox(outputClienti, back);
            box.setSpacing(10);
            Scene scene = new Scene(box,400,900);
            stage.setScene(scene);




        }else {
            printBooleanResultView(false, stage, backscene);
        }
        back.setOnAction(e->{

            stage.setScene(backscene);
        });




    }

    public void stampaClienteSingoloView(Cliente cliente,Stage stage, Scene backscene)
    {
        VBox box = new VBox();
        box.setSpacing(10);
        Label label = new Label();
        Button back = new Button("Back");
        box.getChildren().addAll(label,back);
        Scene scene = new Scene(box,400,100);
        label.setText(cliente.toString()+"--"+calcolaEta(cliente.getAnnoNascita()));
        stage.setScene(scene);
        stage.show();

        back.setOnAction(e -> {
            stage.setScene(backscene);



        });
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

    public void printBooleanResultView(boolean check, Stage stage, Scene backscene)
    {
        Label label = new Label();
        Button back = new Button("Back");
        VBox booleanResult= new VBox(label,back);
        booleanResult.setSpacing(10);
        Scene scene = new Scene(booleanResult,200,150);
        if(check) label.setText("Operazione completata");
        else label.setText("Operazione non completata");



        stage.setScene(scene);

        back.setOnAction(e->{

            stage.setScene(backscene);


        });


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
