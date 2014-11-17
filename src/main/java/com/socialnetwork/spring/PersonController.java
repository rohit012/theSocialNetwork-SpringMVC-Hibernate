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
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}
	
	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST 
			)
	public String addPerson(@ModelAttribute("person") Person p){
		
		if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}
		
		return "redirect:/persons";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.personService.removePerson(id);
        return "redirect:/persons";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
	
    
	
    @RequestMapping(value= "/person/addNewPerson", method = RequestMethod.POST)
	@Consumes("application/json")
    @Produces("application/json")
    public  @ResponseBody HashMap<String, Object>  addNewPerson(@ModelAttribute("person") Person p,@RequestBody BodyRequest bodyRequest,HttpServletRequest request){
		
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		
		if(bodyRequest.getEmail()==null||bodyRequest.getEmail()==""||
				bodyRequest.getLastname()==null||bodyRequest.getLastname()==""||
				bodyRequest.getFirstname()==null||bodyRequest.getFirstname()==""){
			
			responseMap.put("errorCode", 400);
			responseMap.put("errorMsg", "invalid parameters");
			return responseMap;
		}
		
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
    
}
