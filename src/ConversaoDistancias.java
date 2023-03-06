public class ConversaoDistancias {
    // nomedasUnidadesdeMedidasdeDistancias[] = { "Metros", "Quilômetros",
    // "Milimetros", "Milhas", "Jardas",
    // "polegadas" };
    public float converte(int indexIn, int indexOut, Float valor) {
        final float valoremMetros, conversao;
        valoremMetros = tudoParaMetros(indexIn, valor);
        if (indexOut == 0) {
            return valoremMetros;
        }
        conversao = metrosParaTudo(indexOut, valoremMetros);

        return conversao;
    }

    public float metrosParaKm(float valor) {
        return valor / 1000;
    }

    public float metrosParamm(float valor) {
        return valor * 1000;
    }

    public float metrosParaMi(float valor) {
        return valor / 1609;
    }

    public float metrosParaJardas(float valor) {
        return valor * (float) 1.904;
    }

    public float metrosParaPolegas(float valor) {
        return valor * (float) 39.37;
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
                return (float) 1609 * valor;
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

    public float metrosParaTudo(int indexOut, float valor) {
        switch (indexOut) {
            case 1: {
                return metrosParaKm(valor);
            }
            case 2: {
                return metrosParamm(valor);
            }
            case 3: {
                return metrosParaMi(valor);
            }
            case 4: {
                return metrosParaJardas(valor);
            }
            case 5: {
                return metrosParaPolegas(valor);
            }
        }
        return valor;
    }
}
