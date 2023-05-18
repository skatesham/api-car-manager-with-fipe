package core.demo.app.domain.veiculo.exceptions;

public class VeiculoAlreadyExistException extends RuntimeException {

    public VeiculoAlreadyExistException(String placa) {
        super("Already exists veiculo by name=" + placa);
    }

}
