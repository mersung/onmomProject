package org.zerock.onmomProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.dto.FreeBoardCommentDTO;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.FreeBoardComment;
import org.zerock.onmomProject.repository.FreeBoardCommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FreeBoardCommentServiceImpl implements FreeBoardCommentService{

    private  final FreeBoardCommentRepository freeBoardCommentRepository;
    @Override
    public Long register(FreeBoardCommentDTO freeBoardCommentDTO) {

        FreeBoardComment freeBoardComment = dtoToEntity(freeBoardCommentDTO);

        freeBoardCommentRepository.save(freeBoardComment);

        return freeBoardComment.getComment_id();
    }

    @Override
    public List<FreeBoardCommentDTO> getList(Long free_id) {

        List<FreeBoardComment> result = freeBoardCommentRepository.getFreeBoardCommentsByFreeBoardOrderByFree_id(free_id);

        return result.stream().map(freeBoardComment -> entityToDTO(freeBoardComment)).collect(Collectors.toList());
    }

    @Override
    public void modify(FreeBoardCommentDTO freeBoardCommentDTO) {

        FreeBoardComment freeBoardComment = dtoToEntity(freeBoardCommentDTO);

        freeBoardCommentRepository.save(freeBoardComment);
    }

    @Override
    public void remove(Long comment_id) {
        freeBoardCommentRepository.deleteById(comment_id);
    }
}
