package cz.asterionsoft;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	public static void main(String[] args) {
		log.info("start simulator application!");
		
		double vyse = 1_000_000;
		int dobaUveruMesicu = 12 * 10;
		int dobaInvesticeMesicu = 12 * 20;
		double urokUveru = 9;
		double urokInvestice = 9;
		
		Uver uver = new Uver(vyse, dobaUveruMesicu, urokUveru, 100);
		Investice investice = new Investice(vyse, dobaInvesticeMesicu, urokInvestice, 100);
		
		OffsetInvestice offsetInvestice = new OffsetInvestice(uver, investice);
		
		for (int i = 1; i <= 20; i++) {
			offsetInvestice.rok();
			log.info(offsetInvestice.toString());
			
			if (offsetInvestice.isBreakEven2(offsetInvestice)) {
				log.info("Break even after {} years", i);
				break;
			}

			/*
			if (offsetInvestice.isBreakEven(offsetInvestice)) {
				log.info("Break even after {} years", i);
				//break;
			}
			 */
			log.info("--------------------------------------------------");
		}
	}
	
	
}