public class EjercicioDeFibonacci {

    public int tamaño;
    public String nombre;

    public EjercicioDeFibonacci(int tamaño, String nombre) {
        this.nombre = nombre;
        this.tamaño = tamaño;
    }

    public EjercicioDeFibonacci() {

    }

    public int fibonacci(int num) {

        if (num > 1) {
            return fibonacci(num - 1) + fibonacci(num - 2);
        } else if (num == 1) {
            return 1;
        } else if (num == 0) {
            return 0;
        } else {
            System.out.println("El número debe ser mayor a 1");
            return -1;
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void mostrarSerie(){
        System.out.println( this.nombre + " de tamaño " + this.tamaño + " ");
        
        for (int i = 0; i < tamaño; i++) {
            System.out.println(fibonacci(i)+" ");
        }
        System.out.println(" ");
    }
    public static void main(String[] args) {
        EjercicioDeFibonacci fibo1=new EjercicioDeFibonacci(15,"Fibonacci 2");

        fibo1.mostrarSerie();

        EjercicioDeFibonacci fibo2=new EjercicioDeFibonacci();

         fibo2.setNombre("Fibonacci 2");

         fibo2.setTamaño(15);

         fibo2.mostrarSerie();
        
    }
}

