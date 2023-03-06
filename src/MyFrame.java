import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MyFrame {

    private int tipo = 0, contaPonto = 0, indexSelecionadoIn, indexSelecionadoOut;

    public void setZeroContaPonto() {
        this.contaPonto = 0;
    }

    public void meuFrame() {
        final String nomesdosTiposdasConversoes[] = { "Moeda", "Distâncias" },
                nomedasMoedas[] = { "Reais", "Dólar", "Euro", "Libras Esterlinas", "Peso Argentino", "Peso Chileno" },
                nomedasUnidadesdeMedidasdeDistancias[] = { "Metros", "Quilômetros", "Milimetros", "Milhas", "Jardas",
                        "polegadas" };

        // componente JFrame
        JFrame myFrame = new JFrame("Conversor - Java Swing");
        myFrame.setSize(800, 500);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // componente JPanel
        JPanel myJPanel = new JPanel();
        myJPanel.setSize(800, 500);
        myJPanel.setBackground(Color.decode("#5472AE"));
        // usamos este desenho para centralizar os componentes do JPanel
        myJPanel.setLayout(new GridBagLayout());

        // Posiconamento da selecao do tipo da conversao
        JLabel textoIndicadordoSeletorTipoConversao = new JLabel();
        textoIndicadordoSeletorTipoConversao.setText("Tipo da conversão:  ");
        textoIndicadordoSeletorTipoConversao.setForeground(Color.decode("#D3D3D3"));
        GridBagConstraints textoIndicConstraints = new GridBagConstraints();
        textoIndicConstraints.insets = new Insets(10, 0, 0, 0);
        JComboBox<String> seletordeTipoConversao = new JComboBox<String>(nomesdosTiposdasConversoes);
        GridBagConstraints seletorDetipoConversaoConstraints = new GridBagConstraints();
        seletorDetipoConversaoConstraints.insets = new Insets(10, 0, 0, 0);

        seletordeTipoConversao.addActionListener(seletordeTipoConversao);

        JComboBox<String> seletordeMoedasUm = new JComboBox<String>(nomedasMoedas);
        seletordeMoedasUm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexSelecionadoIn = seletordeMoedasUm.getSelectedIndex();
                System.out.println("moeda IndexIn " + indexSelecionadoIn);
            }
        });

        JComboBox<String> seletordeMoedasDois = new JComboBox<String>(nomedasMoedas);
        seletordeMoedasDois.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indexSelecionadoOut = seletordeMoedasDois.getSelectedIndex();
                System.out.println("moeda IndexOut " + indexSelecionadoOut);
            }
        });

        // componente JTextField Texto que fica ao lado da caixa de input do valor
        JLabel textoReferenteaoTipodeInput = new JLabel();
        textoReferenteaoTipodeInput.setForeground(Color.decode("#D3D3D3"));
        textoReferenteaoTipodeInput.setText(seletordeTipoConversao.getSelectedItem() + "");

        JLabel textoReferenteaoTipodeInputDois = new JLabel();
        textoReferenteaoTipodeInputDois.setForeground(Color.decode("#D3D3D3"));
        textoReferenteaoTipodeInputDois.setText(seletordeTipoConversao.getSelectedItem() + "");

        // Componente de texto para input
        JTextArea inputdosValores = new JTextArea(1, 20);

        JTextArea outputdosValores = new JTextArea(1, 20);
        outputdosValores.setEditable(false);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setLayout(new SpringLayout());

        // ação de contexto movida para o seletor de tipo de conversao
        seletordeTipoConversao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                textoReferenteaoTipodeInput.setText(seletordeTipoConversao.getSelectedItem().toString());
                textoReferenteaoTipodeInputDois.setText(seletordeTipoConversao.getSelectedItem().toString());
                tipo = seletordeTipoConversao.getSelectedIndex();
                if (tipo == 0) {
                    seletordeMoedasUm.setModel(new DefaultComboBoxModel<String>(nomedasMoedas));
                    seletordeMoedasDois.setModel(new DefaultComboBoxModel<String>(nomedasMoedas));
                } else {
                    seletordeMoedasUm.setModel(new DefaultComboBoxModel<String>(nomedasUnidadesdeMedidasdeDistancias));
                    seletordeMoedasDois
                            .setModel(new DefaultComboBoxModel<String>(nomedasUnidadesdeMedidasdeDistancias));
                }
                System.out.println(tipo);
            }
        });

        // acao de contexto na confirmacao
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tipo = seletordeTipoConversao.getSelectedIndex();
                final String input = inputdosValores.getText();
                System.out.println(input);
                final int error = SaoNumeros(input, tipo);
                if (error != 0) {
                    return;
                }
                try {
                    float valorSaida = Float.parseFloat(inputdosValores.getText());
                    /*
                     * System.out.println("valor do confirma: " + valorSaida);
                     */
                    final Float resultado;
                    if (tipo == 0) {
                        if (indexSelecionadoIn != indexSelecionadoOut) {
                            ConversorMoedas Conversao = new ConversorMoedas();
                            resultado = Conversao.converte(indexSelecionadoIn, indexSelecionadoOut, valorSaida);
                            outputdosValores.setText(resultado.toString());
                        } else {
                            outputdosValores.setText(inputdosValores.getText());
                        }
                    } else {
                        ConversaoDistancias Conversao = new ConversaoDistancias();
                        resultado = Conversao.converte(indexSelecionadoIn, indexSelecionadoOut, valorSaida);
                        outputdosValores.setText(resultado.toString());
                    }
                    final int resposta = JOptionPane.showConfirmDialog(myJPanel, "Deseja continuar?",
                            "Continuar conversoes?",
                            1);
                    if (resposta == 0) {
                        inputdosValores.requestFocusInWindow();
                    } else if (resposta == 1) {
                        JOptionPane.showMessageDialog(myJPanel, "Programa encerrado", "Mensagem de saida", 0);
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(myJPanel, "Programa finalizado", "Mensagem de saida", 0);
                        System.exit(0);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Valor muito grande ou valor vazio");
                }
                setZeroContaPonto();
            }
        });

        // Restricoes e posicionamentos
        GridBagConstraints seletordeTipoConversaoConstraints = new GridBagConstraints();
        seletordeTipoConversaoConstraints.gridy = 0;
        seletordeTipoConversaoConstraints.weighty = 1;
        seletordeTipoConversaoConstraints.weightx = 3;
        seletordeTipoConversaoConstraints.fill = GridBagConstraints.HORIZONTAL;
        seletordeTipoConversaoConstraints.anchor = GridBagConstraints.FIRST_LINE_END;

        GridBagConstraints seletordeMoedasUmConstraints = new GridBagConstraints();
        seletordeMoedasUmConstraints.weightx = 1;
        seletordeMoedasUmConstraints.weighty = 2;
        seletordeMoedasUmConstraints.gridy = 2;

        GridBagConstraints seletordeMoedasDoisConstraints = new GridBagConstraints();
        seletordeMoedasDoisConstraints.weightx = 1;
        seletordeMoedasDoisConstraints.weighty = 2;
        seletordeMoedasDoisConstraints.gridy = 3;

        GridBagConstraints textoReferenteaoTipoConstraints = new GridBagConstraints();
        textoReferenteaoTipoConstraints.weightx = 1;
        textoReferenteaoTipoConstraints.weighty = 2;
        textoReferenteaoTipoConstraints.gridy = 2;

        GridBagConstraints textoReferenteaoTipoConstraintsDois = new GridBagConstraints();
        textoReferenteaoTipoConstraintsDois.weightx = 1;
        textoReferenteaoTipoConstraintsDois.weighty = 2;
        textoReferenteaoTipoConstraintsDois.gridy = 3;

        GridBagConstraints inputdosValoresConstraints = new GridBagConstraints();
        inputdosValoresConstraints.weightx = 1;
        inputdosValoresConstraints.weighty = 2;
        inputdosValoresConstraints.gridy = 2;

        GridBagConstraints outputdosValoresConstraints = new GridBagConstraints();
        outputdosValoresConstraints.weightx = 1;
        outputdosValoresConstraints.weighty = 2;
        outputdosValoresConstraints.gridy = 3;

        GridBagConstraints confirmbuttonConstraints = new GridBagConstraints();
        confirmbuttonConstraints.gridy = 4;
        confirmbuttonConstraints.fill = GridBagConstraints.HORIZONTAL;
        confirmbuttonConstraints.weightx = 5;
        confirmbuttonConstraints.weighty = 1;
        confirmbuttonConstraints.insets = new Insets(0, 4, 3, 0);
        confirmbuttonConstraints.anchor = GridBagConstraints.LAST_LINE_END;

        // conecta os componentes ao painel
        myJPanel.add(textoIndicadordoSeletorTipoConversao, textoIndicConstraints);
        myJPanel.add(seletordeTipoConversao, seletorDetipoConversaoConstraints);
        myJPanel.add(textoReferenteaoTipodeInput, textoReferenteaoTipoConstraints);
        myJPanel.add(seletordeMoedasUm, seletordeMoedasUmConstraints);
        myJPanel.add(textoReferenteaoTipodeInputDois, textoReferenteaoTipoConstraintsDois);
        myJPanel.add(seletordeMoedasDois, seletordeMoedasDoisConstraints);
        myJPanel.add(inputdosValores, inputdosValoresConstraints);
        myJPanel.add(outputdosValores, outputdosValoresConstraints);
        myJPanel.add(confirmButton, confirmbuttonConstraints);

        // conectar Jpanel a JFrame
        myFrame.add(myJPanel);

        // Faz o myFrame visivel
        myFrame.setVisible(true);
    }

    public int SaoNumeros(String input, int tipo) {

        final int fim = input.length();
        int contaDigitos = -1;
        char c;
        for (int i = 0; i < fim; i++) {
            c = input.charAt(i);
            System.out.println((int) c);
            if ((c <= 47 || c > 58) && c != '.') {
                JOptionPane.showMessageDialog(null, "Somente numeros e pontos sao aceitos");
                setZeroContaPonto();
                return 1;
            }
            if (c == '.') {
                contaPonto++;
                System.out.println("pontos" + contaPonto);
            }
            if (contaPonto > 1) {
                JOptionPane.showMessageDialog(null, "So é permitido o uso de 1 ponto");
                return 1;
            }
            if (contaPonto == 1) {
                contaDigitos++;
                if (contaDigitos > 2 && tipo == 0) {
                    JOptionPane.showMessageDialog(null, "A conversao de moedas aceita somente 2 digitos"
                            + "após o ponto");
                    System.out.println(contaDigitos);
                    setZeroContaPonto();
                    return 1;
                }
                if (contaDigitos > 3 && tipo == 1) {
                    JOptionPane.showMessageDialog(null, "A conversao de distancias aceita somente 3 digitos"
                            + "após o ponto");
                    System.out.println(contaDigitos);
                    setZeroContaPonto();
                    return 1;
                }
            }

        }
        return 0;
    }
}
