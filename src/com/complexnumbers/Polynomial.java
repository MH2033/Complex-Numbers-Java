package com.complexnumbers;

import com.complexnumbers.functions.ComplexFunctions;
import com.complexnumbers.numbers.Complex;
import com.complexnumbers.numbers.ComplexCartesian;
import com.complexnumbers.numbers.ComplexPolar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Polynomial {

    public Polynomial() {

    }

    private static class Term{
        private int exponent;
        private double coefficient;
        private Complex root = new ComplexCartesian(0,0);

        Term(double coefficient, int exponent){
            this.exponent = exponent;
            this.coefficient = coefficient;
        }

        public int getExponent() {
            return exponent;
        }

        public void setExponent(int exponent) {
            this.exponent = exponent;
        }

        public double getCoefficient() {
            return coefficient;
        }

        public void setCoefficient(double coefficient) {
            this.coefficient = coefficient;
        }

        public Complex getRoot() {
            return root;
        }

        public void setRoot(Complex root) {
            this.root = root;
        }
    }

    private ArrayList<Term> terms = new ArrayList<>();

    public Polynomial(double a, int b) {
        this.terms.add(new Term(a, b));
    }

    public Polynomial(Term term) {
        this.terms.add(term);
    }

    public int degree() {
        int d = terms.get(0).getExponent();
        for (Term t: terms)
            if (t.getExponent() > d)
                d = t.getExponent();
        return d;
    }

    public Polynomial add(Polynomial b) {
        Polynomial a = this;
        Polynomial d = new Polynomial();
        d.setTerms((ArrayList<Term>) b.getTerms().clone());
        Polynomial c = new Polynomial();
        boolean isCopied;
        for(Term t1: a.getTerms()){
            isCopied = false;
            for(Term t2: d.getTerms()){
                if(t1.getExponent() == t2.getExponent()){
                    c.getTerms().add(new Term(t1.getCoefficient() + t2.getCoefficient(), t1.getExponent()));
                    isCopied = true;
                }
            }
            if(!isCopied){
                c.getTerms().add(new Term(t1.getCoefficient(), t1.getExponent()));
            }
        }
        boolean flag;
        for(Term t: d.getTerms()){
            flag = false;
            for(Term t2: c.getTerms()) {
                if (t.getExponent() == t2.getExponent())
                    flag = true;
            }
            if(!flag)
                c.getTerms().add(new Term(t.getCoefficient(), t.getExponent()));
        }
        return c;
    }

    public Polynomial subtract(Polynomial b) {
        Polynomial a = this;
        Polynomial d = new Polynomial();
        d.setTerms((ArrayList<Term>) b.getTerms().clone());
        Polynomial c = new Polynomial();
        boolean isCopied;
        for(Term t1: a.getTerms()){
            isCopied = false;
            for(Term t2: d.getTerms()){
                if(t1.getExponent() == t2.getExponent()){
                    c.getTerms().add(new Term(t1.getCoefficient() - t2.getCoefficient(), t1.getExponent()));
                    isCopied = true;
                }
            }
            if(!isCopied){
                c.getTerms().add(new Term(t1.getCoefficient(), t1.getExponent()));
            }
        }
        boolean flag;
        for(Term t: d.getTerms()){
            flag = false;
            for(Term t2: c.getTerms()) {
                if (t.getExponent() == t2.getExponent())
                    flag = true;
            }
            if(!flag)
                c.getTerms().add(new Term(-t.getCoefficient(), t.getExponent()));
        }
        return c;
    }

    public Polynomial multiply(Polynomial b) {
        Polynomial a = this;
        Polynomial c = new Polynomial();
        for (Term t1: a.getTerms())
            for (Term t2: b.getTerms())
                c = c.add(new Polynomial(t1.getCoefficient() * t2.getCoefficient(),t1.getExponent() + t2.getExponent()));
        return c;
    }

    public Polynomial divide(Polynomial b, int numberOfTerms) {
        this.sort();
        b.sort();
        Polynomial p = new Polynomial();
        Polynomial temp = new Polynomial();
        Polynomial result = new Polynomial();
        temp.setTerms((ArrayList<Term>) this.getTerms().clone());
        for (int i = 0; i < numberOfTerms; i++) {
            Iterator<Term> it = temp.getTerms().iterator();
            if(!it.hasNext())
                break;
            Term t = new Term(it.next().getCoefficient() / b.getTerms().get(0).getCoefficient(), temp.getTerms().get(0).getExponent() - b.getTerms().get(0).getExponent());
            result = result.add(new Polynomial(t));
            temp = temp.subtract(new Polynomial(t).multiply(b));

            temp.getTerms().remove(0);
            temp.sort();
        }
        result.sort();
        return result;
    }

    public void sort(){
        this.getTerms().sort(new TermCompare());
    }

    public Complex calculate(Complex z) {
        Complex p = new ComplexPolar(0, 0);
        Complex coefComplex = new ComplexCartesian(0, 0);
        for (Term t: this.getTerms()) {
            coefComplex = new ComplexCartesian(t.getCoefficient(), 0);
            p = p.add(ComplexFunctions.pow(z, t.getExponent()).multiply(coefComplex));
        }
        return p;
    }

    public Polynomial differentiate() {
        Polynomial deriv = new Polynomial();
        for (Term t: this.getTerms()) {
            if(t.getExponent() != 0) {
                deriv.getTerms().add(new Term(t.getCoefficient() * t.getExponent(), t.getExponent() - 1));
            }
        }
        return deriv;
    }

    public Polynomial integrate() {
        Polynomial integral = new Polynomial();
        for (Term t: this.getTerms()) {
            if(t.getExponent() != 0) {
                integral.getTerms().add(new Term(t.getCoefficient() /(t.getExponent() + 1), t.getExponent() + 1));
            }
        }
        return integral;
    }

    public String toString() {
        String s = "f(z) = ";
        for (Term term : this.getTerms()) {
            if (term.getCoefficient() == 0) {
                continue;
            } else if (term.getCoefficient() > 0) {
                s = s + " + " + (term.getCoefficient());
            } else if (term.getCoefficient() < 0) {
                s = s + " - " + (-term.getCoefficient());
            }
            if (term.getExponent() == 1 && term.getRoot().getR() == 0) {
                s = s + "z";
            } else if (term.getExponent() == 1 && term.getRoot().getR() != 0){
                s = s + "(z - " + -term.getRoot().getX() + " + " +  -term.getRoot().getY() + "i)";
            }else if(term.getExponent() != 0 && term.getRoot().getR() == 0){
                s = s + "z^" + term.getExponent();
            }else if(term.getExponent() != 0 && term.getRoot().getR() != 0){
                s = s + "(z - " + -term.getRoot().getX() + " + " +  -term.getRoot().getY() + "i)^" + term.getExponent();
            }
        }
        return s;
    }

    public Complex findRoots(){
        Complex x = new ComplexPolar(0,0);
        Polynomial derivative = this.differentiate();
        double error  = 0.1;
        do {
            x = x.subtract(this.calculate(x).divide(derivative.calculate(x)));
        }while(x.getR() > error);
        return x;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public Polynomial taylor(Complex c, int terms){
        return null;
    }


    public Polynomial laurent(Complex c, int terms){
        return null;
    }

    private static class TermCompare implements Comparator<Term> {

        @Override
        public int compare(Term o1, Term o2) {
            return o1.getExponent() - o2.getExponent();
        }
    }
}