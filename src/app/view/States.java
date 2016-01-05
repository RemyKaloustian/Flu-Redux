package app.view;

/**
 * @author : Karim
 * @date :  03/12/2015.
 */
public enum States {
    HEALTHY,
    SICK,
    RECOVERING,
    DEAD,
    CONTAGIOUS;


    public String toString(){
        return this.name();
    }



}
