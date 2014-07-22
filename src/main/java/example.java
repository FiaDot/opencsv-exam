import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class example {

	// 영문 컬럼 2개
	static void load_simple_2col() throws IOException, IntrospectionException {

		ColumnPositionMappingStrategy<Item> strategy = new ColumnPositionMappingStrategy<Item>();
		strategy.setType(Item.class);
		strategy.setColumnMapping(new String[]{"key","val"} );
        
		String path = new File("src/main/resources/utf8_withouf_bom_2col_en.csv").getAbsolutePath();
		
		
		// 마지막 인자가 skip header. 반드시 1 넣어줘야 함!        
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(path), "UTF8"), ',', '\"', 1);


        CsvToBean<Item> bean = new CsvToBean<Item>();
		List<Item> students = bean.parse(strategy, reader);
		System.out.println(students);
		
		reader.close();
	}
	
	
	// 한글 컬럼 2개 
	static void load_simple_2col_kr() throws IOException, IntrospectionException {

		ColumnPositionMappingStrategy<Item> strategy = new ColumnPositionMappingStrategy<Item>();
		strategy.setType(Item.class);
		strategy.setColumnMapping(new String[]{"key","val"} );
        
		String path = new File("src/main/resources/utf8_withouf_bom_2col_kr.csv").getAbsolutePath();
				
		// 마지막 인자가 skip header. 반드시 1 넣어줘야 함!        
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(path), "UTF8"), ',', '\"', 1);


        CsvToBean<Item> bean = new CsvToBean<Item>();
		List<Item> students = bean.parse(strategy, reader);
		System.out.println(students);
		
		reader.close();
	}
	
	
	static void load_map_col_name()  throws IOException, IntrospectionException {

		HeaderColumnNameTranslateMappingStrategy<Item> strategy = new HeaderColumnNameTranslateMappingStrategy<Item>();	        
		strategy.setType(Item.class);

		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("이름", "key");
		columnMapping.put("무시", null);	// 무시할 필드 
		columnMapping.put("수치", "val");	        	        	      
		
        strategy.setColumnMapping(columnMapping);
        			
    	String path = new File("src/main/resources/utf8_without_bom_3col_kr.csv").getAbsolutePath();		
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(path), "UTF8"));

        CsvToBean<Item> bean = new CsvToBean<Item>();
		List<Item> students = bean.parse(strategy, reader);
		System.out.println(students);	
		
		reader.close();
	}
	
	
	
	public static void main(String[] args) throws IOException, IntrospectionException  {

		load_simple_2col();
		load_simple_2col_kr();
		load_map_col_name();
		
		/*
		 * result
		 * 
		 * [foo=100, bar=200]
		 * [foo한글=100, bar정상=200]
		 * [foo한글=100, bar정상=200]
		 */
	}

}
