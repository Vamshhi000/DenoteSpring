package ednote.com.ednote.registerService;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import ednote.com.ednote.registerModel.AmmountDto;
import ednote.com.ednote.registerModel.AmmountTable;
import ednote.com.ednote.registerModel.BarChartDto;

public interface AmmountService {
	
	public void save(AmmountTable ammountTable,String name,String email);
	
	public 	List<AmmountDto> getalldata(String email) throws ParseException;
	
	 
	
	public List<String> getByNames(String name,String email);
	public List<AmmountDto> deleteammount(String tagname,String email) throws ParseException;
	
	public List<AmmountDto> editAmmount(AmmountTable ammountTable,String name,String email,String tagname ) throws ParseException;
	
	public List<BarChartDto> getBarChartData(String endDate,String startdate,String name,String email) throws ParseException;
	
	public List<AmmountDto> getPieChartData(String endDate,String startDate,String email );
	
}
