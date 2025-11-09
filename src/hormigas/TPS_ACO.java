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
      System.out.println("══════════════════════════════════════════════════════════════");
      System.out.println();
      
      TPS_ACO programa = new TPS_ACO();
      
      // Resolver Deposito H
      System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
      System.out.println("║                     DEPOSITO H                            ║");
      System.out.println("╚═══════════════════════════════════════════════════════════╝");
      System.out.println("Nodos del grafo: A, B, C, D, E, F, G, H, I");
      System.out.println("Total de caminos: 14");
      System.out.println("══════════════════════════════════════════════════════════════");
      programa.activar_hormiga_depositoH();
      
      System.out.println("\n\n\n");
      
      // Resolver Deposito B
      System.out.println("╔═══════════════════════════════════════════════════════════╗");
      System.out.println("║                     DEPOSITO B                            ║");
      System.out.println("╚═══════════════════════════════════════════════════════════╝");
      System.out.println("Nodos del grafo: A, S, C, F, P, B, H, D, G, E");
      System.out.println("Total de caminos: 15");
      System.out.println("══════════════════════════════════════════════════════════════");
      programa.activar_hormiga_depositoB();
      
      System.out.println("\n");
      System.out.println("══════════════════════════════════════════════════════════════");
      System.out.println("        EXPLORACION DE AMBOS DEPOSITOS COMPLETADA");
      System.out.println("══════════════════════════════════════════════════════════════");
   }
   
   public void activar_hormiga_depositoH()
   {
      Hormigas_ambiente ambienteH = new Hormigas_ambiente("H");
      Hormigas h = new Hormigas("Exploradora H", ambienteH);
      h.explorar();
   }
   
   public void activar_hormiga_depositoB()
   {
      Hormigas_ambiente ambienteB = new Hormigas_ambiente("B");
      Hormigas h = new Hormigas("Exploradora B", ambienteB);
      h.explorar();
   }
}