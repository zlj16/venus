package com.meidusa.venus.hello.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.meidusa.venus.annotations.Endpoint;
import com.meidusa.venus.annotations.Param;
import com.meidusa.venus.annotations.Service;
import com.meidusa.venus.hello.entity.Hello;

@Service(name="ParameterizedService",version=1)
public interface ParameterizedService {
	@Endpoint(name="arrayLong")
	public long[] arrayLong(@Param(name="hello") Long[] hello);
	
	@Endpoint(name="arraylong")
	public Long[] arraylong(@Param(name="hello") long[] hello);
	
	@Endpoint(name="getMap")
	Map<String,Hello> getMap();
	
	@Endpoint(name="testSet")
	Set<String> testSet(@Param(name="set") List<String> set);
	
	@Endpoint(name="getList")
	List<Hello> getList();
}
