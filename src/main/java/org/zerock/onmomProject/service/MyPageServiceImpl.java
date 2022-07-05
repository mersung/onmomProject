//package org.zerock.onmomProject.service;
//
//import lombok.RequiredArgsConstructor;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.zerock.onmomProject.dto.*;
//import org.zerock.onmomProject.entity.FreeBoard;
//import org.zerock.onmomProject.entity.Member;
//import org.zerock.onmomProject.entity.ReviewBoard;
//import org.zerock.onmomProject.repository.FreeBoardRepository;
//import org.zerock.onmomProject.repository.MemberRepository;
//import org.zerock.onmomProject.repository.ReviewBoardRepository;
//
//import java.util.Optional;
//import java.util.function.Function;
//
//@Service
//@RequiredArgsConstructor
//@Log4j2
//public class MyPageServiceImpl implements MyPageService{
//
//
//    private final MemberService memberService;
//    private final ReviewBoardService reviewBoardService;
//    private final FreeBoardService freeBoardService;
//    private final MemberRepository memberRepository;
//    private final FreeBoardRepository freeBoardRepository;
//    private final ReviewBoardRepository reviewBoardRepository;
//
//    @Override
//    public MemberDTO selectMember(String member_id) {
//
//        Optional<Member> member = memberRepository.findByEmail(member_id);
//        return member.isPresent() ? memberService.entityToDto(member.get()) : null;
//    }
//
//
//    @Override
//    public FreePageResultDTO<FreeBoardDTO, Object[]> getMyPost(String member_id, FreePageRequestDTO freePageRequestDTO) {
//        log.info(freePageRequestDTO);
//
//        Function<Object[], FreeBoardDTO> fn = (en ->
//                freeBoardService.entityToDTO((FreeBoard) en[0], (Member) en[1], (Long) en[2]));
//
//        Page<Object[]> result = freeBoardRepository.getMyPostByMember_id(member_id,
//                freePageRequestDTO.getPageable(Sort.by("free_id").descending()));
//
//        return new FreePageResultDTO<>(result, fn);
//    }
//
//    @Override
//    public ReviewPageResultDTO<ReviewBoardDTO, Object[]> getMyPost(String member_id, ReviewPageRequestDTO reviewPageRequestDTO) {
//        log.info(reviewPageRequestDTO);
//
//        Function<Object[], ReviewBoardDTO> fn = (en ->
//                reviewBoardService.entityToDTO((ReviewBoard) en[0], (Member) en[1], (Long) en[2]));
//
//        Page<Object[]> result = reviewBoardRepository.getMyPostByMember_id(member_id,
//                reviewPageRequestDTO.getPageable(Sort.by("review_id").descending()));
//
//        return new ReviewPageResultDTO<>(result, fn);
//    }
//}
