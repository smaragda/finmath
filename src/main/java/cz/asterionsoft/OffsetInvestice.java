package cz.asterionsoft;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class OffsetInvestice {
	private final Uver uver;
	private final Investice investice;
	
	public OffsetInvestice(Uver uver, Investice investice) {
		this.uver = uver;
		this.investice = investice;
	}
	
	public double zisk() {
		return investice.getAktualniVyse() - uver.getAktualniVyse() - uver.getZaplaceno();
	}
	
	public void rok() {
		uver.rok();
		investice.rok();
	}
	
	@Override
	public String toString() {
		return "OffsetInvestice{" +
				"uver=" + uver +
				", investice=" + investice +
				'}';
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
}
