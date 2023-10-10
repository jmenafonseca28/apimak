package mye.fisio.mak.apimak.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments {

    @EmbeddedId
    private CommentsKey id;

    @Column(name = "commentary", length = 500)
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId("id_client")
    private Client client;

    public Comments() {
    }

    public Comments(CommentsKey id, String commentary) {
        this.id = id;
        this.commentary = commentary;
    }

    public CommentsKey getId() {
        return id;
    }

    public void setId(CommentsKey id) {
        this.id = id;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "Comments [commentary=" + commentary + ", id=" + id + "]" + "Client: " + client;
    }

}