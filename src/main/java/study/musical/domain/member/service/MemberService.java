package study.musical.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.musical.domain.likes.entity.Likes;
import study.musical.domain.likes.repository.LikeRepository;
import study.musical.domain.member.entity.Member;
import study.musical.domain.member.repository.MemberRepository;
import study.musical.domain.musical.dto.response.MusicalInfoDto;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.repository.MusicalRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final MusicalRepository musicalRepository;

    @Transactional(readOnly = true)
    public List<MusicalInfoDto> getAllMusicalsLiked(Long id) {
        log.info("Member service getAllMusicalsLiked run..");
        Member member = memberRepository.findById(id).orElseThrow();
        List<Musical> musicalList = musicalRepository.findAllMusicalsLiked(getLikes(member));
        return musicalList.stream().map(MusicalInfoDto::from).collect(Collectors.toList());
    }

    private Set<Likes> getLikes(Member member) {
        return likeRepository.findAllByMember(member).orElseThrow();
    }

}
