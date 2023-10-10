package mye.fisio.mak.apimak.config;

public enum ErrorMessages {

    // ERRORES CLIENTE
    CLIENTS_NOT_FOUND("clients_not_found"),
    CLIENT_NOT_FOUND("client_not_found"),
    CLIENT_ALREADY_EXISTS("client_already_exists"),
    CLIENT_IS_NULL("client_is_null"),
    CLIENT_ID_IS_NULL("client_id_is_null"),
    CLIENT_DOCUMENT_IS_NULL("client_document_is_null"),
    CLIENT_DOCUMENT_ALREADY_EXISTS("client_document_already_exists"),
    CLIENT_DOCUMENT_IS_TOO_LONG("client_document_is_too_long"),
    CLIENT_DOCUMENT_IS_TOO_SHORT("client_document_is_too_short"),
    CLIENT_NAME_IS_NULL("client_name_is_null"),
    CLIENT_LASTNAME_IS_NULL("client_lastname_is_null"),
    CLIENT_NO_HAS_CHANGES("client_no_has_changes"),

    // ERRORES COMENTARIOS
    COMMENTS_NOT_FOUND("comments_not_found"),
    COMMENT_NOT_FOUND("comment_not_found"),
    COMMENT_ALREADY_EXISTS("comment_already_exists"),
    COMMENT_IS_NULL("comment_is_null"),
    COMMENT_ID_IS_NULL("comment_id_is_null"),
    COMMENT_IDCLIENT_IS_NULL("comment_idclient_is_null"),
    COMMENT_NO_HAS_CHANGES("comment_no_has_changes");

    private final String data;

    ErrorMessages(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }

}
