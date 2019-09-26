package pe.gob.osce.rnp.seg.controller.rest;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.osce.rnp.seg.model.jpa.dto.CaptchaDTO;
import pe.gob.osce.rnp.seg.model.jpa.dto.ResponseDTO;
import pe.gob.osce.rnp.seg.utils.Enums;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "${api.bs.route}/captcha")
public class CaptchaController {

    public static final Logger LOGGER = LogManager.getLogger(CaptchaController.class);

    @GetMapping(value = "/init")
    public ResponseDTO initCaptchaImagen()
    {
        java.util.List<Color> textColors = Arrays.asList(
                Color.getHSBColor(198,68,66),
                Color.getHSBColor(175,228,216),
                Color.getHSBColor(222,129,94));
        java.util.List<Font> textFonts = Arrays.asList(
                new Font("Arial", Font.PLAIN, 40));

        Captcha captcha = new Captcha.Builder(151, 50)
                .addText(
                        new DefaultTextProducer(),
                        new DefaultWordRenderer(textColors, textFonts))
                .addBackground()
                .addNoise(new CurvedLineNoiseProducer())
                .build();
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(), "png", bos);
            return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, new CaptchaDTO(captcha.getAnswer(), DatatypeConverter.printBase64Binary(bos.toByteArray())));        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            return new ResponseDTO(Enums.ResponseCode.EX_IO.get(), false, null);
        }
    }

    @GetMapping(value = "/refresh")
    public ResponseDTO nuevaImagenCaptcha(){
        java.util.List<Color> textColors = Arrays.asList(
                Color.getHSBColor(198,68,66),
                Color.getHSBColor(175,228,216),
                Color.getHSBColor(222,129,94));
        List<Font> textFonts = Arrays.asList(
                new Font("Arial", Font.PLAIN, 40));

        Captcha captcha = new Captcha.Builder(126, 50)
                .addText(
                        new DefaultTextProducer(),
                        new DefaultWordRenderer(textColors, textFonts))
                .addBackground()
                .addNoise(new CurvedLineNoiseProducer())
                .build();
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(), "png", bos);
            return new ResponseDTO(Enums.ResponseCode.EXITO_GENERICA.get(), true, new CaptchaDTO(captcha.getAnswer(), DatatypeConverter.printBase64Binary(bos.toByteArray())));
        } catch (Exception e){
            LOGGER.info(e.getMessage());
            return new ResponseDTO(Enums.ResponseCode.EX_IO.get(), false, null);
        }
    }
}
