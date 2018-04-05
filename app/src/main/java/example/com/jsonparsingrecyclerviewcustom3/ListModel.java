package example.com.jsonparsingrecyclerviewcustom3;

public class ListModel {

    int rank;
    String countryname;
    String population;
    String flag;

    public ListModel(int rank, String countryname, String population, String flag) {
        this.rank = rank;
        this.countryname = countryname;
        this.population = population;
        this.flag = flag;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
