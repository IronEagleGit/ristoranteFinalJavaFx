package upo20054368.ristorante;// Dario Stilo 20054368 ed Edoardo Scamuzzi 20054488

import java.time.LocalDate;
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


    public RistoranteController(AtomicReference<Ristorante> model, RistoranteView view) {
        this.model=model;
        this.view=view;
    }



    public void inserisciClienteController()
    {

        String identif = view.inserisciIdView();
        int annoNasInser=0;
        try{
            annoNasInser = view.inserisciAnnoNascitaView();
        }catch(InputMismatchException e){System.out.println("Il tipo dell'input non è int.");}

        LocalDate giornoReg=LocalDate.of(0,1,1);//default date
        try{

            giornoReg = view.inserisciGiornoRegView();

        }catch(DateTimeParseException e){System.out.println("Errore nel parsing della data in input.");}

        boolean check;
        if(!identif.isEmpty()) {
            check = model.inserisciClienteModel(identif, annoNasInser, giornoReg);
        }else check= model.inserisciClienteModel(annoNasInser, giornoReg);

        view.printBooleanResultView(check);

    }


    public void ricercaClienteController()
    {
        String iden = view.ricercaClienteView();
        Cliente clienteOutput = model.ricercaClienteModel(iden);
        view.stampaClienteSingoloView(clienteOutput);

    }

    public void ricercaEtaClienteController()
    {
        int[] dateInput = view.ricercaEtaClienteView();
        List<Cliente> listaOut=new ArrayList<>();
        if(dateInput[0]!=0&&dateInput[1]!=0) {
            listaOut= model.ricercaEtaClienteModel(dateInput[0], dateInput[1]);
        }
        view.stampaListaClientiView(listaOut);
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

        view.printBooleanResultView(check);


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
