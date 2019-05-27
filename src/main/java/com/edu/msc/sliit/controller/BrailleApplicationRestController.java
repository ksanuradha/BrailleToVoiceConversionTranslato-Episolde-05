package com.edu.msc.sliit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edu.msc.sliit.service.BinaryValueCalculation;
import com.edu.msc.sliit.service.BraileeDetect;
import com.edu.msc.sliit.service.CountWhireSpots;
import com.edu.msc.sliit.service.EnglishTextToVoise;
import com.edu.msc.sliit.service.FindXCordinates;
import com.edu.msc.sliit.service.UnicodeMApping;

@RestController
@RequestMapping("/api/brailleapplication")
@ComponentScan("com.edu.msc.sliit")
public class BrailleApplicationRestController {
	
	@Autowired
	FindXCordinates findXCordinates;
	
	@Autowired
	CountWhireSpots countWhireSpots;
	
	@Autowired
	EnglishTextToVoise englishTextToVoise;
	
	@Autowired
	UnicodeMApping unicodeMApping;
	
	public static HashMap<Integer, double[]> findRectangle;

	@PostMapping("/uploadFile")
	public void uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {
		try {
			// Get the filename and build the local file path (be sure that the
			// application have write permissions on such directory)
			String filename = uploadfile.getOriginalFilename();
			String directory = "./src/main/resources/static/img";
			String filepath = Paths.get(directory, "brailee.jpg").toString();

			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}// method uploadFile

	@PostMapping("/scan")
	public void scanImage() {				
		Mat imgGrayscale = new Mat();
		Mat image = Imgcodecs.imread("./src/main/resources/static/img/brailee.jpg", 1);
		findRectangle = new BraileeDetect(image).findRectangle();		
	}
	
	@PostMapping(value = "/listen/{imgLanguage}")
	public void listenImage(@PathVariable String imgLanguage) {
		HashMap<Integer, ArrayList<Double>> findAverageXCordinates = findXCordinates.findAverageXCordinates(findRectangle);		
		try {
			HashMap<Integer, int[]> calculatCordinates = BinaryValueCalculation.calculateBanayValuesForEachCordinates(findAverageXCordinates);
			if(imgLanguage.equals("sinhala")){
				HashMap<Integer, String[]> unicodeStringHashMap = unicodeMApping.unicodeStringHashMap(calculatCordinates);
				//String word = unicodeMApping.normalizeHashMap(unicodeStringHashMap);
				//System.out.println(word);				
			}
			else {
				
			}			
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}
}
