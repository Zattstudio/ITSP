package utility;

public class Numbers {
	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}
        
        public static boolean isPrime(int n) {
            for(int i=2;i<n;i++) {
                if(n%i==0)
                    return false;
            }
        return true;
        }
}
