package com.mujoko.gcp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 
@Component
public class AppUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3337623329988640023L;
	
	@Value("${gcp.app.image.storage.bucket}")
	private String imageStorageBucket;


	public String getImageStorageBucket() {
		return imageStorageBucket;
	}

	public void setImageStorageBucket(String imageStorageBucket) {
		this.imageStorageBucket = imageStorageBucket;
	}
	

	
	
}
