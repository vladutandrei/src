package server;

public enum Actiuni {

    LIST("LIST"), MSG("MSG"), NICK("NICK"), QUIT("QUIT"), BCAST("BCAST");
    String s;

    Actiuni(String s) {
        this.s = s;
    }

    public String getMessage() {
        return s;
    }
}
