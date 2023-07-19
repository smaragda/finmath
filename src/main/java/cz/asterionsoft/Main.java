package cz.asterionsoft;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.IOException;

@Slf4j
public class Main {
	public static void main(String[] args) throws IOException {
		log.info("start simulator application!");
		
		double vyse = 200_000;
		int dobaUveruMesicu = 12 * 5;
		int dobaInvesticeMesicu = 12 * 5;
		double urokUveru = 11.2;
		double urokInvestice = 10;
		
		Uver uver = new Uver(vyse, dobaUveruMesicu, urokUveru, 100);
		Investice investice = new Investice(vyse, dobaInvesticeMesicu, urokInvestice, 100);
		
		OffsetInvestice offsetInvestice = new OffsetInvestice(uver, investice);
		
		StringBuffer sb = new StringBuffer(offsetInvestice.csvHeader());
		sb.append(System.lineSeparator());
		sb.append(offsetInvestice.csvRow());
		
		for (int i = 1; i <= dobaInvesticeMesicu; i++) {
			log.info("mesic: {}", i);
			offsetInvestice.mesic();
			sb.append(System.lineSeparator());
			sb.append(offsetInvestice.csvRow());
		}
		
		log.info("Save CSV file...");
		String fileName = createFileName(offsetInvestice);
		FileUtils.writeStringToFile(FileUtils.getFile(fileName), sb.toString(), "UTF-8");
		
		log.info("Save CSV file done!");
		
		
		log.info("end simulator application!");
	}
	
	private static String createFileName(OffsetInvestice i) {
		return "vypis-investice_" + i.getInvestice().getDoba() + "mesicu_"
				+ i.getInvestice().getUrok() + "pct_"
				+ "uver_" + i.getUver().getDoba() + "mesicu_"
				+ i.getUver().getUrokProcent() + "pct.csv";
	}
	
	
}