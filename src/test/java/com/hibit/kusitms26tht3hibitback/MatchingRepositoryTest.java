package com.hibit.kusitms26tht3hibitback;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.repository.MatchingRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchingRepositoryTest {

    @Autowired
    MatchingRepository matchingRepository;

    @After
    public void cleanup() {
        matchingRepository.deleteAll();
    }

    @Test
    public void insertBaseTimeEntity() {
        //given
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime start = LocalDateTime.of(2022,11,14,0,0,0);
        LocalDateTime finish = LocalDateTime.of(2022,11,22,0,0,0);
        LocalDateTime end_d = LocalDateTime.of(2022,11,20,0,0,0);

        Matching matching = matchingRepository.save(Matching.builder()
                        .title("제목")
                        .exhibition("전시회 이름")
                        .content("내용")
                        .number(1)
                        .start_date(start)
                        .finish_date(finish)
                        .end(true)
                        .view(2)
                        .openchat("httpsss")
                        .want("이런 사람 ")
                .build());

        matchingRepository.save(matching);

        //when
        List<Matching> matchingList = matchingRepository.findAll();

        //then
        Matching match = matchingList.get(0);

        System.out.println(">>>>>>>>> createDate=" + matching.getCreatedDate() + ", modifiedDate = " + matching.getModifiedDate() + "<<<<<<<<<<");

        assertThat(matching.getCreatedDate()).isAfter(now);
        assertThat(matching.getModifiedDate()).isAfter(now);
    }
}
