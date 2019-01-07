package pe.gob.osce.rnp.seg.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hashids.Hashids;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Parseador {

	public static final Logger LOGGER = LogManager.getLogger(Parseador.class);
	
	public static Date fromStringToDateSimple(String date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
		Date parseDate = null;
		
		try {
			parseDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOGGER.info("method fromStringToDateSimple() have failed");
			return null;
		}
		return parseDate;
	}
	
	public static Date fromStringToDate(String date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parseDate = null;
		
		try {
			parseDate = sdf.parse(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		} 
		return parseDate;
	}
	
	public static BigDecimal fromDoubleToBigDecimal(double value, int scale) {
		return  BigDecimal.valueOf(value).setScale(scale);
	}
	
	public static int fromStringToInt(String cadena) {
		try {
			return Integer.parseInt(cadena);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public static double fromStringToDouble(String cadena) {
		try {
			return Double.parseDouble(cadena);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public static String removeDiacriticalMarks(String string) {
	    return Normalizer.normalize(string, Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	public static Integer[] stringArrayToIntArray(String[] a){
		Integer[] b = new Integer[a.length];
	    for (int i = 0; i < a.length; i++) {
	    	if(!a[i].equals("")) {
	    		b[i] = Integer.parseInt(a[i]);	
	    	}else {
	    		b[i] = 0;
	    	}
	    }
	    return b;
	}

	public static String getEncodeHash32Id(String schema,  Long id){
		Hashids rfIdHash = new Hashids(schema, 32);
		return rfIdHash.encode(id);
	}

	public static Long getDecodeHash32Id(String schema,  String hash){
		Hashids rfIdHash = new Hashids(schema, 32);
		return Long.valueOf(String.valueOf(rfIdHash.decode(hash).length > 0 ? rfIdHash.decode(hash)[0] : 0));
	}
	public static int getDecodeHash16Id(String schema,  String hash){
		Hashids rfIdHash = new Hashids(schema, 16);
		return Integer.parseInt(String.valueOf(rfIdHash.decode(hash).length > 0 ? rfIdHash.decode(hash)[0] : 0));
	}
}
