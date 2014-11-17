package com.socialnetwork.spring;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialnetwork.spring.model.BodyRequest;
import com.socialnetwork.spring.model.Organization;
import com.socialnetwork.spring.model.Person;
import com.socialnetwork.spring.service.OrganizationService;
import com.socialnetwork.spring.service.PersonService;

@Controller
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping(value= "/organization/addNewOrganization", method = RequestMethod.POST)
	@Consumes("application/json")
    @Produces("application/json")
    public  @ResponseBody HashMap<String, Object>  addNewOrganization(@ModelAttribute("person") Person p,@RequestBody BodyRequest bodyRequest,HttpServletRequest request){
		
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		
		if(bodyRequest.getOrganization().getName()==null||bodyRequest.getOrganization().getName()==""){
			
			responseMap.put("errorCode", 400);
			responseMap.put("errorMsg", "invalid parameters");
			return responseMap;
		}
		
		Organization inputOrg=bodyRequest.getOrganization();
		System.out.println("got organisation "+inputOrg.getName());
		
		
		Organization savedOrg=null;
		
		if(inputOrg!=null){
			System.out.println("org not null");
			savedOrg=organizationService.addOrg(inputOrg);
			System.out.println("saved org"+savedOrg);
			responseMap.put("savedOrg", savedOrg);
			
		}
			if(savedOrg!=null)
			responseMap.put("savedUser", savedOrg);
			else 
				{responseMap.put("errorCode", 457);
				responseMap.put("errorMsg", "server Issue");
				
				}
		
		
		return responseMap;
		
	}
	
	@Consumes("application/json")
    @Produces("application/json")
	@RequestMapping(value = "/organization/{id2}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> listPersons(@PathVariable("id2") String id2,Model model) {
		
		
	//	Long id=Long.parseLong(id2);
		Long id=Long.parseLong("1");
		System.out.println("long val"+id);
		
		
	HashMap<String, Object> responseMap = new HashMap<String, Object>();
	Organization founOrg;
	if(id==0){
		responseMap.put("errorCode", 400);
		responseMap.put("errorMsg", "invalid parameters");
		return responseMap;
		
	}
	else
	{
		 founOrg=organizationService.getOrgById(id);
		responseMap.put("foundOrg", founOrg);

		
	}
		System.out.println("ORGANISATION"+founOrg.getName());
		return responseMap;
	}
	
	/* @RequestMapping("/edit/{id}")
	    public String editPerson(@PathVariable("id") int id, Model model){
	        model.addAttribute("person", this.personService.getPersonById(id));
	        model.addAttribute("listPersons", this.personService.listPersons());
	        return "person";
	    }*/
	
	
}
