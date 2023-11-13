package Modelo;

public class Movimientos {

    String[][] tablero;


    public boolean PosibleMovimiento(String[][] tablero, String posicionAntigua, String posicionNueva) {
        this.tablero = tablero;
        String[] posiblesPosiciones;
        posiblesPosiciones = modificarMovimientos(tablero, posicionAntigua);

        if (posiblesPosiciones != null) {
            for (int i = 0; i < posiblesPosiciones.length; i++) {
                if (posiblesPosiciones[i].equals(posicionNueva)) {
                    comprobarEnrroque(posicionAntigua);
                    return true;
                }
            }
        }
        return false;
    }


    private void comprobarEnrroque(String posAntigua) {
        if (posAntigua.equals("74")) {
            Controlador.Controlador.enrroqueRey1 = false;
        } else if (posAntigua.equals("70")) {
            Controlador.Controlador.enrroqueTorreIzquierda1 = false;
        } else if (posAntigua.equals("77")) {
            Controlador.Controlador.enrroqueTorreDerecha1 = false;
        } else if (posAntigua.equals("04")) {
            Controlador.Controlador.enrroqueRey2 = false;
        } else if (posAntigua.equals("00")) {
            Controlador.Controlador.enrroqueTorreIzquierda2 = false;
        } else if (posAntigua.equals("07")) {
            Controlador.Controlador.enrroqueTorreDerecha2 = false;
        }
    }

    public String[] modificarMovimientos(String[][] tableroM, String posicion) {
        String[] posiblesMovimientos = movimientosFichas(tableroM, posicion);
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));

        return modificacionMovimientos(tableroM, posicion, posiblesMovimientos);
    }
    
    public String[] movimientos2modificados(String[][] tableroM, String posicion) {
        String[] posiblesMovimientos = movimientosFichas(tableroM, posicion);
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));

        return modificacionMovimientos(tableroM, posicion, posiblesMovimientos);
    }

    private String[] modificacionMovimientos(String[][] tableroFuturo, String posicionInicial, String[] posicionesFinales) {
        String posicionesDefinitivas = "";

        int xInicial = Character.getNumericValue(posicionInicial.charAt(1));
        int yInicial = Character.getNumericValue(posicionInicial.charAt(0));

        String ficha = fichaCasilla(tableroFuturo, yInicial, xInicial);
        if (posicionesFinales != null) {
            for (int i = 0; i < posicionesFinales.length; i++) {
                try {
                    String posicionFinal = posicionesFinales[i];
                    int xFinal = Character.getNumericValue(posicionFinal.charAt(1));
                    int yFinal = Character.getNumericValue(posicionFinal.charAt(0));

                    String tableroM[][] = new String[8][8];

                    copiarTableroPrimeroAlSegundo(tableroFuturo, tableroM);

                    tableroM[yFinal][xFinal] = tableroM[yInicial][xInicial];
                    tableroM[yInicial][xInicial] = "";

                    if (ficha.charAt(0) == 'A') {
                        if (reyAenJaque(tableroM) == false) {
                            posicionesDefinitivas += "" + yFinal + "" + xFinal + "_";
                        }
                    } else if (ficha.charAt(0) == 'B') {
                    }
                } catch (Exception ex) {
                }

            }

            String[] arrayPosiciones = posicionesDefinitivas.split("_");
            return arrayPosiciones;
        }
        return null;
    }

    private void copiarTableroPrimeroAlSegundo(String[][] tableroOrigen, String[][] tableroCopia) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tableroCopia[i][j] = tableroOrigen[i][j];
            }
        }
    }

    public String[] todosLosMovimientosFichas2(String[][] tableroM) {

        String posicionesTotales = "";

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String ficha = fichaCasilla(tableroM, i, j);

                if (ficha.equals("B_torre")) {
                    posicionesTotales += transformarPalabra(movimientoTorre2Ataque(tableroM, i, j));
                } else if (ficha.equals("B_alfil")) {
                    posicionesTotales += transformarPalabra(movimientoAlfil2Ataque(tableroM, i, j));
                    posicionesTotales += transformarPalabra(movimientoCaballo2Ataque(tableroM, i, j));
                } else if (ficha.equals("B_peon")) {
                    posicionesTotales += transformarPalabra(movimientoPeon2Ataque(tableroM, i, j));
                }

            }
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    public String[] movimientoAtaqueFicha2(String[][] tableroM, int i, int j) {

        String posicionesTotales = "";

        String ficha = fichaCasilla(tableroM, i, j);

        if (ficha.equals("B_torre")) {
            posicionesTotales += transformarPalabra(movimientoTorre2Ataque(tableroM, i, j));
        } else if (ficha.equals("B_alfil")) {
            posicionesTotales += transformarPalabra(movimientoAlfil2Ataque(tableroM, i, j));
        } else if (ficha.equals("B_caballo")) {
            posicionesTotales += transformarPalabra(movimientoCaballo2Ataque(tableroM, i, j));
        } else if (ficha.equals("B_peon")) {
            posicionesTotales += transformarPalabra(movimientoPeon2Ataque(tableroM, i, j));
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    public String[] movimientoAtaqueFicha1(String[][] tableroM, int i, int j) {

        String posicionesTotales = "";

        String ficha = fichaCasilla(tableroM, i, j);

        if (ficha.equals("A_torre")) {
            posicionesTotales += transformarPalabra(movimientoTorre1Ataque(tableroM, i, j));
        } else if (ficha.equals("A_alfil")) {
            posicionesTotales += transformarPalabra(movimientoAlfil1Ataque(tableroM, i, j));;
        } else if (ficha.equals("A_caballo")) {
            posicionesTotales += transformarPalabra(movimientoCaballo1Ataque(tableroM, i, j));
        } else if (ficha.equals("A_peon")) {
            posicionesTotales += transformarPalabra(movimientoPeon1Ataque(tableroM, i, j));
        }

        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    public String[] todosLosMovimientosFichas1(String[][] tableroM) {

        String posicionesTotales = "";

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String ficha = fichaCasilla(tableroM, i, j);

                if (ficha.equals("A_torre")) {
                    posicionesTotales += transformarPalabra(movimientoTorre1Ataque(tableroM, i, j));
                } else if (ficha.equals("A_alfil")) {
                    posicionesTotales += transformarPalabra(movimientoAlfil1Ataque(tableroM, i, j));
                } else if (ficha.equals("A_caballo")) {
                    posicionesTotales += transformarPalabra(movimientoCaballo1Ataque(tableroM, i, j));
                } else if (ficha.equals("A_peon")) {
                    posicionesTotales += transformarPalabra(movimientoPeon1Ataque(tableroM, i, j));
                }

            }
        }
        String[] arrayTotal = posicionesTotales.split("_");
        return arrayTotal;

    }

    private String transformarPalabra(String[] array) {
        String palabra = "";
        for (int i = 0; i < array.length; i++) {
            palabra += array[i] + "_";
        }
        return palabra;
    }

    public String[] movimientosFichas(String[][] tableroM, String posicion) {
        int x = Character.getNumericValue(posicion.charAt(1));
        int y = Character.getNumericValue(posicion.charAt(0));
        String ficha = fichaCasilla(tableroM, y, x);
        if (ficha.equals("A_peon")) {
            return movimientoPeon1(tableroM, y, x);
        } else if (ficha.equals("B_peon")) {
            return movimientoPeon2(tableroM, y, x);
        } else if (ficha.equals("A_torre")) {
            return movimientoTorre1(tableroM, y, x);
        } else if (ficha.equals("B_torre")) {
            return movimientoTorre2(tableroM, y, x);
        } else if (ficha.equals("A_alfil")) {
            return movimientoAlfil1(tableroM, y, x);
        } else if (ficha.equals("B_alfil")) {
            return movimientoAlfil2(tableroM, y, x);
        } else if (ficha.equals("A_caballo")) {
            return movimientoCaball1(tableroM, y, x);
        } else if (ficha.equals("B_caballo")) {
            return movimientoCaballo2(tableroM, y, x);
        }

        return null;
    }

    private String[] movimientoPeon1(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        if (tableroM[y - 1][x].equals("")) {
            posicionesPosibles += "" + (y - 1) + x + "_";
        }
        try {
            if (tableroM[y - 2][x].equals("") && y == 6) {
                posicionesPosibles += "" + (y - 2) + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y - 1, x + 1)) {
                posicionesPosibles += "" + (y - 1) + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsnegra(tableroM, y - 1, x - 1)) {
                posicionesPosibles += "" + (y - 1) + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoPeon1Ataque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";
        try {
            String forcarError = tableroM[y - 1][x + 1];
            posicionesPosibles += "" + (y - 1) + "" + (x + 1) + "_";
        } catch (Exception ex) {
        }
        try {
            String forcarError = tableroM[y - 1][x - 1];
            posicionesPosibles += "" + (y - 1) + "" + (x - 1) + "_";
        } catch (Exception ex) {
        }

        String array[] = posicionesPosibles.split("_");
        return array;

    }

    private String[] movimientoPeon2Ataque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            String forcarError = tableroM[y + 1][x + 1];
            posicionesPosibles += "" + (y + 1) + "" + (x + 1) + "_";
        } catch (Exception ex) {
        }

        try {
            String forcarError = tableroM[y + 1][x - 1];
            posicionesPosibles += "" + (y + 1) + "" + (x - 1) + "_";
        } catch (Exception ex) {
        }

        String array[] = posicionesPosibles.split("_");
        return array;

    }

    private String[] movimientoPeon2(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        if (tableroM[y + 1][x].equals("")) {
            posicionesPosibles += "" + (y + 1) + x + "_";
        }
        try {
            if (tableroM[y + 2][x].equals("") && y == 1 && tableroM[y + 1][x].equals("")) {
                posicionesPosibles += "" + (y + 2) + (x) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y + 1, x - 1)) {
                posicionesPosibles += "" + (y + 1) + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            if (comprobarSiLaFichaEsBlanca(tableroM, y + 1, x + 1)) {
                posicionesPosibles += "" + (y + 1) + (x + 1) + "_";
            }

        } catch (Exception ex) {
        }

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;
    }

    private String[] movimientoTorre1(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoTorre1Ataque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;
    }

    private String[] movimientoTorre2Ataque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoTorre2(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";
        int i;
        boolean seguir;

        //Movimiento hacia abajo
        seguir = true;
        i = y;
        do {
            i++;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + "" + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia arriba
        seguir = true;
        i = y;
        do {
            i--;

            try {
                if (tableroM[i][x].equals("")) {
                    posicionesPosibles += "" + i + x + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, i, x)) {
                    posicionesPosibles += "" + i + x + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, i, x)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la izquierda
        seguir = true;
        i = x;
        do {
            i--;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        //Movimiento hacia la derecha
        seguir = true;
        i = x;
        do {
            i++;

            try {
                if (tableroM[y][i].equals("")) {
                    posicionesPosibles += "" + y + i + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y, i)) {
                    posicionesPosibles += "" + y + i + "_";
                    seguir = false;
                } else if (comprobarSiLaFichaEsnegra(tableroM, y, i)) {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoAlfil1(String[][] tableroM, int y, int x) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoAlfil1Ataque(String[][] tableroM, int y, int x) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsnegra(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoAlfil2Ataque(String[][] tableroM, int y, int x) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoAlfil2(String[][] tableroM, int y, int x) {
        boolean seguir;
        int i;
        String posicionesPosibles = "";

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y + i][x + i].equals("")) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y + i, x + i)) {
                    posicionesPosibles += "" + (y + i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i++;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        seguir = true;
        i = 0;
        do {
            i--;
            try {
                if (tableroM[y - i][x + i].equals("")) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                } else if (comprobarSiLaFichaEsBlanca(tableroM, y - i, x + i)) {
                    posicionesPosibles += "" + (y - i) + "" + (x + i) + "_";
                    seguir = false;
                } else {
                    seguir = false;
                }
            } catch (Exception ex) {
                seguir = false;
            }

        } while (seguir);

        String[] arrayPosicionesPosibles = posicionesPosibles.split("_");

        return arrayPosicionesPosibles;

    }

    private String[] movimientoCaball1(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            //Movimiento arriba-iaquierda
            if (tableroM[y - 2][x - 1].equals("") || comprobarSiLaFichaEsnegra(tableroM, y - 2, x - 1)) {
                posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento arriba-derecha
            if (tableroM[y - 2][x + 1].equals("") || comprobarSiLaFichaEsnegra(tableroM, y - 2, x + 1)) {
                posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento abajo-izquierda
            if (tableroM[y + 2][x - 1].equals("") || comprobarSiLaFichaEsnegra(tableroM, y + 2, x - 1)) {
                posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento abajo-derecha
            if (tableroM[y + 2][x + 1].equals("") || comprobarSiLaFichaEsnegra(tableroM, y + 2, x + 1)) {
                posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento izquierda-arriba
            if (tableroM[y - 1][x - 2].equals("") || comprobarSiLaFichaEsnegra(tableroM, y - 1, x - 2)) {
                posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento izquierda-abajo
            if (tableroM[y + 1][x - 2].equals("") || comprobarSiLaFichaEsnegra(tableroM, y + 1, x - 2)) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento derecha-arriba
            if (tableroM[y - 1][x + 2].equals("") || comprobarSiLaFichaEsnegra(tableroM, y - 1, x + 2)) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento derecha-abajo
            if (tableroM[y + 1][x + 2].equals("") || comprobarSiLaFichaEsnegra(tableroM, y + 1, x + 2)) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";
            }
        } catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoCaballo1Ataque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x - 1];
            posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x + 1];
            posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x - 1];
            posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x + 1];
            posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x - 2];
            posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x - 2];
            posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x + 2];
            posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x + 2];
            posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";

        } catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoCaballo2Ataque(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x - 1];
            posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 2][x + 1];
            posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x - 1];
            posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 2][x + 1];
            posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x - 2];
            posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x - 2];
            posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y - 1][x + 2];
            posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";

        } catch (Exception ex) {
        }

        try {
            String estoSirvePorSiNoExisteQueDeError = tableroM[y + 1][x + 2];
            posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";

        } catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }

    private String[] movimientoCaballo2(String[][] tableroM, int y, int x) {
        String posicionesPosibles = "";

        try {
            //Movimiento arriba-iaquierda
            if (tableroM[y - 2][x - 1].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y - 2, x - 1)) {
                posicionesPosibles += "" + (y - 2) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento arriba-derecha
            if (tableroM[y - 2][x + 1].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y - 2, x + 1)) {
                posicionesPosibles += "" + (y - 2) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento abajo-izquierda
            if (tableroM[y + 2][x - 1].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y + 2, x - 1)) {
                posicionesPosibles += "" + (y + 2) + "" + (x - 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento abajo-derecha
            if (tableroM[y + 2][x + 1].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y + 2, x + 1)) {
                posicionesPosibles += "" + (y + 2) + "" + (x + 1) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento izquierda-arriba
            if (tableroM[y - 1][x - 2].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y - 1, x - 2)) {
                posicionesPosibles += "" + (y - 1) + "" + (x - 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            // Movimiento izquierda-abajo
            if (tableroM[y + 1][x - 2].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y + 1, x - 2)) {
                posicionesPosibles += "" + (y + 1) + "" + (x - 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento derecha-arriba
            if (tableroM[y - 1][x + 2].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y - 1, x + 2)) {
                posicionesPosibles += "" + (y - 1) + "" + (x + 2) + "_";
            }
        } catch (Exception ex) {
        }

        try {
            //Movimiento derecha-abajo
            if (tableroM[y + 1][x + 2].equals("") || comprobarSiLaFichaEsBlanca(tableroM, y + 1, x + 2)) {
                posicionesPosibles += "" + (y + 1) + "" + (x + 2) + "_";
            }
        } catch (Exception ex) {
        }

        String[] arregloPosicionesPosibles = posicionesPosibles.split("_");

        return arregloPosicionesPosibles;
    }


    private String fichaCasilla(String[][] tableroM, int y, int x) {
        return tableroM[y][x];
    }

    private boolean comprobarSiLaFichaEsnegra(String[][] tableroM, int y, int x) {
        if (!tableroM[y][x].equals("")) {
            return (tableroM[y][x].charAt(0) == 'B') ? true : false;
        }
        return false;
    }

    private boolean comprobarSiLaFichaEsBlanca(String[][] tableroM, int y, int x) {
        if (!tableroM[y][x].equals("")) {
            return (tableroM[y][x].charAt(0) == 'A') ? true : false;
        }
        return false;
    }

    private boolean reyAenJaque(String[][] tableroM) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (fichaCasilla(tableroM, i, j).equals("A_rey")) {
                    String posicionRey = "" + i + "" + j;

                    String[] movimientosEnemigos = todosLosMovimientosFichas2(tableroM);

                    for (int x = 0; x < movimientosEnemigos.length; x++) {
                        if (movimientosEnemigos[x].equals(posicionRey)) {
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
