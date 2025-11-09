package hormigas;

public class Hormigas_ambiente
{
   private String mCaminos[];
   private String mNodos[];
   private double mLogitudes[];
   private double mVisibilidad[];
   private double mFeromonas_en_el_camino[];
   private double mProbabilidades_de_ser_elegido[];
   private int mVeces_que_ha_sido_elegido[];
   private String nombreDeposito;
   
   public final double cnt_P_coeficiente_de_evaporacion_de_las_feromonas = 0.1;
   public final double cnt_Alfa = 1.68309;
   public final double cnt_Beta = 1.28264;

   // Constructor para Deposito H (original)
   public Hormigas_ambiente()
   {
      this.nombreDeposito = "H";
      inicializarDepositoH();
   }

   // Constructor para seleccionar el deposito
   public Hormigas_ambiente(String tipoDeposito)
   {
      this.nombreDeposito = tipoDeposito;
      if (tipoDeposito.equals("B"))
      {
         inicializarDepositoB();
      }
      else
      {
         inicializarDepositoH();
      }
   }

   private void inicializarDepositoH()
   {
      mCaminos = new String[14];
      
      this.mCaminos[0] = "A a B";
      this.mCaminos[1] = "A a D";
      this.mCaminos[2] = "A a G";
      this.mCaminos[3] = "B a C";
      this.mCaminos[4] = "B a G";
      this.mCaminos[5] = "C a H";
      this.mCaminos[6] = "D a E";
      this.mCaminos[7] = "D a G";
      this.mCaminos[8] = "E a F";
      this.mCaminos[9] = "F a G";
      this.mCaminos[10] = "F a I";
      this.mCaminos[11] = "G a H";
      this.mCaminos[12] = "G a I";
      this.mCaminos[13] = "H a I";

      this.mNodos = new String[9];
      this.mNodos[0] = "A";
      this.mNodos[1] = "B";
      this.mNodos[2] = "C";
      this.mNodos[3] = "D";
      this.mNodos[4] = "E";
      this.mNodos[5] = "F";
      this.mNodos[6] = "G";
      this.mNodos[7] = "H";
      this.mNodos[8] = "I";

      this.mLogitudes = new double[this.mCaminos.length];
      this.mLogitudes[0] = 6;
      this.mLogitudes[1] = 10;
      this.mLogitudes[2] = 8;
      this.mLogitudes[3] = 11;
      this.mLogitudes[4] = 5;
      this.mLogitudes[5] = 4;
      this.mLogitudes[6] = 6;
      this.mLogitudes[7] = 15;
      this.mLogitudes[8] = 2;
      this.mLogitudes[9] = 4;
      this.mLogitudes[10] = 6;
      this.mLogitudes[11] = 5;
      this.mLogitudes[12] = 5;
      this.mLogitudes[13] = 7;

      inicializarArraysComunes();
   }

   private void inicializarDepositoB()
   {
      // Basado en el grafo del Deposito B
      mCaminos = new String[15];
      
      this.mCaminos[0] = "A a S";
      this.mCaminos[1] = "A a H";
      this.mCaminos[2] = "A a P";
      this.mCaminos[3] = "S a C";
      this.mCaminos[4] = "C a F";
      this.mCaminos[5] = "C a B";
      this.mCaminos[6] = "P a B";
      this.mCaminos[7] = "P a D";
      this.mCaminos[8] = "H a D";
      this.mCaminos[9] = "H a B";
      this.mCaminos[10] = "B a E";
      this.mCaminos[11] = "B a G";
      this.mCaminos[12] = "D a G";
      this.mCaminos[13] = "G a E";
      this.mCaminos[14] = "F a E";

      this.mNodos = new String[10];
      this.mNodos[0] = "A";
      this.mNodos[1] = "S";
      this.mNodos[2] = "C";
      this.mNodos[3] = "F";
      this.mNodos[4] = "P";
      this.mNodos[5] = "B";
      this.mNodos[6] = "H";
      this.mNodos[7] = "D";
      this.mNodos[8] = "G";
      this.mNodos[9] = "E";

      // Longitudes según el grafo del Deposito B
      this.mLogitudes = new double[this.mCaminos.length];
      this.mLogitudes[0] = 5;   // A-S
      this.mLogitudes[1] = 10;  // A-H
      this.mLogitudes[2] = 3;   // A-P
      this.mLogitudes[3] = 3;   // S-C
      this.mLogitudes[4] = 7;   // C-F
      this.mLogitudes[5] = 5;   // C-B
      this.mLogitudes[6] = 6;   // P-B
      this.mLogitudes[7] = 12;  // P-D
      this.mLogitudes[8] = 2;   // H-D
      this.mLogitudes[9] = 9;   // H-B
      this.mLogitudes[10] = 4;  // B-E
      this.mLogitudes[11] = 6;  // B-G
      this.mLogitudes[12] = 3;  // D-G
      this.mLogitudes[13] = 15; // G-E
      this.mLogitudes[14] = 1;  // F-E

      inicializarArraysComunes();
   }

   private void inicializarArraysComunes()
   {
      this.mVisibilidad = new double[this.mLogitudes.length];
      for (int i = 0; i < this.mLogitudes.length; i++)
      {
         this.mVisibilidad[i] = 1.0 / this.mLogitudes[i];
      }

      this.mFeromonas_en_el_camino = new double[this.mLogitudes.length];
      for (int i = 0; i < this.mLogitudes.length; i++)
      {
         this.mFeromonas_en_el_camino[i] = 0.1;
      }

      this.mProbabilidades_de_ser_elegido = new double[this.mLogitudes.length];
      this.mVeces_que_ha_sido_elegido = new int[this.mLogitudes.length];
   }

   public Hormigas_ambiente(int nueva_cantidad_de_caminos, int nueva_cantidad_de_nodos)
   {
      this.nombreDeposito = "Personalizado";
      this.mCaminos = new String[nueva_cantidad_de_caminos];
      this.mNodos = new String[nueva_cantidad_de_nodos];
      
      for (int i = 0; i < mNodos.length; i++)
      {
         this.mNodos[i] = String.valueOf((char)('A' + i));
      }
      
      this.mLogitudes = new double[nueva_cantidad_de_caminos];
      this.mVisibilidad = new double[nueva_cantidad_de_caminos];
      this.mFeromonas_en_el_camino = new double[nueva_cantidad_de_caminos];
      
      for (int i = 0; i < nueva_cantidad_de_caminos; i++)
      {
         this.mFeromonas_en_el_camino[i] = 0.1;
      }
      
      this.mProbabilidades_de_ser_elegido = new double[nueva_cantidad_de_caminos];
      this.mVeces_que_ha_sido_elegido = new int[nueva_cantidad_de_caminos];
   }

   public String getNombreDeposito()
   {
      return this.nombreDeposito;
   }

   public void setCaminos(String nuevos_caminos[])
   {
      this.mCaminos = nuevos_caminos;
   }

   public String[] getCaminos()
   {
      return this.mCaminos;
   }

   public void setNodos(String nuevos_nodos[])
   {
      this.mNodos = nuevos_nodos;
   }

   public String[] getNodos()
   {
      return this.mNodos;
   }

   public void setProbabilidades_de_ser_elegido(double nuevas_probabilidades[])
   {
      this.mProbabilidades_de_ser_elegido = nuevas_probabilidades;
   }

   public double[] getProbabilidades_de_ser_elegido()
   {
      return this.mProbabilidades_de_ser_elegido;
   }

   public void setLogitudes(double nuevas_longitudes[])
   {
      this.mLogitudes = nuevas_longitudes;
   }

   public double[] getLogitudes()
   {
      return this.mLogitudes;
   }

   public double[] getVisibilidad()
   {
      for (int i = 0; i < this.mLogitudes.length; i++)
      {
         this.mVisibilidad[i] = 1.0 / this.mLogitudes[i];
      }
      return this.mVisibilidad;
   }

   public void setFeromonas_en_el_camino(double[] nuevas_feromonas)
   {
      this.mFeromonas_en_el_camino = nuevas_feromonas;
   }

   public double[] getFeromonas_en_el_camino()
   {
      return this.mFeromonas_en_el_camino;
   }

   public double evaporacion_de_las_feromonas()
   {
      return this.cnt_P_coeficiente_de_evaporacion_de_las_feromonas;
   }

   public void actualizar_feromonas_en_el_camino(double recorrido_de_la_hormiga[])
   {
      for (int i = 0; i < getFeromonas_en_el_camino().length; i++)
      {
         this.mFeromonas_en_el_camino[i] = 
            (1 - this.cnt_P_coeficiente_de_evaporacion_de_las_feromonas) * 
            getFeromonas_en_el_camino()[i] + recorrido_de_la_hormiga[i];
      }
   }

   public void setVeces_que_ha_sido_elegido(int id)
   {
      if (id >= 0 && id < this.mVeces_que_ha_sido_elegido.length)
      {
         this.mVeces_que_ha_sido_elegido[id]++;
      }
   }

   public int[] getVeces_que_ha_sido_elegido()
   {
      return this.mVeces_que_ha_sido_elegido;
   }

   @Override
   public String toString()
   {
      String vDatos_del_camino = "════════════════════════════════════════════════════════\n";
      vDatos_del_camino += "   ALGORITMO DE COLONIA DE HORMIGAS - DEPOSITO " + this.nombreDeposito + "\n";
      vDatos_del_camino += "════════════════════════════════════════════════════════\n\n";
      
      vDatos_del_camino += "NODOS DEL GRAFO: ";
      for (int i = 0; i < this.mNodos.length; i++)
      {
         vDatos_del_camino += this.mNodos[i];
         if (i < this.mNodos.length - 1) vDatos_del_camino += " -> ";
      }
      vDatos_del_camino += "\n\n";

      for (int i = 0; i < this.mCaminos.length; i++)
      {
         vDatos_del_camino += "--- CAMINO " + (i + 1) + " ---\n";
         vDatos_del_camino += "Ruta: " + this.mCaminos[i] + "\n";
         vDatos_del_camino += "Longitud: " + this.mLogitudes[i] + "\n";
         vDatos_del_camino += "Visibilidad: " + String.format("%.4f", this.mVisibilidad[i]) + "\n";
         vDatos_del_camino += "Feromonas: " + String.format("%.4f", this.mFeromonas_en_el_camino[i]) + "\n";
         vDatos_del_camino += "Probabilidad: " + String.format("%.4f", this.mProbabilidades_de_ser_elegido[i]) + "\n";
         vDatos_del_camino += "Veces elegido: " + this.mVeces_que_ha_sido_elegido[i] + "\n\n";
      }

      return vDatos_del_camino;
   }
}