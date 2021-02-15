package com.ex.test.com.ex.ssm.service.impl;

import com.ex.test.com.ex.ssm.entity.Member;
import com.ex.test.com.ex.ssm.mapper.MemberMapper;
import com.ex.test.com.ex.ssm.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author ttfor
 * @since 2021-02-09
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
}