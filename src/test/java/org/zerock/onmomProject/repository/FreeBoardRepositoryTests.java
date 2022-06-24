package org.zerock.onmomProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.Member;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class FreeBoardRepositoryTests {

    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Test
    public void insertBoard() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .member_id("u"+i)
                    .nickname("user"+i)
                    .pw("1111")
                    .build();

            FreeBoard freeBoard = FreeBoard.builder()
                    .content("content....." + i )
                    .hate_cnt((long)(Math.random()*100)) // 1부터 100사이 난수 발생
                    .like_cnt((long)(Math.random()*100)) // 1부터 100사이 난수 발생
                    .title("Title..." + i)
                    .member(member)
                    .build();

            freeBoardRepository.save(freeBoard);

        });
    }

    @Transactional
    @Test
    public void testRead1() {

        Optional<FreeBoard> result = freeBoardRepository.findById(100L); //데이터베이스에 존재하는 번호



        FreeBoard freeBoard = freeBoardRepository.getOne(100L);

        System.out.println(freeBoard);
        System.out.println(freeBoard.getMember());

    }


    @Test
    public void testReadWithWriter() {

        Object result = freeBoardRepository.getFreeBoardByFree_id(100L);

        Object[] arr = (Object[])result;

        System.out.println("-------------------------------");
        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void testGetBoardWithReply() {

        List<Object[]> result = freeBoardRepository.getFreeBoardWithFreeBoardComment(100L);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }



    @Test
    public void testWithReplyCount(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("free_id").descending());

        Page<Object[]> result = freeBoardRepository.getFreeBoardWithFreeBoardCommentCount(pageable);

        result.get().forEach(row -> {

            Object[] arr = (Object[])row;

            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3() {

        Object result = freeBoardRepository.getFreeBoardByFree_id(100L);

        Object[] arr = (Object[])result;

        System.out.println(Arrays.toString(arr));
    }

}
