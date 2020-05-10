package edu.iastate.cs228.hw1;


/**
 * Make a class for finding several details of a triangle
 * @author Amith Kopparapu Venkata Boja
 */
 
 
public class Triangle extends GeometricObject
{
	
	private double side1;
	private double side2;
	private double side3;
	
	public Triangle() {
		side1=1.0;
		side2=1.0;
		side3=1.0;
	}
	public Triangle(double s1, double s2, double s3) {
		side1=s1;
		side2=s2;
		side3=s3;
	}
	public double getSide1() {
		return side1;
	}
	public double getSide2() {
		return side2;
	}
	public double getSide3() {
		return side3;
	}
	public double getArea() {
		double s= (side1+side2+side3)/2.0;
		return Math.sqrt(s * (s-side1) * (s- side2) * (s-side3));
	}
	public double getPerimeter() {
		return side1+side2+side3;
	}
	@Override
	public String toString()
	{
		return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
	}
}
