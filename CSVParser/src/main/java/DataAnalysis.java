
import java.io.FileReader;  
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


public class DataAnalysis {
	
	
	public static void main(String[] args) throws IOException {
	
		List<IdxRecord<Double,CSVRecord>> items = new ArrayList<IdxRecord<Double,CSVRecord>>();
		
		URL resource = DataAnalysis.class.getClassLoader().getResource("co_1980_alabama.csv");
		  
	    Reader in = new FileReader(resource.getFile());
	    Iterable<CSVRecord> records = CSVFormat.DEFAULT
			      .withFirstRecordAsHeader()
			      .parse(in);
	    
	     for (CSVRecord record : records) {
			String value = record.get("daily_max_8_hour_co_concentration");
			
			IdxRecord<Double,CSVRecord> temp = new IdxRecord<Double, CSVRecord>(Double.valueOf(value),record);
			items.add(temp);
			
			System.out.println(String.format("%s, %s", value, ""));
			
	     }

	     in.close();
	     
	     IndexServiceWithDuplicates<IdxRecord<Double,CSVRecord>> index = 
	    		 new IndexServiceWithDuplicates<IdxRecord<Double,CSVRecord>>
	     					((IdxRecord<Double,CSVRecord>[]) items.toArray());
	     

	     
	     
	     
	     
	     
	     
	     

	}
}