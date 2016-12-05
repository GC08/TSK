/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electric_circuit;

/**
 *
 * @author Jacobo
 */
public class Transistor {
    
    public Transistor(double ubase, double ucol, int beta)
    {
        this.Ubase = ubase;
        this.Ucol = ucol;
        this.beta = beta;
    }
    
    public static double UBE =  0.7;
    public static int RCol =  10;
    public static int RBase =  10;
    
    protected int beta;
    
    public int getBeta(){
        return this.beta;
    }
    public void setBeta(int beta){
        this.beta = beta;
    }
    
    protected double Ubase;
    
    public double getUbase(){
        return this.Ubase;
    }
    public void setUbase(double ubase){
        this.Ubase = ubase;
    }
    
    protected double Ucol;
    
    public double getUcol(){
        return this.Ucol;
    }
    public void setUcol(double ucol){
        this.Ucol = ucol;
    }
    
    public double Ibase()
    {
        double tmp = this.Ubase - this.UBE;
        if(tmp <= 0)
            return 0;
        return (this.Ubase - this.UBE)/this.RBase;
    }
    public double Icol()
    {
        if(Ibase() == 0)
            return 0;
        return this.beta * Ibase();
    }
    public double Iemi()
    {
        if(Ibase() == 0)
            return 0;
        return this.Icol() + this.Ibase();
    }
    
}
