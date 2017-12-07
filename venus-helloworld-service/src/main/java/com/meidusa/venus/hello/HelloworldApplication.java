package com.meidusa.venus.hello;

import com.meidusa.toolkit.common.runtime.Application;
import com.meidusa.toolkit.common.runtime.ApplicationConfig;

public class HelloworldApplication extends Application<ApplicationConfig> {

	@Override
	public void doRun() {

	}

	@Override
	public ApplicationConfig getApplicationConfig() {
		return null;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "file:${project.home}/conf/applicationContext-helloworld-server.xml"};
	}

	public static void main(String[] args) {
		System.setProperty(ApplicationConfig.PROJECT_MAINCLASS, HelloworldApplication.class.getName());
		Application.main(args);
	}


	

}
