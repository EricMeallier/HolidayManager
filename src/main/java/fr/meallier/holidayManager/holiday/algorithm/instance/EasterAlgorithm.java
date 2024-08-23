package fr.meallier.holidayManager.holiday.algorithm.instance;

import fr.meallier.holidayManager.holiday.algorithm.HolidayComputeAlgorithm;

import java.time.LocalDate;

public class EasterAlgorithm implements HolidayComputeAlgorithm {

	@Override
	/*
	  Implementation of Butcher-Meeus
	 */
	public LocalDate compute(int year) {
		
		// cycle de Meton
		int n = year % 19;
		
		// centaine et rang de l'annee
		int c = year / 100;
		int u = year % 100;

		// siecle bissextile
		int s = c / 4;
		int t = c % 4;
		
		// cycle de proemptose
		int p = (c+8) / 25;
		
		// proemptose
		int q = (c -p +1) / 3;
				
		// epacte
		int e = (19 * n + c - s - q + 15) % 30;
		
		// annee bissextile
		int b = u / 4;
		int d = u % 4;
		
		// lettre dominicale
		int l = (2 * t + 2 * b - e - d + 32) % 7;
		
		// correction
		int h = (n + 11 * e + 22 * l) / 451;
		
		// mois et quantieme du Samedi saint
		int k = ( e + l - 7 * h +114 );
		int m =  k / 31;
		int j = k % 31;
		
		return LocalDate.of(year, m, j + 1).plusDays(1);
	}
}
