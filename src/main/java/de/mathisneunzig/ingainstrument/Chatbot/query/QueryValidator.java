package de.mathisneunzig.ingainstrument.Chatbot.query;

public class QueryValidator {
	
	public static boolean contains(String query, String... compare) {
		
		boolean contains = false;
		
		for(String s : compare) {
			
			if(query.toLowerCase().contains(s.toLowerCase()))
				contains = true;
			
		}
		
		return contains;
		
	}
	
	public static double similarity(String s1, String s2) {
		
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) {
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0)
        	return 1.0;
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
 
    }
    	
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
 
        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }
    
    public static boolean somewhereRoughlyContains(String query, String compare) {
    	int poss = query.length() - compare.length() + 1;
    	boolean contains = false;
    	for(int i = 0; i<poss; i++) {
    		String s = query.substring(i, i+compare.length());
    		if(similarity(s, compare) > 0.7) 
    			contains = true;
    	}
    	return contains;
    }
	
}
