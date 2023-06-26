
El equipo desarrolló una función llamada parse(), la cual se encarga de procesar y cargar los datos almacenados en un archivo de texto y un archivo CSV en el programa. Para esta tarea, hemos utilizado la librería Apache Commons CSV, la cual es capaz de manejar casos en los que hay comas dentro de comillas en cada línea del archivo CSV, lo que permite una correcta división de los datos.

En el archivo CSV, nos encontramos con cuatro filas que contienen errores. Estas filas tienen información que no corresponde a la columna en la que están ubicadas o les falta información. Debido a la naturaleza de estos errores y teniendo en cuenta el tamaño del archivo, decidimos ignorar estas filas. Inicialmente, utilizamos la biblioteca OpenCSV, pero estos cuatro errores dificultaron enormemente el proceso de carga, por lo que optamos por cambiar a Apache Commons CSV.

En cuanto a la estructura de datos, decidimos almacenar los pilotos en un HashMap (HashMap 1), utilizando el nombre del piloto como clave y una instancia de la clase "activePilot" como valor. Por otro lado, almacenamos los tweets en otro HashMap, utilizando la fecha del tweet como clave y una instancia de la clase "tweet" como valor. La idea original era que, dado que tenemos tres funciones que reciben fechas como entrada, sería óptimo poder acceder directamente a los tweets utilizando su fecha como clave, lo que nos permitiría acceder a ellos en O(1) (aunque en realidad sería O(n), siendo n el número de tweets en ese día). Para lograr esto, fue necesario implementar un nuevo HashMap (HashMap 2) que utiliza listas enlazadas para el manejo de colisiones, ya que cada día puede tener muchos tweets. Sin embargo, después de realizar esta implementación, nos dimos cuenta de que nuestro método add de la LinkedList iteraba por toda la lista para agregar al final, lo que resultaba extremadamente ineficiente. Por lo tanto, decidimos desarrollar un método addFirst, que nos permitió mejorar significativamente los tiempos de carga, reduciéndolos a menos de tres minutos.

Finalmente, también utilizamos otro HashMap para almacenar todos los usuarios identificados por su nombre puesto que a diferencia de un arraylist el hashmap nos permite chequear rápidamente si ya se encuentra registrado o no el usuario. Es importante destacar que tanto en HashMap 1 como en el otro HashMap, contamos con un atributo ArrayList que guarda las claves de cada HashMap, lo que nos permite recorrerlos de manera eficiente. También cabe destacar que dentro de esta función optimizamos los tamaños originales de las estructuras de datos para que no requieran de muchos ” resize” con esto logramos aumentar la velocidad de carga a menos de 10 segundos.


Función 1:

La función comienza creando dos arreglos: "top10" y "top20". Luego, utiliza el paquete "java.time" para obtener las fechas correspondientes al mes y año proporcionados como parámetros.

A continuación, la función busca los tweets para cada día del mes y los copia en una nueva ArrayList que contiene todos los tweets del mes. Después, itera sobre el hash de pilotos y obtiene el primer piloto. Separa el nombre y el apellido del piloto y luego itera sobre la lista de tweets del mes para verificar si el nombre o el apellido del piloto están presentes en cada tweet. Si es así, se incrementa el contador de ocurrencias.

Al finalizar, se agrega el piloto a la lista "top20" en el orden en el que estaba y se asigna la cantidad de ocurrencias a su atributo "numberOfOccurrences". Luego, se utiliza un método de ordenamiento rápido (quicksort) implementado para ordenar el arreglo "top20". Finalmente, se recorta el arreglo para quedarse con los primeros 10 elementos, que conformarán el arreglo "top10".

Es importante destacar que esta función presentó dificultades en términos de rendimiento. Se observó en el primer debugging que, mientras el procesamiento de la fecha "2021/10" tardaba menos de 3 segundos, las fechas con una cantidad considerable de tweets, como "2021/12", requerían más de 20 segundos. Además, se encontraron discrepancias en la cuenta de ocurrencias de los pilotos.

Para abordar estos problemas, se decidió modificar la implementación del hash utilizado en la función. Se implementó un nuevo método de búsqueda que utilizaba el hash total de tweets para verificar uno por uno si correspondían a la fecha buscada y, en ese caso, se verificaba si contenían el nombre o apellido del piloto. Si era así, se actualizaba la cantidad de ocurrencias en el hash de pilotos. Sin embargo, esta implementación no solucionó el problema subyacente y requería resolver nuevamente el problema de contar las ocurrencias en otras funciones.

Finalmente, se nos ocurrió reemplazar la estructura de datos LinkedList utilizada en el hash por un ArrayList,esto fue lo que mejoró significativamente los tiempos de carga logrando que la función se ejecute en menos de 1 segundo, al igual que en las funciones 3 y 4. Sobre el problema en la discrepancia de ocurrencias nos dimos cuenta que conforme aumentabamos el tamaño original del arraylist de cada día, el número total de ocurrencias se acercaba más al número correcto. Ahí nos dimos cuenta que había un problema en el método add de nuestra arraylist el cual cuando se pasaba de tamaño copiaba todos los elementos en un nuevo arreglo del doble de su tamaño original, el problema era que no se copiaba el elemento a agregar. Por lo que por en cada “resize” se perdía un número, todo esto servía para explicar la discrepancia de ocurrencias dependiendo del tamaño del array.

Función 2:

Para la segunda función creamos un arreglo al comienzo de la misma que posee 15 Users. Luego iteramos sobre el hash de usuarios y cargamos los primeros 15 usuarios al arreglo (durante la lectura del usuario se modifica su cantidad de tweets en función de la length the la lista que posee de tweets). A continuación utilizamos el método quicksort previamente implementado para ordenar el arreglo. El método quicksort ordena el arreglo en orden ascendente por lo que cuando continuamos leyendo los users del registryHash solo comparamos su cantidad de tweets con la del user en primer lugar del arreglo, si esta es superior se reemplaza en el arreglo.Implementamos además un método swap que solo compara al elemento en primer lugar con los que tiene a su derecha y los cambia de lugar de ser el de la izquierda mayor en cantidad de tweets que el de la derecha hasta dejarlo en su posición correspondiente. Este método de ordenarlos funciona puesto que el arreglo original ya está ordenado a partir del quicksort. Originalmente utilizábamos quicksort todas las veces, la implementación de swap nos permitió pasar de menos de 1 segundo a menos de 0.1 segundos.

Función 3:

La tercera función utiliza el paquete "java.time" para crear un objeto correspondiente al día ingresado, con el fin de obtener los tweets registrados para ese día. A continuación, se itera sobre la lista de hashtags de cada tweet y se agregan al nuevo hashmap llamado "hashmap 1" de hashtags, creado en la función.

Este hashmap permite verificar si el hashtag ya ha sido registrado previamente. En caso afirmativo, se incrementa el contador correspondiente dentro del hashtag. Después de iterar sobre todos los tweets de ese día, la función devuelve el tamaño de la lista de claves del hashmap. Esto representa la cantidad de hashtags distintos registrados en ese día.


Función 4:

Se utiliza la misma lógica de la función anterior solo que se itera al final sobre el hashmap 1 de hashtags para buscar el que tenga mayor número de ocurrencias sin ser f1.


Función 5:

Se utilizó la misma lógica de la función 2 solo que con 7 elementos en el arreglo y contando por número de favoritos en lugar de número de tweets.


Función 6:

Para la función 6 se itera sobre el total de tweets utilizando el arraylist de tweets. Sobre cada iteración se chequea si contiene o no la palabra a buscar. Cabe destacar que en el array los tweets se registraron en lowercase lo que permite independientemente de si esta en mayuscula o minuscula detectar la palabra. Se devuelve al final la cantidad total de ocurrencias.


Notas:
Todas las funciones son privadas y cuentan con un método público dentro de la clase que imprime correctamente los resultados.
Sobre las funciones 1,3 y 4 se tuvo que desarrollar un método que luego de correr la función se modifican los atributos nuevamente a 0.
También tuvimos problemas con el método resize del hashmap que originalmente habíamos implementado.

El tiempo promedio de carga del csv es de : 8,3 sec
Funcion 1: 0,1
Funcion 2: 0,03
Funcion 3: 0,01
Funcion 4: 0,02
Funcion 5: 0,03
Funcion 6: 0,07

El programa al correr no utiliza mas de 660 MB de memoria
