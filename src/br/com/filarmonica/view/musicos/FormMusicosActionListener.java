package br.com.filarmonica.view.musicos;

import br.com.filarmonica.models.Musico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Marcelo Augusto
 */
public class FormMusicosActionListener implements ActionListener {

    FormMusicos formMusicos;
    
    public FormMusicosActionListener(FormMusicos formMusicos) {
        this.formMusicos = formMusicos;
        addListener();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        switch(event.getActionCommand()) {
            case "Adicionar":
                System.out.println(formToModel());
                
        }
    }
    
    public void addListener() {
        formMusicos.getButtonAdd().addActionListener(this);
    }
    
    public Musico formToModel() {
        Musico musico = new Musico();
        if(!"".equals(formMusicos.getTextFieldId().getText())) {
            musico.setId(Integer.parseInt(formMusicos.getTextFieldId().getText()));
        }
        musico.setNome(formMusicos.getTextFieldNome().getText());
        musico.setApelido(formMusicos.getTextFieldApelido().getText());
        musico.setTelefone(formMusicos.getTextFieldTelefone().getText());
        musico.setInstrumento(formMusicos.getComboInstrumentos().getSelectedItem().toString());
        musico.setAnoIngresso(Integer.parseInt(formMusicos.getTextFieldAnoIngresso().getText()));
        return musico;
    }
    
}
