package cn.ce.framework.core.client;

import org.apache.commons.lang3.StringUtils;

public class MinaConfig {
	private static MinaLoadPrpo config = MinaLoadPrpo.getInstance();

	public static final String DEFAULT_DATE_FORMAT = StringUtils.isNotBlank(config.getValue("default_date_format"))
			? config.getValue("default_date_format")
			: "yyyy-MM-dd HH:mm:ss";

	public static final String SERVER_IP = config.getValue("server_ip");

	public static final int SERVER_PORT = config.getIntValue("server_port");

	public static final int CONNECT_TIMEOUT = config.getIntValue("connect_timeout");

	public static final int TRANS_DATA_SIZE = config.getIntValue("trans_data_size");

	public static final int READBUFFER_MAX_SIZE = config.getIntValue("readbuffer_max_size");

	public static final int WAIT_OUT_TIME = config.getIntValue("wait_out_time");

	public static String getServerIP(String serverName) {
		return config.getValue("server_ip_" + serverName);
	}

	public static Integer getServerPort(String serverName) {
		return Integer.valueOf(config.getIntValue("server_port_" + serverName));
	}
}