package mye.fisio.mak.apimak.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import mye.fisio.mak.apimak.config.ErrorMessages;
import mye.fisio.mak.apimak.domain.Comments;
import mye.fisio.mak.apimak.domain.CommentsKey;
import mye.fisio.mak.apimak.repository.CommentsRepository;
import mye.fisio.mak.apimak.services.ICommentService;

@Service
@Primary
public class CommentsServiceJpa implements ICommentService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public Comments createComment(Comments comment) {

        if (comment == null) {
            System.out.println(ErrorMessages.COMMENT_IS_NULL);
            return null;
        }

        if (commentsRepository.findById(comment.getId()).isPresent()) {
            System.out.println("Comment with id " + comment.getId() + " " + ErrorMessages.COMMENT_ALREADY_EXISTS);
            return null;
        }

        return commentsRepository.save(comment);
    }

    @Override
    public boolean deleteComment(Comments comment) {

        if (comment == null) {
            System.out.println("Comment is null");
            return false;
        }

        if (!commentsRepository.findById(comment.getId()).isPresent()) {
            System.out.println("Comment with id " + comment.getId() + " "+ ErrorMessages.COMMENT_NOT_FOUND);
            return false;
        }

        commentsRepository.delete(comment);
        return true;
    }

    @Override
    public boolean deleteCommentById(Integer id, Integer id_client) {

        if (id == null || id_client == null) {
            System.out.println("id or id_client is null");
            return false;
        }

        if (!commentsRepository.findById(new CommentsKey(id, id_client)).isPresent()) {
            System.out.println("Comment with id " + id + " and id_client " + id_client + " "+ ErrorMessages.COMMENT_NOT_FOUND);
            return false;
        }

        commentsRepository.deleteById(new CommentsKey(id, id_client));
        return true;
    }

    @Override
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    @Override
    public List<Comments> getCommentById(Integer id) {
        return commentsRepository.findById(id);
    }

    @Override
    public Comments getCommentById(Integer id, Integer id_client) {
        return commentsRepository.findById(new CommentsKey(id, id_client)).get();
    }

    @Override
    public Comments updateComment(Comments comment) {

        Comments commentToUpdate = commentsRepository.findById(comment.getId()).get();

        if (commentToUpdate == null) {
            System.out.println("Comment with id " + comment.getId() + " "+ ErrorMessages.COMMENT_NOT_FOUND);
            return null;
        }

        if (commentToUpdate.equals(comment)) {
            System.out.println("Comment with id " + comment.getId() + " " + ErrorMessages.COMMENT_NO_HAS_CHANGES);
            return comment;
        }

        return commentsRepository.save(comment);
    }

}
