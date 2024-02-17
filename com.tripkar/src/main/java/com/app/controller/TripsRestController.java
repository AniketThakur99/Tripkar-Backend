package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ErrorResponse;
import com.app.dto.ResponseDTO;
import com.app.pojos.Trips;
import com.app.services.ITripsService;

@RestController
@RequestMapping("/trips")
@CrossOrigin


public class TripsRestController {

	@Value("${project.image}")
	private  String path;
	//private  String FOLDER_PATH;
	
	@Autowired
	ITripsService tripsService;
	
	
	
	public TripsRestController() {
		System.out.println("in ctor "+getClass().getName());
	}



	@GetMapping
	public List<Trips> fetchAllTrips()
	{
		System.out.println("in fetch all trips");
		return tripsService.getAllTrips();
	}
	
	
	@PostMapping
	public ResponseEntity<?> addTripsDetails(@RequestParam("image") MultipartFile file,
			@RequestParam("heading") String heading,
			@RequestParam("description") String description,
			@RequestParam("price") int price ) throws IOException
	{
		Trips tripsDetails=new Trips();
		//byte[] imageData = file.getBytes();
		
		if(!file.isEmpty())
		{
			//File name
			String fileName=file.getOriginalFilename();
			
			//randaom name generate file name
			String randomID=UUID.randomUUID().toString();
			String updatedFileName=randomID.concat(fileName.substring(fileName.lastIndexOf(".")));
			
			//Fullpath
			String filePath=path+File.separator+updatedFileName;
			System.out.println(filePath);
			//create folder if not created
			File f =new File(path);
			if(!f.exists())
			{
				f.mkdir();
			}
			
			//file copy
			
			Files.copy(file.getInputStream(), Paths.get(filePath));
			
			
			
			
			
			
			// Set the image Name in the trips  object
			tripsDetails.setImage(updatedFileName);
			
			//File saveFile =new ClassPathResource( FOLDER_PATH).getFile();
			//Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+fileName);
			//Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}
		
		
		
		tripsDetails.setDescription(description);
		tripsDetails.setHeading(heading);
		tripsDetails.setPrice(price);
		
        
       
		System.out.println("in  add trips Details "+file.getOriginalFilename());
		try {
			
			return new ResponseEntity<>(tripsService.addTrips(tripsDetails),HttpStatus.CREATED);
		}catch(RuntimeException e)
		{
			System.out.println("err in addDestinationDetails "+e);
			return new ResponseEntity<>( new ErrorResponse("Adding trips Details failed!!!!!",e.getMessage() ),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteTripsDetails(@PathVariable int id)
	{
		System.out.println("in delete trips details " +id);
		//invoke service layer method for deleting Destination details
	
		return ResponseEntity.ok(new ResponseDTO(tripsService.deleteTrips(id)));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTripsDetails(@PathVariable long id)
	{
		System.out.println("in get Trips detail by id "+id);
		try {
				return ResponseEntity.ok(tripsService.getTripsDetails(id));
			}catch(RuntimeException e)
			{
				System.out.println("err in Destination get by id "+e);
				return new ResponseEntity<>( new ErrorResponse("Fetching Trips details failed!!!!!",e.getMessage() ),
						HttpStatus.BAD_REQUEST);
			}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTripsDetails(@RequestBody Trips details,@PathVariable long id)
	{
		System.out.println("in update user "+details + " "+ id);
		try {
		
			Trips existingTrips=tripsService.getTripsDetails(id);
		
		return ResponseEntity.ok(tripsService.addTrips(details));
		}catch(RuntimeException e)
		{
			System.out.println("err in update Destination Details "+e);
			return new ResponseEntity<>( new ErrorResponse("Updating Trips details failed!!!!!",e.getMessage() ),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
