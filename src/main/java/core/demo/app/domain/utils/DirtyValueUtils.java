package core.demo.app.domain.utils;

public class DirtyValueUtils {

    public static Integer convertToNumber(String valor) {
        return Integer.valueOf(valor.replaceAll("[^0-9]", ""));
    }

}