package handler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;


public class ImageRequestHandler {
	
	
	public static boolean ImageInputRequest(BufferedImage img, String Username) throws IOException {
		AmazonS3 S3Client = new AmazonS3Client(new ProfileCredentialsProvider());
		File outfile = new File(Username + ".jpg");
		ImageIO.write(img, ".jpg", outfile);
		try{
			S3Client.putObject(new PutObjectRequest("codesquad-webapp-original", Username, outfile).withCannedAcl(CannedAccessControlList.PublicRead));
			return true;
		}
		catch(AmazonServiceException ase) {
			System.out.println("S3 업로드 중 오류 발생");
			ase.printStackTrace();
			return false;
		}
		
		
		
	}
	
	
}
