package org.zerock.onmomProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.dto.FreeBoardDTO;
import org.zerock.onmomProject.dto.FreePageRequestDTO;
import org.zerock.onmomProject.dto.FreePageResultDTO;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.repository.FreeBoardCommentRepository;
import org.zerock.onmomProject.repository.FreeBoardRepository;
import org.zerock.onmomProject.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2

public class FreeBoardServiceImpl implements FreeBoardService {
    private final FreeBoardRepository freeBoardRepository;

    private final FreeBoardCommentRepository freeBoardCommentRepository;


    @Override
    public Long register(FreeBoardDTO dto) {

        log.info(dto);
        FreeBoard board = dtoToEntity(dto);

        freeBoardRepository.save(board);

        return board.getFree_id();

    }

    @Override
    public FreePageResultDTO<FreeBoardDTO, Object[]> getList(FreePageRequestDTO freePageRequestDTO) {

        log.info(freePageRequestDTO);

        Function<Object[], FreeBoardDTO> fn = (en ->
                entityToDTO((FreeBoard) en[0], (Member) en[1], (Long) en[2]));

        Page<Object[]> result = freeBoardRepository.FreeSearchPage(
                freePageRequestDTO.getType(),
                freePageRequestDTO.getKeyword(),
                freePageRequestDTO.getPageable(Sort.by("free_id").descending()));


        return new FreePageResultDTO<>(result, fn);
    }

    @Override
    public FreePageResultDTO<FreeBoardDTO, Object[]> getMyPost(String member_id, FreePageRequestDTO freePageRequestDTO) {
        log.info(freePageRequestDTO);

        Function<Object[], FreeBoardDTO> fn = (en ->
                entityToDTO((FreeBoard) en[0], (Member) en[1], (Long) en[2]));

        Page<Object[]> result = freeBoardRepository.getMyPostByMember_id(member_id,
                freePageRequestDTO.getPageable(Sort.by("member_id").descending()));


        return new FreePageResultDTO<>(result, fn);
    }

    @Override
    public FreeBoardDTO get(Long free_id) {
        Object result = freeBoardRepository.getFreeBoardByFree_id(free_id);
        Object[] arr = (Object[]) result;
        return entityToDTO((FreeBoard) arr[0], (Member) arr[1], (Long) arr[2]);
    }


    // 내가 쓴 글 불러오기
//    @Override
//    public FreeBoardDTO getMyPost(String member_id) {
//        log.info("===================================");
//        log.info(member_id);
//
//        Object result = freeBoardRepository.getMyPostByMember_id(member_id);
//        Object[] arr = (Object[])result;
//        FreeBoard entity = (FreeBoard)arr[1];
//        Member entity_2 = (Member)arr[0];
//
//        log.info(entity);
//        return entityToDTO(entity, entity_2, entity.getLike_cnt());
//
//    }


    @Transactional
    @Override
    public void removeWithReplies(Long free_id) {

        freeBoardCommentRepository.deleteByFree_id(free_id);

        freeBoardRepository.deleteById(free_id);

    }
    @Transactional
    @Override
    public void modify(FreeBoardDTO freeBoardDTO) {

        FreeBoard freeBoard = freeBoardRepository.getOne(freeBoardDTO.getFree_id());

        if (freeBoard != null){

            freeBoard.changeTitle(freeBoardDTO.getTitle());
            freeBoard.changeContent(freeBoardDTO.getContent());

            freeBoardRepository.save(freeBoard);
        }

    }

}
