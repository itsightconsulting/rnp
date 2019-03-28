package pe.gob.osce.rnp.seg.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class Utilitarios {

    public static final Logger LOGGER = LogManager.getLogger(Utilitarios.class);

    private Utilitarios(){}

    public static void createDirectoryStartUp(String basePath, String[] paths) {

        for (int i = 0; i < paths.length; i++) {
            File dirFile = new File(basePath);

            if (!dirFile.exists()) {
                try {
                    dirFile.mkdir();
                } catch (SecurityException se) {
                    LOGGER.info(se.getMessage());
                }
            } else {
                File nuevoFile = new File(basePath + paths[i]);
                if (!nuevoFile.exists()) {
                    try {
                        nuevoFile.mkdir();
                    } catch (SecurityException se) {
                        LOGGER.info(se.getMessage());
                    }
                }
            }
        }
    }

    public static final String encoderPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static String customResponse(String code, String domainPk) {
        return "{\"code\": \"" + code + "\", \"id\": \"" + domainPk + "\"}";
    }

    public static String[] filterStringArray(String[] array){
        array = Arrays.stream(array)
                .filter(Objects::nonNull)
                .toArray(String[]::new);
        return array;
    }

}

