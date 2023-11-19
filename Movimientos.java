package Modelo;

public class Movimientos {

    String[][] tablero;

    private void copiarTablero(String[][] arr)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                arr[i][j] = tablero[i][j];
            }
        }
    }

    //Hacer que sea posible un moviemiento
    public boolean posibleMovimiento(String[][] tablero, String posicionAntigua, String posicionNueva)
    {
        this.tablero = tablero;
        String[] posicionesPosibles;
        posicionesPosibles = movimientos1Modificados(tablero, posicionAntigua);

        if (posicionesPosibles != null)
        {
            for (int i = 0; i < posicionesPosibles.length; i++)
            {
                if (posicionesPosibles[i].equals(posicionNueva))
                {
                    comprobarEnrroque(posicionAntigua);
                    return true;
                }
            }
        }
        return false;
    }

    //Comrobar si las fichas siguen el patron para hacer enrroque.
    private void comprobarEnrroque(String posAntigua)
    {
        if (posAntigua.equals("74"))
        {
            Controlador.Controlador.enrroqueRey1 = false;
        }
        else if (posAntigua.equals("70"))
        {
            Controlador.Controlador.enrroqueTorreIzquierda1 = false;
        }
        else if (posAntigua.equals("77"))
        {
            Controlador.Controlador.enrroqueTorreDerecha1 = false;
        }
        else if (posAntigua.equals("04"))
        {
            Controlador.Controlador.enrroqueRey2 = false;
        }
        else if (posAntigua.equals("00"))
        {
            Controlador.Controlador.enrroqueTorreIzquierda2 = false;
        }
        else if (posAntigua.equals("07"))
        {
            Controlador.Controlador.enrroqueTorreDerecha2 = false;
        }
    }

    //Comprueba los movimientos de las fichas
    public String[] movimientos1Modificados(String[][] tableroM, String posicion)
    {
        String[] posiblesMovimientos = movimientosFichas(tableroM, posicion);
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));

        return modificacionTodosMovimientos(tableroM, posicion, posiblesMovimientos);
    }

    //Comprueba movientos fichas
    public String[] movimientos2Modificados(String[][] tableroM, String posicion)
    {
        String[] posiblesMovimientos = movimientosFichas(tableroM, posicion);
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));

        return modificacionTodosMovimientos(tableroM, posicion, posiblesMovimientos);
    }

    private String[] modificacionTodosMovimientos(String[][] tableroFuturo, String posicionInicial, String[] posicionesFinales)
    {
        String posicionesDefinitivas = "";

        int xInicial = Character.getNumericValue(posicionInicial.charAt(1));
        int yInicial = Character.getNumericValue(posicionInicial.charAt(0));

        String ficha = fichaCasilla(tableroFuturo, yInicial, xInicial);

        if (posicionesFinales != null)
        {
            for (int i = 0; i < posicionesFinales.length; i++)
            {
                try
                {
                    String posicionFinal = posicionesFinales[i];
                    int xFinal = Character.getNumericValue(posicionFinal.charAt(1));
                    int yFinal = Character.getNumericValue(posicionFinal.charAt(0));

                    String tableroM[][] = new String[8][8];

                    copiarDelPrimeroAlSegundoElTablero(tableroFuturo, tableroM);

                    tableroM[yFinal][xFinal] = tableroM[yInicial][xInicial];
                    tableroM[yInicial][xInicial] = "";

                    if (ficha.charAt(0) == 'A')
                    {
                        if (rey1Jaque(tableroM) == false)
                        {
                            posicionesDefinitivas += "" + yFinal + "" + xFinal + "_";
                        }
                    }
                    else if (ficha.charAt(0) == 'B')
                    {
                        if (rey2Jaque(tableroM) == false)
                        {
                            posicionesDefinitivas += "" + yFinal + "" + xFinal + "_";
                        }
                    }
                }
                catch (Exception ex)
                {
                }

            }

            String[] arrayPosiciones = posicionesDefinitivas.split("_");
            return arrayPosiciones;
        }
        return null;
    }

    private void copiarDelPrimeroAlSegundoElTablero(String[][] tableroOrigen, String[][] tableroCopia)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                tableroCopia[i][j] = tableroOrigen[i][j];
            }
        }
    }

    public String[] MovimientosFichas2(String[][] tableroM)
    {

        String posicionesTotales = "";

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                String ficha = fichaCasilla(tableroM, i, j);

                if (ficha.equals("2_torre"))
                {
                    posicionesTotales += cambiarPalabra(movimientoTorre2Ataque(tableroM, i, j));
                }
                else if (ficha.equals("2_alfil"))
                {
                    posicionesTotales += cambiarPalabra(movimientoAlfil2Ataque(tableroM, i, j));
                }
                else if (ficha.equals("2_reina"))
                {
                    posicionesTotales += cambiarPalabra(movimientoReina2Ataque(tableroM, i, j));
                }
                else if (ficha.equals("2_caballo"))
                {
                    posicionesTotales += cambiarPalabra(movimientoCaballo2Ataque(tableroM, i, j));
                }
                else if (ficha.equals("2_rey"))
                {
                    posicionesTotales += cambiarPalabra(movimientoRey2Ataque(tableroM, i, j));
                }
                else if (ficha.equals("2_peon"))
                {
                    posicionesTotales += cambiarPalabra(movimientoPeon2Ataque(tableroM, i, j));
                }

            }
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    //Funcion para atacar con blancas.
    public String[] ataqueFicha2(String[][] tableroM, int i, int j)
    {

        String posicionesTotales = "";

        String ficha = fichaCasilla(tableroM, i, j);

        if (ficha.equals("B_torre"))
        {
            posicionesTotales += cambiarPalabra(movimientoTorre2Ataque(tableroM, i, j));
        }
        else if (ficha.equals("B_alfil"))
        {
            posicionesTotales += cambiarPalabra(movimientoAlfil2Ataque(tableroM, i, j));
        }
        else if (ficha.equals("B_reina"))
        {
            posicionesTotales += cambiarPalabra(movimientoReina2Ataque(tableroM, i, j));
        }
        else if (ficha.equals("B_caballo"))
        {
            posicionesTotales += cambiarPalabra(movimientoCaballo2Ataque(tableroM, i, j));
        }
        else if (ficha.equals("B_rey"))
        {
            posicionesTotales += cambiarPalabra(movimientoRey2Ataque(tableroM, i, j));
        }
        else if (ficha.equals("B_peon"))
        {
            posicionesTotales += cambiarPalabra(movimientoPeon2Ataque(tableroM, i, j));
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    public String[] ataqueFicha1(String[][] tableroM, int i, int j)
    {

        String posicionesTotales = "";

        String ficha = fichaCasilla(tableroM, i, j);

        if (ficha.equals("1_torre"))
        {
            posicionesTotales += cambiarPalabra(movimientoTorre1Ataque(tableroM, i, j));
        }
        else if (ficha.equals("1_alfil"))
        {
            posicionesTotales += cambiarPalabra(movimientoAlfil1Ataque(tableroM, i, j));
        }
        else if (ficha.equals("1_reina"))
        {
            posicionesTotales += cambiarPalabra(movimientoReina1Ataque(tableroM, i, j));
        }
        else if (ficha.equals("1_caballo"))
        {
            posicionesTotales += cambiarPalabra(movimientoCaballo1Ataque(tableroM, i, j));
        }
        else if (ficha.equals("1_rey"))
        {
            posicionesTotales += cambiarPalabra(movimientoRey1Ataque(tableroM, i, j));
        }
        else if (ficha.equals("1_peon"))
        {
            posicionesTotales += cambiarPalabra(movimientoPeon1Ataque(tableroM, i, j));
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    public String[] MovimientosFichas1(String[][] tableroM)
    {

        String posicionesTotales = "";

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                String ficha = fichaCasilla(tableroM, i, j);

                if (ficha.equals("A_torre")) {

                    posicionesTotales += cambiarPalabra(movimientoTorre1Ataque(tableroM, i, j));
                }
                else if (ficha.equals("A_alfil"))
                {
                    posicionesTotales += cambiarPalabra(movimientoAlfil1Ataque(tableroM, i, j));
                }
                else if (ficha.equals("A_reina"))
                {
                    posicionesTotales += cambiarPalabra(movimientoReina1Ataque(tableroM, i, j));
                }
                else if (ficha.equals("A_caballo"))
                {
                    posicionesTotales += cambiarPalabra(movimientoCaballo1Ataque(tableroM, i, j));
                }
                else if (ficha.equals("A_rey"))
                {
                    posicionesTotales += cambiarPalabra(movimientoRey1Ataque(tableroM, i, j));
                }
                else if (ficha.equals("A_peon"))
                {
                    posicionesTotales += cambiarPalabra(movimientoPeon1Ataque(tableroM, i, j));
                }

            }
        }
        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    private String cambiarPalabra(String[] array)
    {
        String palabra = "";

        for (int i = 0; i < array.length; i++)
        {
            palabra += array[i] + "_";
        }
        return palabra;
    }

    public String[] movimientosFichas(String[][] tableroM, String posicion)
    {
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));
        String ficha = fichaCasilla(tableroM, y, x);

        if (ficha.equals("A_peon"))
        {
            return movimientoPeon1(tableroM, y, x);
        }
        else if (ficha.equals("B_peon"))
        {
            return movimientoPeon2(tableroM, y, x);
        }
        else if (ficha.equals("A_torre"))
        {
            return movimientoTorre1(tableroM, y, x);
        }
        else if (ficha.equals("B_torre"))
        {
            return movimientoTorre2(tableroM, y, x);
        }
        else if (ficha.equals("A_alfil"))
        {
            return movimientoAlfil1(tableroM, y, x);
        }
        else if (ficha.equals("B_alfil"))
        {
            return movimientoAlfil2(tableroM, y, x);
        }
        else if (ficha.equals("A_caballo"))
        {
            return movimientoCaballo1(tableroM, y, x);
        }
        else if (ficha.equals("B_caballo"))
        {
            return movimientoCaballo2(tableroM, y, x);
        }
        else if (ficha.equals("A_reina"))
        {
            return movimientoReina1(tableroM, y, x);
        }
        else if (ficha.equals("B_reina"))
        {
            return movimientoReina2(tableroM, y, x);
        }
        else if (ficha.equals("A_rey"))
        {
            return movimientoRey1(tableroM, y, x);
        }
        else if (ficha.equals("B_rey"))
        {
            return movimientoRey2(tableroM, y, x);
        }

        return null;
    }

    //Funcion para el movimiento del peon de las fichas negras
    private String[] movimientoPeon1(String[][] tableroM, int y, int x)
    {
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

    //Funcion de ataque peon negro
    private String[] movimientoPeon1Ataque(String[][] tableroM, int y, int x)
    {
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

    //Funcon ataque peon blanco
    private String[] movimientoPeon2Ataque(String[][] tableroM, int y, int x)
    {
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

    //Funcion de los movimientos de los peones blnacos
    private String[] movimientoPeon2(String[][] tableroM, int y, int x)
    {
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

    //Funcion movimiento de la torre negra
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

    //Funcion de ataque de la torre negra.
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

    //Funcion ataque torre blanca.
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

    //Funcion del alfil negro
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

    //Funcion de ataque del alfil negro
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

    //Funcion movimiento caballo negro
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

    //Funcion ataque caballo negro
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

    //Funcion caballo blanco comer
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

    //Funcion mover caballo blanco
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

    //Funcion movimiento reina negra
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

    //Funcion ataque reina negra
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

    //Funcion ataque reina blanca
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

    //funcion movimiento reina blanca
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

    //funcion movimiento rey negro
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

    //Funcion ataque rey negro
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

    //Funcion ataque rey blanco
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

    //funcion mover rey blanco
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

    private String fichaCasilla(String[][] tableroM, int y, int x)
    {
        return tableroM[y][x];
    }

    //Funcion para comprobar que la ficha es negra
    private boolean fichaNegra(String[][] tableroM, int y, int x)
    {
        if (!tableroM[y][x].equals(""))
        {
            return (tableroM[y][x].charAt(0) == 'B') ? true : false;
        }
        return false;
    }

    //funcion para comprobar que la ficha es blnaca
    private boolean FichaBlanca(String[][] tableroM, int y, int x)
    {
        if (!tableroM[y][x].equals(""))
        {
            return (tableroM[y][x].charAt(0) == 'A') ? true : false;
        }
        return false;
    }

    //Funcion para ver si el rey negro esta en jaque.
    private boolean rey1Jaque(String[][] tableroM)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (fichaCasilla(tableroM, i, j).equals("A_rey"))
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

    //Funcion comprobar rey blanco esta en jaque
    private boolean rey2Jaque(String[][] tableroM)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (fichaCasilla(tableroM, i, j).equals("B_rey"))
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
