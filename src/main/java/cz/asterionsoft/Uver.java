package cz.asterionsoft;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Uver {
	
	private final double vyse;
	private final int doba;
	private final double urokProcent;
	private double splatka;
	private double celkemBudeZaplaceno;
	private final double poplatekZaVedeni;
	
	// ------------- vypocet ----------------
	private double aktualniVyse;
	private int aktualniMesic; //konec mesice
	private double urokCelkem;
	private double zaplacenoCelkem;
	private double zaplacenoUrok;
	private double zaplacenaJistina;
	
	public Uver(double vyse, int dobaMesicu, double urokProcent, double poplatekZaVedeni) {
		this.vyse = vyse;
		this.doba = dobaMesicu;
		this.urokProcent = urokProcent;
		this.poplatekZaVedeni = poplatekZaVedeni;
		init();
	}
	
	void init() {
		aktualniVyse = vyse;
		splatka = splatka();
		celkemBudeZaplaceno = (splatka + poplatekZaVedeni) * doba;
		urokCelkem = celkemBudeZaplaceno - vyse; // vcetne poplatku za vedeni
	}
	
	void mesic() {
		if (++aktualniMesic > doba) {
			log.warn("Už jste splatil úvěr.");
			return;
		}
		double upravenaSplatka = splatka;
		double zbyvaDoplatit = aktualniVyse - splatka + poplatekZaVedeni;
		if (zbyvaDoplatit < splatka) {
			upravenaSplatka = zbyvaDoplatit;    // posledni splatka
		}
		double urok = aktualniVyse * urokProcent / 100 / 12;
		
		aktualniVyse = aktualniVyse + urok;
		aktualniVyse = aktualniVyse - upravenaSplatka + poplatekZaVedeni;
		zaplacenoCelkem = zaplacenoCelkem + upravenaSplatka + poplatekZaVedeni;
		zaplacenoUrok = zaplacenoUrok + urok;
		zaplacenaJistina = zaplacenaJistina + upravenaSplatka - urok;
	}
	
	void rok() {
		for (int i = 0; i < 12; i++) {
			mesic();
		}
	}
	
	double splatka() {
		return vyse * urokProcent / 100 / 12 / (1 - Math.pow(1 + urokProcent / 100 / 12, -doba));
	}
	
	
	@Override
	public String toString() {
		return "Uver{" +
				"celkemBudeZaplaceno=" + celkemBudeZaplaceno +
				", aktualniVyse=" + aktualniVyse +
				", aktualniMesic=" + aktualniMesic +
				", zaplaceno=" + zaplacenoCelkem +
				", urokCelkem=" + urokCelkem +
				", zaplacenoUrok=" + zaplacenoUrok +
				", zaplacenaJistina=" + zaplacenaJistina +
				'}';
	}
}

