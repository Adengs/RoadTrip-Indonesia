package app.codelabs.forum.models;

public class Vote {

    private String nama;
    private Boolean rbVote;

    public Vote( String nama, Boolean rbVote) {
        this.nama = nama;
        this.rbVote = rbVote;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Boolean getRbVote() {
        return rbVote;
    }

    public void setRbVote(Boolean rbVote) {
        this.rbVote = rbVote;
    }
}
