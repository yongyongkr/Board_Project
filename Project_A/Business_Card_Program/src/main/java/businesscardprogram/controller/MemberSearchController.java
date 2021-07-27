package businesscardprogram.controller;

import businesscardprogram.domain.Member;
import businesscardprogram.service.MemberService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberSearchController {

    private final MemberService memberService;

    @Autowired
    public MemberSearchController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/find")
    public String search(Model model) {
        model.addAttribute("name", new MemberForm());
        return "members/search";
    }

    @PostMapping("/members/find")
    public String searchList(MemberForm form, Model model) {
        String name = form.getName();
        Member member = memberService.searchMembers(name).get();
        model.addAttribute("member", member);

        return "members/memberList";
    }
}
