import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Jugador 

{

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
            ));
            

        
                for (int i = 0; i < mismasPintas.size(); i++) {

                 ArrayList<Carta> secuencia = new ArrayList<>();
                    secuencia.add(mismasPintas.get(i));

                    for (int j = i + 1; j < mismasPintas.size(); j++) {

                        Carta anterior = secuencia.get(secuencia.size() - 1);
                        Carta actual = mismasPintas.get(j);

                     if (actual.getNombre().ordinal()
                        == anterior.getNombre().ordinal() + 1) {

                        secuencia.add(actual);

                        } else if (actual.getNombre().ordinal()
                        > anterior.getNombre().ordinal() + 1) {
                        break;
                     }
                        }

                        if (secuencia.size() >= 3) {
                     escaleras.add(secuencia);
                    }
                 }
         }

     return escaleras;
    }   
}
