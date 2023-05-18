package core.demo.app.domain.veiculo.exceptions;

public class MarcaNotFoundException extends RuntimeException {

    public MarcaNotFoundException(int marca) {
        super("Not found marca=" + marca);
    }

}
