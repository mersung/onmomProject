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

import javax.transaction.Transactional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2

public class FreeBoardServiceImpl implements FreeBoardService{
    private final FreeBoardRepository freeBoardRepository;

    private final FreeBoardCommentRepository commentRepository;
    @Override
    public Long register(FreeBoardDTO dto) {

        FreeBoard board = dtoToEntity(dto);

        freeBoardRepository.save(board);

        return board.getFree_id();

    }

    @Override
    public FreePageResultDTO<FreeBoardDTO, Object[]> getList(FreePageRequestDTO freePageRequestDTO) {

        Function<Object[],FreeBoardDTO> fn = (en ->
                entityToDTO((FreeBoard)en[0],(Member)en[1],(Long)en[2]));

        Page<Object[]> result = freeBoardRepository.FreeSearchPage(
                freePageRequestDTO.getType(),
                freePageRequestDTO.getKeyword(),
                freePageRequestDTO.getPageable(Sort.by("free_id").descending()) );


       return new FreePageResultDTO<>(result, fn);
    }

    @Override
    public FreeBoardDTO get(Long free_id) {
        Object result = freeBoardRepository.getFreeBoardByFree_id(free_id);
        Object[] arr = (Object[])result;
        return entityToDTO((FreeBoard)arr[0], (Member)arr[1], (Long)arr[2]);
    }


    @Transactional
    @Override
    public void removeWithReplies(Long free_id) {

//        commentRepository.deleteByFree_id(free_id);
//
//        repository.deleteById(free_id);

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
