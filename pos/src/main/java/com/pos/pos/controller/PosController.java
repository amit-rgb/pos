package com.pos.pos.controller;

import com.pos.pos.model.PartOfSpeech;
import com.pos.pos.model.ResponseMapper;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PosController {

    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;

    @GetMapping("/")
    public ModelAndView getContext(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
    @PostMapping("/pos")
    public ModelAndView getPos(@RequestParam("input") String input, Model model){
        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        model.addAttribute("posResult",pos(coreDocument, input));
        ModelAndView modelAndView = new ModelAndView("result");
        return modelAndView;
    }

    private List<ResponseMapper> pos(CoreDocument coreDocument, String input) {
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        List<ResponseMapper> listOfPos = new ArrayList<ResponseMapper>();
        for(CoreLabel coreLabel: coreLabelList){
            ResponseMapper responseMapper = new ResponseMapper();
            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            responseMapper.setOriginalText(coreLabel.originalText());
            responseMapper.setPos(PartOfSpeech.get(pos));
            listOfPos.add(responseMapper);
        }
        return listOfPos;
    }

    @GetMapping("/about")
    public ModelAndView getAboutWebsite(){
        ModelAndView about = new ModelAndView("aboutWebsite");
        return about;
    }
}
