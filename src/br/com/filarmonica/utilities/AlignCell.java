package br.com.filarmonica.utilities;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Marcelo Augusto
 */
public class AlignCell {
    
    private static DefaultTableCellRenderer defaultTableCellRenderer;
    
    /**
     * Método utilizado para centralizar as colunas de uma tabela
     * @param column A coluna a ser centralizada
     */
    public static void center(TableColumn column) {
        defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        column.setCellRenderer(defaultTableCellRenderer);
    }
    
}
