package com.complexnumbers.numbers;

public class ComplexCartesian extends Complex{

    private double x, y;

    public ComplexCartesian(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getR() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public double getTheta() {
        return Math.atan2(y, x);
    }

    @Override
    public Complex conjugate() {
        return new ComplexCartesian(x, -y);
    }
}
