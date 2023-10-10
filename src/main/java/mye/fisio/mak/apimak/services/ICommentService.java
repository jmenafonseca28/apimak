package mye.fisio.mak.apimak.services;

import java.util.List;

import mye.fisio.mak.apimak.domain.Comments;

public interface ICommentService {

    public Comments createComment(Comments comment);

    public Comments updateComment(Comments comment);

    public boolean deleteComment(Comments comment);

    public boolean deleteCommentById(Integer id, Integer idClient);

    public List<Comments> getCommentById(Integer id);

    public Comments getCommentById(Integer id, Integer idClient);

    public List<Comments> getAllComments();
    
}
