package br.com.filarmonica.view.frequencia;

import br.com.filarmonica.constants.Constantes;
import br.com.filarmonica.models.Frequencia;
import br.com.filarmonica.models.Musico;
import br.com.filarmonica.models.Tocata;
import br.com.filarmonica.services.FrequenciaService;
import br.com.filarmonica.services.MusicoService;
import br.com.filarmonica.services.TocataService;
import br.com.filarmonica.utilities.AlignCell;
import br.com.filarmonica.utilities.ShowMessage;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public final class FormFrequenciaActionListener implements ActionListener, ItemListener, ListSelectionListener {

    private final FormFrequencia formFrequencia;
    private final MusicoService musicoService;
    private final TocataService tocataService;
    private final FrequenciaService frequenciaService;
    private FrequenciaTableModel tableModel;
    private Map<Integer, Musico> mapMusicos;
    private Map<Integer, Tocata> mapTocatas;
    private List<Frequencia> frequencias;

    /**
     * CONSTRUTOR DA CLASSE INICIALIZANDO OS OBJETOS
     *
     * @param formFrequencia
     */
    public FormFrequenciaActionListener(FormFrequencia formFrequencia) {
        this.formFrequencia = formFrequencia;
        this.formFrequencia.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
        musicoService = new MusicoService();
        tocataService = new TocataService();
        frequenciaService = new FrequenciaService();
        mapMusicos = new HashMap<>();
        mapTocatas = new HashMap<>();
        frequencias = new ArrayList<>();
        addListener();
        startComboMusicos();
        startComboTocatas();
        startTable();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "AddTo":
                addToList();
                break;
            case "Cancelar":
                enableToEdit(true);
                formFrequencia.getTableFrequencia().clearSelection();
                break;
            case "Deletar":
                if (ShowMessage.questionDialog("Deseja apagar a lista de frequência desta tocata?")) {
                    if (delete()) {
                        startTable();
                        clearList();
                        clearFields();
                        enableToSave(true);
                        releaseCombo(true);
                        startComboMusicos();
                        ShowMessage.msgSuccess("Lista de frequência deletada.");
                    }
                }
                break;
            case "Editar":
                if (edit()) {
                    startTable(frequencias);
                    releaseCombo(true);
                    ShowMessage.msgSuccess("Frequência atualizada");
                }
                break;
            case "Finalizar":
                if (checkDataToSave()) {
                    if (saveAll()) {
                        clearFields();
                        clearList();
                        releaseCombo(true);
                        ShowMessage.msgSuccess("Lista de frequência salva.");
                    } else {
                        ShowMessage.msgError("Erro ao inserir lista.");
                    }
                }
                break;
            case "Liberar":
                releaseCombo(true);
                break;
            case "PDF":
                gerarPdf();
                break;
            case "Remove":
                if (ShowMessage.question("Deseja remover este componente?")) {
                    if (removeComponent()) {
                        startTable(frequencias);
                    }
                }
                break;
        }
    }

    /**
     * MÉTODO UTILIZADO PARA ADICIONAR OS LISTENERS AOS COMPONENTES DO FORM
     */
    public void addListener() {
        formFrequencia.getButtonFinalizar().addActionListener(this);
        formFrequencia.getButtonAddTo().addActionListener(this);
        formFrequencia.getButtonEditar().addActionListener(this);
        formFrequencia.getButtonCancelar().addActionListener(this);
        formFrequencia.getButtonDelete().addActionListener(this);
        formFrequencia.getButtonRemove().addActionListener(this);
        formFrequencia.getButtonReleaseCombo().addActionListener(this);
        formFrequencia.getButtonExportToPDF().addActionListener(this);
        formFrequencia.getComboTocatas().addItemListener(this);
        formFrequencia.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if (!frequencias.isEmpty()) {
//                    if (ShowMessage.question("Você tem dados ainda não salvos. Deseja fechar sem salvá-los?")) {
//
//                    }
                    e.getInternalFrame().dispose();
                }
                e.getInternalFrame().dispose();
            }
        });
    }

    /**
     * MÉTODO UTILIZADO PARA ADICIONAR UM MÚSICO SELECIONADO NO COMBOBOX AO
     * JTABLE
     */
    public void addToList() {
        if (checkInputFields()) {
            Frequencia f = new Frequencia();

            String[] dados = catchComboSelectedValue(formFrequencia.getComboTocatas().getSelectedItem().toString());
            String local = dados[0];
            String data = dados[1];

            for (Integer key : mapTocatas.keySet()) {
                Tocata tocata = mapTocatas.get(key);
                if (tocata.getLocal().equals(local) && formatter(tocata.getData()).equals(data)) {
                    tocata.setId(key);
                    f.setTocata(tocata);
                }
            }

            dados = catchComboSelectedValue(formFrequencia.getComboMusicos().getSelectedItem().toString());
            String nome = dados[0];
            String instrumento = dados[1];

            for (Integer key : mapMusicos.keySet()) {
                Musico musico = mapMusicos.get(key);
                if (musico.getNome().equals(nome) && musico.getInstrumento().equals(instrumento)) {
                    musico.setId(key);
                    f.setMusico(musico);
                }
            }

            boolean presenca = formFrequencia.getRadioButtonPresenca().isSelected();
            f.setPresenca(presenca);

            //VERIFICAÇÃO DESNECESSÁRIA, JÁ QUE OS OBJETOS SÃO REMOVIDOS DO COMBO APÓS SEREM ADICIONADOS AO JTABLE
            if (!frequencias.contains(f)) {
                formFrequencia.getComboMusicos().removeItemAt(formFrequencia.getComboMusicos().getSelectedIndex());
                frequencias.add(f);
                startTable(frequencias);
                calcValueFaults(frequencias);
            } else {
                ShowMessage.msgWarning("O músico selecionado já está na lista de frequência!");
            }
        } else {
            ShowMessage.msgWarning("Preencha todos os campos obrigatórios");
        }
    }

    /**
     * MÉTODO UTILIZADO PARA CAPTURAR A OPÇÃO SELECIONADA EM UM COMBOBOX,
     * RETIRAR OS CARACTERES DESNECESSÁRIOS (",", "(", ")") E RETORNAR UM VETOR
     * COM AS INFORMAÇÕES NECESSÁRIAS
     *
     * @param value
     * @return String[]
     */
    public String[] catchComboSelectedValue(String value) {
        String stringSplitted = Arrays.toString(value.split("\\("));
        String dados[] = stringSplitted.split(",");
        String dataAtLeft = removeCharacters(dados[0]);
        String dataAtRight = removeCharacters(dados[1]);
        String result[] = {dataAtLeft, dataAtRight};
        return result;
    }

    /**
     * MÉTODO QUE CHECA SE O USUÁRIO PREENCHEU OS COMBOBOX'S E MARCOU UMA DAS
     * OPÇÕES DO RadioButton
     *
     * @return boolean
     */
    public boolean checkInputFields() {
        return !(formFrequencia.getComboTocatas().getSelectedIndex() == 0) && !(formFrequencia.getComboMusicos().getSelectedIndex() == 0) && (formFrequencia.getRadioButtonFalta().isSelected() || formFrequencia.getRadioButtonPresenca().isSelected());
    }

    /**
     * MÉTODO QUE CHECA SE O USUÁRIO ADICIONOU ALGUM COMPONENTE A LISTA DE
     * FREQUÊNCIA A SER SALVA
     *
     * @return boolean
     */
    public boolean checkDataToSave() {
        if (frequencias.isEmpty()) {
            ShowMessage.msgWarning("Lista de frequência vazia!");
            return false;
        }
        return true;
    }

    /**
     * MÉTODO QUE CALCULA O VALOR TOTAL DE TODAS AS FALTAS DA FREQUÊNCIA
     *
     * @param frequencias
     */
    public void calcValueFaults(List<Frequencia> frequencias) {
        int value = 0;
        if (!frequencias.isEmpty() || !(frequencias == null)) {
            for (Frequencia f : frequencias) {
                if (!f.isPresenca()) {
                    value += 10;
                }
            }
        }
        formFrequencia.getLabelValueFaults().setText(String.format("R$ %d,00", value));
    }

    /**
     * MÉTODO UTILIZADO PARA LIMPAR TODOS OS CAMPOS APÓS UMA AÇÃO TER SIDO
     * REALIZADA
     */
    public void clearFields() {
        formFrequencia.getComboTocatas().setSelectedIndex(0);
        formFrequencia.getComboMusicos().setSelectedIndex(0);
        formFrequencia.getTableFrequencia().setModel(new FrequenciaTableModel());
        formFrequencia.getLabelValueFaults().setText("R$ 0,00");
    }

    /**
     * MÉTODO UTILIZADO PARA LIMPAR A LISTA DO JTABLE
     */
    public void clearList() {
        frequencias.clear();
    }

    /**
     * MÉTODO UTILIZADO PARA EDITAR UMA LISTA DE FREQUÊNCIA PREVIAMENTE
     * CADASTRADA
     *
     * @return boolean
     */
    public boolean edit() {
        return frequenciaService.update(frequencias);
    }

    /**
     * MÉTODO UTILIZADO PARA DELETAR TODA UMA LISTA DE FREQUÊNCIAS
     *
     * @return boolean
     */
    public boolean delete() {
        return frequenciaService.delete(frequencias);
    }

    /**
     * MÉTODO UTILIZADO PARA PREENCHER O COMBOBOX DE MÚSICOS RECUPERADOS DO
     * BANDO DE DADOS
     */
    public void startComboMusicos() {
        List<Musico> musicos = musicoService.listMusicos();
        formFrequencia.getComboMusicos().removeAllItems();
        formFrequencia.getComboMusicos().addItem("Selecione...");
        for (Musico m : musicos) {
            formFrequencia.getComboMusicos().addItem(m.getNome() + " (" + m.getInstrumento() + ")");
            mapMusicos.put(m.getId(), new Musico(m.getNome(), m.getApelido(), m.getTelefone(), m.getEmail(), m.getInstrumento(), m.getAnoIngresso()));
        }
        formFrequencia.getComboMusicos().setSelectedIndex(0);
    }

    /**
     * MÉTODO UTILIZADO PARA PREENCHER O COMBOBOX COM UMA LISTA DE MÚSICOS
     * ESPECÍFICA
     *
     * @param musicos
     */
    public void startComboMusicos(List<Musico> musicos) {
        formFrequencia.getComboMusicos().removeAllItems();
        formFrequencia.getComboMusicos().addItem("Selecione...");
        for (Musico m : musicos) {
            formFrequencia.getComboMusicos().addItem(m.getNome() + " (" + m.getInstrumento() + ")");
            mapMusicos.put(m.getId(), new Musico(m.getNome(), m.getApelido(), m.getTelefone(), m.getEmail(), m.getInstrumento(), m.getAnoIngresso()));
        }
        formFrequencia.getComboMusicos().setSelectedIndex(0);
    }

    /**
     * MÉTODO UTILIZADO PARA PREENCHER O COMBOBOX DAS TOCATAS RECUPERADAS DO
     * BANCO DE DADOS
     */
    public void startComboTocatas() {
        List<Tocata> tocatas = tocataService.listTocatas();
        formFrequencia.getComboTocatas().removeAllItems();
        formFrequencia.getComboTocatas().addItem("Selecione...");
        for (Tocata t : tocatas) {
            formFrequencia.getComboTocatas().addItem(t.getLocal() + " (" + formatter(t.getData()) + ")");
            mapTocatas.put(t.getId(), new Tocata(t.getComentarios(), t.getData(), t.getHorario(), t.getLocal()));
        }
        formFrequencia.getComboTocatas().setSelectedIndex(0);
    }

    /**
     * MÉTODO UTILIZADO PARA FORMATAR UMA DATA E RETORNÁ-LA EM FORMATO STRING
     * (dd/mm/aaaa)
     *
     * @param data
     * @return String
     */
    public String formatter(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constantes.FormatDate.getValor());
        return sdf.format(data);
    }

    private Tocata searchAndReturnTocataInMap(String local, String data) {
        Tocata tocata;
        for (Integer key : mapTocatas.keySet()) {
            Tocata t = mapTocatas.get(key);
            if (t.getLocal().equals(local) && formatter(t.getData()).equals(data)) {
                tocata = mapTocatas.get(key);
                tocata.setId(key);
                return tocata;
            }
        }
        return null;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            if (formFrequencia.getComboTocatas().getSelectedIndex() != 0) {
                //LÓGICA PARA RECUPERAR A FREQUÊNCIA DE ACORDO COM A TOCATA SELECIONADA NO COMBO
                String[] dados = catchComboSelectedValue(formFrequencia.getComboTocatas().getSelectedItem().toString());
                String local = dados[0];
                String data = dados[1];
                Tocata tocata;
                tocata = searchAndReturnTocataInMap(local, data);
                frequencias = frequenciaService.listFrequencias(tocata);
                List<Musico> musicos = frequenciaService.listMusicosToTocata(tocata);
                if (!frequencias.isEmpty()) {
                    enableToEdit(true);
                    startTable(frequencias);
                    startComboMusicos(musicos);
                    calcValueFaults(frequencias);
                } else {
                    enableToSave(true);
                    startTable();
                    startComboMusicos();
                    calcValueFaults(frequencias);

                }
                formFrequencia.getComboTocatas().setEnabled(false);
                releaseCombo(false);
            } else {
                clearList();
                enableToSave(true);
                startTable();
                startComboMusicos();
                calcValueFaults(frequencias);
            }
        }
    }

    /**
     * MÉTODO UTILIZADO PARA SALVAR UMA LISTA DE FREQUÊNCIA
     *
     * @return boolean
     */
    public boolean saveAll() {
        return frequenciaService.insert(frequencias);
    }

    /**
     * MÉTODO UTILIZADO PARA ATIVAR SOMENTE O BOTÃO DE SALVAR LISTA E
     * DESABILITAR OS DEMAIS
     *
     * @param valor
     */
    public void enableToSave(boolean valor) {
        formFrequencia.getButtonFinalizar().setEnabled(valor);
        formFrequencia.getButtonDelete().setEnabled(!valor);
        formFrequencia.getButtonEditar().setEnabled(!valor);
        formFrequencia.getButtonExportToPDF().setEnabled(!valor);
        formFrequencia.getButtonRemove().setEnabled(!valor);
        formFrequencia.getButtonCancelar().setEnabled(!valor);
    }
    
    public void enableToEdit(boolean valor) {
        formFrequencia.getButtonFinalizar().setEnabled(!valor);
        formFrequencia.getButtonDelete().setEnabled(valor);
        formFrequencia.getButtonEditar().setEnabled(valor);
        formFrequencia.getButtonExportToPDF().setEnabled(valor);
        formFrequencia.getButtonRemove().setEnabled(!valor);
        formFrequencia.getButtonCancelar().setEnabled(!valor);
    }
    
    /**
     * MÉTODO UTILIZADO PARA HABILITAR O BOTÃO QUE REMOVE UM OBJETO DO JTABLE
     * @param valor
     */
    public void enableToRemove(boolean valor) {
        formFrequencia.getButtonFinalizar().setEnabled(!valor);
        formFrequencia.getButtonDelete().setEnabled(!valor);
        formFrequencia.getButtonEditar().setEnabled(!valor);
        formFrequencia.getButtonExportToPDF().setEnabled(!valor);
        formFrequencia.getButtonRemove().setEnabled(valor);
        formFrequencia.getButtonCancelar().setEnabled(valor);
    }

    /**
     * MÉTODO UTILIZADO PARA INICIALIZAR E CUSTOMIZAR O JTABLE
     */
    public void startTable() {
        tableModel = new FrequenciaTableModel();
        formFrequencia.getTableFrequencia().setModel(tableModel);
        formFrequencia.getTableFrequencia().getSelectionModel().addListSelectionListener(this);
        formFrequencia.getTableFrequencia().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        AlignCell.center(formFrequencia.getTableFrequencia().getColumnModel().getColumn(0));
        AlignCell.center(formFrequencia.getTableFrequencia().getColumnModel().getColumn(1));
    }

    /**
     * MÉTODO UTILIZADO PARA INICIALIZAR, PREENCHER E CUSTOMIZAR O JTABLE
     *
     * @param frequencias
     */
    public void startTable(List<Frequencia> frequencias) {
        tableModel = new FrequenciaTableModel(frequencias);
        formFrequencia.getTableFrequencia().setModel(tableModel);
        formFrequencia.getTableFrequencia().getSelectionModel().addListSelectionListener(this);
        formFrequencia.getTableFrequencia().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        AlignCell.center(formFrequencia.getTableFrequencia().getColumnModel().getColumn(0));
        AlignCell.center(formFrequencia.getTableFrequencia().getColumnModel().getColumn(1));
    }

    /**
     * MÉTODO UTILIZADO PARA BLOQUEAR/DESBLOQUEAR O COMBOBOX DAS TOCATAS APÓS A
     * SELEÇÃO DE UM ITEM
     *
     * @param valor
     */
    public void releaseCombo(boolean valor) {
        formFrequencia.getComboTocatas().setEnabled(valor);
        formFrequencia.getButtonReleaseCombo().setEnabled(!valor);
    }

    private String removeCharacters(String var) {
        String aux = var.replaceAll("^\\s+", "");
        aux = aux.replaceAll("\\s+$", "");
        if (var.contains("[")) {
            aux = aux.replaceAll("\\[", "");
        }
        if (var.contains("]")) {
            aux = aux.replaceAll("\\]", "");
        }
        if (var.contains(")")) {
            aux = aux.replaceAll("\\)", "");
        }
        return aux;
    }

    /**
     * MÉTODO UTILIZADO PARA REMOVER UMA LINHA ESPECÍFICA DO JTABLE
     *
     * @return boolean
     */
    public boolean removeComponent() {
        if (formFrequencia.getTableFrequencia().getSelectedRow() >= 0) {
            for (Frequencia f : tableModel.getFrequencias()) {
                if (f == tableModel.getFrequencias().get(formFrequencia.getTableFrequencia().getSelectedRow())) {
                    boolean ok = frequenciaService.delete(f);
                    String[] dados = catchComboSelectedValue(formFrequencia.getComboTocatas().getSelectedItem().toString());
                    Tocata t = searchAndReturnTocataInMap(dados[0], dados[1]);
                    frequencias = frequenciaService.listFrequencias(t);
                    startComboMusicos(frequenciaService.listMusicosToTocata(t));
                    startTable(frequencias);
                    calcValueFaults(frequencias);
                    return ok;
                }
            }
        }
        return false;
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if (formFrequencia.getTableFrequencia().getSelectedRow() >= 0) {
            enableToRemove(true);
        } else {
            if(!frequencias.isEmpty()) {
                enableToEdit(true);
            } else {
                enableToSave(true);
            }
        }
    }

    /**
     * MÉTODO UTILIZADO PARA GERAR O PDF DE ACORDO COM OS DADOS CONTIDOS NO
     * JTABLE
     */
    public void gerarPdf() {
        Document document = new Document();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione um local para salvar o arquivo");
        int userSelection = fileChooser.showSaveDialog(this.formFrequencia);

        Font fontBold = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font fontUnder = new Font(FontFamily.TIMES_ROMAN, 14, Font.UNDERLINE);
        Font fontNormal = new Font(FontFamily.TIMES_ROMAN, 14);
        Font fontItalic = new Font(FontFamily.TIMES_ROMAN, 14, Font.ITALIC);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String pathFile = fileToSave.getAbsolutePath();
            try {
                if (!pathFile.contains(".pdf")) {
                    pathFile = pathFile.concat(".pdf");
                }
                PdfWriter.getInstance(document, new FileOutputStream(pathFile));
                document.open();
                PdfPTable table = new PdfPTable(2);
                Paragraph paragraph = new Paragraph(formFrequencia.getComboTocatas().getSelectedItem().toString(), fontUnder);
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);
                document.add(new Paragraph(" "));

                PdfPCell cellHeader = new PdfPCell(new Phrase("COMPONENTES", fontBold));
                cellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellHeader.setVerticalAlignment(Element.ALIGN_CENTER);
                cellHeader.setBackgroundColor(BaseColor.GRAY);
                cellHeader.setUseAscender(true);

                table.addCell(cellHeader);
                cellHeader = new PdfPCell(new Phrase("CHAMADA", fontBold));
                cellHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellHeader.setBackgroundColor(BaseColor.GRAY);
                cellHeader.setUseAscender(true);
                table.addCell(cellHeader);

                String[] dados = catchComboSelectedValue(formFrequencia.getComboTocatas().getSelectedItem().toString());
                String local = removeCharacters(dados[0]);
                String data = removeCharacters(dados[1]);
                Tocata tocata = searchAndReturnTocataInMap(local, data);

                int valor = 0;
                for (Frequencia f : frequencias) {
                    PdfPCell cell = new PdfPCell(new Phrase(f.getMusico().getNome(), fontNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setUseAscender(true);
                    table.addCell(cell);

                    if (!(f.isPresenca())) {
                        fontItalic.setColor(BaseColor.RED);
                        valor += 10;
                    }
                    cell = new PdfPCell(new Phrase((f.isPresenca() ? "PRESENTE" : "FALTOU"), (f.isPresenca() ? fontNormal : fontItalic)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setUseAscender(true);
                    table.addCell(cell);
                }

                /*------- CÉLULAS FINAIS DA TABELA QUE MOSTRAM O VALOR TOTAL ----------*/
                fontBold.setColor(BaseColor.WHITE);
                PdfPCell cellFinal = new PdfPCell(new Phrase("VALOR TOTAL", fontBold));
                cellFinal.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellFinal.setBackgroundColor(BaseColor.GRAY);
                cellFinal.setUseAscender(true);
                table.addCell(cellFinal);

                cellFinal.setPhrase(new Phrase(String.format("R$ %d,00", valor), fontBold));
                cellFinal.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellFinal.setBackgroundColor(BaseColor.GRAY);
                cellFinal.setUseAscender(true);
                table.addCell(cellFinal);
                document.add(table);
            } catch (DocumentException | FileNotFoundException ex) {
                System.out.println("Erro: " + ex);
            } finally {
                document.close();
            }
            try {
                Desktop.getDesktop().open(new File(pathFile));
            } catch (IOException ex) {
                System.out.println("Erro: " + ex);
            }
        }
    }
}
