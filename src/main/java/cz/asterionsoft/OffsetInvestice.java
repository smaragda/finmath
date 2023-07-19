package cz.asterionsoft;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
class OffsetInvestice {
	private final Uver uver;
	private final Investice investice;
	
	public OffsetInvestice(Uver uver, Investice investice) {
		this.uver = uver;
		this.investice = investice;
	}
	
	public double zisk() {
		return investice.getAktualniVyse() - uver.getAktualniVyse() - uver.getZaplacenoCelkem();
	}
	
	public void mesic() {
		uver.mesic();
		investice.mesic();
	}
	
	@Override
	public String toString() {
		return "OffsetInvestice { "
				+ "zisk (investice+uver celkem) = " + zisk()
				+ ", uver=" + uver
				+ ", investice=" + investice +
				'}';
	}
	
	public String csvHeader() {
		return "aktualni_mesic;rozdil_inv_uver;investice_aktualni_vyse;investice_zisk;"
				+ "uver_akutalni_vyse;uver_zaplaceno_celkem;uver_zaplaceno_urok;uver_zaplacena_jistina;urok_celkem;"
				+ "zisk_procent";
	}
	
	public String csvRow() {
		return investice.getAktualniMesic() + ";"
				+ this.zisk() + ";"
				+ investice.getAktualniVyse() + ";"
				+ investice.getZisk() + ";"
				+ uver.getAktualniVyse() + ";"
				+ uver.getZaplacenoCelkem() + ";"
				+ uver.getZaplacenoUrok() + ";"
				+ uver.getZaplacenaJistina() + ";"
				+ uver.getUrokCelkem() + ";"
				+ ziskProcent();
	}
	
	public double ziskProcent() {
		return zisk() / uver.getVyse() * 100;
	}
	
	/**
	 * pokud zisk z investice se vyrovna urokum z uveru, tak je to break even
	 *
	 * @param offsetInvestice - uver a investice
	 * @return true pokud je break even
	 */
	boolean isBreakEven(OffsetInvestice offsetInvestice) {
		return offsetInvestice.investice.getZisk() - offsetInvestice.uver.getZaplacenoUrok() >= 0;
	}
	
	boolean isBreakEven2(OffsetInvestice offsetInvestice) {
		return zisk() >= 0;
	}
	
}
