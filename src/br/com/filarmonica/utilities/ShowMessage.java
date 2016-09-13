package br.com.filarmonica.utilities;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ShowMessage extends JOptionPane{

    public static void msgError(String msg) {
        showMessageDialog(null, msg, "Erro", ERROR_MESSAGE);
    }
    
    public static void msgInfo(String msg) {
        showMessageDialog(null, msg, "Informacional", INFORMATION_MESSAGE);
    }
    
    public static boolean question(String msg) {
        int response = showConfirmDialog(null, msg, "Leia com atenção", YES_NO_OPTION);
        return response == YES_OPTION;
    }

    public static void msgSuccess(String msg) {
        Icon image = new ImageIcon(ShowMessage.class.getResource("/br/com/filarmonica/images/success.png"));
        showMessageDialog(null, msg, "Sucesso", INFORMATION_MESSAGE, image);
    }

    public static void msgWarning(String msg) {
        showMessageDialog(null, msg, "Atenção", WARNING_MESSAGE);
    }
}