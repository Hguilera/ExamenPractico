Cómo funciona el programa**
**1. La canción y el nodo**

`Cancion` guarda los datos: ID, título, artista y duración.
`Nodo` guarda esa canción y dos referencias que funcionan como enlaces: una apunta a la canción anterior y otra a la siguiente. Así conectamos las canciones sin usar arreglos.

**2. Las canciones forman un círculo**
Si tenemos A, B y C, al avanzar pasamos por A, B, C y volvemos a A. Si retrocedemos desde A, llegamos a C.
Esto pasa porque la última canción está conectada con la primera. Si solo hay una canción, sus dos enlaces apuntan a ella misma.

**3. Cómo sabemos dónde estamos**
La lista guarda cuatro datos:

- `inicio`: dónde empieza la lista.
- `fin`: dónde termina.
- `actual`: qué canción está seleccionada.
- `cantidad`: cuántas canciones hay.

Al principio no hay canciones, así que las referencias están en `null` y la cantidad es cero. Cuando agregamos la primera, esa canción es el inicio, el final y la actual.

**4. Cómo agregamos canciones**
Conectamos la nueva canción entre la última y la primera. Dependiendo de la opción elegida, la marcamos como el nuevo inicio o el nuevo final.
La canción actual se mantiene. Como solo cambiamos unos enlaces y no recorremos toda la lista, esta operación cuesta **O(1)**.

**5. Cómo eliminamos canciones**
Antes de quitar una canción, guardamos las referencias a sus dos vecinos. Luego conectamos esos vecinos entre sí para cerrar el espacio que dejó.

Si quitamos la canción actual, la siguiente pasa a ser la actual. Si quitamos la única canción, la lista queda vacía.

Eliminar la actual cuesta **O(1)** porque ya tenemos su ubicación. Eliminar por ID cuesta **O(n)** porque primero debemos buscarla.

Java se encarga de recuperar la memoria del nodo eliminado cuando ya no queda ninguna referencia que permita llegar a él.

**6. Cómo buscamos y mostramos la lista**
Para buscar, revisamos los IDs uno por uno hasta encontrar la canción o terminar la vuelta.
Para mostrar la lista, recorremos tantos nodos como canciones haya. Así mostramos cada canción una sola vez en cada sentido y evitamos quedarnos dando vueltas sin parar.

**7. Cómo simulamos la reproducción**
El programa muestra la canción actual y después avanza a la siguiente.
Por ejemplo, si tenemos A, B y C, empezamos en A y pedimos cinco reproducciones, aparece: **A, B, C, A y B**.

Al terminar, C queda como actual porque sería la próxima en reproducirse. No suena música; solo mostramos los datos en la consola.

**8. El menú y los datos que escribe el usuario**
`Main` muestra el menú y llama a las funciones de la lista.
`EntradaConsola` revisa lo que escribe el usuario. Si introduce letras donde va un número, un valor fuera del rango permitido o un texto vacío, muestra un mensaje y vuelve a pedir el dato.

**9. Cómo revisamos que funcionara**
Las pruebas comprueban el orden de las canciones, cuál es la actual y cuántas quedan.

También revisan que los enlaces estén bien conectados en ambos sentidos y que la canción actual siga dentro de la lista. Esta revisión completa se hace en las pruebas para no volver más lentas las operaciones del programa.