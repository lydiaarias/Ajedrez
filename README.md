# Ajedrez
El trabajo que vamos a realizar es modelar el juego del ajedrez. 
El tablero que vamos a usar es el siguiente:



Como se puede observar vamos a usar piezas blancas y negras.
Movimientos de las piezas.
Rey: Se mueve una casilla en cualquier dirección: horizontal, vertical o diagonal.
Reina: Se mueve en cualquier dirección y distancia, tanto horizontal, vertical como diagonal.
Torre: Se mueve en línea recta, horizontal o vertical, cualquier cantidad de casillas.
Alfil: Se mueve diagonalmente, cualquier cantidad de casillas.
Caballo: Realiza un movimiento especial en forma de "L". Se mueve dos casillas en una dirección (horizontal o vertical) y luego una casilla en una dirección perpendicular.
Peón: Se mueve hacia adelante una casilla en la columna en la que se encuentra, en su primer movimiento tiene la opción de avanzar dos casillas. Además, capturan en diagonal moviéndose una casilla hacia adelante en diagonal hacia una pieza enemiga.


La estructura que vamos a seguir es la que se encuentra representada en el siguiente diagrama de clases:

 ![image](https://github.com/lydiaarias/Ajedrez/assets/144675375/0ebc63ad-4135-4b3c-a906-d25ef443f295)

Los primeros que seguiremos es la creación de clases. El tipo de lo que será cada atributo esta definido junto a ellos. 
Clase Tablero.
Tiene unos atributos que serán privados como: casillas: Casillas [], turnoBlancas : boolean. 
Los atributos públicos serán: moverPieza : boolean, inicializarPieza (pieza Casilla[]): void, imprimirPieza(): void, estaVacio (): boolean y validarDestino.
Clase Casilla.
Como atributos privados tiene: blanca: boolean, coordenadaX : int y coordenadaY: int. 
Como atributos públicos tiene: mover (origen Casilla, tablero Tablero): boolena, validarDesplazamiento y mover (origen Casilla, destino Casilla, tablero Tablero): boolean.
Las clases de las piezas (Peón, Alfil, Caballo, Dama, Torre, Rey) no tendrán ningún atributo en privado, todos serán públicos y tendrán los mismos atributos: mover (origen Casilla, destino Casilla): boolean y ValidarDesplazamiento. 



 
