package furb.web2.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - Registro não encontrado
    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleNaoEncontrado(RegistroNaoEncontradoException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 400 - Parâmetro obrigatório ausente ou inválido
    @ExceptionHandler(ParametroObrigatorioException.class)
    public ResponseEntity<Map<String, Object>> handleParametroObrigatorio(ParametroObrigatorioException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // 409 - Erro de integridade (ex: deletar registro referenciado)
    @ExceptionHandler(ErroIntegridadeException.class)
    public ResponseEntity<Map<String, Object>> handleIntegridade(ErroIntegridadeException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // 500 - Erro ao acessar o banco de dados
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccess(DataAccessException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados: " + ex.getMostSpecificCause().getMessage());
    }

    // 500 - Erro genérico não tratado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenerico(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno: " + ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String mensagem) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("erro", status.getReasonPhrase());
        body.put("mensagem", mensagem);
        return ResponseEntity.status(status).body(body);
    }
}
