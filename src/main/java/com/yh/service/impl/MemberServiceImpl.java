package com.yh.service.impl;

import com.yh.mapper.MemberMapper;
import com.yh.model.Member;
import com.yh.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }
}
