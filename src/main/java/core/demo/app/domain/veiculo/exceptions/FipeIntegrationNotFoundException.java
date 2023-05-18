package core.demo.app.domain.veiculo.exceptions;

public class FipeIntegrationNotFoundException extends RuntimeException {

    public FipeIntegrationNotFoundException() {
        super("Integration error, not found price on FIPE");
    }

}
