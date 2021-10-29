package businesscardprogram.controller;

import businesscardprogram.domain.Member;
import businesscardprogram.dto.MemberForm;
import businesscardprogram.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberSearchController {

    private final MemberService memberService;

    @GetMapping("/members/find")
    public String search(Model model) {
        model.addAttribute("name", new MemberForm());
        return "members/search";
    }

    @PostMapping("/members/find")
    public String searchList(MemberForm form, Model model) {
        String name = form.getName();
        List<Member> memberList = memberService.findMembersByName(name);
        model.addAttribute("memberList", memberList);

        return "members/memberList";
    }
}
