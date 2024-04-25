package edu.monmouth.lab3;

import java.util.Comparator;

public class BowlerComparator implements Comparator<Competitor>{

		@Override
		public int compare(Competitor competitor1, Competitor competitor2) {
			return Integer.compare(competitor2.getScore(), competitor1.getScore());
		}

	    }

