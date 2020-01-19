package com.complexnumbers.numbers;

public abstract class Complex {
    public Complex add(Complex x) {
        return new ComplexCartesian(x.getX() + this.getX(), x.getY() + this.getY());
    }

    public Complex subtract(Complex x) {
        return new ComplexCartesian(x.getX() - this.getX(), x.getY() - this.getY());
    }

    public Complex multiply(Complex x) {
        return new ComplexPolar(x.getR() * this.getR(), x.getTheta() + this.getTheta());
    }

    public Complex divide(Complex x) {
        return new ComplexPolar(x.getR() / this.getR(), x.getTheta() - this.getTheta());
    }

    public void printCartesian() {
        System.out.printf("Z = %.2f + %.2fi\n", this.getX(), this.getY());
    }

    public void printPolar() {
        System.out.printf("Z = %.2f * e ^ (%.2fi)\n", this.getR(), this.getTheta());
    }

    public abstract double getX();

    public abstract double getY();

    public abstract double getR();

    public abstract double getTheta();

    public abstract Complex conjugate();
}
