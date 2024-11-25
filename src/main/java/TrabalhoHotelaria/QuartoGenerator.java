package TrabalhoHotelaria;

import java.util.ArrayList;
import java.util.List;

public class QuartoGenerator {

    public static List<Quarto> gerarQuartos(int andares, int quartosPorAndar) {
        List<Quarto> listaQuartos = new ArrayList<>();
        String[] categorias = { "Luxo", "Econômico", "Executivo" }; // Categorias de quartos
        int categoriaIndex = 0;

        for (int andar = 1; andar <= andares; andar++) {
            for (int numeroQuarto = 1; numeroQuarto <= quartosPorAndar; numeroQuarto++) {
                String numero = andar + String.format("%02d", numeroQuarto); // Ex.: "101", "102"
                String categoria = categorias[categoriaIndex % categorias.length];
                listaQuartos.add(new Quarto(numero, categoria));
                categoriaIndex++; // Avança para a próxima categoria
            }
        }

        return listaQuartos;
    }
}
