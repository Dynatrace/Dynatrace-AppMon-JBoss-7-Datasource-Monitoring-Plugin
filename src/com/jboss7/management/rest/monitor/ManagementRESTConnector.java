package com.jboss7.management.rest.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import com.dynatrace.diagnostics.pdk.Status;

public class ManagementRESTConnector {
	
	long activeCount = 0;
	
	private static final Logger log = Logger.getLogger(ManagementRESTConnector.class.getName());
	
	public static void main(String args[])
	{
		ManagementRESTConnector con = new ManagementRESTConnector();
		con.execute("http://test.lab.gomez.com:9990/management/subsystem/datasources/data-source/apmweb-liferayDB-mssql-ds/statistics/pool?include-runtime=true", "liferay", "l1f3r@y", "9990");
	}

	public Status.StatusCode execute(String url, final String username, final String password, String DSMeasure) {

		log.fine("URL : " + url);
		String retString = "0";
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
		
			int start = line.indexOf(DSMeasure);
			int end = line.indexOf(",", start+3);
			retString = line.substring(start+16, end-1);
			log.fine("Result : " + retString);
			
			r.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Status.StatusCode.ErrorInternalResourceUnavailable;
		}
		activeCount = Long.parseLong(retString);
		
		return Status.StatusCode.Success;
	}
	
	public long getActiveCount()
	{
		return activeCount;
	}

}
