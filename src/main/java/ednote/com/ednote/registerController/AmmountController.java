package ednote.com.ednote.registerController;

import static java.util.stream.Collectors.toList;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ednote.com.ednote.GlobalExceptionHandler.DuplicatetagName;
import ednote.com.ednote.registerModel.AmmountDto;
import ednote.com.ednote.registerModel.AmmountTable;
import ednote.com.ednote.registerModel.BarChartDto;
import ednote.com.ednote.registerModel.Responcedto;
import ednote.com.ednote.registerRepository.AmmountRepository;
import ednote.com.ednote.registerService.AmmountService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
@RestController
@RequestMapping(value="/Ammount")
@CrossOrigin("http://localhost:4200")
public class AmmountController {

	
	
	@Autowired
	private AmmountRepository ammountRepository;
	
	@Autowired
	private AmmountService ammountService;
	
	

	@PostMapping(value="/{name}")
	public ResponseEntity<Responcedto> save(@RequestBody AmmountTable ammountTable,@PathVariable String name,@RequestParam("email") String email) {
		Responcedto responcedto = new Responcedto();
		
		if(ammountRepository.findByTagNameAndRegisterModelEmail(ammountTable.getTagName(), email)==null) {
		try {
			ammountService.save(ammountTable, name,email);
			responcedto.setMsg("Ammount has successfully saved");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.OK);
			
		} catch (Exception e) {
			responcedto.setMsg("Internal server error");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		}else {
			responcedto.setMsg("ammount already added");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	
	@GetMapping
	public 	List<AmmountDto> getNames(@RequestParam("email") String email) throws ParseException{
		

		 return ammountService.getalldata(email); 
	}
	
	@DeleteMapping(value="/{tagname}")
	public ResponseEntity<List<AmmountDto>> deleteAmmount(@RequestParam("email") String email,@PathVariable String tagname) {
		AmmountDto aammountDto = new AmmountDto();
		List<AmmountDto> aaammountDto = new ArrayList<AmmountDto>();
			try {
				
				List<AmmountDto> ammountDto = ammountService.deleteammount(tagname,email);
				return new ResponseEntity<List<AmmountDto>>(ammountDto,HttpStatus.OK);
			} catch (Exception e) {
				
				aammountDto.setMessage("Internal server error");
				aaammountDto.add(aammountDto);
				return new ResponseEntity<List<AmmountDto>>(aaammountDto,HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		
		
		
		
		
		
	}
	
	@PutMapping(value="/{name}/{tagname}")
	public ResponseEntity<List<AmmountDto>> editAmmount(@RequestBody AmmountTable ammountTable,@RequestParam("email") String email,@PathVariable String name,@PathVariable String tagname) throws DuplicatetagName{
		AmmountDto aammountDto = new AmmountDto();
		List<AmmountDto> aaammountDto = new ArrayList<AmmountDto>();
			try {
				List<AmmountTable> filterr=ammountRepository.findByNameAndRegisterModelEmail(name,email);
				List<AmmountTable> filterrr=filterr.stream().filter(p->p.getTagName().equals(tagname)).collect(toList());
				filterr.removeAll(filterrr);
				
				for(AmmountTable c:filterr) {
					
					if(c.getTagName().equals(ammountTable.getTagName())) {
						throw new DuplicatetagName("do not edit duplicate tag");
					}
					
				}
				
				List<AmmountDto> ammountDto =ammountService.editAmmount(ammountTable, name, email, tagname);
				return new ResponseEntity<List<AmmountDto>>(ammountDto,HttpStatus.OK);
				
			} catch (DuplicatetagName e) {
				aammountDto.setMessage("Edit on wrong column");
				aaammountDto.add(aammountDto);
				return new ResponseEntity<List<AmmountDto>>(aaammountDto,HttpStatus.BAD_REQUEST);
			}catch (Exception e) {
				aammountDto.setMessage("Internal server error");
				aaammountDto.add(aammountDto);
				return new ResponseEntity<List<AmmountDto>>(aaammountDto,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		 
	}
	
	@GetMapping(value="/{name}")
	public ResponseEntity<List<BarChartDto>> chartsData(@RequestParam("email") String email,@PathVariable String name) throws ParseException {
	
		try {
			AmmountTable ammountTable = new AmmountTable();  
			
			List<BarChartDto> barChartDtoList=ammountService.getBarChartData(ammountTable.endDate(), ammountTable.startDate(), name, email);
			return new ResponseEntity<List<BarChartDto>>(barChartDtoList,HttpStatus.OK);
		}catch (Exception e) {
			 
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
       
        
	
		
		
        

	
	
		

	}
	
	@GetMapping(value="/pie/{reports}")
	public ResponseEntity<List<AmmountDto>> pieChartData(@RequestParam("email") String email,@PathVariable String reports){
			try {
				AmmountTable ammountTable = new AmmountTable();  
				List<AmmountDto> pieChartData=null;
				
				switch (reports) {
				  case "Days reports":
					  pieChartData=ammountService.getPieChartData(ammountTable.endDate(), ammountTable.startDate(), email);
				    break;
				  case "Weeks reports":
					  
					  System.out.println(ammountTable.startDate());
					  System.out.println(ammountTable.endWeeks());
					  pieChartData=ammountService.getPieChartData(ammountTable.endWeeks(), ammountTable.startDate(), email);
					break;
				  case "Months reports":
					  pieChartData=ammountService.getPieChartData(ammountTable.endMonths(), ammountTable.startDate(), email);
				    break;
				  case "Annually reports":
					  pieChartData=ammountService.getPieChartData(ammountTable.endAnnual(), ammountTable.startDate(), email);
				    break;
				
				}
				return new ResponseEntity<List<AmmountDto>>(pieChartData,HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		
		
		
		
		
		

		
		
	}
	
	
	
}
	
