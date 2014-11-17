package com.socialnetwork.spring;

import java.awt.PageAttributes.MediaType;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialnetwork.spring.model.BodyRequest;
import com.socialnetwork.spring.model.Person;
import com.socialnetwork.spring.service.PersonService;

@Controller
public class PersonController {
	
	private PersonService personService;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService ps){
		this.personService = ps;
	}
	

	/*New Person object is added into the organization table in the database
	 * And Person object is returned back
	 * @param - Person Org
	 * @return - org object
	 * (non-Javadoc)
	 * #addOrg(com.socialnetwork.spring.model.Person)
	 */			
	public String addPerson(@ModelAttribute("person") Person p){
		
		if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}
		
		return "";
		
	}
	 
	
	
	/*New Person object is removed from the organization table in the database
	 * And Person object is returned back
	 * @param - Person Org
	 * @return - org object
	 * (non-Javadoc)
	 * #addOrg(com.socialnetwork.spring.model.Person)
	 */			
		@Consumes("application/json")
	    @Produces("application/json")
	@RequestMapping("/remove/{id}")
    public  @ResponseBody HashMap<String, Object>  removePerson(@PathVariable("id") int id){
			HashMap<String, Object> responseMap = new HashMap<String, Object>();

        this.personService.removePerson(id);
        responseMap.put("updateStatus", "done");

        return responseMap;
    }
		
		
		
		/*New Person object is edit into the organization table in the database
		 * And Person object is returned back
		 * @param - Person Org
		 * @return - org object
		 * (non-Javadoc)
		 * #addOrg(com.socialnetwork.spring.model.Person)
		 */		
 
	@Consumes("application/json")
    @Produces("application/json")
    @RequestMapping("/edit/{id}")
    public  @ResponseBody HashMap<String, Object> editPerson(@PathVariable("id") int id,@RequestBody BodyRequest bodyRequest, Model model){
		
		HashMap<String, Object> responseMap = new HashMap<String, Object>();

		Person p=bodyRequest.getPerson();
		
        personService.updatePerson(p);
        
        responseMap.put("updateStatus", "done");
        
        return responseMap;
    }
	
    
	
    @RequestMapping(value= "/person/addNewPerson", method = RequestMethod.POST)
	@Consumes("application/json")
    @Produces("application/json")
    public  @ResponseBody HashMap<String, Object>  addNewPerson(@ModelAttribute("person") Person p,@RequestBody BodyRequest bodyRequest,HttpServletRequest request){
		
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		
		/*if(bodyRequest.getPerson().getEmail()==null||bodyRequest.getPerson().getEmail()==""||
				bodyRequest.getPerson().getLastname()==null||bodyRequest.getPerson().getLastname()==""||
				bodyRequest.getPerson().getFirstname()==null||bodyRequest.getPerson().getFirstname()==""){
			
			responseMap.put("errorCode", 400);
			responseMap.put("errorMsg", "invalid parameters");
			return responseMap;
		}
		*/
		Person inputPerson=bodyRequest.getPerson();
		System.out.println("got user "+inputPerson.getFirstname());
		
		
		String personEmailId = personService.getPersonByEmail2(inputPerson);
		
		System.out.println("person saved");
		
		Person savedPerson=null;
		if(personEmailId==null){
		
			System.out.println("person doesnot exist");
			savedPerson=personService.addPerson(inputPerson);
		
			responseMap.put("savedUser", savedPerson);
		}
		
		return responseMap;
		
	}
    
    
    

    

    @RequestMapping(value= "/person/getPerson", method = RequestMethod.POST)
	@Consumes("application/json")
    @Produces("application/json")
    public  @ResponseBody HashMap<String, Object>  getPerson(@ModelAttribute("person") Person p,@RequestBody BodyRequest bodyRequest,HttpServletRequest request){
		
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		
		/*if(bodyRequest.getPerson().getEmail()==null||bodyRequest.getPerson().getEmail()==""||
				bodyRequest.getPerson().getLastname()==null||bodyRequest.getPerson().getLastname()==""||
				bodyRequest.getPerson().getFirstname()==null||bodyRequest.getPerson().getFirstname()==""){
			
			responseMap.put("errorCode", 400);
			responseMap.put("errorMsg", "invalid parameters");
			return responseMap;
		}
		*/
		Person inputPerson=bodyRequest.getPerson();
		System.out.println("got user "+inputPerson.getFirstname());
		
		
		Person personGot = personService.getPersonById((bodyRequest.getPerson().getId()));
		responseMap.put("savedUser", personGot);
		
		System.out.println("person saved");
		

		
		return responseMap;
		
	}
    
    
    @RequestMapping(value= "/friends/{id1}/{id2}", method = RequestMethod.POST)

    @Produces("application/json")
    public @ResponseBody HashMap<String, Object> addFriend(@PathVariable("id1") String id1,@PathVariable("id2") String id2 ,HttpServletRequest request){

    HashMap<String, Object> responseMap = new HashMap<String, Object>();

    System.out.println(id1);
    System.out.println(id2);
    int id3=Integer.parseInt(id1);
    int id4=Integer.parseInt(id2);

    Person p1=personService.getPersonById(id3);
    Person p2=personService.getPersonById(id4);

    if(p1==null&&p2==null)
    {
    System.out.println("Invalid Request");
    }
    else
    {
    personService.addFriend(id3, id4);
    }
    responseMap.put("Status", "Success");
    return responseMap;

    }

    
    
    
    
}
