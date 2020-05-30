package com.pos.pos.model;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class Pipeline {
    private static StanfordCoreNLP standfordCoreNLP;
    private static Properties properties;
    private static String propertiesName="tokenize,ssplit,pos,lemma,ner,parse,sentiment";
    private Pipeline(){}
    static {
        properties = new Properties();
        properties.setProperty("annotators",propertiesName);
    }
    @Bean(name = "stanfordCoreNLP")
    public static StanfordCoreNLP getPipeline(){
        if(standfordCoreNLP==null){
            standfordCoreNLP = new StanfordCoreNLP(properties);
        }
        return standfordCoreNLP;
    }
}
