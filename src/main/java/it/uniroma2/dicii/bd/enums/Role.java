package main.java.it.uniroma2.dicii.bd.enums;

public enum Role {

    SEGRETERIA(0),
    TORNELLO(1);

    private final int id;

    private Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role getRole(int id) {
        for (Role r: values()) {
            if(r.getId() == id) return r;
        }
        return null;
    }
}

