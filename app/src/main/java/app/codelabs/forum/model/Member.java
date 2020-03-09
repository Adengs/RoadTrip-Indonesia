package app.codelabs.forum.model;

public class Member {
    private Boolean follow;
    private Boolean unfollow;
    private int foto;
    private String follows;

    public Boolean getFollow() {
        return follow;
    }

    public void setFollow(Boolean follow) {
        this.follow = follow;
    }

    public Boolean getUnfollow() {
        return unfollow;
    }

    public void setUnfollow(Boolean unfollow) {
        this.unfollow = unfollow;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getFollows() {
        return follows;
    }

    public void setFollows(String follows) {
        this.follows = follows;
    }
}
