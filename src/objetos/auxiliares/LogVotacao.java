/*
 * AQUI CONTÉM MÉTODO COM ARGUMENTOS VARIADOS
 * No método “adicionarRegistroVoto” da classe “LogVotacao” é utilizado uma lista 
 * de argumentos variados que serão utilizados para gravar o log de dados que foram computados na urna;
 */
package objetos.auxiliares;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Anderson
 */
public class LogVotacao 
{
    private final String SEPARADORDATA = "/";
    private final String SEPARADORHORA = ":";
    private ArrayList<String> dados;
    
    public LogVotacao()
    {
        dados = new ArrayList();
    }
    
    public void adicionarRegistroVoto(String... dados)
    {
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);
        int segundo = c.get(Calendar.SECOND);
        
        String data = dia+ SEPARADORDATA +mes+ SEPARADORDATA +ano;
        String horario = hora+ SEPARADORHORA +minuto+ SEPARADORHORA +segundo;
        String informacao = "\n";
        horario = "[" +data + " - " + horario + "]";
        
        informacao += horario + " -> {";
        for(String dado : dados)
            informacao += "[" + dado + "]";
        informacao += "}";
        
        this.dados.add(informacao);
    }
    
    
    public String getVotosRegistrados()
    {
        String log = "";
        for(int index = 0; index < dados.size(); index++)
            log += dados.get(index) + "\n";
        return log;
    }
}
