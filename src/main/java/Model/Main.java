package Model;

import java.util.Objects;

public class Main {
    public static void main(String []args){
        Number n = new Integer(10);
        m(n);
    }
    public static void m(Object j){
        System.out.println("obj");
    }
    public static void m(Number n){
        System.out.println("num");
    }
    public static void m(Integer i){
        System.out.println("int");
    }
}
