package org.zerock.onmomProject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.onmomProject.dto.FreeBoardDTO;
import org.zerock.onmomProject.dto.FreePageRequestDTO;
import org.zerock.onmomProject.dto.FreePageResultDTO;

@SpringBootTest
public class FreeBoardServiceTests {

        @Autowired
        private FreeBoardService freeBoardService;

        @Test
        public void testRegister() {


            FreeBoardDTO dto = FreeBoardDTO.builder()
                    .content("Test...")
                    .like_cnt((long)(Math.random()*100))
                    .hate_cnt((long)(Math.random()*100))
                    .title("Test.")
                    .member_id("u55")  //현재 데이터베이스에 존재하는 회원 이메일
                    .build();

            Long free_id = freeBoardService.register(dto);

        }
    @Test
    public void testList() {

        //1페이지 10개
        FreePageRequestDTO freePageRequestDTO = new FreePageRequestDTO();

        FreePageResultDTO<FreeBoardDTO, Object[]> result = freeBoardService.getList(freePageRequestDTO);

        for (FreeBoardDTO boardDTO : result.getDtoList()) {
            System.out.println(boardDTO);
        }

    }

    @Test
    public void testGet() {

        Long free_id = 100L;

        FreeBoardDTO freeBoardDTO = freeBoardService.get(free_id);

        System.out.println(freeBoardDTO);
    }

    @Test
    public void testRemove() {

        Long free_id = 29L;

        freeBoardService.removeWithReplies(free_id);

    }
    @Test
    public void testModify() {

        FreeBoardDTO boardDTO = FreeBoardDTO.builder()
                .free_id(2L)
                .title("제목 변경합니다.2")
                .content("내용 변경합니다.2")
                .build();

        freeBoardService.modify(boardDTO);

    }
}
