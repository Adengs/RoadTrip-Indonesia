package app.codelabs.forum.models;

public class Vote {
    private String imagevote;
    private String nama;
    private Boolean rbVote;

    public String getImagevote() {
        return imagevote;
    }

    public void setImagevote(String imagevote) {
        this.imagevote = imagevote;
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
