package cz.asterionsoft;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Uver {
	
	private final double vyse;
	private final int doba;
	private final double urok;
	private double splatka;
	private double celkemBudeZaplaceno;
	private final double poplatekZaVedeni;
	
	// ------------- vypocet ----------------
	private double aktualniVyse;
	private int aktualniMesic; //konec mesice
	private double zaplaceno;
	private double urokCelkem;
	private double zaplacenoUrok;
	private double zaplacenaJistina;
	
	public Uver(double vyse, int dobaMesicu, double urok, double poplatekZaVedeni) {
		this.vyse = vyse;
		this.doba = dobaMesicu;
		this.urok = urok;
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
		aktualniVyse = aktualniVyse - splatka + poplatekZaVedeni;
		aktualniVyse = aktualniVyse + aktualniVyse * urok / 100 / 12;
		zaplaceno = zaplaceno + splatka + poplatekZaVedeni;
		zaplacenoUrok = zaplacenoUrok + aktualniVyse * urok / 100 / 12;
		zaplacenaJistina = zaplacenaJistina + splatka - aktualniVyse * urok / 100 / 12;
	}
	
	void rok() {
		for (int i = 0; i < 12; i++) {
			mesic();
		}
	}
	
	double splatka() {
		return vyse * urok / 100 / 12 / (1 - Math.pow(1 + urok / 100 / 12, -doba));
	}
	
	
	@Override
	public String toString() {
		return "Uver{" +
				"celkemBudeZaplaceno=" + celkemBudeZaplaceno +
				", aktualniVyse=" + aktualniVyse +
				", aktualniMesic=" + aktualniMesic +
				", zaplaceno=" + zaplaceno +
				", urokCelkem=" + urokCelkem +
				", zaplacenoUrok=" + zaplacenoUrok +
				", zaplacenaJistina=" + zaplacenaJistina +
				'}';
	}
}

