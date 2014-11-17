package com.socialnetwork.spring;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialnetwork.spring.model.BodyRequest;
import com.socialnetwork.spring.model.Organization;
import com.socialnetwork.spring.service.OrganizationService;

@Controller
public class OrganizationController {
/*
 * 	
 */
	
	
	/*
	 * to update organisation
	 * 
	 */
	@Autowired
	private OrganizationService organizationService;
	
	
	@RequestMapping(value= "/organization/{id2}", method = RequestMethod.POST)
	@Consumes("application/json")
    @Produces("application/json")
    public  @ResponseBody HashMap<String, Object>  updateOrganization(@RequestBody BodyRequest bodyRequest,HttpServletRequest request,@PathVariable("id2") String id2){
		
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
	//	Long id=Long.parseLong(id2);
		Long id=Long.parseLong("1");

		
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
			inputOrg.setId(id);
			organizationService.updateOrg(inputOrg);
			System.out.println("saved org"+savedOrg);
			responseMap.put("status", "done");
			
		}
			/**/
		
		
		return responseMap;
		
	}

	
	/*
	 * add new organisation
	 * 
	 */
	
	
	@RequestMapping(value= "/organization/addNewOrganization", method = RequestMethod.POST)
	@Consumes("application/json")
    @Produces("application/json")
    public  @ResponseBody HashMap<String, Object>  addNewOrganization(@RequestBody BodyRequest bodyRequest,HttpServletRequest request){
		
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
	
	
	
	/*
	 * to remove organisation
	 * 
	 */
	@Consumes("application/json")
	@Produces("application/json")
	@RequestMapping(value ="/removeOrg/{id}", method = RequestMethod.POST)
	public @ResponseBody String removeOrg(@PathVariable("id") String id){

	Long id2=Long.parseLong("1");
	this.organizationService.removeOrg(id2);
	return "Success";
	}
	
	
	/*
	 * T
	 * 
	 */
	
	@Consumes("application/json")
    @Produces("application/text")
	@RequestMapping(value = "/listOrg/{id2}", method = RequestMethod.GET)
//	public @ResponseBody HashMap<String, Object> listPersons(@PathVariable("id2") String id2,Model model,HttpServletRequest request)
		public @ResponseBody String listOrganisation(@PathVariable("id2") String id2,Model model,HttpServletRequest request) {

	{
		
		
	//	Long id=Long.parseLong(id2);
		Long id=Long.parseLong("1");
		System.out.println("long val"+id);
		
		
	HashMap<String, Object> responseMap = new HashMap<String, Object>();
	Organization founOrg;
	if(id==0){
		responseMap.put("errorCode", 400);
		responseMap.put("errorMsg", "invalid parameters");
		return "0";
		
	}
	else
	{
		 founOrg=organizationService.getOrgById(id);
		responseMap.put("foundOrg", founOrg);
		String response="";
		response+="desc:"+founOrg.getDescription()+"\n";
		response+="name"+founOrg.getName()+"\n";
		response+=""+founOrg.getAddress();
		
		return response;

		
	}
		//return responseMap;
	}
	
	/* @RequestMapping("/edit/{id}")
	    public String editPerson(@PathVariable("id") int id, Model model){
	        model.addAttribute("person", this.personService.getPersonById(id));
	        model.addAttribute("listPersons", this.personService.listPersons());
	        return "person";
	    }*/
	

	
	
	}
}
