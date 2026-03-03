import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Jugador 

{
    private boolean[] cartasEnEscalera() {

        boolean[] usadas = new boolean[cartas.length];

        ArrayList<ArrayList<Carta>> escaleras = obtenerEscaleras();

        for (ArrayList<Carta> esc : escaleras) {
            for (Carta c : esc) {
                for (int i = 0; i < cartas.length; i++) {
                    if (cartas[i] == c) {
                        usadas[i] = true;
                    }
                }
            }
        }

        return usadas;
    }
    private final int TOTAL_CARTAS = 10;
    private final int MARGEN_IZQUIERDA = 10;
    private final int DISTANCIA_ENTRE_CARTAS = 50;
    private final int MARGEN_SUPERIOR = 10;

    private Random r = new Random();
    private Carta[] cartas = new Carta[TOTAL_CARTAS];

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        pnl.setLayout(null);
        int posicion = MARGEN_IZQUIERDA + DISTANCIA_ENTRE_CARTAS * (TOTAL_CARTAS - 1);
        for (Carta andrea : cartas) {
            andrea.mostrar(posicion, MARGEN_SUPERIOR, pnl);
            posicion -= DISTANCIA_ENTRE_CARTAS;
        }
        pnl.repaint();
    }

    public String getGrupos() {
        String resultado = "No se encontraron grupos";

        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }
        boolean hayGrupos = false;
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                hayGrupos = true;
                break;
            }
        }
        if (hayGrupos) {
            resultado = "Se encontraron los siguientes grupos:\n";
            int indice = 0;
            for (int contador : contadores) {
                if (contador >= 2) {
                    resultado += Grupo.values()[contador].toString() + " de " + NombreCarta.values()[indice].toString()
                            + "\n";
                }
                indice++;
            }
        }

        
       
        return resultado;
    }

    private int valorCarta(Carta c) {

        NombreCarta nombre = c.getNombre();

        switch (nombre) {
        case AS:
        case JACK:
        case QUEEN:
        case KING:
            return 10;
        default:
            return nombre.ordinal() + 1;
        }
    }
   
    public ArrayList<ArrayList<Carta>> obtenerEscaleras() {

        ArrayList<ArrayList<Carta>> escaleras = new ArrayList<>();

        for (Pinta pinta : Pinta.values()) {

            ArrayList<Carta> mismasPintas = new ArrayList<>();

            for (Carta c : cartas) {
                if (c.getPinta() == pinta) {
                    mismasPintas.add(c);
                }
            }

        
            mismasPintas.sort((a, b) ->
                Integer.compare(
                    a.getNombre().ordinal(),
                    b.getNombre().ordinal()
                )
            );

            ArrayList<Carta> secuenciaActual = new ArrayList<>();

            for (int i = 0; i < mismasPintas.size(); i++) {

                if (secuenciaActual.isEmpty()) {
                    secuenciaActual.add(mismasPintas.get(i));
                    continue;
                }

                Carta anterior = secuenciaActual.get(secuenciaActual.size() - 1);
                Carta actual = mismasPintas.get(i);

                if (actual.getNombre().ordinal()
                        == anterior.getNombre().ordinal() + 1) {

                    secuenciaActual.add(actual);

                } else {

                    if (secuenciaActual.size() >= 3) {
                        escaleras.add(new ArrayList<>(secuenciaActual));
                    }

                    secuenciaActual.clear();
                    secuenciaActual.add(actual);
                }
            }

        
            if (secuenciaActual.size() >= 3) {
                escaleras.add(secuenciaActual);
            }
        }

     return escaleras;
    }

    public int calcularPuntaje() {

        boolean[] usada = cartasEnEscalera();

       
        int[] contadores = new int[NombreCarta.values().length];

        for (Carta carta : cartas)
        {
            contadores[carta.getNombre().ordinal()]++;
        }

        for (int i = 0; i < cartas.length; i++) 
        {

            if (!usada[i]) 
            {
                NombreCarta nombre = cartas[i].getNombre();

                if (contadores[nombre.ordinal()] >= 2) {
                    usada[i] = true;
                }
            }
        }

        int puntaje = 0;

        for (int i = 0; i < cartas.length; i++) 
        {
            if (!usada[i]) {
                puntaje += valorCarta(cartas[i]);
            }
        }

        return puntaje;
    }
    
}

