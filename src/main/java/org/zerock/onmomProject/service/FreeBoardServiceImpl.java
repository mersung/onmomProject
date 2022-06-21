package org.zerock.onmomProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.dto.FreeBoardDTO;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.repository.FreeBoardRepository;

@Service
@RequiredArgsConstructor
@Log4j2

public class FreeBoardServiceImpl implements FreeBoardService{
    private final FreeBoardRepository repository;
    @Override
    public Long register(FreeBoardDTO dto) {

        FreeBoard board = dtoToEntity(dto);

        repository.save(board);

        return board.getFree_id();

    }
}
