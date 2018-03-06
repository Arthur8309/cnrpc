package cn.ce.test.client;

import com.google.gson.Gson;

import cn.ce.framework.core.client.MinaClientManager;
import cn.ce.framework.core.client.RequestMessage;
import cn.ce.framework.core.client.ResponseMessage;

public class ClientMain {
	public static void main(String[] args) throws Exception {
		/* clien config see minaConfig.properties */
		MinaClientManager minaClientMgr = MinaClientManager.getInstance();
		RequestMessage request = new RequestMessage("test.testservice", new Gson().toJson("[{id:1}]"));
		ResponseMessage response = minaClientMgr.getMessage(request);
		String data = response.getData();

		System.out.println(data);

		System.exit(0);
	}

}
