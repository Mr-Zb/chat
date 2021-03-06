package com.fun.framework.utils;

import com.fun.framework.utils.support.sequence.Sequence;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.Striped;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@NoArgsConstructor(staticName = "of")
public final class Constants {
	public static boolean init = false;
	public static AtomicInteger memIdAtomic;
	//
	public static final String KEY_VALIDATION = "_a";
	public static final Sequence sequence = new Sequence(0, 0);
	public static final Map<String, String> params = Maps.newHashMap();

	public static final Cache<Long, String> sessions = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).initialCapacity(10000).maximumSize(100000).build();
	public static final String SESSIONIDS = "sessionids";
	public static final String TOKEN = "token";
	public static final String ADMINTOKEN = "admintoken";
	public static final String BLACKSTATE = "blackstate";
	public static final String SYSINFO = "sysinfo";
	public static final String ONLINE_STATUS = "onlinestatus";
	public static final String LOTLIST = "lotlist";
	public static final String MEM_INFO = "MEM-INFO";

	//	public static final Cache<Long, Date> sessionIds = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).initialCapacity(10000).maximumSize(100000).build();
//	public static final Cache<String, Long> tokens = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).initialCapacity(10000).maximumSize(100000).build();
	// 授权码
	public static final Map<String, String> AUTHORITY_CODES = Maps.newHashMap();
	public static final Map<String, String> AUTHORITY_NAMES = Maps.newHashMap();
	static {
		AUTHORITY_CODES.put("101", "le:admin");
		AUTHORITY_NAMES.put("101", "编辑");
	}

	public final static RateLimiter limiter = RateLimiter.create(10);// 限流
	public final static Lock lock = new ReentrantLock(true);// 全局锁
	public final static Striped<Lock> striped = Striped.lazyWeakLock(15590);// 局部锁
}
