package com.yh.mapper;

import com.yh.model.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MemberMapper {
    List<Member>  findAll();
}
