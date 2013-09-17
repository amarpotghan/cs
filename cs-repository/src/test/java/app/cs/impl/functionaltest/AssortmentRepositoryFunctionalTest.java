package app.cs.impl.functionaltest;

import java.io.File;
import java.io.Serializable;

import junit.framework.TestCase;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.impl.assortment.AssortmentRepository;
import app.cs.interfaces.assortment.IAssortmentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-test.xml")
public class AssortmentRepositoryFunctionalTest {
	

	@Autowired
	private IAssortmentRepository assortmentRepository;
	
	@Test
	public void createAssortmentFunctionalUnitTest(){
		try {
			MultiDimensionalObject object = new ObjectMapper().readValue(new File(
					"src\\test\\resources\\JSONData\\CreateAssortment.json"),
					MultiDimensionalObject.class);
			
			
			System.out.println("exception not caught");
			assortmentRepository.save(object);

						
			}catch (Exception e) {
			e.printStackTrace();
			TestCase.fail();

		}
		
	}

	
	@Test
	public void copyAssortmentFunctionalUnitTest(){
		
		try {
			
			MultiDimensionalObject object = new ObjectMapper().readValue(new File(
					"src\\test\\resources\\JSONData\\CopyAssortment.json"),
					MultiDimensionalObject.class);
			
			
			System.out.println("exception not caught");
			assortmentRepository.copy(object);
 
			
		} catch (Exception e) {
			e.printStackTrace();
			TestCase.fail();
		}
	}

	
	
	
	@Test
	public void updateAssortmentFunctionalUnitTest(){
		
	try {
			
		MultiDimensionalObject object = new ObjectMapper().readValue(new File(
				"src\\test\\resources\\JSONData\\UpdateAssortment.json"),
				MultiDimensionalObject.class);
		
		
		System.out.println("exception not caught");
		assortmentRepository.updateAssortment(object);
			
		} catch (Exception e) {
			e.printStackTrace();
			TestCase.fail();
		}
    }
}
	
	




