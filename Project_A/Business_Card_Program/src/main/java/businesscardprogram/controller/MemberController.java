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
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = Member.createMember(form.getName(), form.getNumber(), form.getCompany());
        memberService.create(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> memberList = memberService.findAllMembers();
        model.addAttribute("memberList", memberList);
        return "members/memberList";
    }
}
