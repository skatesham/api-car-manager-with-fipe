package core.demo.app.domain.veiculo.exceptions;

public class ModeloNotFoundException extends RuntimeException {

    public ModeloNotFoundException(int modelo) {
        super("Not found modelo=" + modelo);
    }

}
