package hormigas;

public class Hormigas
{
   private String vNombre_de_la_Hormiga = "";
   private int vCantidad_de_caminos;
   private boolean mCamino_elegido_por_la_hormiga[];
   private double mFeromonas_entre_visibilidad[];
   private double mPonderado_en_la_escala_de_probabilidades[];
   private double mPxy_probabilidad_de_un_camino_entre_la_sumatoria_de_todos[];
   public final double cnt_Q = 0.0001;
   private Hormigas_ambiente vAmbiente_global = null;

   public Hormigas()
   {
      this.vCantidad_de_caminos = 3;
      this.vAmbiente_global = new Hormigas_ambiente();
      this.vNombre_de_la_Hormiga = "Exploradora default";
      this.mFeromonas_entre_visibilidad = new double[vAmbiente_global.getLogitudes().length];
      this.mPonderado_en_la_escala_de_probabilidades = new double[vAmbiente_global.getLogitudes().length];
      this.mPxy_probabilidad_de_un_camino_entre_la_sumatoria_de_todos = new double[vAmbiente_global.getLogitudes().length];
      this.mCamino_elegido_por_la_hormiga = new boolean[vAmbiente_global.getLogitudes().length];
   }

   public Hormigas(String nombre_de_la_hormiga, Hormigas_ambiente ambiente_de_la_hormiga)
   {
      this.vNombre_de_la_Hormiga = nombre_de_la_hormiga;
      this.vAmbiente_global = ambiente_de_la_hormiga;
      this.mFeromonas_entre_visibilidad = new double[vAmbiente_global.getLogitudes().length];
      this.mPonderado_en_la_escala_de_probabilidades = new double[vAmbiente_global.getLogitudes().length];
      this.mPxy_probabilidad_de_un_camino_entre_la_sumatoria_de_todos = new double[vAmbiente_global.getLogitudes().length];
      this.mCamino_elegido_por_la_hormiga = new boolean[vAmbiente_global.getLogitudes().length];
      this.vCantidad_de_caminos = 3;
   }

   public double[] fProbabilidad_de_elegir_un_camino(Hormigas_ambiente ambiente_a_elegir)
   {
      int i = 0;
      double vSumatoria_de_probabilidades = 0.0;
      
      for (i = 0; i < ambiente_a_elegir.getFeromonas_en_el_camino().length; i++)
      {
         this.mFeromonas_entre_visibilidad[i] = 
            ambiente_a_elegir.getFeromonas_en_el_camino()[i] * 
            ambiente_a_elegir.getVisibilidad()[i];
         
         vSumatoria_de_probabilidades += this.mFeromonas_entre_visibilidad[i];
      }

      double vTemp = 0.0;

      for (i = 0; i < ambiente_a_elegir.getFeromonas_en_el_camino().length; i++)
      {
         this.mPxy_probabilidad_de_un_camino_entre_la_sumatoria_de_todos[i] = 
            this.mFeromonas_entre_visibilidad[i] / vSumatoria_de_probabilidades;
         
         this.mPonderado_en_la_escala_de_probabilidades[i] = 
            mPxy_probabilidad_de_un_camino_entre_la_sumatoria_de_todos[i] + vTemp;
         
         vTemp = this.mPonderado_en_la_escala_de_probabilidades[i];
      }

      return mPonderado_en_la_escala_de_probabilidades;
   }

   public double[] getPxy()
   {
      return this.mPxy_probabilidad_de_un_camino_entre_la_sumatoria_de_todos;
   }

   public double[] fRecorrido_de_regreso(Hormigas_ambiente recorrido_de_la_hormiga)
   {
      double mRecorrido_de_regreso[] = new double[recorrido_de_la_hormiga.getLogitudes().length];
      
      for (int i = 0; i < recorrido_de_la_hormiga.getLogitudes().length; i++)
      {
         mRecorrido_de_regreso[i] = this.cnt_Q / recorrido_de_la_hormiga.getLogitudes()[i];
      }

      return mRecorrido_de_regreso;
   }

   @Override
   public String toString()
   {
      String vDatos_de_la_hormiga = "\n════════════════════════════════════════════════════════\n";
      vDatos_de_la_hormiga += "              DATOS DE LA HORMIGA EXPLORADORA\n";
      vDatos_de_la_hormiga += "════════════════════════════════════════════════════════\n\n";

      for (int i = 0; i < getPxy().length; i++)
      {
         vDatos_de_la_hormiga += "--- ANALISIS DEL CAMINO " + (i + 1) + " ---\n";
         vDatos_de_la_hormiga += "Ruta: " + this.vAmbiente_global.getCaminos()[i] + "\n";
         vDatos_de_la_hormiga += "Hormiga: " + this.vNombre_de_la_Hormiga + "\n";
         vDatos_de_la_hormiga += "Probabilidad Pxy: " + 
            String.format("%.6f", getPxy()[i]) + "\n";
         vDatos_de_la_hormiga += "Feromonas x Visibilidad: " + 
            String.format("%.6f", this.mFeromonas_entre_visibilidad[i]) + "\n";
         vDatos_de_la_hormiga += "Elegido: " + 
            (this.mCamino_elegido_por_la_hormiga[i] ? "SI" : "NO") + "\n\n";
      }

      return vDatos_de_la_hormiga;
   }

   private int elegir_2_caminos(double mBusqueda[], int id, boolean escribir_datos_en_pantalla)
   {
      if (id == -1) { id = 0; }
      if (id == 0 || mBusqueda.length < 2) { return 0; }
      
      String vDetalles = "";
      int vRespuesta = 0;

      if (mBusqueda[id] > mBusqueda[id - 1])
      {
         vDetalles = "Id del camino elegido: " + id + "\nCamino: " + 
                     this.vAmbiente_global.getCaminos()[id] + "\n\n";
         vRespuesta = id;
      }
      else
      {
         vDetalles = "Id del camino elegido: " + (id - 1) + "\nCamino: " + 
                     this.vAmbiente_global.getCaminos()[id - 1] + "\n\n";
         vRespuesta = id - 1;
      }

      if (escribir_datos_en_pantalla)
      {
         this.mCamino_elegido_por_la_hormiga[vRespuesta] = true;
         this.vAmbiente_global.setVeces_que_ha_sido_elegido(vRespuesta);
         System.out.println(vDetalles);
      }

      return vRespuesta;
   }

   private int elegir_3_caminos(double mBusqueda[], int id)
   {
      if (id == -1) { id = 2; }
      if (id < 2) { return 0; }

      int maxIndex = id - 2;
      double maxValue = mBusqueda[id - 2];

      if (mBusqueda[id - 1] > maxValue)
      {
         maxValue = mBusqueda[id - 1];
         maxIndex = id - 1;
      }

      if (mBusqueda[id] > maxValue)
      {
         maxValue = mBusqueda[id];
         maxIndex = id;
      }

      System.out.println("Id del camino elegido: " + maxIndex + 
                         "\nCamino: " + this.vAmbiente_global.getCaminos()[maxIndex] + "\n");
      
      this.mCamino_elegido_por_la_hormiga[maxIndex] = true;
      this.vAmbiente_global.setVeces_que_ha_sido_elegido(maxIndex);

      return maxIndex;
   }

   private int elegir_4_caminos(double mBusqueda[], int id)
   {
      if (id < 3) { return 0; }

      int maxIndex = id - 3;
      double maxValue = mBusqueda[id - 3];

      for (int i = id - 2; i <= id && i < mBusqueda.length; i++)
      {
         if (mBusqueda[i] > maxValue)
         {
            maxValue = mBusqueda[i];
            maxIndex = i;
         }
      }

      System.out.println("Id del camino elegido: " + maxIndex + 
                         "\nCamino: " + this.vAmbiente_global.getCaminos()[maxIndex] + "\n");
      
      this.mCamino_elegido_por_la_hormiga[maxIndex] = true;
      this.vAmbiente_global.setVeces_que_ha_sido_elegido(maxIndex);

      return maxIndex;
   }

   private void setCantidad_de_caminos(int id)
   {
      switch (id)
      {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 6:
         case 7:
         case 9:
         case 10:
         case 13:
            this.vCantidad_de_caminos = 3;
            break;

         case 11:
         case 12:
            this.vCantidad_de_caminos = 5;
            break;

         case 5:
         case 8:
            this.vCantidad_de_caminos = 2;
            break;

         default:
            this.vCantidad_de_caminos = 3;
            break;
      }
   }

   private int getCantidad_de_caminos()
   {
      return this.vCantidad_de_caminos;
   }

   public int fRuta_elegida(double mbuscar[])
   {
      for (int i = 0; i < mbuscar.length; i++)
      {
         if (i >= 2)
         {
            if (getCantidad_de_caminos() == 2)
            {
               setCantidad_de_caminos(elegir_2_caminos(mbuscar, i, true));
            }
            else if (getCantidad_de_caminos() == 3)
            {
               setCantidad_de_caminos(elegir_3_caminos(mbuscar, i));
            }
            else if (getCantidad_de_caminos() == 4 || getCantidad_de_caminos() == 5)
            {
               setCantidad_de_caminos(elegir_4_caminos(mbuscar, i));
            }
         }
      }

      return 0;
   }

   public void explorar()
   {
      System.out.println("\n>>> INICIANDO EXPLORACION DEL DEPOSITO H <<<\n");
      System.out.println("Objetivo: Encontrar el camino optimo desde el nodo A\n");

      for (int i = 0; i < 5; i++)
      {
         System.out.println("=== ITERACION " + (i + 1) + " ===\n");
         
         this.vAmbiente_global.setProbabilidades_de_ser_elegido(
            fProbabilidad_de_elegir_un_camino(this.vAmbiente_global));
         
         this.vAmbiente_global.actualizar_feromonas_en_el_camino(
            fRecorrido_de_regreso(this.vAmbiente_global));
      }

      System.out.println("\n>>> SELECCION DE RUTA BASADA EN VISIBILIDAD <<<\n");
      fRuta_elegida(this.vAmbiente_global.getVisibilidad());
      
      System.out.println("\n" + vAmbiente_global.toString());
      System.out.println(toString());
   }
}