package upo20054368.ristorante;// Dario Stilo 20054368 ed Edoardo Scamuzzi 20054488

import java.time.LocalDate;
import java.io.Serializable;

/**
 * Classe che rappresenta l'ordine di un cliente.
 * @version 31.01.2025
 * @author Dario Stilo 20054368
 * @author Edoardo Scamuzzi 20054488
 */
public class Ordine implements Serializable{

    /**
     * Data dell'ordine
     */
    LocalDate dataOrdine;
    /**
     * Numero di piatti nell'ordine
     */
    int numeroPiatti;
    /**
     * Tipo di menù dell'ordine.
     * Può essere piccolo (value: 1), medio (value: 2), grande (value: 3).
     */
    int tipoMenu;




    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Ordine ordine = (Ordine) o;
        return (this.dataOrdine.isEqual(ordine.dataOrdine))&&(this.numeroPiatti==ordine.numeroPiatti)&&(this.tipoMenu==ordine.tipoMenu);


    }

    @Override
    public String toString()

    {
    return this.dataOrdine + "\t"+this.numeroPiatti+"\t"+this.tipoMenu;
    }



    /**
     * Costruttore della classe Ordine.
     *
     * @param dataOrdineC
     * Data dell'ordine
     * @param numeroPiattiC
     * Numero di piatti nell'ordine
     * @param tipoMenuC
     * Tipo di menù dell'ordine.
     *
     * Può essere piccolo (value: 1), medio (value: 2), grande (value: 3).
     *
     */



    //le C in fondo ai nomi dei parametri sono per distinguerle visivamente dagli attributi della classe
    public Ordine(LocalDate dataOrdineC, int numeroPiattiC, int tipoMenuC)
    {


        this.dataOrdine = dataOrdineC;
        if(numeroPiattiC<1)
            throw new IllegalArgumentException("Il numero di piatti non può essere minore di 1.");
        if(tipoMenuC<1 || tipoMenuC>3)
            throw new IllegalArgumentException("Il tipo di menù deve essere 1, 2 o 3.");
        this.numeroPiatti = numeroPiattiC;
        this.tipoMenu = tipoMenuC;
    }

    /**
     * Questo metodo ottiene la Data dell'ordine.
     * @return dataOrdine, usa java.time.LocalDate
     */
    public LocalDate getDataOrdine()
    {
        return dataOrdine;
    }
    /**
     * Questo metodo ottiene il numero dei piatti dell'ordine.
     * @return numeroPiatti
     */
    public int getNumeroPiatti()
    {
        return numeroPiatti;
    }
    /**
     * Questo metodo ottiene il tipo di menù dell'ordine.
     * @return tipoMenu
     */
    public int getTipoMenu()
    {
        return tipoMenu;
    }

}
