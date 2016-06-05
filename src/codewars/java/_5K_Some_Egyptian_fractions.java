/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codewars.java;

/**
 *
 * @author bpolus
 * 
 * @Title      : 5 kyu - Some Egyptian fractions
 * @URL        : http://www.codewars.com/kata/54f8693ea58bce689100065f
 * 
 * @Description: Given a rational number n
 *               
 *               - n >= 0, with denominator strictly positive -
 *               
 *               as a string (example: "2/3" in Ruby, Python, Clojure, JS, CS) or as two strings (example: "2" "3" in Haskell, Java, CSharp) decompose this number as a sum of rationals with numerators equal to one and without repetitions (2/3 = 1/2 + 1/6 is correct but not 2/3 = 1/3 + 1/3, 1/3 is repeated).
 *               
 *               The algorithm must be "greedy", so at each stage the new rational obtained in the decomposition must have a denominator as small as possible. In this manner the sum of a few fractions in the decomposition gives a rather good approximation of the rational to decompose.
 *               
 *               2/3 = 1/3 + 1/3 doesn't fit because of the repetition but also because the first 1/3 has a denominator bigger than the one in 1/2 in the decomposition 2/3 = 1/2 + 1/6.
 *               
 *               Example:
 *               
 *               decompose("21/23") or decompose "21" "23" 
 *               should return ["1/2", "1/3", "1/13", "1/359", "1/644046"] in Ruby, Python, Clojure, JS, CS, Haskell
 *               and "[1/2, 1/3, 1/13, 1/359, 1/644046]" in Java, CSharp.
 *               The decomposition of 21/23 as
 *               
 *               21/23 = 1/2 + 1/3 + 1/13 + 1/598 + 1/897
 *               is exact but don't fulfill our requirement because 598 is bigger than 359. Same for
 *               
 *               21/23 = 1/2 + 1/3 + 1/23 + 1/46 + 1/69 (23 is bigger than 13)
 *               or 
 *               21/23 = 1/2 + 1/3 + 1/15 + 1/110 + 1/253 (15 is bigger than 13).
 *               The rational given to decompose could be greater than one, in which case the first "fraction" will be an integer (with an implicit denominator of 1).
 *               
 *               If the numerator parses to zero the function "decompose" returns [].
 *               The number could also be a decimal which can be expressed as a rational (ex: "0.6" in Ruby, Python, Clojure, JS, CS or "66" "100" in Haskell, Java, CSharp).
 *               Ref: http://en.wikipedia.org/wiki/Egyptian_fraction
 */
public class _5K_Some_Egyptian_fractions {
    
    public static String decompose(String nrStr, String drStr) {

        return decomposeRecursive(Long.parseLong(nrStr),
                                  Long.parseLong(drStr),
                                  "");
        
        
    }

        
    private static String decomposeRecursive(long lNumerator,
                                             long lDenominator,
                                             String sCurrentStr) {

System.out.println("" + lNumerator + "/" + lDenominator + ", " + sCurrentStr);
        
        String sReturn = sCurrentStr += sCurrentStr.length() > 0 ? ", ": "[";
                
        if (lNumerator == 0L) {
            sReturn += "]";
            return sReturn;
        }
        
        // Case when the given fraction is > 1        
        if (lNumerator >= lDenominator) {
            sReturn += lNumerator / lDenominator;            

            if (lNumerator % lDenominator == 0) {
                sReturn += "]";
            } else {
                sReturn = decomposeRecursive(lNumerator % lDenominator,
                                             lDenominator,
                                             sReturn); 
            }
            return sReturn;
        }

        if (lNumerator == 1L) {
            sReturn += 1 + "/" + lDenominator + "]";
            return sReturn;
        } 

        // Find the smallest n where 1/n < sNum / sDen
        long lCurrendDen = 2;
        while (lCurrendDen * lNumerator < lDenominator) lCurrendDen++;
        
        sReturn += 1 + "/" + lCurrendDen;
        
        long lNewNum = lCurrendDen * lNumerator - lDenominator;
        long lNewDen = lCurrendDen * lDenominator;
        
        if (lNewNum == 0) {
            sReturn += "]";
        } else {
            sReturn = decomposeRecursive(lNewNum,
                                         lNewDen,
                                         sReturn); 
        }
        return sReturn;
        
    }
    
    
}
