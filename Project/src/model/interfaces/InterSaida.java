package model.interfaces;

import model.AtendimentoSaida;

public interface InterSaida {
	
	public void preencheDadosSaida(AtendimentoSaida listAtendimento);
	
	public void exluirAtendimentoSaida(Integer numAtendimento);
}
