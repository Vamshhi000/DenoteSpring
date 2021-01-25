package ednote.com.ednote.registerServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import static java.util.stream.Collectors.toList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ednote.com.ednote.registerModel.AmmountDto;
import ednote.com.ednote.registerModel.AmmountTable;
import ednote.com.ednote.registerModel.BarChartDto;
import ednote.com.ednote.registerModel.ImgModel;
import ednote.com.ednote.registerModel.RegisterModel;
import ednote.com.ednote.registerRepository.AmmountRepository;
import ednote.com.ednote.registerRepository.ImageRepository;
import ednote.com.ednote.registerRepository.RegisterRepository;
import ednote.com.ednote.registerService.AmmountService;
@Service
public class AmmountServiceImpl implements AmmountService{
	@Autowired
	private AmmountRepository ammountRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private RegisterRepository registerRepository;
	BarChartDto barChartDtoGlobal = new BarChartDto();
	List<Long> ccc=new ArrayList<Long>();
	long vv;
	public long total=0;
	public long totalAmmount=0;
	@Override
	public void save(AmmountTable ammountTable, String name,String email) {
		AmmountTable aammountTable=new AmmountTable();
		RegisterModel registerModel = registerRepository.findByEmail(email);

		ammountTable.setAmmountDate(aammountTable.getAmmountDate());
		ammountTable.setName(name);
		ammountTable.setRegisterModel(registerModel);
		ammountRepository.save(ammountTable);
		
	}
	@Override
	public 	List<AmmountDto> getalldata(String email) throws ParseException {

		
		
		List<AmmountDto> listOfAmmountDto=new ArrayList<AmmountDto>();
		List<List<AmmountDto>> listOfListOfAmmountDto=new ArrayList<List<AmmountDto>>();
		List<AmmountTable> table=ammountRepository.findByRegisterModelEmail(email);
		
		for(AmmountTable x:table) {
			  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  //for
			AmmountDto ammountTable = new AmmountDto();							//formating
		    formatter = new SimpleDateFormat("dd MMMM yyyy"); 					//date
		    DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 			//to
		    Date ddate = (Date) parser.parse(x.getAmmountDate());				//this 07 January 2021
		    String strDate = formatter.format(ddate);  							//format
	
		    
			ammountTable.setAmmount(x.getAmmount());
			ammountTable.setAmmountDate(strDate);
			ammountTable.setTagName(x.getTagName());
			ammountTable.setName(x.getName());
			listOfAmmountDto.add(ammountTable);
		}
		
		
		List<String> names=listOfAmmountDto.stream().map(p->p.getName()).distinct().collect(toList());		
		

		
		for(String naame:names) {
			AmmountDto ammountTable = new AmmountDto();
			
			List<AmmountDto> l1=listOfAmmountDto.stream().filter(p->p.getName()==naame).collect(toList());
			vv=l1.stream().mapToLong(o ->o.getAmmount()).sum();
			ammountTable.setTotal(vv);
			l1.add(ammountTable);
			ccc.add(vv);

			
			listOfListOfAmmountDto.add(l1);
		
			
		}
		
	

		
	
		
		
		

	return listOfAmmountDto;
		}
		


	@Override
	public List<String> getByNames(String name, String email) {;

		return ammountRepository.findByNameAndEmail(email);
	}
	@Override
	public List<AmmountDto> deleteammount(String tagname,String email) throws ParseException {
		AmmountTable ammountTable=ammountRepository.findByTagNameAndRegisterModelEmail(tagname, email);
		ammountRepository.delete(ammountTable);
		
		return getalldata(email);
	}
	@Override
	public List<AmmountDto> editAmmount(AmmountTable ammountTable, String name, String email, String tagname) throws ParseException {
AmmountTable aammountTable=ammountRepository.findByNameAndTagNameAndEmail(name, tagname, email);
		
		aammountTable.setAmmount(ammountTable.getAmmount());
		aammountTable.setTagName(ammountTable.getTagName());
		ammountRepository.save(aammountTable);
		return getalldata(email);
	}
	@Override
	public List<BarChartDto> getBarChartData(String endDate, String startdate, String name, String email) throws ParseException {
		List<AmmountTable> chartData=null;
		if(name.equals("all categories")) {
			chartData=ammountRepository.findByammountDateAndEmail(endDate,startdate, email);
		}else {
			chartData= ammountRepository.findBammountDateAndNameAndEmail(endDate,startdate, name, email);	
		}
	
		
		List<BarChartDto> listOfBarChartDto = new LinkedList<BarChartDto>();
		Map<String,Long> barChartMap= new LinkedHashMap<String,Long>();
		String datOfWeek;
		
		
		for(AmmountTable x:chartData) {
			BarChartDto barChartDtoLocal = new BarChartDto();
			datOfWeek=getWeek(x.getAmmountDate());
			barChartDtoLocal.setWeekDay(datOfWeek);
			barChartDtoLocal.setAmmount(x.getAmmount());
			listOfBarChartDto.add(barChartDtoLocal);
			
		}
//		for(AmmountTable x:chartData) {
//			
//			datOfWeek=getWeek(x.getAmmountDate());
//			if(barChartMap.get(datOfWeek)!=null) {
//				long update=barChartMap.get(datOfWeek);
//				update+=x.getAmmount();
//				barChartMap.put(datOfWeek, update);	
//			}else {
//				barChartMap.put(datOfWeek, x.getAmmount());	
//			}
//			
//		}
		return listOfBarChartDto;
	}
	
public String getWeek(String date) throws ParseException {
	

  SimpleDateFormat format1=new SimpleDateFormat("yyyy-mm-dd");
  Date dt1=format1.parse(date);
  DateFormat format2=new SimpleDateFormat("EEEE"); 
  String finalDay=format2.format(dt1);

  return finalDay;
}
@Override
public List<AmmountDto> getPieChartData(String endDate, String startDate, String email) {
	List<AmmountDto> listOfAmmountDto=new ArrayList<AmmountDto>();
	List<AmmountDto> pieChartList= new ArrayList<AmmountDto>();
	List<AmmountTable> table=ammountRepository.findByammountDateAndEmail(endDate, startDate, email);
	for(AmmountTable x:table) {

		AmmountDto ammountTable = new AmmountDto();						
						

	    
		ammountTable.setAmmount(x.getAmmount());

		ammountTable.setName(x.getName());
		listOfAmmountDto.add(ammountTable);
	}
	List<String> names=listOfAmmountDto.stream().map(p->p.getName()).distinct().collect(toList());		
	

	
	for(String naame:names) {
		AmmountDto ammountTable = new AmmountDto();
		
		List<AmmountDto> l1=listOfAmmountDto.stream().filter(p->p.getName().equals(naame)).collect(toList());
		vv=l1.stream().mapToLong(o ->o.getAmmount()).sum();
		ammountTable.setTotal(vv);
		ammountTable.setName(naame);

		pieChartList.add(ammountTable);
		
	
		
	}
	return pieChartList;
}

}
