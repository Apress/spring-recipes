//FINAL 
package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.domain.Member;
import com.apress.springrecipes.court.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@Controller
// Bind controller to all URLs under/member/* 
// initial view will be resolved to the name returned in the default GET method
@RequestMapping("/member/*")
@SessionAttributes("guests")
public class MemberController {
    // JSR-303 validator
    private static Validator validator;
    private MemberService memberService;

    // Wire service and validator in constructor, available in application context 
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

        // Initialize JSR-303 bean validation
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    // Controller will always look for a default GET method to call first, irrespective of name
    // In this case, named setupForm to ease identification
    @RequestMapping(method = RequestMethod.GET)
    // Method contains Model input to setup member object
    public String setupForm(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("guests", memberService.list());

        // Return view memberList. Via resolver the view
        // will be mapped to /WEB-INF/jsp/memberList.jsp
        return "memberList";
    }

    // Controller will always look for a default POST method irrespective of name
    // when a submission ocurrs on the URL (i.e.@RequestMapping(/member/*)) 
    // In this case, named submitForm to ease identification
    @RequestMapping(method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("member")
    Member member, BindingResult result, Model model) {
        // Get JSR-303 errors ( See domain Member class for annotations validation) 
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        // Loop over possible errors
        for (ConstraintViolation<Member> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult so Spring can display them in view via a FieldError
            result.addError(new FieldError("member", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
        }

        // Use JSR-303 violations.size() == 0 or standard Spring result.hasErrors() which now has the JSR-303 errors
        if (!result.hasErrors()) {
            // No errors
            memberService.add(member);
            model.addAttribute("guests", memberService.list());

            // NOTE THAT SessionStatus.setComplete(); is NEVER called 
            // This allow the memberList to grow since its stored in session
            // calling SessionStatus.setComplete() would destroy values in memberList
            // Return view memberList. Via resolver the view
            // will be mapped to /WEB-INF/jsp/memberList.jsp
            return "memberList";
        } else {
            // Return view memberList so user can correct errors. Via resolver the view
            // will be mapped to /WEB-INF/jsp/memberList.jsp
            return "memberList";
        }
    }

    // Method mapped to URL /member/remove
    @RequestMapping("remove")
    public String removeMember(@RequestParam("memberName")
    String memberName) {
        memberService.remove(memberName);

        // Use redirect so list is refreshed
        // Since the Controller uses a wildcard, any URL will do, use root ':'(i.e./member/)
        return "redirect:";
    }
}
