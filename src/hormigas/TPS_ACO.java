package hormigas;

public class TPS_ACO
{
   public static void main(String args[])
   {
      System.out.println("══════════════════════════════════════════════════════════════");
      System.out.println("                                                              ");
      System.out.println("     ALGORITMO DE OPTIMIZACION POR COLONIA DE HORMIGAS       ");
      System.out.println("                    (ANT COLONY OPTIMIZATION)                 ");
      System.out.println("                                                              ");
      System.out.println("                      DEPOSITO H                              ");
      System.out.println("                                                              ");
      System.out.println("══════════════════════════════════════════════════════════════");
      System.out.println();
      System.out.println("Nodos del grafo: A, B, C, D, E, F, G, H, I");
      System.out.println("Total de caminos: 14");
      System.out.println("Algoritmo: Busqueda del camino optimo mediante feromonas");
      System.out.println();
      System.out.println("══════════════════════════════════════════════════════════════");
      System.out.println();

      TPS_ACO programa = new TPS_ACO();
      programa.activar_hormiga();

      System.out.println();
      System.out.println("══════════════════════════════════════════════════════════════");
      System.out.println("             EXPLORACION COMPLETADA CON EXITO");
      System.out.println("══════════════════════════════════════════════════════════════");
   }

   public void activar_hormiga()
   {
      Hormigas h = new Hormigas();
      h.explorar();
   }
}