class GenerateSeqWithGivenProb {
	/*
	 * Generate sequence with given probability
	 * ex: cha = {A, B, C, D}, probability = {0.6, 0.2, 0.1, 0.1}. Binary search.
	 */
    public String getRandom(char[] cha, double[] prob) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 1; i < prob.length; i++) {
    		prob[i] += prob[i-1];
    	}
    	for (int i = 0; i < 1000; i++) {
    		double rand = Math.random();
    		int l = 0, r = prob.length-1, mid = 0;
    		while (l < r) {
    			mid = (l+r)/2;
    			if (rand >= prob[mid-1] && rand < prob[mid]) {
    				break;
    			}
    			else if (rand >= prob[mid-1]) {
    				l = mid+1;
    			}
    			else {
    				r = mid-1;
    			}
    		}
    		if (l == r) {
    			mid = l;
    		}
    		sb.append(cha[mid]);
    	}
    	return sb.toString();
    }
}
