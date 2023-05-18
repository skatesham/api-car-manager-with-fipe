package core.demo.app.domain.common;

import java.math.BigInteger;

public class DirtyValueUtils {

    public static BigInteger convertToNumber(String valor) {
        return BigInteger.valueOf(Long.parseLong(valor.replaceAll("[^0-9]", "")));
    }

}
