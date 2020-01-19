package com.complexnumbers.numbers;

public class ComplexPolar extends Complex{

    private double r, theta;

    public ComplexPolar(double r, double theta){
        this.r = r;
        this.theta = theta;
    }


    @Override
    public double getX() {
        return r * Math.cos(theta);
    }

    @Override
    public double getY() {
        return r * Math.sin(theta);
    }

    @Override
    public double getR() {
        return r;
    }

    @Override
    public double getTheta() {
        return theta;
    }

    @Override
    public Complex conjugate() {
        return new ComplexPolar(r, -theta);
    }
}
