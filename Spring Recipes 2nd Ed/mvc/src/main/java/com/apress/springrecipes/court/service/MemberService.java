package com.apress.springrecipes.court.service;

import java.util.List;

import com.apress.springrecipes.court.domain.Member;

public interface MemberService {

    public void add(Member member);
    public void remove(String memberName);
    public List<Member> list();
}
