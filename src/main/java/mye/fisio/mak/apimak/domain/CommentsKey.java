package mye.fisio.mak.apimak.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Embeddable
public class CommentsKey {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_client")
    private int id_client;

    public CommentsKey() {
    }

    public CommentsKey(int id, int id_client) {
        this.id = id;
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "CommentsKey{" + "id=" + id + ", id_client=" + id_client + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.id_client;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CommentsKey)) {
            return false;
        }
        CommentsKey other = (CommentsKey) obj;
        return this.id == other.id && this.id_client == other.id_client;
    }

    
}
