package br.com.filarmonica.utilities;

import br.com.filarmonica.constants.Constantes;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Now {

    private static String date_format;
    private static String hour_format;
    private static String date;
    private static String hour;
    
    public static String getDate() {
        return formatDate();
    }

    public static String getHour() {
        return formatHour();
    }
    
    private static String formatDate() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(Constantes.FormatDate.getValor());
        date = sdf.format(now);
        return date;
    }
    
    private static String formatHour() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(Constantes.FormatHour.getValor());
        hour = sdf.format(now);
        return hour;
    }
}
