package cn.ce.test.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.ce.framework.core.tools.JsonMapping;
import cn.ce.framework.core.tools.ResponseData;
import cn.ce.test.modle.User;

@Service
public class TestAA {
	private final Logger LOG = Logger.getLogger(TestAA.class);

	@JsonMapping(mappingMemo = "测试方法", mappingName = "test.testservice", autoJson = true)
	public ResponseData TestSevice(String json) {
		System.out.println(json);
		try {
			return ResponseData.newOk("获取用户信息", this.testUser());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return ResponseData.newError();
		}
	}

	public List testUser() {
		ArrayList a1 = new ArrayList();
		User u1 = new User();
		u1.setId("1");
		u1.setEage("2");
		u1.setName("3");
		a1.add(u1);
		return a1;
	}
}
