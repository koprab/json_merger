package com.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class FileCreator {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Instant start = Instant.now();
		String text = "3526 HIGH ST,SACRAMENTO,95838,CA,2,1,836,Residential,Wed May 21 00:00:00 EDT 2008,59222,38.631913,-121.434879\n";
		String headers = "street,city,zip,state,beds,baths,sq__ft,type,sale_date,price,latitude,longitude\n";
		String file_path = "d:\\bigfile.csv";
		Path p =Paths.get(file_path);
		if(Files.exists(p)){
			System.out.println("File Exists, Deleting ...");
		Files.deleteIfExists(p);
		System.out.println("File Deleted succesfully.");
		}
		FileWriter fr = new FileWriter(file_path, true);
		BufferedWriter br = new BufferedWriter(fr);
		br.write(headers);
		br.flush();
		int total_lines = 999999999;
		int index = 0;
		while(index<total_lines){
			br.write(text);
			index++;
		}
		br.flush();
		br.close();
		Instant end = Instant.now();
		long total_time = Duration.between(start, end).toMillis();
		System.out.println("Total Time Taken to write : "+total_lines+" "+total_time+" ms");
		long filesize = Files.size(p);
		if (filesize/1000000 > 1){ //try to check if in mb or KB
			System.out.println("File size ->" +(int)(filesize/1000000)+" MB");
		}else{
			System.out.println("File size ->" +(int)(filesize/1000)+" KB");
		}
				
	}

}
