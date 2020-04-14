package cn.edu.zju.controller;

import cn.edu.zju.entity.DosingGuideline;
import cn.edu.zju.entity.Drug;
import cn.edu.zju.entity.DrugLabel;
import cn.edu.zju.repository.DosingGuidelineRepository;
import cn.edu.zju.repository.DrugLabelRepository;
import cn.edu.zju.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BiomedController {

    @Autowired
    private DrugLabelRepository drugLabelRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private DosingGuidelineRepository dosingGuidelineRepository;

    @RequestMapping({"/", "index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping("/drugs")
    public String drug(Model model) {

        List<Drug> all = drugRepository.findAll();
        model.addAttribute("drugs", all);
        return "drugs";
    }

    @RequestMapping("/drugLabels")
    public String drugLabels(Model model) {

        List<DrugLabel> all = drugLabelRepository.findAll();
        model.addAttribute("drugLabels", all);
        return "drug_labels";
    }

    @RequestMapping("/dosingGuideline")
    public String dosingGuideline(Model model) {

        List<DosingGuideline> all = dosingGuidelineRepository.findAll();
        model.addAttribute("dosingGuidelines", all);
        return "dosing_guideline";
    }

}
