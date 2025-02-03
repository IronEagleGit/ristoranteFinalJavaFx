package upo20054368.ristorante;// Dario Stilo 20054368 ed Edoardo Scamuzzi 20054488

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Classe controller (paradigma MVC) per il model ristorante
 *
 * @version 31.01.2025
 * @author Dario Stilo 20054368
 * @author Edoardo Scamuzzi 20054488
 *
 */
public class RistoranteController {

    Ristorante model= new Ristorante();
    RistoranteView view= new RistoranteView();


    public RistoranteController(Ristorante model, RistoranteView view) {
        this.model=model;
        this.view=view;
    }


    public void inserisciClienteController(Stage stage1, Scene backscene) {

        TextField ggRegistrazioneInsCli= new TextField();
        ggRegistrazioneInsCli.setPromptText("dd/mm/yyyy");
        TextField inserisciAnnoNascita = new TextField();
        inserisciAnnoNascita.setPromptText("Anno nascita cliente (int)");
        TextField inserisciIdCliente = new TextField();
        inserisciIdCliente.setPromptText("Id cliente");
        Button okInserisciCliente = new Button("Save");
        Button back = new Button("Back");
        VBox box = new VBox();
        box.setSpacing(10);
        box.getChildren().addAll(inserisciIdCliente,inserisciAnnoNascita,ggRegistrazioneInsCli,okInserisciCliente,back);

        Scene scene = new Scene(box,400,300);
        stage1.setScene(scene);
        stage1.show();


        okInserisciCliente.setOnAction(e -> {
            LocalDate date=LocalDate.now();
            int anno =0;
            String identif = "";
            boolean check = false;
            try
            {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
                date = LocalDate.parse(ggRegistrazioneInsCli.getText(),dateFormat);
                anno = Integer.parseInt(inserisciAnnoNascita.getText());
                identif=inserisciIdCliente.getText();
                if(!identif.isEmpty())
                {
                    check=model.inserisciClienteModel(identif, anno, date );
                }else check =model.inserisciClienteModel(anno, date );

            }catch(DateTimeParseException | InputMismatchException dtpe){System.out.println(dtpe);}


            view.printBooleanResultView(check, stage1,backscene);


        });

        back.setOnAction(e->{

            stage1.setScene(backscene);


        });


    }



    public void ricercaClienteController(Stage stage, Scene backscene)
    {
        VBox box = new VBox();
        box.setSpacing(10);
        TextField inserisciIdCliente = new TextField();
        inserisciIdCliente.setPromptText("Id cliente");
        Button okCercaCliente = new Button("Cerca");
        Button back = new Button("Back");
        box.getChildren().addAll(inserisciIdCliente,okCercaCliente,back);
        Scene scene = new Scene(box,400,300);

        stage.setScene(scene);
        stage.show();




        okCercaCliente.setOnAction(e -> {
            String identif=inserisciIdCliente.getText();
            Cliente clienteOutput = model.ricercaClienteModel(identif);
            if(clienteOutput!=null) view.stampaClienteSingoloView(clienteOutput, stage,backscene);
            else view.printBooleanResultView(false, stage,backscene);
        });
        back.setOnAction(e->{
            stage.setScene(backscene);

        });

    }

    public void ricercaEtaClienteController(Stage stage, Scene backscene)
    {
        VBox box = new VBox();
        box.setSpacing(10);
        TextField inserisciEtaMin = new TextField();
        inserisciEtaMin.setPromptText("Eta Min");
        TextField inserisciEtaMax = new TextField();
        inserisciEtaMax.setPromptText("Eta Max");
        Button okDateInput = new Button("Ok");
        Button back = new Button("Back");
        box.getChildren().addAll(inserisciEtaMin,inserisciEtaMax,okDateInput,back);
        Scene scene = new Scene(box, 400,300);
        stage.setScene(scene);

        okDateInput.setOnAction(e -> {

            int datMin=0;
            int datMax=0;
            List<Cliente> listaOut=new ArrayList<>();
            try{
                datMin =Integer.parseInt(inserisciEtaMin.getText());
                datMax = Integer.parseInt(inserisciEtaMax.getText());
                listaOut= model.ricercaEtaClienteModel(datMin, datMax);
            }catch(DateTimeParseException | NumberFormatException ricClientEta){System.out.println(ricClientEta);}

            view.stampaListaClientiView(listaOut, stage,backscene);

        });
        back.setOnAction(e->{
            stage.setScene(backscene);

        });

    }

    public void aggiungiUnOrdineController()
    {
        Boolean check=false;
        String iden = view.ricercaClienteView();

        LocalDate dataOut = LocalDate.of(0,1,1);
        try
        {
            dataOut=view.inserisciDataGenericaView();
        }catch(DateTimeParseException e){System.out.println("Errore nel parsing della data in input.");}
        int[] arr =new int[2];
        arr=view.inputDatiOrdineView();
        try
        {
            check= model.aggiungiUnOrdineModel(iden, dataOut,arr[0], arr[1]);
        }catch(IllegalArgumentException e){System.out.println("Errore nell'inserimento dei dati in input.");}

        //view.printBooleanResultView(check,stage);


    }

    public void statisticheNumeroPiattiController()
    {
        int[] arr = new int[4];
        arr= model.statisticheNumeroPiattiModel();
        float media=0;
        media= model.calcolaMediaModel(arr[2], arr[3]);
        view.statisticheNumeroPiattiView(arr[0],arr[1],media);

    }

    public void statisticheTipoMenuController()
    {
        int[] arr= new int[3];
        arr = model.statisticheTipoMenuModel();
        view.statisticheTipoMenuView(arr[0],arr[1],arr[2]);


    }


    public void getOrdineDaDataController()
    {
        String iden =view.ricercaClienteView();
        try
        {
            LocalDate dataIn = view.inserisciDataGenericaView();
            view.stampaListaOrdiniView(model.getOrdineDaDataModel(iden, dataIn));

        }catch(NoSuchElementException e){System.out.println("Cliente non presente");}
        catch(DateTimeParseException f){System.out.println("Errore nel parsing della data in input.");}

    }
    public void getListaOrdiniDaTimeFrameController()
    {
        String iden =view.ricercaClienteView();

        try
        {
            LocalDate[] arr;
            arr= view.inputDoppiaDataView();
            view.stampaListaOrdiniView(model.getListaOrdiniDaTimeFrameModel(iden, arr[0], arr[1]));

        }catch(DateTimeParseException e){System.out.println("Errore nel parsing della data in input.");}
        catch (IllegalArgumentException f){System.out.println("Seconda data precedente alla prima.");}
        catch (NoSuchElementException g){System.out.println("Cliente non presente nella lista.");}


    }
    public void getListaOrdiniDaTipoMenuController()
    {
        String iden =view.ricercaClienteView();

        try {

            int tipMen= view.inserisciTipoMenu();
            view.stampaListaOrdiniView(model.getListaOrdiniDaTipoMenuModel(iden, tipMen));

        }catch(NoSuchElementException e){System.out.println("Cliente non presente nella lista.");}
        catch(IllegalArgumentException g){System.out.println("Il menu non è un intero compreso tra 1 e 3.");}
    }
    public void getListaOrdiniDaNumeroPiattiController()
    {
        String ident =view.ricercaClienteView();

        try {

            int numPiat= view.inserisciNumeroPiatti();
            view.stampaListaOrdiniView(model.getListaOrdiniDaTipoMenuModel(ident, numPiat));

        }catch(NoSuchElementException e){System.out.println("Cliente non presente nella lista.");}
        catch(IllegalArgumentException g){System.out.println("Il numero di piatti non è un intero maggiore di 0.");}
    }

    public void getNumberOfOrdersController()
    {
        String ident =view.ricercaClienteView();

        try {


            view.getNumberOfOrdersView(model.getNumberOfOrdersModel(ident));

        }catch(NoSuchElementException e){System.out.println("Cliente non presente nella lista.");}

    }

    public void cancellaDaTimeFrameController()
    {
        String ident =view.ricercaClienteView();

        try {
            LocalDate[] arr;
            arr= view.inputDoppiaDataView();
            model.cancellaDaTimeFrameModel(ident, arr[0], arr[1]);

        }catch(NoSuchElementException e){System.out.println("Cliente non presente nella lista.");}
        catch(DateTimeParseException e){System.out.println("Errore nel parsing della data in input.");}
        catch (IllegalArgumentException f){System.out.println("Seconda data precedente alla prima.");}

    }

    public void cancellaDaTipoMenuController()
    {
        String ident =view.ricercaClienteView();

        try {

            int tipMen= view.inserisciTipoMenu();
            model.cancellaDaTipoMenuModel(ident, tipMen);

        }catch(NoSuchElementException e){System.out.println("Cliente non presente nella lista.");}
        catch(IllegalArgumentException g){System.out.println("Il menu non è un intero compreso tra 1 e 3.");}


    }

    public void cancellaDaNumeroPiattiController()
    {
        String ident =view.ricercaClienteView();

        try {

            int numPiat= view.inserisciNumeroPiatti();
            model.cancellaDaNumeroPiattiModel(ident, numPiat);

        }catch(NoSuchElementException e){System.out.println("Cliente non presente nella lista.");}
        catch(IllegalArgumentException g){System.out.println("Il numero di piatti non è un intero maggiore di 0.");}

    }


}
