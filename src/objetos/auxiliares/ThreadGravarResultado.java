/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.auxiliares;

import arquivo.VerificaArquivo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import objetos.Candidato;
/**
 *
 * @author Anderson
 */
public class ThreadGravarResultado extends Thread
{
    private ProcessoVotacao processoVotacao; 
    private ObjetoCompartilhado oc;
    
    public ThreadGravarResultado(ObjetoCompartilhado oc, ProcessoVotacao processoVotacao)
    {
        this.oc = oc;
        this.processoVotacao = processoVotacao;
    }
    
    @Override
    public void run() 
    {
        ArrayList<Candidato> candidatos = processoVotacao.getCandidatos();
        int index = 0;                             //(100 * index) / size
        int porcentagem = 0;
        String caminho = new File("").getAbsolutePath()+"\\Arquivos\\Resultado\\"+processoVotacao.getEleicoes().get(0).getEleicao()+".txt";
        File arquivo = new File(caminho);
        if(!arquivo.exists())
        {
            try 
            {   arquivo.createNewFile(); } 
            catch (IOException ex) 
            {    System.err.print("Erro ao criar novo arquivo de resultado: "+ex.toString());  }
        }

        try 
        {
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();

            while(index < candidatos.size())
            {
                bw.write(VerificaArquivo.ABREPOLITICO);
                bw.newLine();

                bw.write("\t" + VerificaArquivo.ABRENOME + candidatos.get(index).getEleitor().getNome() + VerificaArquivo.FECHANOME);
                bw.newLine();
                bw.write("\t" + VerificaArquivo.ABRETITULO + candidatos.get(index).getEleitor().getTitulo() + VerificaArquivo.FECHATITULO);
                bw.newLine();
                bw.write("\t" + VerificaArquivo.ABRECARGO + candidatos.get(index).getCargo() + VerificaArquivo.FECHACARGO);
                bw.newLine();
                bw.write("\t" + VerificaArquivo.ABRENUMERO + candidatos.get(index).getNumero() + VerificaArquivo.FECHANUMERO);
                bw.newLine();
                bw.write("\t" + VerificaArquivo.ABREPARTIDO + candidatos.get(index).getPartido() + VerificaArquivo.FECHAPARTIDO);
                bw.newLine();
                if(candidatos.get(index).temVice())
                {
                    bw.write("\t" + VerificaArquivo.ABREVICE + candidatos.get(index).getNomeVice() + VerificaArquivo.FECHAVICE);
                    bw.newLine();
                    bw.write("\t" + VerificaArquivo.ABREPARTIDOVICE + candidatos.get(index).getPartidoVice() + VerificaArquivo.ABREPARTIDOVICE);
                    bw.newLine();
                }
                else
                {
                    bw.write("\t" + VerificaArquivo.ABREVICE + VerificaArquivo.FECHAVICE);
                    bw.newLine();
                    bw.write("\t" + VerificaArquivo.ABREPARTIDOVICE + VerificaArquivo.ABREPARTIDOVICE);
                    bw.newLine();
                }
                bw.write("\t" + VerificaArquivo.ABREVOTOS + candidatos.get(index).getVotos() + VerificaArquivo.FECHAVOTOS);
                bw.newLine();

                bw.write(VerificaArquivo.FECHAPOLITICO);
                bw.newLine();

                porcentagem = (100 * index) / candidatos.size();
                oc.setValor(porcentagem);
                index++;
                try {
                    Thread.sleep(10);
                 } catch (Exception e) {
                    e.printStackTrace();
                 }
            }
            oc.setValor(100);

            bw.close();
            fw.close();
        } 
        catch (IOException ex) 
        {   System.err.print("Erro ao gravar arquivo de resultado: "+ex.toString());  }
    }
}
