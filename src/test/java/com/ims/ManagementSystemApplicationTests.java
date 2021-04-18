package com.ims;

import com.ims.beans.Client;
import com.ims.beans.Condition;
import com.ims.beans.Threshold;
import com.ims.repository.ClientRepository;
import com.ims.repository.ConditionRepository;
import com.ims.repository.IncentiveRepository;
import com.ims.repository.ThresholdRepository;
import com.ims.service.IncentiveService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class ManagementSystemApplicationTests {

	@Mock
	private ClientRepository clrMock;

	@Mock
	private ConditionRepository crMock;

	@Mock
	private ThresholdRepository thrMock;

	@Mock
	private IncentiveRepository isMock;

	@InjectMocks
	private IncentiveService is;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_incentive_fail() {
		List<Client> clientList = new ArrayList<Client>();
		clientList.add(new Client(1, "userName", "password"));

		Map<String, String> params = new HashMap<String, String>();
		params.put("key", "test");

		List<Condition> clist = new ArrayList<Condition>();
		clist.add( new Condition(1, 1, "cond", "category"));

		List<Threshold> tlist = new ArrayList<Threshold>();
		tlist.add(new Threshold(1, 1, "thr", "param"));

		when(crMock.getConditionsByIncentive(Mockito.anyInt())).thenReturn(clist);
		when(clrMock.getClientByAuthCode(Mockito.anyString())).thenReturn(clientList);
		when(thrMock.getThresholdsByCondition(Mockito.anyInt())).thenReturn(tlist);

		assertEquals(-1, is.incentiveFulfilled(1, params));
	}

	@Test
	public void test_incentive_pass_isBoolean() {
		List<Client> clientList = new ArrayList<Client>();
		clientList.add(new Client(1, "userName", "password"));

		Map<String, String> params = new HashMap<String, String>();
		params.put("key", "test");
		params.put("thr", "true"); // Passing in a boolean threshold

		List<Condition> clist = new ArrayList<Condition>();
		clist.add( new Condition(1, 1, "cond", "category"));

		List<Threshold> tlist = new ArrayList<Threshold>();
		tlist.add(new Threshold(1, 1, "true", "thr")); // Boolean threshold

		when(crMock.getConditionsByIncentive(Mockito.anyInt())).thenReturn(clist);
		when(clrMock.getClientByAuthCode(Mockito.anyString())).thenReturn(clientList);
		when(thrMock.getThresholdsByCondition(Mockito.anyInt())).thenReturn(tlist);

		assertEquals(200 , is.incentiveFulfilled(1, params)); // Successful incentive fulfillment
	}

	@Test
	public void test_incentive_pass_isDouble() {
		List<Client> clientList = new ArrayList<Client>();
		clientList.add(new Client(1, "userName", "password"));

		Map<String, String> params = new HashMap<String, String>();
		params.put("key", "test");
		params.put("thr", "1"); // Passing in an int threshold

		List<Condition> clist = new ArrayList<Condition>();
		clist.add( new Condition(1, 1, "cond", "category"));

		List<Threshold> tlist = new ArrayList<Threshold>();
		tlist.add(new Threshold(1, 1, "1", "thr")); // int threshold

		when(crMock.getConditionsByIncentive(Mockito.anyInt())).thenReturn(clist);
		when(clrMock.getClientByAuthCode(Mockito.anyString())).thenReturn(clientList);
		when(thrMock.getThresholdsByCondition(Mockito.anyInt())).thenReturn(tlist);

		assertEquals(200 , is.incentiveFulfilled(1, params)); // Successful incentive fulfillment
	}

	@Test
	public void test_incentive_pass_remove_incentive() {
		when(isMock.existsById(Mockito.anyInt())).thenReturn(false); // incv doesn't exist

		assertEquals(-1, is.removeIncentive(1));

		when(isMock.existsById(Mockito.anyInt())).thenReturn(true); // incv exists for removal

		assertEquals(200, is.removeIncentive(1));
	}

}