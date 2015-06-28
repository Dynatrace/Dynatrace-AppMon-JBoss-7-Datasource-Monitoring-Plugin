package com.jboss7.management.rest.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import com.dynatrace.diagnostics.pdk.Status;
import com.google.gson.Gson;

public class ManagementRESTConnector {
	
	private int activeCount = 0;
	private int availableCount = 0;
	private int averageBlockingTime = 0;
	private int averageCreationTime = 0;
	private int createdCount = 0;
	private int destroyedCount = 0;
	private int maxCreationTime = 0;
	private int maxUsedCount = 0;
	private int maxWaitTime = 0;
	private int timedOut = 0;
	private int totalBlockingTime = 0;
	private int totalCreationTime = 0;
	
	public int getActiveCount() {
		return activeCount;
	}

	public int getAvailableCount() {
		return availableCount;
	}

	public int getAverageBlockingTime() {
		return averageBlockingTime;
	}

	public int getAverageCreationTime() {
		return averageCreationTime;
	}

	public int getCreatedCount() {
		return createdCount;
	}

	public int getDestroyedCount() {
		return destroyedCount;
	}

	public int getMaxCreationTime() {
		return maxCreationTime;
	}

	public int getMaxUsedCount() {
		return maxUsedCount;
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public int getTimedOut() {
		return timedOut;
	}

	public int getTotalBlockingTime() {
		return totalBlockingTime;
	}

	public int getTotalCreationTime() {
		return totalCreationTime;
	}
	
	private static final Logger log = Logger.getLogger(ManagementRESTConnector.class.getName());
	
	public static void main(String args[])
	{
		
		ManagementRESTConnector con = new ManagementRESTConnector();
		//con.execute("http://test.lab.gomez.com:9990/management/subsystem/datasources/data-source/apmweb-liferayDB-mssql-ds/statistics/pool?include-runtime=true", "liferay", "l1f3r@y", "9990");

		// Note: 1. change standalone/configuration/standalone.xml 
		//     <inet-address value="${jboss.bind.address.management:127.0.0.1}"/>
		//  to:
		//     <any-address/>
		//       2. run standalone.sh
		//       3. run add-user.sh (for "Management user")
		con.execute("http://192.168.1.58:9990/management/subsystem/datasources/data-source/ExampleDS/statistics/pool?include-runtime=true", "admin", "pass");

	}

	public Status.StatusCode execute(String url, final String username, final String password) {

		log.fine("URL : " + url);
		// set default authenticator
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password
						.toCharArray());
			}
		});

		// get URL
		URL dsURL;
		try {
			dsURL = new URL(url);

			URLConnection conn = dsURL.openConnection();
			BufferedReader r = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuffer line = new StringBuffer(); 
			line.append(r.readLine());
			String lineString = line.toString();
		
			//System.out.println("line:" + line);
			//System.out.println("lineString:" + lineString);
			
			Gson gson = new Gson();
			DSStatistics stats = gson.fromJson(lineString, DSStatistics.class);  
			
			//System.out.println("stats:" + stats.toString());
			
			activeCount = stats.getActiveCount();			
			availableCount = stats.getAvailableCount();
			averageBlockingTime = stats.getAverageBlockingTime();
			averageCreationTime = stats.getAverageCreationTime();
			createdCount = stats.getCreatedCount();
			destroyedCount = stats.getDestroyedCount();
			maxCreationTime = stats.getMaxCreationTime();
			maxUsedCount = stats.getMaxUsedCount();
			maxWaitTime = stats.getMaxWaitTime();
			timedOut = stats.getTimedOut();
			totalBlockingTime = stats.getTotalBlockingTime();
			totalCreationTime = stats.getTotalCreationTime();
			
			r.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Status.StatusCode.ErrorInternalResourceUnavailable;
		}

		return Status.StatusCode.Success;
	}
	
}
