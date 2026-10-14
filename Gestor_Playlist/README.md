# Gestor de listas de reproducción en Java

## Descripción

Este proyecto consiste en un gestor de listas de reproducción desarrollado en Java. Utiliza una lista doblemente enlazada circular para almacenar y administrar canciones.

Cada canción tiene un ID único, título, artista y duración en segundos. El programa permite agregar, buscar, seleccionar y eliminar canciones, además de avanzar o retroceder entre ellas.

## Estructura de datos

Se implementó una **lista doblemente enlazada circular**, en la que cada nodo contiene una canción y referencias al nodo anterior y al siguiente.

La lista mantiene referencias al inicio, al final y a la canción actual. Al ser circular, desde la última canción se puede avanzar al inicio y desde la primera se puede retroceder al final.

## Funciones del programa

El menú permite realizar las siguientes operaciones:

1. Agregar una canción al inicio.
2. Agregar una canción al final.
3. Buscar una canción por ID.
4. Seleccionar una canción por ID.
5. Eliminar una canción por ID.
6. Eliminar la canción actual.
7. Consultar la canción actual.
8. Avanzar a la siguiente canción.
9. Retroceder a la canción anterior.
10. Mostrar las canciones en orden hacia adelante.
11. Mostrar las canciones en orden hacia atrás.
12. Consultar la cantidad de canciones y simular reproducciones.
13. Salir del programa.

## Requisitos

- Java JDK 17 o una versión compatible.
- Visual Studio Code, NetBeans u otro editor de Java.

Para comprobar la versión de Java instalada, ejecuta:

```bash
java -version
javac -version
```

## Estructura del proyecto

- `Cancion.java`: almacena los datos de cada canción.
- `Nodo.java`: representa cada nodo y sus enlaces.
- `ListaCircularDoble.java`: implementa las operaciones de la lista.
- `EntradaConsola.java`: ayuda a leer los datos ingresados por el usuario.
- `Main.java`: contiene el menú principal.
- `tests/Pruebas.java`: contiene las pruebas automáticas del proyecto.

## Compilación y ejecución

Abre la terminal en la carpeta principal `Gestor_Playlist`.

Para compilar el programa, ejecuta:

```bash
javac -encoding UTF-8 -d out src/Cancion.java src/Nodo.java src/ListaCircularDoble.java src/EntradaConsola.java src/Main.java
```

Para ejecutar el programa:

```bash
java -cp out Main
```

## Ejecutar las pruebas

Para compilar el programa junto con las pruebas, ejecuta:

```bash
javac -encoding UTF-8 -d out src/Cancion.java src/Nodo.java src/ListaCircularDoble.java src/EntradaConsola.java src/Main.java tests/Pruebas.java
```

Después, ejecuta:

```bash
java -cp out Pruebas
```

Las pruebas permiten revisar el funcionamiento de las operaciones principales y la consistencia de los enlaces de la lista.

## Complejidad

| Operación | Complejidad |
|---|---|
| Agregar al inicio o al final | O(1) |
| Consultar la canción actual | O(1) |
| Avanzar o retroceder | O(1) |
| Eliminar la canción actual | O(1) |
| Buscar o seleccionar por ID | O(n) |
| Eliminar por ID | O(n) |
| Mostrar todas las canciones | O(n) |
| Simular k reproducciones | O(k) |
| Consultar la cantidad de canciones | O(1) |

## Integrantes

- Aguilera Villavicencio Hayat
- Casarrubias Peñaloza Valeria Guadalupe
- Luque Mendoza David

## Repositorio

GitHub: https://github.com/Hguilera/ExamenPractico
