package cz.asterionsoft;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	public static void main(String[] args) {
		log.info("start simulator application!");
		
		double výše = 1_000_000;
		int dobaÚvěruMěsíců = 12 * 12;
		int dobaInvesticeMěsíců = 12 * 20;
		double úrokÚvěru = 9;
		double úrokInvestice = 9;
		
		Uver uver = new Uver(výše, dobaÚvěruMěsíců, úrokÚvěru, 100);
		Investice investice = new Investice(výše, dobaInvesticeMěsíců, úrokInvestice, 100);
		
		OffsetInvestice offsetInvestice = new OffsetInvestice(uver, investice);
		
		for (int i = 1; i <= 20; i++) {
			offsetInvestice.rok();
			log.info(offsetInvestice.toString());
			if (offsetInvestice.isBreakEven(offsetInvestice)) {
				log.info("Break even after {} years", i);
				break;
			}
			log.info("--------------------------------------------------");
		}
	}
	
	
}