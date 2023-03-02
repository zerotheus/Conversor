public class Moeda {
    private String code;
    private float quantidade;

    public void setCode(int index) {
        final String code[] = { "BRL", "USD", "EUR", "GBP", "ARS", "CLP" };
        this.code = code[index];
    }

    public String getCode() {
        return code;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public void setMoeda(int indexSelecionado, Float quantidade) {
        final String code[] = { "BRL", "USD", "EUR", "GBP", "ARS", "CLP" };
        this.code = code[indexSelecionado];
        this.quantidade = quantidade;
    }

}
