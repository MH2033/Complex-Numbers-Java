package com.complexnumbers.functions;

import com.complexnumbers.numbers.Complex;
import com.complexnumbers.numbers.ComplexCartesian;
import com.complexnumbers.numbers.ComplexPolar;

public class ComplexFunctions {

    public static Complex exp(Complex x){
        return new ComplexCartesian(Math.exp(x.getX()) * Math.cos(x.getY()), Math.exp(x.getX()) * Math.sin(x.getY()));
    }

    public static Complex ln(Complex x){
        return new ComplexCartesian(Math.log(x.getR()), x.getTheta());
    }

    public static Complex sin(Complex x){
        return new ComplexCartesian(Math.sin(x.getX()) * Math.cosh(x.getY()),Math.cos(x.getX()) * Math.sinh(x.getY()));
    }

    public static Complex cos(Complex x){
        return new ComplexCartesian(Math.cos(x.getX() * Math.cosh(x.getY())), -Math.sin(x.getX()) * Math.sinh(x.getY()));
    }

    public static Complex sinh(Complex x){
        return new ComplexCartesian(Math.sinh(x.getX()) * Math.cos(x.getY()), Math.sin(x.getY()) * Math.cosh(x.getX()));
    }

    public static Complex cosh(Complex x){
        return new ComplexCartesian(Math.cosh(x.getX()) * Math.cos(x.getY()), Math.sin(x.getX()) * Math.sinh(x.getY()));
    }

    public static Complex pow(Complex x, int n){
        return new ComplexPolar(Math.pow(x.getR(), n), x.getTheta() * n);
    }

}
