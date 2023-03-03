public class ConversaoDistancias {
    // nomedasUnidadesdeMedidasdeDistancias[] = { "Metros", "Quilômetros",
    // "Milimetros", "Milhas", "Jardas",
    // "polegadas" };
    public float converte(int indexIn, int indexOut, Float valor) {
        final float valoremMetros;
        valoremMetros = tudoParaMetros(indexIn, valor);

        return 0;
    }

    public float metrosParaKm(float valor) {
        return valor / 1000;
    }

    public float metrosParamm(float valor) {
        return valor * 1000;
    }

    public float metrosParaMi(float valor) {
        return valor;
    }

    public float metrosParaJardas(float valor) {
        return valor;
    }

    public float metrosParaPolegas(float valor) {
        return valor;
    }

    public float tudoParaMetros(int indexIn, Float valor) {
        switch (indexIn) {
            case 1: {
                return 1000 * valor;
            }
            case 2: {
                return valor / 1000;
            }
            case 3: {
                return (float) 1.609 * valor;
            }
            case 4: {
                return valor / (float) 1.094;
            }
            case 5: {
                return valor / (float) 39.37;
            }
        }
        return valor;
    }
}
