package model;

import control.ObjectDAO;

public class MTEspecie extends ObjectDAO {
	
	Integer BDIDESPECIE;
	
	String BDNOMEESPECIE;

	public Integer getBDIDESPECIE() {
		return BDIDESPECIE;
	}

	public void setBDIDESPECIE(Integer bDIDESPECIE) {
		BDIDESPECIE = bDIDESPECIE;
	}

	public String getBDNOMEESPECIE() {
		return BDNOMEESPECIE;
	}

	public void setBDNOMEESPECIE(String bDNOMEESPECIE) {
		BDNOMEESPECIE = bDNOMEESPECIE;
	}

}
