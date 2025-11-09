
# Algoritmo de Optimización por Colonia de Hormigas (ACO)

Implementación del algoritmo **Ant Colony Optimization** para encontrar rutas óptimas en grafos.

## 📋 Descripción

Este proyecto implementa el algoritmo de colonia de hormigas para resolver problemas de optimización de rutas en dos depósitos diferentes (H y B), cada uno con su propia topología de grafo.

## 🎯 Características

- **Deposito H**: 9 nodos (A-I) con 14 caminos
- **Deposito B**: 10 nodos (A, S, C, F, P, B, H, D, G, E) con 15 caminos
- Cálculo de feromonas y visibilidad
- Actualización iterativa de probabilidades
- Selección de rutas óptimas basada en el algoritmo ACO

## 🏗️ Estructura del Proyecto

```
hormigas/
├── Hormigas_ambiente.java    # Gestión del entorno (grafos, nodos, caminos)
├── Hormigas.java              # Lógica de la hormiga exploradora
└── TPS_ACO.java               # Clase principal (main)
```

## 🚀 Uso

### Compilar y ejecutar

```bash
javac hormigas/*.java
java hormigas.TPS_ACO
```

### Desde NetBeans

1. Abrir el proyecto
2. Clic derecho en `TPS_ACO.java`
3. Seleccionar **Run File** (Shift + F6)

## 📊 Parámetros del Algoritmo

- **Coeficiente de evaporación (ρ)**: 0.1
- **Alfa (α)**: 1.68309
- **Beta (β)**: 1.28264
- **Q**: 0.0001
- **Iteraciones**: 5

## 📈 Salida

El programa muestra:
- Proceso de exploración iterativo
- Feromonas acumuladas en cada camino
- Probabilidades de selección
- Ruta óptima encontrada
- Estadísticas de cada camino (veces elegido, visibilidad, etc.)

## 🔧 Requisitos

- Java 8 o superior
- NetBeans IDE (opcional)

## 📝 Ejemplo de Salida

```
══════════════════════════════════════════════════════════════
     ALGORITMO DE OPTIMIZACION POR COLONIA DE HORMIGAS       
══════════════════════════════════════════════════════════════

╔═══════════════════════════════════════════════════════════╗
║                     DEPOSITO H                            ║
╚═══════════════════════════════════════════════════════════╝

>>> INICIANDO EXPLORACION DEL DEPOSITO H <<<

=== ITERACION 1 ===
...
```

## 👨‍💻 Autor

Proyecto desarrollado como implementación educativa del algoritmo ACO.

## 📄 Licencia

Este proyecto es de código abierto y está disponible para uso educativo.
