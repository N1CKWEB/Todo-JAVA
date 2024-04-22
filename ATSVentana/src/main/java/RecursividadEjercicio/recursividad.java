
package RecursividadEjercicio;

import java.util.Scanner;

public class recursividad {
//Realizar en Java o Python el siguiente programa. Usar RECURSION para programar. Subir a GitHub
//
//El usuario ingresa dos elementos de la sucesion, las posiciones que ocupan dichos numeros en la sucesion(tener en cuenta que se pueden ingresar valores que no sean consecutivos.) Ademas el usuario debe ingresar cuantos términos de la sucesion desea generar n
//Mostrar por pantalla los datos generados de izquierda a derecha y de derecha a izquierda
//    
    public static void main(String[] args) {
     
        int pos1,pos2,elemento1,elemento2,n;
        
        Scanner nd=new Scanner(System.in);
        
        System.out.println("Ingresa el primer elemento");
        elemento1=nd.nextInt();
        
        System.out.println("Ingrese la posición del primer elemento:");
        pos1=nd.nextInt();
        
        System.out.println("Ingresa el segundo elemento");
        elemento2=nd.nextInt();
        
        System.out.println("Ingrese la posición del primer elemento:");
        pos2=nd.nextInt();
        
        System.out.println("Cúantos terminos de la sucesión desas generar ? ");
        n=nd.nextInt();
        
        System.out.println("Generación de la sucesión de izquierda a derecha:");
                
         // generarSucesion(elemento1,elemento2,n,0,1);
          
          
          
        System.out.println("Generación de la sucesión de derecha a izquierda:");
                
//          generarSucesion(elemento1,elemento2,n,0,-1);
          
              
    
    }

    private static void generarSucesion(int pos1,int pos2,int elemento1,int elemento2,int n,int count,int diferencia) {
     
        if (count>0) {
           int elementoQueSigue=elemento1-elemento2;
           int posicionSigue=pos2-pos1;
            System.out.println(elemento1+"1");
           generarSucesion(pos2,elemento2,elemento1,elemento2,n,count+1,diferencia);
        }else if(diferencia==-1 && count>n){
            System.out.println(elemento1+"");
        }
        
    
    }
    
}


