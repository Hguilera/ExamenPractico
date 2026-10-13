# Gestor de listas de reproducción

En este proyecto hicimos un programa en Java para manejar una lista de canciones. Usamos una lista doblemente enlazada circular, por lo que podemos avanzar y regresar entre las canciones. Al llegar a la última, vuelve a la primera.

El programa funciona desde la consola. No reproduce música, solo muestra la información de la canción.

# Requisitos

Para ejecutarlo se necesita JDK 17 o una versión más nueva. No necesita internet, base de datos ni instalar librerías adicionales.

# Cómo ejecutar

Primero hay que abrir la carpeta `Gestor_Playlist` y después abrir una terminal. Se ejecutan estos comandos uno por uno:

```sh
mkdir out
javac -encoding UTF-8 -d out src/*.java tests/*.java
java -cp out Main
```

Si ya existe la carpeta `out`, se omite el primer comando. Después de ejecutar el último aparece el menú.

# Pruebas

Para correr las pruebas se usa:

```sh
java -cp out Pruebas
```

Las pruebas muestran cómo estaba la lista, qué operación se hizo, qué esperábamos y qué resultado salió. También revisan la cantidad de canciones y que los enlaces estén bien.

Si algo falla, aparece un `AssertionError`. Los resultados de las pruebas realizadas están en `EVIDENCIA_PRUEBAS.txt`.

# Archivos del proyecto

- `Cancion.java`: guarda el ID, título, artista y duración.
- `Nodo.java`: guarda la canción y sus enlaces.
- `ListaCircularDoble.java`: tiene las operaciones de la lista.
- `EntradaConsola.java`: revisa los datos que escribe el usuario.
- `Main.java`: contiene el menú.
- `Pruebas.java`: comprueba que las operaciones funcionen.
- `EXPLICACION.md`: explica cómo está hecho el programa.
- Los archivos de evidencias contienen los resultados de las pruebas.

# Funcionamiento

Los IDs se asignan automáticamente y van aumentando. Aunque borremos una canción, su ID no se vuelve a usar durante esa ejecución.

La primera canción que agregamos queda como actual. Cuando agregamos otras, la actual no cambia.

Si eliminamos la actual, queda seleccionada la que seguía. Si eliminamos otra, conservamos la selección. Cuando ya no quedan canciones, las referencias quedan en `null` y la cantidad en cero.

La opción 10 muestra la lista en ambos sentidos. La opción 12 pide cuántas canciones queremos reproducir, muestra sus datos y va avanzando. Al terminar queda seleccionada la siguiente canción.

# Complejidad

`n` representa la cantidad de canciones y `k` las reproducciones solicitadas.

| Operación | Complejidad |
| --- | --- |
| Agregar al inicio o al final | O(1) |
| Consultar la actual | O(1) |
| Avanzar o retroceder | O(1) |
| Eliminar la actual | O(1) |
| Consultar la cantidad | O(1) |
| Buscar o seleccionar por ID | O(n) |
| Eliminar por ID | O(n) |
| Mostrar la lista completa | O(n) |
| Reproducir k canciones | O(k) |

Las operaciones O(1) usan referencias que ya tenemos y no necesitan recorrer la lista. Para buscar por ID sí tenemos que revisar los nodos, por eso cuesta O(n). La reproducción cuesta O(k) porque repite el proceso k veces.

La lista utiliza O(n) memoria. La revisión completa de los enlaces también cuesta O(n), pero solo se hace en las pruebas.

# Restricciones

Implementamos nuestros propios nodos y la lista. No usamos arreglos, `ArrayList`, `LinkedList` ni otras colecciones para almacenar canciones.

El `String[] args` de `Main` es parte del inicio del programa y no guarda canciones. Java se encarga de recuperar la memoria de los nodos cuando dejan de ser alcanzables.

# Integrantes

1. Luque Mendoza David.
2. [Nombre pendiente].
3. Cazarrubias Peñaloza Valeria.

# Entrega

En GitHub se debe subir el código, las pruebas y este README. Hay que revisar que el profesor pueda entrar al repositorio y ejecutar el proyecto con las instrucciones.

También se entregan por separado el reporte en PDF y el video de funcionamiento.