package com.carl.study.function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changez
 * @desc
 * @datetime 2019/10/14 17:51
 */
@RequestMapping("/properties/value")
@RestController
@Slf4j
public class ValuePropertiesController {

    @Value("#{'${data.time.limit}'}")
    public String xtimeLimit;
    @Value("#{'${data.time.limit}' ?: 20*60*60}")
    public String xxtimeLimit;

    public Integer getxxtimeLimit(){
        ExpressionParser parser =new SpelExpressionParser();
        return parser.parseExpression(xxtimeLimit).getValue(Integer.class);
    }

    @Value("${data.time.limit}")
    private String timeLimit;

    @Value("${data.time.limit.default:100}")
    private String timeLimitDefault;


    @RequestMapping("/propertiesValue")
    public Object propertiesValue(){

        System.out.println("xtimeLimit="+xtimeLimit);
        System.out.println("xxtimeLimit="+xxtimeLimit);
        System.out.println("getxxtimeLimit="+getxxtimeLimit());
        System.out.println("=======================================");
        System.out.println("timeLimit="+timeLimit);
        System.out.println("timeLimitDefault="+timeLimitDefault);
        return null;
    }

}
