package Application;

public class Function extends Object{
    private String fullName;
    private String equivalent;

    public Function(String fullName, String equivalent){
        this.fullName = fullName;
        this.equivalent = equivalent;
    }

    public String getEquivalent() {
        return equivalent;
    }

    public String toString(){
        return fullName;
    }

}