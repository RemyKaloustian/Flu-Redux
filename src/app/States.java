package app;

/**
 * @author : Karim
 * @date :  03/12/2015.
 */
public enum States {
    Healthy,
    Sick,
    Recovering,
    Dead,
    Contagious;

    private States() {
    }

    public String toString(){
        return this.name();
    }



}
