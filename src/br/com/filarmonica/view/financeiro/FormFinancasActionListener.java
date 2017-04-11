package br.com.filarmonica.view.financeiro;

import br.com.filarmonica.models.Musico;
import br.com.filarmonica.models.Pagamento;
import br.com.filarmonica.services.MusicoService;
import br.com.filarmonica.services.PagamentoService;
import br.com.filarmonica.utilities.AlignCell;
import br.com.filarmonica.view.musicos.MusicosTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * CLASSE UTILIZADA PARA GERENCIAR OS LISTENERS DOS COMPONENTES DO FORM
 * @author Marcelo Augusto
 */
public class FormFinancasActionListener implements ActionListener, ListSelectionListener{
    
    private MusicoService musicosService;
    private PagamentoService pService;
    private MusicosTableModel musicosTableModel;
    private PagamentosTableModel pagamentosTableModel;
    private FormFinancas formFinancas;
    
    
    public FormFinancasActionListener(FormFinancas formFinancas) {
        this.formFinancas = formFinancas;
        musicosService = new MusicoService();
        pService = new PagamentoService();
        startTableMusicos();
        startTablePagamentos();
        addListener();
    }
    
    public void addListener() {
        formFinancas.getButtonAddPagamento().addActionListener(this);
        formFinancas.getTableMusicos().getSelectionModel().addListSelectionListener(this);
    }
    
    public void startTableMusicos() {
        List<Musico> musicos = musicosService.listMusicos();
        if(musicos != null) {
            musicosTableModel = new MusicosTableModel(musicos);
            formFinancas.getTableMusicos().setModel(musicosTableModel);
            formFinancas.getTableMusicos().getColumnModel().getColumn(0).setPreferredWidth(5);
            formFinancas.getTableMusicos().getColumnModel().getColumn(1).setPreferredWidth(210);
            formFinancas.getTableMusicos().getColumnModel().getColumn(2).setPreferredWidth(20);
            /*----------- ALINHANDO AS COLUNAS DE FORMA CENTRALIZADA ------------*/
            AlignCell.center(formFinancas.getTableMusicos().getColumnModel().getColumn(0));
            AlignCell.center(formFinancas.getTableMusicos().getColumnModel().getColumn(1));
            AlignCell.center(formFinancas.getTableMusicos().getColumnModel().getColumn(2));
        }
    }
    
    public void startTablePagamentos() {
        pagamentosTableModel = new PagamentosTableModel();
        formFinancas.getTableDebitos().setModel(pagamentosTableModel);
        AlignCell.center(formFinancas.getTableDebitos().getColumnModel().getColumn(0));
        AlignCell.center(formFinancas.getTableDebitos().getColumnModel().getColumn(1));
    }
    
    public void startTablePagamentos(List<Pagamento> pagamentos) {
        pagamentosTableModel = new PagamentosTableModel(pagamentos);
        formFinancas.getTableDebitos().setModel(pagamentosTableModel);
        AlignCell.center(formFinancas.getTableDebitos().getColumnModel().getColumn(0));
        AlignCell.center(formFinancas.getTableDebitos().getColumnModel().getColumn(1));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch(event.getActionCommand()) {
            case "Pagamento":
                FormConfirmacaoPagamento confirmacaoPagamento = new FormConfirmacaoPagamento();
                formFinancas.getDesktopPane().add(confirmacaoPagamento);
                Musico m = formToModel();
                confirmacaoPagamento.setFieldValues(m.getId(), m.getNome());
                confirmacaoPagamento.setVisible(true);
                confirmacaoPagamento.setCenterPosition();
                break;
            case "PDF":
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(formFinancas.getTableMusicos().getSelectedRow() >= 0) {
            enableButtons(true);
            Musico m = musicosTableModel.getMusicos().get(formFinancas.getTableMusicos().getSelectedRow());
            List<Pagamento> pagamentos = pService.listPagamentos(m.getId());
            for (Pagamento p : pagamentos) {
                p.setMusico(m);
            }
            startTablePagamentos(pagamentos);
            formFinancas.getPanelDividaMusico().setBorder(BorderFactory.createTitledBorder("À receber de "+m.getNome()));
        }
    }
    
    private void enableButtons(boolean valor) {
        formFinancas.getButtonAddPagamento().setEnabled(valor);
        formFinancas.getButtonGerarPDF().setEnabled(valor);
    }
    
    public Musico formToModel() {
        Musico m = new Musico();
        if(formFinancas.getTableMusicos().getSelectedRow() >= 0) {
            m = musicosTableModel.getMusicos().get(formFinancas.getTableMusicos().getSelectedRow());
        }
        return m;
    }
    
}
