/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitivasgraficas;


/**
 *
 * @author g4l1l3u
 */
public class NoAET implements Comparable<NoAET>{
    
    private double Ymax, Xmin, IncrX;

    public NoAET(double Ymax, double Xmin, double IncrX) {
        this.Ymax = Ymax;
        this.Xmin = Xmin;
        this.IncrX = IncrX;
    }

    public double getYmax() {
        return Ymax;
    }

    public void setYmax(double Ymax) {
        this.Ymax = Ymax;
    }

    public double getXmin() {
        return Xmin;
    }

    public void setXmin(double Xmin) {
        this.Xmin = Xmin;
    }

    public double getIncrX() {
        return IncrX;
    }

    public void setIncrX(double IncrX) {
        this.IncrX = IncrX;
    }
    
    @Override 
    public int compareTo(NoAET no)
    {
        if(no.getXmin() < Xmin)
            return 1;
        else
            return -1;
    }
    
}
