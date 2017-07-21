package uk.ac.ebi.ebiweb;

public class Accession implements Comparable<Accession>{

    String accessionNumber;
    String alphabetPart;
    String numericPart;

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getAlphabetPart() {
        return alphabetPart;
    }

    public void setAlphabetPart(String alphabetPart) {
        this.alphabetPart = alphabetPart;
    }

    public String getNumericPart() {
        return numericPart;
    }

    public void setNumericPart(String numericPart) {
        this.numericPart = numericPart;
    }

    public Accession(String accessionNumber) {
        /*Assumption is that user many input lower case or upper case, (mostly uppercase)
         * If lowercase or mixed, then automatically converted to uppercase
         */
    	this.accessionNumber = accessionNumber.toUpperCase();        
        String[] accessionPart = this.accessionNumber.split("(?<=\\D)(?=\\d)");
        this.alphabetPart = accessionPart[0];
        this.numericPart = accessionPart[1];
    }

    public Accession(String accessionNumber, String alphabetPart, String numericPart) {
        this.accessionNumber = accessionNumber.toUpperCase();
        this.alphabetPart = alphabetPart;
        this.numericPart = numericPart;
    }

    @Override
    public int compareTo(Accession o) {
        /*
        1 - not consecutuve
        0 - Consecutive
        Logic -
        1. if the number of digits in numeric part is not same, then they are not consecutive
        2. if number of zeros in the string are not same, then they are not consecutive
            One exception is if "this" object ends with 9, then compared accession may be next number if
            it is next in numberals (ends with 0)
        3. when numeric part is next number, then it is consecutiveand hence in the range
        4.
         */
        String zeroRegEx = "[1-9]+$";
        String nonZeroRegEx = "^[0]+";
        if (this.getNumericPart().length() != o.getNumericPart().length()) {
         //   System.out.print("Not of Equal Length");
            return 1;
        } else if (this.getNumericPart().startsWith("0") && o.getNumericPart().startsWith("0")) {
          //  System.out.println("Both Accessions start with 0");
           // System.out.println("Size of zeros =>"+this.getNumericPart().split(zeroRegEx)[0]);
           // System.out.println("Size of zeros =>"+o.getNumericPart().split(zeroRegEx)[0]);
            if (this.getNumericPart().split(zeroRegEx).length == o.getNumericPart().split(zeroRegEx).length) {
              //  System.out.println("Number of 0's are same");
               // System.out.println(Long.parseLong(this.getNumericPart()+1));
               // System.out.println(Long.parseLong(o.getNumericPart()));
                if ((Long.parseLong(this.getNumericPart())+1) == Long.parseLong(o.getNumericPart())) {
                //    System.out.println("Number is consecutive");
                    return 0;
                } else {
                //    System.out.println("Number is not consecutive");
                    return 1;
                }
            }
        } else {
            if ((Long.parseLong(this.getNumericPart())+1) == (Long.parseLong(o.getNumericPart()))) {
             //   System.out.println("Number is consecutive");
                return 0;
            } else {
            //    System.out.println("Number is not consecutive");
                return 1;
            }
        }
        return 1;
    }


}

