package uk.ac.ebi.ebiweb;

public class AccessionRange {

    private String startAccession;
    private String endAccession;

    public AccessionRange(String startAccession) {
        this.startAccession = startAccession;
    }

    public AccessionRange(String startAccession, String endAccession) {
        this.startAccession = startAccession;
        this.endAccession = endAccession;
    }

    public String getStartAccession() {
        return startAccession;
    }

    public void setStartAccession(String startAccession) {
        this.startAccession = startAccession;
    }

    public String getEndAccession() {
        return endAccession;
    }

    public void setEndAccession(String endAccession) {
        this.endAccession = endAccession;
    }

    /*
     *   Overridden method toString to print the range in given format
     */
    @Override
    public String toString() {
        if (this.endAccession!=null && this.endAccession!= "") {
            return this.getStartAccession()+"-"+this.getEndAccession();
        } else {
            return this.getStartAccession();
        }
    }
}
