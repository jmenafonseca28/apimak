package mye.fisio.mak.apimak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mye.fisio.mak.apimak.config.Config;
import mye.fisio.mak.apimak.domain.Comments;
import mye.fisio.mak.apimak.domain.CommentsKey;

public interface CommentsRepository extends JpaRepository<Comments, CommentsKey> {

    
    @Query(value = "SELECT * FROM " + Config.COMMENTS_TABLE + " c WHERE c.id = :id", nativeQuery = true)
    public List<Comments> findById(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM " + Config.COMMENTS_TABLE + " c WHERE c.id_client = :id_client", nativeQuery = true)
    public List<Comments> findByIdClient(@Param("id_client") Integer id_client);
    
    @Query(value = "SELECT * FROM " + Config.COMMENTS_TABLE + " c WHERE c.id = :id AND c.id_client = :id_client", nativeQuery = true)
    public Comments findByIdAndIdClient(@Param("id") Integer id, @Param("id_client") Integer id_client);
    

}
