//FINAL 
package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RestMemberController {
    @RequestMapping("/members")
    public String getRestMembers(Model model) {
        // Return view membertemplate. Via resolver the view
        // will be mapped to a JAXB Marshler bound to the Member class
        Member member = new Member();
        member.setName("John Doe");
        member.setPhone("1-800-800-800");
        member.setEmail("john@doe.com");
        model.addAttribute("member", member);

        return "membertemplate";
    }
}
