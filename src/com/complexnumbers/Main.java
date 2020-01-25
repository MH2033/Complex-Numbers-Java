package com.complexnumbers;

import com.complexnumbers.functions.ComplexFunctions;
import com.complexnumbers.numbers.Complex;
import com.complexnumbers.numbers.ComplexCartesian;
import com.complexnumbers.numbers.ComplexPolar;

public class Main {

    public static void main(String[] args) {
        Complex c1 = new ComplexCartesian(3, 4);
        Complex c2 = new ComplexPolar(5, Math.atan2(4, 3));
        Complex c3;
        c3 = c1.conjugate();
        c3.printCartesian();
        c3 = c1.add(c2);
        c3.printCartesian();
        c3.printPolar();
        ComplexFunctions.sin(c1).printPolar();
        ComplexFunctions.cos(c1).printCartesian();
        ComplexFunctions.cosh(c1).printCartesian();
        ComplexFunctions.sinh(c2).printPolar();
        ComplexFunctions.exp(c2).printPolar();
        ComplexFunctions.ln(c2).printPolar();
        Polynomial p1 = new Polynomial(4, 3);
        Polynomial p2 = new Polynomial(3, 2);
        Polynomial p3 = new Polynomial(2, 1);
        Polynomial p4 = new Polynomial(1, 0);
        Polynomial p = p1.add(p2).add(p3).add(p4);   //p = 4z^3 + 3z^2 + 2z + 1
        //System.out.println(p);
        Complex c4 = new ComplexCartesian(2, 2);
        Polynomial p10 = new Polynomial(1, 8);
        //p10.calculate(new ComplexCartesian(1,1)).printCartesian();
        //System.out.println(p10.add(p));
        //System.out.println(p10.multiply(p));
        Polynomial p11 = p2.add(p3).add(p4);
        p.calculate(c4).printCartesian();
        System.out.println(p.divide(p10, 20));
    }
}
