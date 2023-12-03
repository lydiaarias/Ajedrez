package Modelo;
/**
 * La clase Movimientos representa movimientos en un tablero de ajedrez.
 */
public class Movimientos {

    String[][] tablero;

/**
* Copia el estado actual del tablero de ajedrez a otra matriz.
*
* @param arr La matriz en la que se copiará el estado del tablero de ajedrez.
*/

    private void copiarTablero(String[][] arr)
    {
        // Lógica para copiar el tablero de ajedrez
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                arr[i][j] = tablero[i][j];
            }
        }
    }

/**
 * Comprueba si un movimiento particular es posible en el tablero de ajedrez.
 *
 * @param tablero El estado del tablero de ajedrez.
 * @param posicionAntigua La posición actual de la pieza.
 * @param posicionNueva La posición a la que se moverá la pieza.
 * @return Verdadero si el movimiento es posible; en caso contrario, falso.
 */

    public boolean posibleMovimiento(String[][] tablero, String posicionAntigua, String posicionNueva){
        // Lógica para comprobar si un movimiento es posible
        this.tablero = tablero;
        String[] posicionesPosibles;
        posicionesPosibles = movimientos1Modificados(tablero, posicionAntigua);

        if (posicionesPosibles != null){
            for (int i = 0; i < posicionesPosibles.length; i++){
                if (posicionesPosibles[i].equals(posicionNueva)){
                    comprobarEnrroque(posicionAntigua);
                    return true;
                }
            }
        }
        return false;
    }

/**
 * Comprueba si las fichas siguen el patrón para realizar enroque.
 *
 * @param posAntigua La posición de la ficha antes del movimiento.
 */
    private void comprobarEnrroque(String posAntigua){
        // Lógica para verificar y actualizar el estado del enroque
        if (posAntigua.equals("74")){
            Controlador.Controlador.enrroqueRey1 = false;
        }
        else if (posAntigua.equals("70")){
            Controlador.Controlador.enrroqueTorreIzquierda1 = false;
		}
        else if (posAntigua.equals("77")){
            Controlador.Controlador.enrroqueTorreDerecha1 = false;
        }
        else if (posAntigua.equals("04")){
            Controlador.Controlador.enrroqueRey2 = false;
        }
        else if (posAntigua.equals("00")){
            Controlador.Controlador.enrroqueTorreIzquierda2 = false;
        }
        else if (posAntigua.equals("07")){
            Controlador.Controlador.enrroqueTorreDerecha2 = false;
        }
    }

/**
 * Comprueba los movimientos posibles de las fichas en una posición específica en el tablero.
 *
 * @param tableroM   El tablero de ajedrez actual.
 * @param posicion   La posición en la que se encuentra la ficha.
 * @return Un arreglo de cadenas con las posiciones a las que se puede mover la ficha.
 */
    public String[] movimientos1Modificados(String[][] tableroM, String posicion){
        // Lógica para calcular los movimientos posibles de una ficha en una posición
        String[] posiblesMovimientos = movimientosFichas(tableroM, posicion);
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));

        return modificacionTodosMovimientos(tableroM, posicion, posiblesMovimientos);
    }

/**
 * Calcula los posibles movimientos de las fichas en una posición dada en el tablero.
 *
 * @param tableroM  El tablero de ajedrez actual.
 * @param posicion  La posición actual de la ficha.
 * @return Un arreglo de cadenas con las posiciones a las que la ficha puede moverse.
 */
    public String[] movimientos2Modificados(String[][] tableroM, String posicion){
        // Lógica para calcular movimientos modificados de las fichas en una posición
        String[] posiblesMovimientos = movimientosFichas(tableroM, posicion);
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));

        return modificacionTodosMovimientos(tableroM, posicion, posiblesMovimientos);
    }

    private String[] modificacionTodosMovimientos(String[][] tableroFuturo, String posicionInicial, String[] posicionesFinales){
        String posicionesDefinitivas = "";

        int xInicial = Character.getNumericValue(posicionInicial.charAt(1));
        int yInicial = Character.getNumericValue(posicionInicial.charAt(0));

        String ficha = fichaCasilla(tableroFuturo, yInicial, xInicial);

        if (posicionesFinales != null){
            for (int i = 0; i < posicionesFinales.length; i++){
                try{
                    String posicionFinal = posicionesFinales[i];
                    int xFinal = Character.getNumericValue(posicionFinal.charAt(1));
                    int yFinal = Character.getNumericValue(posicionFinal.charAt(0));

                    String tableroM[][] = new String[8][8];

                    copiarDelPrimeroAlSegundoElTablero(tableroFuturo, tableroM);

                    tableroM[yFinal][xFinal] = tableroM[yInicial][xInicial];
                    tableroM[yInicial][xInicial] = "";

                    if (ficha.charAt(0) == '1'){
                        if (rey1Jaque(tableroM) == false){
                            posicionesDefinitivas += "" + yFinal + "" + xFinal + "_";
                        }
                    }
                    else if (ficha.charAt(0) == '2'){
                        if (rey2Jaque(tableroM) == false){
                            posicionesDefinitivas += "" + yFinal + "" + xFinal + "_";
                        }
                    }
                }
                catch (Exception ex){
                }

            }

            String[] arrayPosiciones = posicionesDefinitivas.split("_");
            return arrayPosiciones;
        }
        return null;
    }


/**
* Realiza una copia del tablero de origen al tablero de copia.
 *
 * @param tableroOrigen El tablero original que se copiará.
 * @param tableroCopia  El tablero donde se copiará el estado del tablero original.
 */

    private void copiarDelPrimeroAlSegundoElTablero(String[][] tableroOrigen, String[][] tableroCopia){
        // Lógica para copiar el estado del tablero original al tablero de copia
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                tableroCopia[i][j] = tableroOrigen[i][j];
            }
        }
    }

/**
 * Calcula los movimientos de las fichas blancas en el tablero actual.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @return Un arreglo de cadenas con los movimientos posibles de las fichas blancas.
 */

    public String[] MovimientosFichas2(String[][] tableroM){
        String posicionesTotales = "";
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                String ficha = fichaCasilla(tableroM, i, j);

                if (ficha.equals("2_torre")){
                    posicionesTotales += cambiarPalabra(movimientoTorre2Ataque(tableroM, i, j));
                }
                else if (ficha.equals("2_alfil")){
                    posicionesTotales += cambiarPalabra(movimientoAlfil2Ataque(tableroM, i, j));
                }
                else if (ficha.equals("2_reina")){
                    posicionesTotales += cambiarPalabra(movimientoReina2Ataque(tableroM, i, j));
                }
                else if (ficha.equals("2_caballo")){
                    posicionesTotales += cambiarPalabra(movimientoCaballo2Ataque(tableroM, i, j));
                }
                else if (ficha.equals("2_rey")){
                    posicionesTotales += cambiarPalabra(movimientoRey2Ataque(tableroM, i, j));
                }
                else if (ficha.equals("2_peon")){
                    posicionesTotales += cambiarPalabra(movimientoPeon2Ataque(tableroM, i, j));
                }

            }
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }


/**
 * Calcula los posibles movimientos de ataque de una ficha en una posición específica para las piezas negras.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param i        La fila de la posición de la ficha.
 * @param j        La columna de la posición de la ficha.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para las piezas negras.
 */
    public String[] ataqueFicha2(String[][] tableroM, int i, int j){
        // Lógica para calcular movimientos de ataque de piezas negras
        String posicionesTotales = "";

        String ficha = fichaCasilla(tableroM, i, j);

        if (ficha.equals("2_torre")){
            posicionesTotales += cambiarPalabra(movimientoTorre2Ataque(tableroM, i, j));
        }
        else if (ficha.equals("2_alfil")){
            posicionesTotales += cambiarPalabra(movimientoAlfil2Ataque(tableroM, i, j));
        }
        else if (ficha.equals("2_reina")){
            posicionesTotales += cambiarPalabra(movimientoReina2Ataque(tableroM, i, j));
        }
        else if (ficha.equals("2_caballo")){
            posicionesTotales += cambiarPalabra(movimientoCaballo2Ataque(tableroM, i, j));
        }
        else if (ficha.equals("2_rey")){
            posicionesTotales += cambiarPalabra(movimientoRey2Ataque(tableroM, i, j));
        }
        else if (ficha.equals("2_peon")){
            posicionesTotales += cambiarPalabra(movimientoPeon2Ataque(tableroM, i, j));
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

/**
 * Calcula los posibles movimientos de ataque de una ficha en una posición específica para las piezas blancas.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param i        La fila de la posición de la ficha.
 * @param j        La columna de la posición de la ficha.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para las piezas blancas.
 */

    public String[] ataqueFicha1(String[][] tableroM, int i, int j){
        // Lógica para calcular movimientos de ataque de piezas blancas
        String posicionesTotales = "";
        String ficha = fichaCasilla(tableroM, i, j);

        if (ficha.equals("1_torre")){
            posicionesTotales += cambiarPalabra(movimientoTorre1Ataque(tableroM, i, j));
        }
        else if (ficha.equals("1_alfil")){
            posicionesTotales += cambiarPalabra(movimientoAlfil1Ataque(tableroM, i, j));
        }
        else if (ficha.equals("1_reina")){
            posicionesTotales += cambiarPalabra(movimientoReina1Ataque(tableroM, i, j));
        }
        else if (ficha.equals("1_caballo")){
            posicionesTotales += cambiarPalabra(movimientoCaballo1Ataque(tableroM, i, j));
        }
        else if (ficha.equals("1_rey")){
            posicionesTotales += cambiarPalabra(movimientoRey1Ataque(tableroM, i, j));
        }
        else if (ficha.equals("1_peon")){
            posicionesTotales += cambiarPalabra(movimientoPeon1Ataque(tableroM, i, j));
        }
        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;
    }

/**
 * Calcula los posibles movimientos de las fichas blancas en el tablero actual.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @return Un arreglo de cadenas con los movimientos posibles de las fichas blancas.
 */
    public String[] MovimientosFichas1(String[][] tableroM){
        String posicionesTotales = "";

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                String ficha = fichaCasilla(tableroM, i, j);

                if (ficha.equals("1_torre")) {

                    posicionesTotales += cambiarPalabra(movimientoTorre1Ataque(tableroM, i, j));
                }
                else if (ficha.equals("1_alfil")){
                    posicionesTotales += cambiarPalabra(movimientoAlfil1Ataque(tableroM, i, j));
                }
                else if (ficha.equals("1_reina")){
                    posicionesTotales += cambiarPalabra(movimientoReina1Ataque(tableroM, i, j));
                }
                else if (ficha.equals("1_caballo")){
                    posicionesTotales += cambiarPalabra(movimientoCaballo1Ataque(tableroM, i, j));
                }
                else if (ficha.equals("1_rey")){
                    posicionesTotales += cambiarPalabra(movimientoRey1Ataque(tableroM, i, j));
                }
                else if (ficha.equals("1_peon")){
                    posicionesTotales += cambiarPalabra(movimientoPeon1Ataque(tableroM, i, j));
                }
            }
        }
        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;
    }

    private String cambiarPalabra(String[] array){
        String palabra = "";

        for (int i = 0; i < array.length; i++){
            palabra += array[i] + "_";
        }
        return palabra;
    }

    public String[] movimientosFichas(String[][] tableroM, String posicion){
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));
        String ficha = fichaCasilla(tableroM, y, x);

        if (ficha.equals("1_peon")){
            return movimientoPeon1(tableroM, y, x);
        }
        else if (ficha.equals("2_peon")){
            return movimientoPeon2(tableroM, y, x);
        }
        else if (ficha.equals("1_torre")){
            return movimientoTorre1(tableroM, y, x);
        }
        else if (ficha.equals("2_torre")){
            return movimientoTorre2(tableroM, y, x);
        }
        else if (ficha.equals("1_alfil")){
            return movimientoAlfil1(tableroM, y, x);
        }
        else if (ficha.equals("2_alfil")){
            return movimientoAlfil2(tableroM, y, x);
        }
        else if (ficha.equals("1_caballo")){
            return movimientoCaballo1(tableroM, y, x);
        }
        else if (ficha.equals("2_caballo")){
            return movimientoCaballo2(tableroM, y, x);
        }
        else if (ficha.equals("1_reina")){
            return movimientoReina1(tableroM, y, x);
        }
        else if (ficha.equals("2_reina")){
            return movimientoReina2(tableroM, y, x);
        }
        else if (ficha.equals("1_rey")){
            return movimientoRey1(tableroM, y, x);
        }
        else if (ficha.equals("2_rey")){
            return movimientoRey2(tableroM, y, x);
        }
        return null;
    }

/**
 * Calcula los movimientos posibles de una ficha peón para las piezas negras.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición de la ficha.
 * @param x        La columna de la posición de la ficha.
 * @return Un arreglo de cadenas con los movimientos posibles para el peón negro.
 */
    private String[] movimientoPeon1(String[][] tableroM, int y, int x)
    {
        // Lógica para calcular movimientos del peón negro
        String posicionesPosibles = "";

        if (tableroM[y - 1][x].equals(""))
        {
            posicionesPosibles += "" + (y - 1) + x + "_";
        }
        try
        {
            if (tableroM[y - 2][x].equals("") && y == 6)
            {
                posicionesPosibles += "" + (y - 2) + (x) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try
        {
            if (fichaNegra(tableroM, y - 1, x + 1))
            {
                posicionesPosibles += "" + (y - 1) + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try
        {
            if (fichaNegra(tableroM, y - 1, x - 1))
            {
                posicionesPosibles += "" + (y - 1) + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

/**
* Calcula los movimientos de ataque posibles para un peón negro en una posición específica.
*
* @param tableroM El tablero de ajedrez actual.
* @param y        La fila de la posición de la ficha.
* @param x        La columna de la posición de la ficha.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para el peón negro.
*/
    private String[] movimientoPeon1Ataque(String[][] tableroM, int y, int x)
    {
        // Lógica para calcular movimientos de ataque del peón negro
        String posicionesPosibles = "";
        try
        {
            String forcarError = tableroM[y - 1][x + 1];
            posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
        } catch (Exception ex)
        {
        }
        try
        {
            String forcarError = tableroM[y - 1][x - 1];
            posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
        }
        catch (Exception ex)
        {
        }

        String array[] = posicionesPosibles.split("_");
        return array;

    }

/**
* Calcula los movimientos de ataque posibles para un peón blanco en una posición específica.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición de la ficha.
 * @param x        La columna de la posición de la ficha.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para el peón blanco.
 */
    private String[] movimientoPeon2Ataque(String[][] tableroM, int y, int x)
    {
        // Lógica para calcular movimientos de ataque del peón blanco
        String posicionesPosibles = "";

        try
        {
            String forcarError = tableroM[y + 1][x + 1];
            posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
        }
        catch (Exception ex) {
        }

        try
        {
            String forcarError = tableroM[y + 1][x - 1];
            posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
        }
        catch (Exception ex) {
        }

        String array[] = posicionesPosibles.split("_");
        return array;

    }

/**
 * Calcula los movimientos posibles de un peón blanco en el tablero actual.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición de la ficha.
 * @param x        La columna de la posición de la ficha.
 * @return Un arreglo de cadenas con los movimientos posibles para el peón blanco.
 */
    private String[] movimientoPeon2(String[][] tableroM, int y, int x)
    {
        // Lógica para calcular movimientos del peón blanco
        String posicionesPosibles = "";

        if (tableroM[y + 1][x].equals(""))
        {
            posicionesPosibles += "" + (y + 1) + x + "_";
        }
        try
        {
            if (tableroM[y + 2][x].equals("") && y == 1 && tableroM[y + 1][x].equals(""))
            {
                posicionesPosibles += "" + (y + 2) + (x) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try
        {
            if (FichaBlanca(tableroM, y + 1, x - 1))
            {
                posicionesPosibles += "" + (y + 1) + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try
        {
            if (FichaBlanca(tableroM, y + 1, x + 1))
            {
                posicionesPosibles += "" + (y + 1) + (x + 1) + "_";
            }

        }
        catch (Exception ex)
        {
        }

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;
    }

/**
* Calcula los movimientos posibles de una torre negra en el tablero actual.
*
* @param tableroM El tablero de ajedrez actual.
* @param y        La fila de la posición de la ficha.
 * @param x        La columna de la posición de la ficha.
 * @return Un arreglo de cadenas con los movimientos posibles para la torre negra.
*/
    private String[] movimientoTorre1(String[][] tableroM, int y, int x)
    {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals(""))
                {
                    posicionesPosibles += "" + i + x + "_";
                }
                else if (fichaNegra(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
                else if (FichaBlanca(tableroM, i, x))
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals(""))
                {
                    posicionesPosibles += "" + i + x + "_";
                }
                else if (fichaNegra(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
                else if (FichaBlanca(tableroM, i, x))
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals(""))
                {
                    posicionesPosibles += "" + y + i + "_";
                }
                else if (fichaNegra(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
                else if (FichaBlanca(tableroM, y, i))
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals(""))
                {
                    posicionesPosibles += "" + y + i + "_";
                }
                else if (fichaNegra(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
                else if (FichaBlanca(tableroM, y, i))
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

/**
 * Calcula los movimientos de ataque posibles para una torre negra en una posición específica.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición de la ficha.
 * @param x        La columna de la posición de la ficha.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para la torre negra.
 */
    private String[] movimientoTorre1Ataque(String[][] tableroM, int y, int x)
    {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals(""))
                {
                    posicionesPosibles += "" + i + x + "_";
                }
                else if (fichaNegra(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
                else if (FichaBlanca(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals(""))
                {
                    posicionesPosibles += "" + i + x + "_";
                }
                else if (fichaNegra(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
                else if (FichaBlanca(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals(""))
                {
                    posicionesPosibles += "" + y + i + "_";
                }
                else if (fichaNegra(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
                else if (FichaBlanca(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals(""))
                {
                    posicionesPosibles += "" + y + i + "_";
                }
                else if (fichaNegra(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
                else if (FichaBlanca(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;
    }

/**
 * Calcula los movimientos de ataque posibles para una torre blanca en una posición específica.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición de la ficha.
 * @param x        La columna de la posición de la ficha.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para la torre blanca.
 */
    private String[] movimientoTorre2Ataque(String[][] tableroM, int y, int x)
    {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals(""))
                {
                    posicionesPosibles += "" + i + x + "_";
                }
                else if (FichaBlanca(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                }
                else if (fichaNegra(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals(""))
                {
                    posicionesPosibles += "" + i + x + "_";
                }
                else if (FichaBlanca(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
                else if (fichaNegra(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals(""))
                {
                    posicionesPosibles += "" + y + i + "_";
                }
                else if (FichaBlanca(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
                else if (fichaNegra(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals(""))
                {
                    posicionesPosibles += "" + y + i + "_";
                }
                else if (FichaBlanca(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
                else if (fichaNegra(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    //Funcion moviento torre blanca
/**
 * Calcula los movimientos posibles de una torre blanca en el tablero actual.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición de la ficha.
 * @param x        La columna de la posición de la ficha.
 * @return Un arreglo de cadenas con los movimientos posibles para la torre blanca.
 */
    private String[] movimientoTorre2(String[][] tableroM, int y, int x)
    {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals(""))
                {
                    posicionesPosibles += "" + i + x + "_";
                }
                else if (FichaBlanca(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                }
                else if (fichaNegra(tableroM, i, x))
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals(""))
                {
                    posicionesPosibles += "" + i + x + "_";
                }
                else if (FichaBlanca(tableroM, i, x))
                {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
                else if (fichaNegra(tableroM, i, x))
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals(""))
                {
                    posicionesPosibles += "" + y + i + "_";
                }
                else if (FichaBlanca(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
                else if (fichaNegra(tableroM, y, i))
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals(""))
                {
                    posicionesPosibles += "" + y + i + "_";
                }
                else if (FichaBlanca(tableroM, y, i))
                {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
                else if (fichaNegra(tableroM, y, i))
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

/**
 * Calcula los movimientos posibles en diagonal hacia arriba y abajo a la derecha e izquierda de un alfil en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición del alfil.
 * @param x        La columna de la posición del alfil.
 * @return Un arreglo de cadenas con las posiciones posibles para el alfil en la diagonal.
 */
    private String[] movimientoAlfil1(String[][] tableroM, int y, int x)
    {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try
            {
                if (tableroM[y + i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                }
                else if (fichaNegra(tableroM, y + i, x + i))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                }
                else if (fichaNegra(tableroM, y + i, x + i))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                }
                else if (fichaNegra(tableroM, y - i, x + i))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                }
                else if (fichaNegra(tableroM, y - i, x + i))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

/**
 * Calcula los movimientos posibles en diagonal hacia arriba y abajo a la derecha e izquierda de un alfil para ataques en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición del alfil.
 * @param x        La columna de la posición del alfil.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para el alfil en la diagonal.
 */
    private String[] movimientoAlfil1Ataque(String[][] tableroM, int y, int x) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                }
                else if (fichaNegra(tableroM, y + i, x + i))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                }
                else if (fichaNegra(tableroM, y + i, x + i))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                }
                else if (fichaNegra(tableroM, y - i, x + i))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                }
                else if (fichaNegra(tableroM, y - i, x + i))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    //Funcion ataque alfil blanco
    private String[] movimientoAlfil2Ataque(String[][] tableroM, int y, int x)
    {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                }
                else if (FichaBlanca(tableroM, y + i, x + i))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                }
                else if (FichaBlanca(tableroM, y + i, x + i))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                }
                else if (FichaBlanca(tableroM, y - i, x + i))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                }
                else if (FichaBlanca(tableroM, y - i, x + i))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    //Funcion movimiento alfin blanco
    private String[] movimientoAlfil2(String[][] tableroM, int y, int x)
    {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                }
                else if (FichaBlanca(tableroM, y + i, x + i))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                }
                else if (FichaBlanca(tableroM, y + i, x + i))
                {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                }
                else if (FichaBlanca(tableroM, y - i, x + i))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals(""))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                }
                else if (FichaBlanca(tableroM, y - i, x + i))
                {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
                else
                {
                    seguir = false;
                }
            }
            catch (Exception ex)
            {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

/**
 * Calcula los movimientos posibles del caballo negro en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición del caballo.
 * @param x        La columna de la posición del caballo.
 * @return Un arreglo de cadenas con las posiciones posibles para el caballo negro.
 */
    private String[] movimientoCaballo1(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            //Movimiento arriba-iaquierda
            if (tableroM[y - 2][x - 1].equals("") || fichaNegra(tableroM, y - 2, x - 1))
            {
                posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            //Movimiento arriba-derecha
            if (tableroM[y - 2][x + 1].equals("") || fichaNegra(tableroM, y - 2, x + 1))
            {
                posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            //Movimiento abajo-izquierda
            if (tableroM[y + 2][x - 1].equals("") || fichaNegra(tableroM, y + 2, x - 1))
            {
                posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            //Movimiento abajo-derecha
            if (tableroM[y + 2][x + 1].equals("") || fichaNegra(tableroM, y + 2, x + 1))
            {
                posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex) {
        }

        try {
            //Movimiento izquierda-arriba
            if (tableroM[y - 1][x - 2].equals("") || fichaNegra(tableroM, y - 1, x - 2))
            {
                posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";
            }
        }
        catch (Exception ex) {
        }

        try {
            // Movimiento izquierda-abajo
            if (tableroM[y + 1][x - 2].equals("") || fichaNegra(tableroM, y + 1, x - 2))
            {
                posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            //Movimiento derecha-arriba
            if (tableroM[y - 1][x + 2].equals("") || fichaNegra(tableroM, y - 1, x + 2))
            {
                posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            //Movimiento derecha-abajo
            if (tableroM[y + 1][x + 2].equals("") || fichaNegra(tableroM, y + 1, x + 2))
            {
                posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

/**
 * Calcula los movimientos de ataque posibles del caballo negro en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición del caballo.
 * @param x        La columna de la posición del caballo.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para el caballo negro.
 */
    private String[] movimientoCaballo1Ataque(String[][] tableroM, int y, int x)
    {
        String posicionesPosibles = "";

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x - 1];
            posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x + 1];
            posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x - 1];
            posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x + 1];
            posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x - 2];
            posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x - 2];
            posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x + 2];
            posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x + 2];
            posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";

        }
        catch (Exception ex)
        {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

/**
 * Calcula los movimientos de ataque posibles del caballo blanco en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición del caballo.
 * @param x        La columna de la posición del caballo.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para el caballo blanco.
 */
    private String[] movimientoCaballo2Ataque(String[][] tableroM, int y, int x)
    {
        String posicionesPosibles = "";

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x - 1];
            posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x + 1];
            posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x - 1];
            posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x + 1];
            posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x - 2];
            posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x - 2];
            posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x + 2];
            posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";

        }
        catch (Exception ex)
        {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x + 2];
            posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";

        }
        catch (Exception ex)
        {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

/**
 * Calcula los movimientos posibles del caballo blanco en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición del caballo.
 * @param x        La columna de la posición del caballo.
 * @return Un arreglo de cadenas con las posiciones posibles para el caballo blanco.
 */
    private String[] movimientoCaballo2(String[][] tableroM, int y, int x)
    {
        String posicionesPosibles = "";

        try {
            //Movimiento arriba-iaquierda
            if (tableroM[y - 2][x - 1].equals("") || FichaBlanca(tableroM, y - 2, x - 1))
            {
                posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            //Movimiento arriba-derecha
            if (tableroM[y - 2][x + 1].equals("") || FichaBlanca(tableroM, y - 2, x + 1))
            {
                posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex) {
        }

        try {
            //Movimiento abajo-izquierda
            if (tableroM[y + 2][x - 1].equals("") || FichaBlanca(tableroM, y + 2, x - 1))
            {
                posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex) {
        }

        try {
            //Movimiento abajo-derecha
            if (tableroM[y + 2][x + 1].equals("") || FichaBlanca(tableroM, y + 2, x + 1))
            {
                posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex) {
        }

        try {
            //Movimiento izquierda-arriba
            if (tableroM[y - 1][x - 2].equals("") || FichaBlanca(tableroM, y - 1, x - 2))
            {
                posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";
            }
        }
        catch (Exception ex) {
        }

        try {
            // Movimiento izquierda-abajo
            if (tableroM[y + 1][x - 2].equals("") || FichaBlanca(tableroM, y + 1, x - 2))
            {
                posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";
            }
        }
        catch (Exception ex) {
        }

        try {
            //Movimiento derecha-arriba
            if (tableroM[y - 1][x + 2].equals("") || FichaBlanca(tableroM, y - 1, x + 2))
            {
                posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";
            }
        }
        catch (Exception ex) {
        }

        try {
            //Movimiento derecha-abajo
            if (tableroM[y + 1][x + 2].equals("") || FichaBlanca(tableroM, y + 1, x + 2))
            {
                posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";
            }
        }
        catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

/**
 * Calcula los movimientos posibles de la reina negra en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición de la reina.
 * @param x        La columna de la posición de la reina.
 * @return Un arreglo de cadenas con las posiciones posibles para la reina negra.
 */
    private String[] movimientoReina1(String[][] tableroM, int y, int x)
    {
        String[] movimientoDiagonal = movimientoAlfil1(tableroM, y, x);
        String[] movimientoRecto = movimientoTorre1(tableroM, y, x);

        int numeroPosiciones = movimientoDiagonal.length + movimientoRecto.length;

        String[] movimientosReina = new String[numeroPosiciones];

        int n = 0;

        for (int i = 0; i < movimientoDiagonal.length; i++)
        {
            movimientosReina[n] = movimientoDiagonal[i];
            n++;
        }

        for (int i = 0; i < movimientoRecto.length; i++)
        {
            movimientosReina[n] = movimientoRecto[i];
            n++;
        }

        return movimientosReina;
    }

/**
 * Calcula los movimientos de ataque posibles de la reina negra en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición de la reina.
 * @param x        La columna de la posición de la reina.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para la reina negra.
 */
    private String[] movimientoReina1Ataque(String[][] tableroM, int y, int x)
    {
        String[] movimientoDiagonal = movimientoAlfil1Ataque(tableroM, y, x);
        String[] movimientoRecto = movimientoTorre1Ataque(tableroM, y, x);

        int numeroPosiciones = movimientoDiagonal.length + movimientoRecto.length;

        String[] movimientosReina = new String[numeroPosiciones];

        int n = 0;

        for (int i = 0; i < movimientoDiagonal.length; i++)
        {
            movimientosReina[n] = movimientoDiagonal[i];
            n++;
        }

        for (int i = 0; i < movimientoRecto.length; i++)
        {
            movimientosReina[n] = movimientoRecto[i];
            n++;
        }

        return movimientosReina;
    }

/**
 * Calcula los movimientos de ataque posibles de la reina blanca en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición de la reina.
 * @param x        La columna de la posición de la reina.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para la reina blanca.
 */
    private String[] movimientoReina2Ataque(String[][] tableroM, int y, int x)
    {
        String[] movimientoDiagonal = movimientoAlfil2Ataque(tableroM, y, x);
        String[] movimientoRecto = movimientoTorre2Ataque(tableroM, y, x);

        int numeroPosiciones = movimientoDiagonal.length + movimientoRecto.length;

        String[] movimientosReina = new String[numeroPosiciones];

        int n = 0;

        for (int i = 0; i < movimientoDiagonal.length; i++)
        {
            movimientosReina[n] = movimientoDiagonal[i];
            n++;
        }

        for (int i = 0; i < movimientoRecto.length; i++)
        {
            movimientosReina[n] = movimientoRecto[i];
            n++;
        }

        return movimientosReina;
    }

/**
 * Calcula los movimientos posibles de la reina blanca en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición de la reina.
 * @param x        La columna de la posición de la reina.
 * @return Un arreglo de cadenas con las posiciones posibles para la reina blanca.
 */
    private String[] movimientoReina2(String[][] tableroM, int y, int x)
    {
        String[] movimientoDiagonal = movimientoAlfil2(tableroM, y, x);
        String[] movimientoRecto = movimientoTorre2(tableroM, y, x);

        int numeroPosiciones = movimientoDiagonal.length + movimientoRecto.length;

        String[] movimientosReina = new String[numeroPosiciones];

        int n = 0;

        for (int i = 0; i < movimientoDiagonal.length; i++)
        {
            movimientosReina[n] = movimientoDiagonal[i];
            n++;
        }

        for (int i = 0; i < movimientoRecto.length; i++)
        {
            movimientosReina[n] = movimientoRecto[i];
            n++;
        }

        return movimientosReina;
    }

/**
 * Calcula los movimientos posibles del rey negro en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición del rey.
 * @param x        La columna de la posición del rey.
 * @return Un arreglo de cadenas con las posiciones posibles para el rey negro.
 */
    private String[] movimientoRey1(String[][] tableroM, int y, int x)
    {

        String posicionesPosibles = "";

        try {
            if (FichaBlanca(tableroM, y - 1, x - 1) == false)
            {
                posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (FichaBlanca(tableroM, y - 1, x) == false)
            {
                posicionesPosibles += "" + (y - 1) + "" + (x) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (FichaBlanca(tableroM, y - 1, x + 1) == false)
            {
                posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (FichaBlanca(tableroM, y, x - 1) == false)
            {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (FichaBlanca(tableroM, y, x + 1) == false)
            {
                posicionesPosibles += "" + (y) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (FichaBlanca(tableroM, y + 1, x - 1) == false)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (FichaBlanca(tableroM, y + 1, x) == false)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (FichaBlanca(tableroM, y + 1, x + 1) == false)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex) {
        }

        //Enrroque 
        if (Controlador.Controlador.enrroqueRey1 == true)
        {
            if (tableroM[7][1].equals("") && tableroM[7][2].equals("") && tableroM[7][3].equals("") && Controlador.Controlador.enrroqueTorreIzquierda1 == true)
            {
                posicionesPosibles += "" + "72" + "_";
            }
            if (tableroM[7][5].equals("") && tableroM[7][6].equals("") && Controlador.Controlador.enrroqueTorreDerecha1 == true)
            {
                posicionesPosibles += "" + "76" + "_";
            }
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

/**
 * Calcula los movimientos de ataque posibles del rey negro en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición del rey.
 * @param x        La columna de la posición del rey.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para el rey negro.
 */
    private String[] movimientoRey1Ataque(String[][] tableroM, int y, int x)
    {

        String posicionesPosibles = "";

        if (y > 0 && x > 0)
        {
            posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
        }

        try {
            if (y > 0 && x < 7)
            {
                posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {

        }

        try {
            if (y > 0)
            {
                posicionesPosibles += "" + (y - 1) + "" + (x) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (x > 0)
            {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";
            }

        }
        catch (Exception ex)
        {

        }

        try {
            if (x < 7)
            {
                posicionesPosibles += "" + (y) + "" + (x + 1) + "_";
            }

        }
        catch (Exception ex)
        {

        }

        try {
            if (y < 7 && x > 0)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
            }

        }
        catch (Exception ex)
        {

        }

        try {
            if (y < 7)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x) + "_";
            }

        }
        catch (Exception ex)
        {
        }

        try {
            if (y < 7 && x < 7)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

/**
 * Calcula los movimientos de ataque posibles del rey blanco en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición del rey.
 * @param x        La columna de la posición del rey.
 * @return Un arreglo de cadenas con las posiciones de ataque posibles para el rey blanco.
 */
    private String[] movimientoRey2Ataque(String[][] tableroM, int y, int x)
    {

        String posicionesPosibles = "";

        if (y > 0 && x > 0)
        {
            posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
        }

        try {
            if (y > 0)
            {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";

            }

        }
        catch (Exception ex)
        {
        }

        try {
            if (y > 0 && x < 7)
            {
                posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (x > 0)
            {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";
            }

        }
        catch (Exception ex)
        {
        }

        try {
            if (x < 7)
            {
                posicionesPosibles += "" + (y) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (y < 7 && x > 7)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (y < 7)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (y < 7 && x < 7)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

/**
 * Calcula los movimientos posibles del rey blanco en el tablero de ajedrez.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición del rey.
 * @param x        La columna de la posición del rey.
 * @return Un arreglo de cadenas con las posiciones posibles para el rey blanco.
 */
    private String[] movimientoRey2(String[][] tableroM, int y, int x)
    {

        String posicionesPosibles = "";

        try {
            if (fichaNegra(tableroM, y - 1, x - 1) == false)
            {
                posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (fichaNegra(tableroM, y - 1, x) == false)
            {
                posicionesPosibles += "" + (y - 1) + "" + (x) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (fichaNegra(tableroM, y - 1, x + 1) == false)
            {
                posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (fichaNegra(tableroM, y, x - 1) == false)
            {
                posicionesPosibles += "" + (y) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (fichaNegra(tableroM, y, x + 1) == false)
            {
                posicionesPosibles += "" + (y) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex) {
        }

        try {
            if (fichaNegra(tableroM, y + 1, x - 1) == false)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
            }
        }
        catch (Exception ex) {
        }

        try {
            if (fichaNegra(tableroM, y + 1, x) == false)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        try {
            if (fichaNegra(tableroM, y + 1, x + 1) == false)
            {
                posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
            }
        }
        catch (Exception ex)
        {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

/**
 * Obtiene el tipo de ficha en una casilla del tablero.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición.
 * @param x        La columna de la posición.
 * @return El tipo de ficha en la posición dada del tablero.
 */
    private String fichaCasilla(String[][] tableroM, int y, int x)
    {
        return tableroM[y][x];
    }

/**
 * Verifica si la ficha en la casilla dada es negra.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición.
 * @param x        La columna de la posición.
 * @return true si la ficha en la posición es negra, de lo contrario false.
 */
    private boolean fichaNegra(String[][] tableroM, int y, int x)
    {
        if (!tableroM[y][x].equals(""))
        {
            return (tableroM[y][x].charAt(0) == '2') ? true : false;
        }
        return false;
    }

/**
 * Verifica si la ficha en la casilla dada es blanca.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @param y        La fila de la posición.
 * @param x        La columna de la posición.
 * @return true si la ficha en la posición es blanca, de lo contrario false.
 */
    private boolean FichaBlanca(String[][] tableroM, int y, int x)
    {
        if (!tableroM[y][x].equals(""))
        {
            return (tableroM[y][x].charAt(0) == '1') ? true : false;
        }
        return false;
    }

/**
 * Verifica si el rey negro está en jaque.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @return true si el rey negro está en jaque, de lo contrario false.
 */
    private boolean rey1Jaque(String[][] tableroM)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (fichaCasilla(tableroM, i, j).equals("1_rey"))
                {
                    String posicionRey = "" + i + "" + j;

                    String[] movimientosEnemigos = MovimientosFichas2(tableroM);

                    for (int x = 0; x < movimientosEnemigos.length; x++)
                    {
                        if (movimientosEnemigos[x].equals(posicionRey))
                        {
                            return true;
                        }
                    }
                    return false;

                }
            }
        }
        return false;
    }

/**
 * Verifica si el rey blanco está en jaque.
 *
 * @param tableroM El tablero de ajedrez actual.
 * @return true si el rey blanco está en jaque, de lo contrario false.
 */
    private boolean rey2Jaque(String[][] tableroM)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (fichaCasilla(tableroM, i, j).equals("2_rey"))
                {
                    String posicionRey = "" + i + "" + j;

                    String[] movimientosEnemigos = MovimientosFichas1(tableroM);

                    for (int x = 0; x < movimientosEnemigos.length; x++)
                    {
                        if (movimientosEnemigos[x].equals(posicionRey))
                        {
                            return true;
                        }
                    }
                    return false;

                }
            }
        }
        return false;
    }


}
