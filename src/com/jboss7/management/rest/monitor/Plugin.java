
 /**
  * This template file was generated by dynaTrace client.
  * The dynaTrace community portal can be found here: http://community.dynatrace.com/
  * For information how to publish a plugin please visit http://community.dynatrace.com/plugins/contribute/
  **/ 

package com.jboss7.management.rest.monitor;

import com.dynatrace.diagnostics.pdk.*;

import java.util.Collection;
import java.util.logging.Logger;


public class Plugin implements Monitor {

	private static final Logger log = Logger.getLogger(Plugin.class.getName());
	
	private static final String METRIC_GROUP = "Jboss7 DS Statistics Metric Group";
	private static final String MSR_EXEC_SUCCESS = "ActiveCount";	


	/**
	 * Initializes the Plugin. This method is called in the following cases:
	 * <ul>
	 * <li>before <tt>execute</tt> is called the first time for this
	 * scheduled Plugin</li>
	 * <li>before the next <tt>execute</tt> if <tt>teardown</tt> was called
	 * after the last execution</li>
	 * </ul>
	 * <p>
	 * If the returned status is <tt>null</tt> or the status code is a
	 * non-success code then {@link Plugin#teardown() teardown()} will be called
	 * next.
	 * <p>
	 * Resources like sockets or files can be opened in this method.
	 * @param env
	 *            the configured <tt>MonitorEnvironment</tt> for this Plugin;
	 *            contains subscribed measures, but <b>measurements will be
	 *            discarded</b>
	 * @see Plugin#teardown()
	 * @return a <tt>Status</tt> object that describes the result of the
	 *         method call
	 */
	@Override
	public Status setup(MonitorEnvironment env) throws Exception {
		// TODO
		return new Status(Status.StatusCode.Success);
	}

	/**
	 * Executes the Monitor Plugin to retrieve subscribed measures and store
	 * measurements.
	 *
	 * <p>
	 * This method is called at the scheduled intervals. If the Plugin execution
	 * takes longer than the schedule interval, subsequent calls to
	 * {@link #execute(MonitorEnvironment)} will be skipped until this method
	 * returns. After the execution duration exceeds the schedule timeout,
	 * {@link TaskEnvironment#isStopped()} will return <tt>true</tt>. In this
	 * case execution should be stopped as soon as possible. If the Plugin
	 * ignores {@link TaskEnvironment#isStopped()} or fails to stop execution in
	 * a reasonable timeframe, the execution thread will be stopped ungracefully
	 * which might lead to resource leaks!
	 *
	 * @param env
	 *            a <tt>MonitorEnvironment</tt> object that contains the
	 *            Plugin configuration and subscribed measures. These
	*            <tt>MonitorMeasure</tt>s can be used to store measurements.
	 * @return a <tt>Status</tt> object that describes the result of the
	 *         method call
	 */
	@Override
	public Status execute(MonitorEnvironment env) throws Exception {
		
		ManagementRESTConnector restCon = new ManagementRESTConnector();
		
		String host = env.getHost().getAddress();
		String port = env.getConfigString("managementPort");
		String dsName = env.getConfigString("datasourceName");
		String DSMeasure = env.getConfigString("DSMeasure");
		
		String url = "http://" + host + ":" + port + "/management/subsystem/datasources/data-source/" + dsName + "/statistics/pool?include-runtime=true";
		String username = env.getConfigString("jbossusername");
		String password = env.getConfigString("jbosspassword");
		Status.StatusCode status = restCon.execute(url, username, password, DSMeasure);
		
		Collection<MonitorMeasure> measures;
		if ((measures = env.getMonitorMeasures(METRIC_GROUP, MSR_EXEC_SUCCESS)) != null) {
			for (MonitorMeasure measure : measures){
				measure.setValue(restCon.getActiveCount());
			}
				
		}
		return new Status(Status.StatusCode.Success);
	}

	/**
	 * Shuts the Plugin down and frees resources. This method is called in the
	 * following cases:
	 * <ul>
	 * <li>the <tt>setup</tt> method failed</li>
	 * <li>the Plugin configuration has changed</li>
	 * <li>the execution duration of the Plugin exceeded the schedule timeout</li>
	 * <li>the schedule associated with this Plugin was removed</li>
	 * </ul>
	 *
	 * <p>
	 * The Plugin methods <tt>setup</tt>, <tt>execute</tt> and
	 * <tt>teardown</tt> are called on different threads, but they are called
	 * sequentially. This means that the execution of these methods does not
	 * overlap, they are executed one after the other.
	 *
	 * <p>
	 * Examples:
	 * <ul>
	 * <li><tt>setup</tt> (failed) -&gt; <tt>teardown</tt></li>
	 * <li><tt>execute</tt> starts, configuration changes, <tt>execute</tt>
	 * ends -&gt; <tt>teardown</tt><br>
	 * on next schedule interval: <tt>setup</tt> -&gt; <tt>execute</tt> ...</li>
	 * <li><tt>execute</tt> starts, execution duration timeout,
	 * <tt>execute</tt> stops -&gt; <tt>teardown</tt></li>
	 * <li><tt>execute</tt> starts, <tt>execute</tt> ends, schedule is
	 * removed -&gt; <tt>teardown</tt></li>
	 * </ul>
	 * Failed means that either an unhandled exception is thrown or the status
	 * returned by the method contains a non-success code.
	 *
	 *
	 * <p>
	 * All by the Plugin allocated resources should be freed in this method.
	 * Examples are opened sockets or files.
	 *
	 * @see Monitor#setup(MonitorEnvironment)
	 */	@Override
	public void teardown(MonitorEnvironment env) throws Exception {
		// TODO
	}
}