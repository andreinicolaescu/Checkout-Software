package services;

public class CurrentID {
    
    private static CurrentID currentId;

    int id;

    private CurrentID() {

    }

    public static CurrentID getInstance() {
        if(currentId == null) {
            currentId = new CurrentID();
            currentId.id = 0;
        }
        currentId.id++;
        return currentId;
    }

    public static void reset() {
        currentId.id = 0;
    }
}
