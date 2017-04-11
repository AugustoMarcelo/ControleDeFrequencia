package br.com.filarmonica.view.financeiro;

import br.com.filarmonica.models.Pagamento;
import br.com.filarmonica.services.PagamentoService;
import br.com.filarmonica.utilities.ShowMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Marcelo Augusto
 */
public class FormConfirmacaoPagamentoActionListener implements ActionListener {

    FormConfirmacaoPagamento formConfirmacao;
    PagamentoService service;
    
    public FormConfirmacaoPagamentoActionListener(FormConfirmacaoPagamento f) {
        this.formConfirmacao = f;
        service = new PagamentoService();
        addListener();
    }
    
    public void addListener() {
        formConfirmacao.getButtonConfirmacao().addActionListener(this);
        formConfirmacao.getButtonCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        switch(event.getActionCommand()) {
            case "Confirmar":
                if(debitar()) {
                    ShowMessage.msgSuccess("Débito realizado com sucesso!");
                    this.formConfirmacao.dispose();
                }
                break;
            case "Cancelar":
                this.formConfirmacao.dispose();
                break;
        }
    }
    
    private boolean debitar() {
        return service.save(formToPagamento());
    }
    
    private Pagamento formToPagamento() {
        Pagamento p = new Pagamento();
        if (!"".equals(formConfirmacao.getTextFieldIdMusico().getText())) {
            p.setMusico(service.searchMusico(Integer.parseInt(formConfirmacao.getTextFieldIdMusico().getText())));
        }
        p.setValor(Double.parseDouble(formConfirmacao.getTextFieldValor().getText()));
        p.setData(formConfirmacao.getDataChooserData().getDate());
        return p;
    }
}
