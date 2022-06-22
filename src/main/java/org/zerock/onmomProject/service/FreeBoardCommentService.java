//package org.zerock.onmomProject.service;
//
//import org.zerock.onmomProject.dto.FreeBoardCommentDTO;
//import org.zerock.onmomProject.dto.MemberDTO;
//import org.zerock.onmomProject.entity.FreeBoard;
//import org.zerock.onmomProject.entity.FreeBoardComment;
//import org.zerock.onmomProject.entity.Member;
//import org.zerock.onmomProject.entity.QMember;
//
//import java.util.List;
//
//public interface FreeBoardCommentService {
//
//    Long register(FreeBoardCommentDTO freeBoardCommentDTO);// 댓글의 등록
//
//    List<FreeBoardCommentDTO> getList(Long free_id);//특정 게시물 댓글목
//
//    void modify(FreeBoardCommentDTO freeBoardCommentDTO);
//
//    void remove(Long comment_id);
//
//    default FreeBoardComment dtoToEntity(FreeBoardCommentDTO freeBoardCommentDTO){
//
//        FreeBoard freeBoard = FreeBoard.builder().free_id(FreeBoardCommentDTO.getFree_id()).
//                build();
//
//        Member member = Member.builder().member_id(MemberDTO.getMember_id()).build();
//
//        FreeBoardComment freeBoardComment = FreeBoardComment.builder()
//                .comment_id(FreeBoardCommentDTO.getComment_id())
//                .member(Member)
//                .board(freeBoard)
//                .content(FreeBoardCommentDTO.getContent())
//                .build();
//        return freeBoardComment;
//    }
//
//    default FreeBoardCommentDTO entityToDTO(FreeBoardComment freeBoardComment){
//
//
//        FreeBoardCommentDTO freeBoardCommentDTO = FreeBoardCommentDTO.builder()
//                .comment_id(freeBoardComment.getComment_id())
//                .content(freeBoardComment.getContent())
//                .regDate(freeBoardComment.getRegDate())
//                .modDate(freeBoardComment.getModDate())
//                .build();
//        return freeBoardCommentDTO;
//    }
//}
