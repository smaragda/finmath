package cz.asterionsoft;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Investice {
	private double vyse;
	private int doba;
	private double urok;
	private double poplatekZaVedeni;
	//private double dan; po trech letech se z akcii neplati dan
	
	// ------------- vypocet ----------------
	private double aktualniVyse;
	private int aktualniMesic; //konec mesice
	private double zisk;
	
	public Investice(double vyse, int doba, double urok, double poplatekZaVedeni) {
		this.vyse = vyse;
		this.doba = doba;
		this.urok = urok;
		this.poplatekZaVedeni = poplatekZaVedeni;
		init();
	}
	
	private void init() {
		aktualniVyse = vyse;
	}
	
	void mesic() {
		aktualniMesic++;
		aktualniVyse = aktualniVyse + aktualniVyse * urok / 100 / 12;
		aktualniVyse = aktualniVyse - poplatekZaVedeni;
		zisk = aktualniVyse - vyse;
	}
	
	void rok() {
		for (int i = 0; i < 12; i++) {
			mesic();
		}
	}
	
	@Override
	public String toString() {
		return "Investice{" +
				"aktualniVyse=" + aktualniVyse +
				", aktualniMesic=" + aktualniMesic +
				", zisk=" + zisk +
				'}';
	}
}
