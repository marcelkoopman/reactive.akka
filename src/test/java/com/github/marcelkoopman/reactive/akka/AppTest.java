package com.github.marcelkoopman.reactive.akka;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private static final int MAX = 2000;
	
	@Test
    public void testApp() throws InterruptedException {
		final File file = new File("testFile.csv");
		assertTrue("Kan bestand niet vinden: "+file.getAbsolutePath(), file.exists());
    	final App app = new App();
    	final List<File> files = new ArrayList<>();
    	for (int i=0; i<MAX; ++i) {
    		files.add(file);
    	}
    	final UploadMessage uploadMsg = new UploadMessage(files);
    	app.start(uploadMsg);
    	Thread.sleep(25000);
    	app.stop();
    }

}
