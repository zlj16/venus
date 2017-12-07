package com.meidusa.venus.hello.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import com.meidusa.venus.hello.api.ParameterizedService;
import com.meidusa.venus.hello.entity.EnumType;
import com.meidusa.venus.hello.entity.Hello;

public class DefaultParameterizedService implements ParameterizedService {

	@Override
	public long[] arrayLong(Long[] hello) {
		return ArrayUtils.toPrimitive(hello);
	}

	@Override
	public Long[] arraylong(long[] hello) {
		return ArrayUtils.toObject(hello);
	}

	@Override
	public Map<String, Hello> getMap() {
		Map<String,Hello> helloMap = new HashMap<String,Hello>();
		
		Hello hello = new Hello();
		hello.setAge(1001);
		hello.setName("asdfqwerqwer");
		hello.setGreeting("venus服务框架欢迎您。。。");
		Map<String,EnumType> map = new HashMap<String,EnumType>();
		hello.setMap(map);
		hello.setType(EnumType.HELLO);
		map.put("1", EnumType.ABCD);
		map.put("2", EnumType.HELLO);
		hello.setBigDecimal(new BigDecimal("1.341241233412"));
		
		helloMap.put("abcd", hello);
		return helloMap;
	}

	@Override
	public List<Hello> getList() {
		
		Hello hello = new Hello();
		hello.setAge(1001);
		hello.setName("asdfqwerqwer");
		hello.setGreeting("venus服务框架欢迎您。。。");
		Map<String,EnumType> map = new HashMap<String,EnumType>();
		hello.setMap(map);
		hello.setType(EnumType.HELLO);
		map.put("1", EnumType.ABCD);
		map.put("2", EnumType.HELLO);
		hello.setBigDecimal(new BigDecimal("1.341241233412"));
		
		List<Hello> list = new ArrayList<Hello>();
		
		list.add(hello);
		
		return list;
		
	}

	@Override
	public Set<String> testSet(List<String> set) {
		Set<String> s = new HashSet<String>();
		s.addAll(set);
		return s;
	}

}
